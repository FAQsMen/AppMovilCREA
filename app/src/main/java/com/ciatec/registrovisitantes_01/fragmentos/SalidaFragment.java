package com.ciatec.registrovisitantes_01.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.ciatec.registrovisitantes_01.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalidaFragment extends Fragment {

    FrameLayout mLayout;
    private TextView txvFechaEntrada;
    private TextClock txcHoraEntrada;
    private Button btnRegistrarSalida;

    public SalidaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_salida, container, false);

        txvFechaEntrada = view.findViewById(R.id.txv_FechaEntrada);
        txcHoraEntrada = view.findViewById(R.id.txc_HoraEntrada);
        btnRegistrarSalida = view.findViewById(R.id.btn_RegistrarSalida);

        EstablecerFecha();

        txcHoraEntrada.setFormat12Hour(null);
        txcHoraEntrada.setFormat24Hour("HH:mm:ss");

        btnRegistrarSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                clearLayout();
                FragmentTransaction transaction = manager.beginTransaction();
                CalificacionFragment calificacionFragment = new CalificacionFragment();
                transaction.add(R.id.frameRegistro, calificacionFragment);
                transaction.commit();
            }
        });

        return view;
    }

    private void EstablecerFecha(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(calendar.getTime());

        String diaSemana = null;
        String mes = null;
        int diaSemana_int =  calendar.get(Calendar.DAY_OF_WEEK);
        int diaMes = calendar.get(Calendar.DAY_OF_MONTH);
        int mes_int = calendar.get(Calendar.MONTH);
        int anio = calendar.get(Calendar.YEAR);

        switch (diaSemana_int)
        {
            case 1: diaSemana = "Domingo";
                break;
            case 2: diaSemana = "Lunes";
                break;
            case 3: diaSemana = "Martes";
                break;
            case 4: diaSemana = "Miércoles";
                break;
            case 5: diaSemana = "Jueves";
                break;
            case 6: diaSemana = "Viernes";
                break;
            case 7: diaSemana = "Sábado";
                break;
        }

        switch (mes_int)
        {
            case 0: mes = "Enero";
                break;
            case 1: mes = "Febrero";
                break;
            case 2: mes = "Marzo";
                break;
            case 3: mes = "Abril";
                break;
            case 4: mes = "Mayo";
                break;
            case 5: mes = "Junio";
                break;
            case 6: mes = "Julio";
                break;
            case 7: mes = "Agosto";
                break;
            case 8: mes = "Septiembre";
                break;
            case 9: mes = "Octubre";
                break;
            case 10: mes = "Noviembre";
                break;
            case 11: mes = "Diciembre";
                break;
        }

        String tiempo = calendar.getTime().toString();

        //txvFechaEntrada02.setText("tiempo -> " + tiempo);
        txvFechaEntrada.setText(diaSemana + " " + " " + diaMes + " de " + mes + " de " + anio);
        Log.i("Entrada", "formateddDate: " + formattedDate);
        Log.i("Entrada", "tiempo: " + tiempo);
        Log.i("Entrada", "diaSemana: " + diaSemana + " diaSemana_int : " + diaSemana_int);
        Log.i("Entrada", "mes: " + mes + " mes_int : " + mes_int);
    }

    private void clearLayout() {
        mLayout = (FrameLayout) getActivity().findViewById(R.id.frameRegistro);
        mLayout.removeAllViews();
    }

}
