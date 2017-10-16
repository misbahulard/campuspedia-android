package com.campuspedia.campuspedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.adapter.EventAdapter;
import com.campuspedia.campuspedia.adapter.MainCategoryAdapter;
import com.campuspedia.campuspedia.model.EventMainCategory;
import com.campuspedia.campuspedia.model.EventMainCategoryMeta;
import com.campuspedia.campuspedia.model.EventMeta;
import com.campuspedia.campuspedia.model.MainCategoryListResponse;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by misbahulard on 10/16/2017.
 */

public class MainCategoryFragment extends Fragment {
    private ArrayList<EventMainCategory> mMainCategories;
    private EventMainCategoryMeta mMainCategoryMeta;
    private MainCategoryAdapter mMainCategoryAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public MainCategoryFragment() {

    }

    public static MainCategoryFragment newInstance() {
        return new MainCategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_category, container, false);

        mMainCategories = new ArrayList<>();
        mMainCategoryMeta = new EventMainCategoryMeta();

        getMainCategoryDataset();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_main_category);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMainCategoryAdapter = new MainCategoryAdapter(getActivity(), mMainCategories, mMainCategoryMeta);
        mRecyclerView.setAdapter(mMainCategoryAdapter);

        return rootView;
    }

    private void getMainCategoryDataset() {
        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.mainCategoryRequest().enqueue(new Callback<MainCategoryListResponse>() {
            @Override
            public void onResponse(Call<MainCategoryListResponse> call, Response<MainCategoryListResponse> response) {
                mMainCategories.clear();
                mMainCategories.addAll(response.body().getMainCategories());

                EventMainCategoryMeta meta = response.body().getMeta();
                setMeta(meta);
                mMainCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainCategoryListResponse> call, Throwable t) {

            }
        });
    }

    private void setMeta(EventMainCategoryMeta meta) {
        this.mMainCategoryMeta.setCurrentPage(meta.getCurrentPage());
        this.mMainCategoryMeta.setFrom(meta.getFrom());
        this.mMainCategoryMeta.setLastPage(meta.getLastPage());
        this.mMainCategoryMeta.setPath(meta.getPath());
        this.mMainCategoryMeta.setPerPage(meta.getPerPage());
        this.mMainCategoryMeta.setTo(meta.getTo());
        this.mMainCategoryMeta.setTotal(meta.getTotal());
    }
}
