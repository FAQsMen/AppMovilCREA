package com.ciatec.registrovisitantes_01.actividades;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.ciatec.registrovisitantes_01.R;


public class SeleccionRegistroActivity extends AppCompatActivity {

    private Button btnEntrada, btnSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_registro);

        //Obteniendo instancias de los botones
        btnEntrada = findViewById(R.id.btn_Entrada);
        btnSalida = findViewById(R.id.btn_Salida);

        //Registrando la escucha
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirRegistroActivity(true, false);
            }
        });
        btnSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirRegistroActivity(false, true);
            }
        });
    }

    private void AbrirRegistroActivity(boolean entrada, boolean salida){
        startActivity(new Intent(SeleccionRegistroActivity.this, RegistroActivity.class));

        Intent intentRegistro = new Intent(SeleccionRegistroActivity.this, RegistroActivity.class);
        intentRegistro.putExtra("ENTRADA", entrada);
        intentRegistro.putExtra("SALIDA", salida);
        startActivity(intentRegistro);
    }

    @Override
    public void onBackPressed() {
        Intent intentRegistro = new Intent(SeleccionRegistroActivity.this, AccesoActivity.class);
        startActivity(intentRegistro);
    }

}
