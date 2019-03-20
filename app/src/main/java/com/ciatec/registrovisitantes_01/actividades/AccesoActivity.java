package com.ciatec.registrovisitantes_01.actividades;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.ciatec.registrovisitantes_01.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


public class AccesoActivity extends AppCompatActivity {

    MaterialBetterSpinner materialDesignSpinner;
    EditText etxClaveAcceso;
    private Button btnIngresar;

    String[] SPINNERLIST = {"Edificio A", "Edificio C", "Edificio D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        materialDesignSpinner = findViewById(R.id.spn_acceso);
        materialDesignSpinner.setAdapter(arrayAdapter);

        etxClaveAcceso = findViewById(R.id.etx_claveAcceso);

        //Obteniendo una instancia del boton btnIngresar
        btnIngresar = findViewById(R.id.btn_Ingresar);

        //Registrando la escucha sobre la actividad Acceso
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean formvacio = EncontrarFormVacio();
                if (formvacio == false)
                    startActivity(new Intent(AccesoActivity.this, SeleccionRegistroActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(AccesoActivity.this);
        alerta.setTitle("Salir")
                .setMessage("¿Estás seguro de salir?")
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AccesoActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Quiero quedarme", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Acciones a realizar cuando salgamos. Siempre llamar al método súper.
                    }
                });
        alerta.show();
    }

    private boolean EncontrarFormVacio(){
        boolean formvacio = true;
        int duration = Toast.LENGTH_SHORT;
        String smdsEntrada = materialDesignSpinner.getText().toString();
        String setxClaveAcceso = etxClaveAcceso.getText().toString();

        if ( smdsEntrada.equals("")){
            formvacio = true;
            Log.i("EntradaFragment", "Nombre" + smdsEntrada);
            Toast toast = Toast.makeText(getApplicationContext(), "Campo ACCESO vacío", duration);
            toast.show();
        }else if(setxClaveAcceso.equals("")){
            formvacio = true;
            Log.i("EntradaFragment", "EmpresaVisitante" + setxClaveAcceso);
            Toast toast = Toast.makeText(getApplicationContext(), "Campo CLAVE vacío", duration);
            toast.show();
        }else{
            formvacio = false;
        }
        return formvacio;
    }

    public void ObtenerDatosUbicacion(){

        final ProgressDialog progressDialog = new ProgressDialog(AccesoActivity.this);
        progressDialog.setMessage("Obteniendo Datos...");
        progressDialog.show();

        String idunique = getIntent().getStringExtra("UNIQUEID");
        Toast.makeText(AccesoActivity.this, idunique,Toast.LENGTH_SHORT).show();
        Long IdUnique = Long.valueOf(idunique);


        AsyncHttpClient cliente = new AsyncHttpClient(true, 8088, 443);
        String url="http://actv-fisica.ciatec.mx:8088/court/ubicacion_obtener.php";

        RequestParams parametros = new RequestParams();
        parametros.put("uniqueId",IdUnique);

        cliente.get(url, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responsebody) {
                // Protocolo de la W3 -> 10.2.1 200 OK, cuando la solicitud ha tenido éxito
                if (statusCode == 200) {
                    progressDialog.dismiss();

                    try {
                        JSONArray jsonArray = new JSONArray(responsebody.toString());
                        for (int i=0;i<jsonArray.length();i++) {
                            String Nombre = jsonArray.getJSONObject(i).getString("Nombre");
                            String latitud = jsonArray.getJSONObject(i).getString("Latitud");
                            String longitud = jsonArray.getJSONObject(i).getString("Longitud");
                            String nbateria = jsonArray.getJSONObject(i).getString("NivelBateria");
                            String fechahora = jsonArray.getJSONObject(i).getString("UltimaUbicacion");

                            Toast.makeText(AccesoActivity.this, Nombre, Toast.LENGTH_LONG).show();

                            Double lat = Double.valueOf(latitud).doubleValue();
                            Double lon = Double.valueOf(longitud).doubleValue();
                            Integer nbat = Integer.valueOf(nbateria).intValue();
                            if(nbat<10){
                                AlertDialog.Builder alerta = new AlertDialog.Builder(AccesoActivity.this);
                                alerta
                                        .setTitle("Bateria Baja")
                                        .setMessage("El nivel de bateria del dispositivo "+ Nombre+" es muy bajo, Se necesita recargar")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                            }
                                        });

                                alerta.show();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(AccesoActivity.this);
                        alerta.setTitle("Sin datos de ubicacion")
                                .setMessage("El dispositivo no tiene conexión, favor de revisarlo")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        alerta.show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                progressDialog.dismiss();

                if (statusCode == 404) {
                    Toast.makeText(AccesoActivity.this, "404 - Servidor no encontrado", Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(AccesoActivity.this, "500 - Error en el servidor", Toast.LENGTH_LONG).show();
                } else if (statusCode == 403) {
                    Toast.makeText(AccesoActivity.this, "403- Denegado el acceso a la acción que se solicita.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AccesoActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
