package com.campuspedia.campuspedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuspedia.campuspedia.R;
import com.campuspedia.campuspedia.model.Campus;
import com.campuspedia.campuspedia.response.CampusResponse;
import com.campuspedia.campuspedia.util.GlideApp;
import com.campuspedia.campuspedia.util.api.ApiUtils;
import com.campuspedia.campuspedia.util.api.BaseApiService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment ini berfungsi untuk menampilkan detail dari suatu campus
 *
 * @author misbahulard
 * @version 1.0
 * @since 17 Oktober 2017
 */
public class CampusDetailFragment extends Fragment {
    public static final String CAMPUS_ID = "BUNDLE_CAMPUS_ID";

    private Campus mCampus;
    private BaseApiService mBaseApiService;

    private ImageView mIvCampus;
    private TextView mTvName, mTvWeb, mTvStreet, mTvCity, mTvProvince;

    private MapView mMapView;

    public CampusDetailFragment() {
    }

    public static CampusDetailFragment newInstance() {
        return new CampusDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campus_detail, container, false);

        /**
         * Inisialisasi View
         */
        mIvCampus = (ImageView) rootView.findViewById(R.id.image_campusdetail_logo);
        mTvName = (TextView) rootView.findViewById(R.id.text_campusdetail_name);
        mTvWeb = (TextView) rootView.findViewById(R.id.text_campusdetail_web);
        mTvStreet = (TextView) rootView.findViewById(R.id.text_campusdetail_street);
        mTvCity = (TextView) rootView.findViewById(R.id.text_campusdetail_city);
        mTvProvince = (TextView) rootView.findViewById(R.id.text_campusdetail_province);


        /**
         * Inisialisasi Map View untuk menampilkan peta Campus
         */
        mMapView = (MapView) rootView.findViewById(R.id.map_campusdetail);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        getCampusDetailData();

        return rootView;
    }

    /**
     * Method yang berfungsi untuk mendapatkan data tentang detail campus dari API
     */
    private void getCampusDetailData() {
        /**
         * Mengambil Bundle yang dikirim oleh activity / fragment lain
         * Bundle ini berisi data ID dari suatu kampus
         */
        Bundle bundle = getArguments();
        int campusId = bundle.getInt(CAMPUS_ID);

        mBaseApiService = ApiUtils.getApiService();

        mBaseApiService.campusById(campusId).enqueue(new Callback<CampusResponse>() {
            @Override
            public void onResponse(Call<CampusResponse> call, Response<CampusResponse> response) {
                if (response.isSuccessful()) {
                    /**
                     * Simpan data dari API ke model
                     */
                    mCampus = new Campus();
                    mCampus = response.body().getCampus();

                    /**
                     * Menggabungkan uri gambar dan path gambar untuk ditampilkan ke view
                     * Lihat {@link ApiUtils.CAMPUS_IMG_PATH}
                     */
                    final String imageUri = ApiUtils.CAMPUS_IMG_PATH + mCampus.getLogo();

                    /**
                     * Tampilkan data ke view
                     */
                    GlideApp.with(getContext()).load(imageUri).centerCrop().into(mIvCampus);

                    mTvName.setText(mCampus.getName());
                    mTvWeb.setText(mCampus.getWeb());
                    mTvStreet.setText(mCampus.getLocation().getStreetAddress());
                    mTvCity.setText(mCampus.getLocation().getCity() + ",");
                    mTvProvince.setText(mCampus.getLocation().getStateProvince());

                    /**
                     * Listener untuk mendapatkan PETA dari Campus
                     */
                    mMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng eventLoc = new LatLng(mCampus.getLocation().getLatitute(),
                                    mCampus.getLocation().getLongtitude());

                            googleMap.getUiSettings().setZoomControlsEnabled(true);
                            googleMap.getUiSettings().setZoomGesturesEnabled(true);
                            googleMap.addMarker(new MarkerOptions().position(eventLoc).title(mCampus.getName() + " venue"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLoc, 15.0f));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CampusResponse> call, Throwable t) {
                Log.e("debug", "on Failure: ERROR > " + t.toString());
                Toast.makeText(getActivity(), "Please check your network connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
