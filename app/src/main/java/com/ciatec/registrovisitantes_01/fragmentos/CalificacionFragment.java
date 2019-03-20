package com.ciatec.registrovisitantes_01.fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ciatec.registrovisitantes_01.R;
import com.ciatec.registrovisitantes_01.actividades.SeleccionRegistroActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalificacionFragment extends Fragment {

    RatingBar rbrCalificacion;
    TextView txvCalificacion;
    EditText etxCalificacion;
    Button btnEnviarCalificacion;

    public CalificacionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calificacion, container, false);

        rbrCalificacion = view.findViewById(R.id.rbr_calificacion);
        txvCalificacion = view.findViewById(R.id.txv_calificacion);
        //etxCalificacion = view.findViewById(R.id.ed)
        btnEnviarCalificacion = view.findViewById(R.id.btn_enviar_calificacion);

                rbrCalificacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        //txvCalificacion.setText(String.valueOf(v));
                        switch ((int) ratingBar.getRating()) {
                            case 1:
                                txvCalificacion.setText("Pésimo");
                                break;
                            case 2:
                                txvCalificacion.setText("Decepcionante");
                                break;
                            case 3:
                                txvCalificacion.setText("Pueden mejorar");
                                break;
                            case 4:
                                txvCalificacion.setText("Bueno");
                                break;
                            case 5:
                                txvCalificacion.setText("Excelente");
                                break;
                            default:
                                txvCalificacion.setText("");
                        }
                    }
                });

                btnEnviarCalificacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), SeleccionRegistroActivity.class));
                    }
                });

        return view;
    }

}
