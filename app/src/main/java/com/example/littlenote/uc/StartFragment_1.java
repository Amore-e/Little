package com.example.littlenote.uc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littlenote.R;

public class StartFragment_1 extends androidx.fragment.app.Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.start1,container,false);
        initui();

        return rootView;
    }
    private void initui(){

    }
}
