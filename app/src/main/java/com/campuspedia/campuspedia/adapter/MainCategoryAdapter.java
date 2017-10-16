package com.campuspedia.campuspedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.EventMainCategory;
import com.campuspedia.campuspedia.model.EventMainCategoryMeta;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Adapter yang berfungsi untuk MainCategory
 *
 * @author misbahulard
 * @version 1.0
 * @since 16 Oktober 2017
 */
public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder> {

    private Context mContext;
    private ArrayList<EventMainCategory> mMainCategories;
    private EventMainCategoryMeta mMainCategoryMeta;

    public MainCategoryAdapter(Context context, ArrayList<EventMainCategory> mainCategories, EventMainCategoryMeta mainCategoryMeta) {
        this.mContext = context;
        this.mMainCategories = mainCategories;
        this.mMainCategoryMeta = mainCategoryMeta;
    }

    @Override
    public MainCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new MainCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainCategoryViewHolder holder, int position) {
        EventMainCategory eventMainCategory = mMainCategories.get(position);
        String name = eventMainCategory.getName();
        holder.mtvMainCategoryName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mMainCategories.size();
    }

    /**
     * Class untuk custom view Adapter
     */
    class MainCategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvCategoryIcon;
        private TextView mtvMainCategoryName;

        public MainCategoryViewHolder(View itemView) {
            super(itemView);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.image_category_icon);
            mtvMainCategoryName = (TextView) itemView.findViewById(R.id.text_main_category_name);
        }
    }
}
