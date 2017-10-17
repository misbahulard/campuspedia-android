package com.campuspedia.campuspedia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.Event;
import com.campuspedia.campuspedia.model.EventMeta;
import com.campuspedia.campuspedia.util.GlideApp;

import java.util.ArrayList;

/**
 * Adapter yang berfungsi untuk Event
 *
 * @author misbahulard
 * @version 1.0
 * @since 14 October 2017
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mContext;
    private ArrayList<Event> mEvents;
    private EventMeta mEventMeta;

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.mContext = context;
        this.mEvents = events;
    }

    public EventAdapter(Context context, ArrayList<Event> events, EventMeta eventMeta) {
        this.mContext = context;
        this.mEvents = events;
        this.mEventMeta = eventMeta;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = mEvents.get(position);
        String name = event.getName();
        String category = event.getCategory();
        String location = event.getLocation().getCity();
        String photo = mEventMeta.getEventImgPath() + event.getPhoto();
        holder.setData(photo, name, category, location);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    /**
     * Class untuk custom View Holder
     */
    class EventViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvEvent;
        private TextView mTvEventName, mTvEventCategory, mTvEventLocation;

        /**
         * Custom View Holder
         *
         * @param itemView
         */
        public EventViewHolder(View itemView) {
            super(itemView);
            mIvEvent = (ImageView) itemView.findViewById(R.id.image_campus_logo);
            mTvEventName = (TextView) itemView.findViewById(R.id.text_main_category_name);
            mTvEventCategory = (TextView) itemView.findViewById(R.id.text_event_category);
            mTvEventLocation = (TextView) itemView.findViewById(R.id.text_event_location);
        }

        /**
         * Set Data untuk view
         *
         * @param photo
         * @param name
         * @param category
         * @param location
         */
        public void setData(String photo, String name, String category, String location) {
            GlideApp.with(mContext).load(photo).centerCrop().into(mIvEvent);
            mTvEventName.setText(name);
            mTvEventCategory.setText(category);
            mTvEventLocation.setText(location);
        }
    }
}
