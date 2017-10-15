package com.campuspedia.campuspedia.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by misbahulard on 10/14/2017.
 */

public class EventDetailAdapter extends RecyclerView.Adapter<EventDetailAdapter.EventDetailViewHolder> {
    private Context mContext;
    private ArrayList<Event> mEvents;
    private EventMeta mEventMeta;

    public EventDetailAdapter(Context context, ArrayList<Event> events) {
        this.mContext = context;
        this.mEvents = events;
    }

    public EventDetailAdapter(Context context, ArrayList<Event> events, EventMeta eventMeta) {
        this.mContext = context;
        this.mEvents = events;
        this.mEventMeta = eventMeta;
    }

    @Override
    public EventDetailAdapter.EventDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventDetailAdapter.EventDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventDetailAdapter.EventDetailViewHolder holder, int position) {
        Event event = mEvents.get(position);
        String name = event.getName();
        String category = event.getCategory();
        String location = event.getLocation().getCity();
        String photo = mEventMeta.getEventImgPath() + event.getPhoto();
        holder.setData(photo, name, category, location);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    /**
     * Class untuk custom View Holder
     */
    class EventDetailViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private ImageView mIvEvent;
        private TextView mTvEventName, mTvEventCategory, mTvEventLocation;

        /**
         * Custom View Holder
         *
         * @param itemView
         */
        public EventDetailViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_event);
            mIvEvent = (ImageView) itemView.findViewById(R.id.image_event);
            mTvEventName = (TextView) itemView.findViewById(R.id.text_event_name);
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
