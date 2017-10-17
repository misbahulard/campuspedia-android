package com.campuspedia.campuspedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.Campus;
import com.campuspedia.campuspedia.model.CampusMeta;
import com.campuspedia.campuspedia.util.GlideApp;

import java.util.ArrayList;

/**
 * Adapter ini berfungsi untuk adapter Campus
 * @author misbahulard
 * @version 1.0
 * @since 17 Oktober 2017
 */
public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.CampusAdapterViewHolder> {

    private Context mContext;
    private ArrayList<Campus> mCampuses;
    private CampusMeta mCampusMeta;

    public CampusAdapter(Context mContext, ArrayList<Campus> mCampuses, CampusMeta mCampusMeta) {
        this.mContext = mContext;
        this.mCampuses = mCampuses;
        this.mCampusMeta = mCampusMeta;
    }

    @Override
    public CampusAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campus, parent, false);
        return new CampusAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampusAdapterViewHolder holder, int position) {
        Campus campus = mCampuses.get(position);
        String name = campus.getName();
        String location = campus.getLocation().getCity();
        String logo = mCampusMeta.getCampusImgPath() + campus.getLogo();
        holder.setData(logo, name, location);
    }

    @Override
    public int getItemCount() {
        return mCampuses.size();
    }

    /**
     * Class untuk custom View Holder
     */
    class CampusAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvLogo;
        private TextView mTvCampusName, mTvCampusLocation;

        /**
         * Custom View Holder
         *
         * @param itemView
         */
        public CampusAdapterViewHolder(View itemView) {
            super(itemView);
            mIvLogo = (ImageView) itemView.findViewById(R.id.image_campus_logo);
            mTvCampusName = (TextView) itemView.findViewById(R.id.text_campus_name);
            mTvCampusLocation = (TextView) itemView.findViewById(R.id.text_campus_location);
        }

        /**
         * Set data untuk view
         * @param logo
         * @param name
         * @param location
         */
        public void setData(String logo, String name, String location) {
            GlideApp.with(mContext).load(logo).centerCrop().into(mIvLogo);
            mTvCampusName.setText(name);
            mTvCampusLocation.setText(location);
        }
    }
}
