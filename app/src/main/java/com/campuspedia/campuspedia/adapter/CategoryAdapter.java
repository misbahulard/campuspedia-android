package com.campuspedia.campuspedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.EventCategory;
import com.campuspedia.campuspedia.model.MetaWithoutImg;

import java.util.ArrayList;

/**
 * Created by misbahulard on 10/16/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private ArrayList<EventCategory> mCategories;
    private MetaWithoutImg mMeta;

    public CategoryAdapter(Context mContext, ArrayList<EventCategory> mCategories, MetaWithoutImg mMeta) {
        this.mContext = mContext;
        this.mCategories = mCategories;
        this.mMeta = mMeta;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        EventCategory category = mCategories.get(position);
        String name = category.getName();
        holder.mtvCategoryName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvCategoryIcon;
        private TextView mtvCategoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.image_campus_logo);
            mtvCategoryName = (TextView) itemView.findViewById(R.id.text_main_category_name);
        }
    }
}
