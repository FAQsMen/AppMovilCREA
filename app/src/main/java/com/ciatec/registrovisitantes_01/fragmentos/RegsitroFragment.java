package com.ciatec.registrovisitantes_01.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ciatec.registrovisitantes_01.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegsitroFragment extends Fragment {


    public RegsitroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regsitro, container, false);

        return view;
    }


}
