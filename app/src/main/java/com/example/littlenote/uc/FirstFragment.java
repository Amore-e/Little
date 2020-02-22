package com.example.littlenote.uc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littlenote.R;

import androidx.annotation.Nullable;

public class FirstFragment extends androidx.fragment.app.Fragment {

   private View rootView;

   @Nullable
   @Override
   public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       rootView=inflater.inflate(R.layout.first_fragment,container,false);
      initui();

       return rootView;
   }
   private void initui(){

   }
}
