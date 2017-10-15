package com.campuspedia.campuspedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.Event;
import com.campuspedia.campuspedia.model.EventMeta;
import com.campuspedia.campuspedia.model.EventResponse;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by misbahulard on 10/14/2017.
 */

public class EventDetailFragment extends Fragment {
    public static final String EVENT_ID = "BUNDLE_EVENT_ID";
    private Event mEvent;
    private EventMeta mEventMeta;
    private BaseApiService mBaseApiService;

    private TextView mTvName, mTvCategory, mTvDescription, mTvDate, mTvStreet, mTvCity, mTvProvince;

    public EventDetailFragment() {
    }

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);

        mTvName = (TextView) rootView.findViewById(R.id.text_eventdetail_name);
        mTvCategory = (TextView) rootView.findViewById(R.id.text_eventdetail_category);
        mTvDescription = (TextView) rootView.findViewById(R.id.text_eventdetail_description);
        mTvDate = (TextView) rootView.findViewById(R.id.text_eventdetail_date);
        mTvStreet = (TextView) rootView.findViewById(R.id.text_eventdetail_street);
        mTvCity = (TextView) rootView.findViewById(R.id.text_eventdetail_city);
        mTvProvince = (TextView) rootView.findViewById(R.id.text_eventdetail_province);

        getEventDetailData();

        return rootView;
    }

    private void getEventDetailData() {
        Bundle bundle = getArguments();
        int eventId = bundle.getInt(EVENT_ID);

        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.eventByIdRequest(eventId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {

                    mEvent = new Event();
                    mEvent = response.body().getEvent();

                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy"); // Set your date format
                    String date = sdf.format(mEvent.getEventDate()); // Get Date String according to date format

                    mTvName.setText(mEvent.getName());
                    mTvCategory.setText(mEvent.getCategory());
                    mTvDescription.setText(mEvent.getDescription());
                    mTvDate.setText(date);
                    mTvStreet.setText(mEvent.getLocation().getStreetAddress());
                    mTvCity.setText(mEvent.getLocation().getCity() + ",");
                    mTvProvince.setText(mEvent.getLocation().getStateProvince());

                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });
    }

}
