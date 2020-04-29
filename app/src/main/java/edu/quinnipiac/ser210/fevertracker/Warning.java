package edu.quinnipiac.ser210.fevertracker;
/*
Authors: Tim Carta, Ryan Hayes, Neel Bains
Class Name: Warning
Description: contains a layout for the warning of a high temperature.
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Warning extends Fragment {


    public Warning() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warning, container, false);
    }
}
