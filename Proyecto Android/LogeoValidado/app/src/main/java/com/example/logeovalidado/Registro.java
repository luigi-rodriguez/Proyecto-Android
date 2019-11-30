package com.example.logeovalidado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // rescata obtener los datos del registro
        final EditText nombreT   = (EditText)findViewById(R.id.nombreRegistro);
        final EditText usuarioT  = (EditText)findViewById(R.id.usuarioRegistro);
        final EditText claveT    = (EditText)findViewById(R.id.claveRegistro);
        final EditText edadT     = (EditText)findViewById(R.id.edadRegistro);
        // boton que permite enviar el registro
        final Button btnRegistro = (Button)findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recarga el texto para enviar a la BASE DE DATOS
                String nombre  = nombreT.getText().toString();
                String usuario = usuarioT.getText().toString();
                String clave   = claveT.getText().toString();
                int edad       = Integer.parseInt( edadT.getText().toString() );


                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // atapa la respuesta en formato JSON y mostrara la vista Bienvenido
                        try
                        {
                          JSONObject jsonRespuesta = new JSONObject(response); //respuesta del servidor
                          boolean ok = jsonRespuesta.getBoolean("success");
                          if (ok == true) {
                            Intent i = new Intent(Registro.this, MainActivity.class); // si se encuentra bien registrado se devuelve al login
                            Registro.this.startActivity(i);
                            Registro.this.finish();
                          }
                            else {
                              AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
                              alerta.setMessage("Fallo en el registro")
                                      .setNegativeButton("Reintentar", null)
                                      .create()
                                      .show();
                            }
                        }
                        catch (JSONException e)
                        {
                          e.getMessage();
                        }
                    }
                };
                RegistroRequest r = new RegistroRequest(nombre, usuario, clave, edad, respuesta);
                RequestQueue cola = Volley.newRequestQueue(Registro.this); // se define la cola de registros con el volley
                cola.add(r);
            }
        });

    }
}
