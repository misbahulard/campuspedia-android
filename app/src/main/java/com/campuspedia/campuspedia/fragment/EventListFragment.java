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
import com.campuspedia.campuspedia.adapter.EventAdapter;
import com.campuspedia.campuspedia.model.Event;
import com.campuspedia.campuspedia.model.EventListResponse;
import com.campuspedia.campuspedia.model.EventMeta;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by misbahulard on 10/16/2017.
 */

public class EventListFragment extends Fragment {
    public static final String CATEGORY_ID = "BUNDLE_CATEGORY_ID";

    private ArrayList<Event> mEvents;
    private EventMeta mEventMeta;
    private EventAdapter mEventAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventListFragment.OnEventSelectedListener mListener;

    public EventListFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (EventListFragment.OnEventSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnEventSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Inisialisasi Model dan load data set Event pertama kali
         */
        mEvents = new ArrayList<>();
        mEventMeta = new EventMeta();

        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);

        /**
         * Inisialisasi RecyclerView berserta adapter
         */
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_event);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mEventAdapter = new EventAdapter(getActivity(), mEvents, mEventMeta);
        mRecyclerView.setAdapter(mEventAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = mEvents.get(position);
                mListener.onEventSelected(event.getEventId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    /**
     * Method untuk menginisialisasi data Event
     */
    public void initDataset() {
        Bundle bundle = getArguments();
        final int categoryId = bundle.getInt(CATEGORY_ID);

        mBaseApiService = ApiUtils.getApiService();

        /**
         * Request data Event terbaru
         */
        mBaseApiService.eventByCategoryRequest(categoryId).enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
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
            public void onFailure(Call<EventListResponse> call, Throwable t) {
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

    /**
     * Event listener interface
     */
    public interface OnEventSelectedListener {
        public void onEventSelected(int id);
    }
}
