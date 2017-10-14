package com.campuspedia.campuspedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campuspedia.campuspedia.R;

/**
 * Created by misbahulard on 10/14/2017.
 */

public class SuggestFragment extends Fragment {
    public SuggestFragment() {
    }

    public static SuggestFragment newInstance() {
        return new SuggestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_suggest, container, false);
        return rootView;
    }
}
