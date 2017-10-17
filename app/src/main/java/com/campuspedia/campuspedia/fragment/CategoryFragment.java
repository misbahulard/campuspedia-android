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

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.adapter.CategoryAdapter;
import com.campuspedia.campuspedia.model.EventCategory;
import com.campuspedia.campuspedia.model.EventCategoryListResponse;
import com.campuspedia.campuspedia.model.MetaWithoutImg;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by misbahulard on 10/14/2017.
 */

public class CategoryFragment extends Fragment {
    public static final String MAIN_CATEGORY_ID = "BUNDLE_MAIN_CATEGORY_ID";

    private ArrayList<EventCategory> mCategories;
    private MetaWithoutImg mMeta;
    private CategoryAdapter mCategoryAdapter;
    private BaseApiService mBaseApiService;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnCategorySelectedListener mListener;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (CategoryFragment.OnCategorySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCategorySelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        mCategories = new ArrayList<>();
        mMeta = new MetaWithoutImg();

        getCategoryDataset();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_category);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCategoryAdapter = new CategoryAdapter(getActivity(), mCategories, mMeta);
        mRecyclerView.setAdapter(mCategoryAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                EventCategory category = mCategories.get(position);
                mListener.onCategorySelected(category.getId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    private void getCategoryDataset() {
        Bundle bundle = getArguments();
        final int mainCategoryId = bundle.getInt(MAIN_CATEGORY_ID);

        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.categoryRequest(mainCategoryId).enqueue(new Callback<EventCategoryListResponse>() {
            @Override
            public void onResponse(Call<EventCategoryListResponse> call, Response<EventCategoryListResponse> response) {
                mCategories.clear();
                mCategories.addAll(response.body().getEventCategories());

                Log.d("DEBUG > ", "ID : " + mainCategoryId);

                MetaWithoutImg meta = response.body().getMeta();
                setMeta(meta);
                mCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EventCategoryListResponse> call, Throwable t) {

            }
        });
    }

    private void setMeta(MetaWithoutImg meta) {
        this.mMeta.setCurrentPage(meta.getCurrentPage());
        this.mMeta.setFrom(meta.getFrom());
        this.mMeta.setLastPage(meta.getLastPage());
        this.mMeta.setPath(meta.getPath());
        this.mMeta.setPerPage(meta.getPerPage());
        this.mMeta.setTo(meta.getTo());
        this.mMeta.setTotal(meta.getTotal());
    }

    public interface OnCategorySelectedListener {
        public void onCategorySelected(int id);
    }
}
