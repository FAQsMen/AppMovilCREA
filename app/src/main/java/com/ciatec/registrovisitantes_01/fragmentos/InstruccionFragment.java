package com.ciatec.registrovisitantes_01.fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ciatec.registrovisitantes_01.R;
import com.ciatec.registrovisitantes_01.actividades.AccesoActivity;
import com.ciatec.registrovisitantes_01.actividades.SeleccionRegistroActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstruccionFragment extends Fragment {

    Button btnEntendido;

    public InstruccionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instruccion, container, false);

        btnEntendido = view.findViewById(R.id.btn_entendido);

        btnEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeleccionRegistroActivity.class));
            }
        });


        return view;
    }

}
