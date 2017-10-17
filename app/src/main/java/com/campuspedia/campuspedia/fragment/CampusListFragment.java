package com.campuspedia.campuspedia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.adapter.CampusAdapter;
import com.campuspedia.campuspedia.model.Campus;
import com.campuspedia.campuspedia.response.CampusListResponse;
import com.campuspedia.campuspedia.model.CampusMeta;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment ini berfungsi untuk menampilkan list campus
 *
 * @author misbahulard
 * @version 1.0
 * @since 17 Oktober 2017
 */
public class CampusListFragment extends Fragment {
    private ArrayList<Campus> mCampuses;
    private CampusMeta mCampusMeta;
    private CampusAdapter mCampusAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CampusListFragment.OnCampusSelectedListener mListener;

    public CampusListFragment() {

    }
    public static CampusListFragment newInstance() {
        return new CampusListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (CampusListFragment.OnCampusSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCampusSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Inisialisasi Model dan load data set Campus pertama kali
         */
        mCampuses = new ArrayList<>();
        mCampusMeta = new CampusMeta();

        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campus_list, container, false);

        /**
         * Inisialisasi RecyclerView berserta adapter
         */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_campus);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCampusAdapter = new CampusAdapter(getActivity(), mCampuses, mCampusMeta);
        mRecyclerView.setAdapter(mCampusAdapter);

        /**
         * Menambahkan listener jika campus di klik
         */
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Campus campus = mCampuses.get(position);
                mListener.onCampusSelected(campus.getCampusid());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    /**
     * Method untuk menginisialisasi data Campus
     */
    public void initDataset() {

        mBaseApiService = ApiUtils.getApiService();

        /**
         * Request data Campus terbaru
         */
        mBaseApiService.campusRequest().enqueue(new Callback<CampusListResponse>() {
            @Override
            public void onResponse(Call<CampusListResponse> call, Response<CampusListResponse> response) {
                if (response.isSuccessful()) {
                    /**
                     * Hapus semua data terlebih dahulu
                     * Simpan semua data Campus terbaru dan Meta-nya
                     * Lalu beritahu ada perubahan data ke Adapter
                     */
                    mCampuses.clear();
                    mCampuses.addAll(response.body().getCampuses());

                    CampusMeta meta = response.body().getMeta();
                    setMeta(meta);

                    mCampusAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CampusListResponse> call, Throwable t) {
                Log.e("debug", "on Failure: ERROR > " + t.toString());
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Method untuk set Meta
     *
     * @param meta
     */
    private void setMeta(CampusMeta meta) {
        this.mCampusMeta.setCampusImgPath(meta.getCampusImgPath());
        this.mCampusMeta.setCurrentPage(meta.getCurrentPage());
        this.mCampusMeta.setFrom(meta.getFrom());
        this.mCampusMeta.setLastPage(meta.getLastPage());
        this.mCampusMeta.setPath(meta.getPath());
        this.mCampusMeta.setPerPage(meta.getPerPage());
        this.mCampusMeta.setTo(meta.getTo());
        this.mCampusMeta.setTotal(meta.getTotal());
    }

    /**
     * Campus listener interface
     */
    public interface OnCampusSelectedListener {
        public void onCampusSelected(int id);
    }
}
