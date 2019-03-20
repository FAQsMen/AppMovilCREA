package com.ciatec.registrovisitantes_01.actividades;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ciatec.registrovisitantes_01.R;
import com.ciatec.registrovisitantes_01.fragmentos.EntradaFragment;
import com.ciatec.registrovisitantes_01.fragmentos.SalidaFragment;

public class RegistroActivity extends AppCompatActivity {

    FrameLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        boolean entrada = false;
        boolean salida = false;
        String s_entrada = null;
        String s_salida = null;

        try {
            entrada = getIntent().getExtras().getBoolean("ENTRADA");
            salida = getIntent().getExtras().getBoolean("SALIDA");
            s_entrada = String.valueOf(entrada);
            s_salida = String.valueOf(salida);

        }catch (Exception exception){
            Toast.makeText(RegistroActivity.this, "Exception" + exception,Toast.LENGTH_SHORT).show();
        }

        if(s_entrada != null && s_salida != null){
            AbrirFragmento(entrada, salida);
        }
        else {
            Intent intentRegistro = new Intent(RegistroActivity.this, SeleccionRegistroActivity.class);
            startActivity(intentRegistro);
        }
    }

    private void AbrirFragmento(boolean entrada, boolean salida){

        if (entrada == true && salida == false){
            clearLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            EntradaFragment entradaFragment = new EntradaFragment();
            fragmentTransaction.replace(R.id.frameRegistro, entradaFragment);
            fragmentTransaction.commit();

        }else if(entrada == false && salida == true){
            clearLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            SalidaFragment salidaFragment = new SalidaFragment();
            fragmentTransaction.replace(R.id.frameRegistro, salidaFragment);
            fragmentTransaction.commit();
        }
    }

    private void AbrirFragmento02(){
            clearLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            EntradaFragment entradaFragment = new EntradaFragment();
            fragmentTransaction.replace(R.id.frameRegistro, entradaFragment);
            fragmentTransaction.commit();
    }

    private void clearLayout() {
        mLayout = findViewById(R.id.frameRegistro);
        mLayout.removeAllViews();
    }

    @Override
    public void onBackPressed() {
        Intent intentRegistro = new Intent(RegistroActivity.this, SeleccionRegistroActivity.class);
        startActivity(intentRegistro);
    }
}
