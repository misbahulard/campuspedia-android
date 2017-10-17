package com.campuspedia.campuspedia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.Event;
import com.campuspedia.campuspedia.response.EventResponse;
import com.campuspedia.campuspedia.util.GlideApp;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment ini berfungsi untuk menampilkan detail dari suatu event
 *
 * @author misbahulard
 * @version 1.0
 * @since 15 Oktober 2017
 */

public class EventDetailFragment extends Fragment {
    public static final String EVENT_ID = "BUNDLE_EVENT_ID";

    private Event mEvent;
    private BaseApiService mBaseApiService;

    private Button mBtnShare;
    private ImageView mIvEvent;
    private TextView mTvName, mTvCategory, mTvDescription, mTvDate, mTvStreet, mTvCity, mTvProvince;
    private FloatingActionButton mFabRemindMe;

    private MapView mMapView;

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

        /**
         * Inisialisasi View
         */
        mBtnShare = (Button) rootView.findViewById(R.id.button_eventdetail_share);
        mIvEvent = (ImageView) rootView.findViewById(R.id.image_eventdetail);
        mTvName = (TextView) rootView.findViewById(R.id.text_eventdetail_name);
        mTvCategory = (TextView) rootView.findViewById(R.id.text_eventdetail_category);
        mTvDescription = (TextView) rootView.findViewById(R.id.text_eventdetail_description);
        mTvDate = (TextView) rootView.findViewById(R.id.text_eventdetail_date);
        mTvStreet = (TextView) rootView.findViewById(R.id.text_eventdetail_street);
        mTvCity = (TextView) rootView.findViewById(R.id.text_eventdetail_city);
        mTvProvince = (TextView) rootView.findViewById(R.id.text_eventdetail_province);

        mFabRemindMe = (FloatingActionButton) rootView.findViewById(R.id.fab_remind_me);

        /**
         * Inisialisasi Map View untuk menampilkan peta Event
         */
        mMapView = (MapView) rootView.findViewById(R.id.map_eventdetail);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        getEventDetailData();

        return rootView;
    }

    /**
     * Method yang berfungsi untuk mendapatkan data tentang detail event dari API
     */
    private void getEventDetailData() {
        /**
         * Mengambil Bundle yang dikirim oleh activity / fragment lain
         * Bundle ini berisi data ID dari suatu event
         */
        Bundle bundle = getArguments();
        int eventId = bundle.getInt(EVENT_ID);

        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.eventByIdRequest(eventId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    /**
                     * Simpan data dari API ke model
                     */
                    mEvent = new Event();
                    mEvent = response.body().getEvent();

                    /**
                     * Melakukan format tanggal ke format yang lebih cantik
                     * Contoh: Sun, 15 Oct 2017
                     */
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                    final String date = sdf.format(mEvent.getEventDate());

                    /**
                     * Menggabungkan uri gambar dan path gambar untuk ditampilkan ke view
                     * Lihat {@link ApiUtils.EVENT_IMG_PATH}
                     */
                    final String imageUri = ApiUtils.EVENT_IMG_PATH + mEvent.getPhoto();

                    /**
                     * Tampilkan data ke view
                     */
                    GlideApp.with(getContext()).load(imageUri).centerCrop().into(mIvEvent);

                    mTvName.setText(mEvent.getName());
                    mTvCategory.setText(mEvent.getCategory());
                    mTvDescription.setText(mEvent.getDescription());
                    mTvDate.setText(date);
                    mTvStreet.setText(mEvent.getLocation().getStreetAddress());
                    mTvCity.setText(mEvent.getLocation().getCity() + ",");
                    mTvProvince.setText(mEvent.getLocation().getStateProvince());

                    /**
                     * Listener untuk mendapatkan PETA dari Event
                     */
                    mMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng eventLoc = new LatLng(mEvent.getLocation().getLatitute(),
                                    mEvent.getLocation().getLongtitude());

                            googleMap.getUiSettings().setZoomControlsEnabled(true);
                            googleMap.getUiSettings().setZoomGesturesEnabled(true);
                            googleMap.addMarker(new MarkerOptions().position(eventLoc).title(mEvent.getName() + " venue"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLoc, 15.0f));
                        }
                    });

                    /**
                     * Listener untuk share event
                     */
                    mBtnShare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String textToShare = "Campuspedia:\n" + "There is "
                                    + mEvent.getName() + " on " + date + ", Let's Join!";

                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TITLE, "Campuspedia");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                            getContext().startActivity(Intent.createChooser(shareIntent, "Share this event using"));
                        }
                    });

                    /**
                     * Listener untuk Floating Action Button Remind me
                     */
                    mFabRemindMe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "Set reminder success!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.e("debug", "on Failure: ERROR > " + t.toString());
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
