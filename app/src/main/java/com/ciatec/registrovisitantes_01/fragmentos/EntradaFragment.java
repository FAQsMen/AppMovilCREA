package com.ciatec.registrovisitantes_01.fragmentos;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.ciatec.registrovisitantes_01.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntradaFragment extends Fragment {

    FrameLayout mLayout;
    private TextView txvFechaEntrada;
    private EditText etxNombre, etxEmpresaVisitante, etxNombreAnfitrion, etxAsunto;
    private TextClock txcHoraEntrada;
    private Button btnRegistrarDatos, btnBorrarDatos;

    public EntradaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrada, container, false);

        String timeZone = TimeZone.getDefault().getDisplayName();
        //TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");

        txvFechaEntrada = view.findViewById(R.id.txv_FechaEntrada);
        txcHoraEntrada = view.findViewById(R.id.txc_HoraEntrada);
        etxNombre = view.findViewById(R.id.etx_Nombre);
        etxEmpresaVisitante = view.findViewById(R.id.etx_EmpresaVisitante);
        etxNombreAnfitrion = view.findViewById(R.id.etx_NombreAnfitrion);
        etxAsunto = view.findViewById(R.id.etx_Asunto);
        btnRegistrarDatos = view.findViewById(R.id.btn_RegistrarDatos);
        btnBorrarDatos = view.findViewById(R.id.btn_BorrarDatos);

        //txcHoraEntrada.setTimeZone(timeZone);
        String txcTimeZone = txcHoraEntrada.getTimeZone();

        txcHoraEntrada.setFormat12Hour(null);
        txcHoraEntrada.setFormat24Hour("HH:mm:ss");

        EstablecerFecha();

        btnRegistrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean formvacio = EncontrarFormVacio();
                if (formvacio == false)
                    NavegarInstruccionFragment();

            }
        });

        btnBorrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LimpiarFormulario();
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

        int hdi = calendar.getFirstDayOfWeek();

        String tiempo = calendar.getTime().toString();

        //txvFechaEntrada02.setText("tiempo -> " + tiempo);
        txvFechaEntrada.setText(diaSemana + " " + " " + diaMes + " de " + mes + " de " + anio);
        Log.i("Entrada", "formateddDate: " + formattedDate);
        Log.i("Entrada", "tiempo: " + tiempo);
        Log.i("Entrada", "diaSemana: " + diaSemana + " diaSemana_int : " + diaSemana_int);
        Log.i("Entrada", "mes: " + mes + " mes_int : " + mes_int);

        System.out.println(hdi);
    }

    private void clearLayout() {
        mLayout = (FrameLayout) getActivity().findViewById(R.id.frameRegistro);
        mLayout.removeAllViews();
    }

    private void NavegarInstruccionFragment(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        clearLayout();
        FragmentTransaction transaction = manager.beginTransaction();
        InstruccionFragment instruccionFragment = new InstruccionFragment();
        transaction.add(R.id.frameRegistro, instruccionFragment);
        transaction.commit();
    }

    private void LimpiarFormulario(){
        // EditText etxNombre, etxEmpresaVisitante, etxNombreAnfitrion, etxAsunto;
        etxNombre.setText(null);
        etxEmpresaVisitante.setText(null);
        etxNombreAnfitrion.setText(null);
        etxAsunto.setText(null);
    }

    private boolean EncontrarFormVacio(){

        boolean formvacio = true;
        int duration = Toast.LENGTH_SHORT;
        String setxNombre = etxNombre.getText().toString();
        String setxEmpresaVisitante = etxEmpresaVisitante.getText().toString();
        String setxNombreAnfrition = etxNombreAnfitrion.getText().toString();
        String setxAsunto = etxAsunto.getText().toString();

        if ( setxNombre.equals("")){
            formvacio = true;
            Log.i("EntradaFragment", "Nombre" + setxNombre);
            Toast toast = Toast.makeText(getContext(), "Campo NOMBRE vacío", duration);
            toast.show();
        }else if(setxEmpresaVisitante.equals("")){
            formvacio = true;
            Log.i("EntradaFragment", "EmpresaVisitante" + setxEmpresaVisitante);
            Toast toast = Toast.makeText(getContext(), "Campo EMPRESA vacío", duration);
            toast.show();
        }else if(setxNombreAnfrition.equals("")){
            formvacio = true;
            Log.i("EntradaFragment", "NombreAnfitrion" + setxNombreAnfrition);
            Toast toast = Toast.makeText(getContext(), "Campo NOMBRE A QUIEN VISITA vacío", duration);
            toast.show();
        }else if(setxAsunto.matches("")){
            formvacio = true;
            Log.i("EntradaFragment", "Asunto" + setxAsunto);
            Toast toast = Toast.makeText(getContext(), "Campo ASUNTO vacío", duration);
            toast.show();
        }else{
            formvacio = false;
        }
        return formvacio;
    }

}