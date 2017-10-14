package com.campuspedia.campuspedia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.adapter.EventAdapter;
import com.campuspedia.campuspedia.model.Event;
import com.campuspedia.campuspedia.model.EventMeta;
import com.campuspedia.campuspedia.model.EventResponse;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment yang berfungsi untuk menampilkan layar utama yakni menu HOME
 *
 * @author misbahulard
 * @version 1.0
 * @since 14 October 2017
 */

public class HomeFragment extends Fragment {
    private ArrayList<Event> mEvents;
    private EventMeta mEventMeta;
    private EventAdapter mEventAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public HomeFragment() {
        // Dibutuhkan untuk public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Inisialisasi Model dan load data set Event pertama kali
         */
        mEvents = new ArrayList<>();
        mEventMeta = new EventMeta();

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * Inisialisasi RecyclerView berserta adapter
         */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_home_event);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mEventAdapter = new EventAdapter(getActivity(), mEvents, mEventMeta);
        mRecyclerView.setAdapter(mEventAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Method untuk menginisialisasi data Event
     */
    public void initDataset() {

        mBaseApiService = ApiUtils.getApiService();

        /**
         * Request data Event terbaru
         */
        mBaseApiService.eventRequest().enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                /**
                 * Hapus semua data terlebih dahulu
                 * Simpan semua data Event terbaru dan Meta-nya
                 * Lalu beritahu ada perubahan data ke Adapter
                 */
                mEvents.clear();
                mEvents.addAll(response.body().getEvents());

                EventMeta meta = response.body().getMeta();
                setMeta(meta);

                mEventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.e("debug", "on Failure: ERROR > " + t.toString());
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setMeta(EventMeta meta) {
        this.mEventMeta.setEventImgPath(meta.getEventImgPath());
        this.mEventMeta.setCurrentPage(meta.getCurrentPage());
        this.mEventMeta.setFrom(meta.getFrom());
        this.mEventMeta.setLastPage(meta.getLastPage());
        this.mEventMeta.setPath(meta.getPath());
        this.mEventMeta.setPerPage(meta.getPerPage());
        this.mEventMeta.setTo(meta.getTo());
        this.mEventMeta.setTotal(meta.getTotal());
    }
}

