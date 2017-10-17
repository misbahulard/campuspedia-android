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
import com.campuspedia.campuspedia.adapter.MainCategoryAdapter;
import com.campuspedia.campuspedia.model.EventMainCategory;
import com.campuspedia.campuspedia.model.MetaWithoutImg;
import com.campuspedia.campuspedia.response.MainCategoryListResponse;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment ini berfungsi untuk menampilkan list main category
 *
 * @author misbahulard
 * @version 1.0
 * @since 17 Oktober 2017
 */
public class MainCategoryFragment extends Fragment {

    private ArrayList<EventMainCategory> mMainCategories;
    private MetaWithoutImg mMainCategoryMeta;
    private MainCategoryAdapter mMainCategoryAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnMainCategorySelectedListener mListener;

    public MainCategoryFragment() {

    }

    public static MainCategoryFragment newInstance() {
        return new MainCategoryFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (MainCategoryFragment.OnMainCategorySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnEventSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Inisialisasi Model dan load data set Main Category pertama kali
         */
        mMainCategories = new ArrayList<>();
        mMainCategoryMeta = new MetaWithoutImg();

        getMainCategoryDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_category, container, false);

        /**
         * Inisialisasi RecyclerView berserta adapter
         */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_main_category);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMainCategoryAdapter = new MainCategoryAdapter(getActivity(), mMainCategories, mMainCategoryMeta);
        mRecyclerView.setAdapter(mMainCategoryAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                EventMainCategory mainCategory = mMainCategories.get(position);
                mListener.onMainCategorySelected(mainCategory.getId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    /**
     * Method untuk menginisialisasi data Main Category
     */
    private void getMainCategoryDataset() {
        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.mainCategoryRequest().enqueue(new Callback<MainCategoryListResponse>() {
            @Override
            public void onResponse(Call<MainCategoryListResponse> call, Response<MainCategoryListResponse> response) {
                if (response.isSuccessful()) {
                    /**
                     * Hapus semua data terlebih dahulu
                     * Simpan semua data Main Category terbaru dan Meta-nya
                     * Lalu beritahu ada perubahan data ke Adapter
                     */
                    mMainCategories.clear();
                    mMainCategories.addAll(response.body().getMainCategories());

                    MetaWithoutImg meta = response.body().getMeta();
                    setMeta(meta);
                    mMainCategoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainCategoryListResponse> call, Throwable t) {
                Log.e("debug", "on Failure: ERROR > " + t.toString());
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     *
     * @param meta
     */
    private void setMeta(MetaWithoutImg meta) {
        this.mMainCategoryMeta.setCurrentPage(meta.getCurrentPage());
        this.mMainCategoryMeta.setFrom(meta.getFrom());
        this.mMainCategoryMeta.setLastPage(meta.getLastPage());
        this.mMainCategoryMeta.setPath(meta.getPath());
        this.mMainCategoryMeta.setPerPage(meta.getPerPage());
        this.mMainCategoryMeta.setTo(meta.getTo());
        this.mMainCategoryMeta.setTotal(meta.getTotal());
    }


    public interface OnMainCategorySelectedListener {
        public void onMainCategorySelected(int id);
    }
}
