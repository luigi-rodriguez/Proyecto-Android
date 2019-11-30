package com.example.logeovalidado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView registro = (TextView) findViewById(R.id.registroLogin); // busca el registro a traves de un enlaces
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        final EditText usuarioT = (EditText) findViewById(R.id.usuarioLogin);
        final EditText claveT = (EditText) findViewById(R.id.claveLogin);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(registro);// enlaza la lista del registro
                finish(); //cierra el login Activity_Main.xml
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recarga el texto para enviar a la BASE DE DATOS
                final String usuario = usuarioT.getText().toString();
                final String clave = claveT.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response); //respuesta del servidor
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if (ok == true) {
                                String nombre = jsonRespuesta.getString("nombre");
                                int edad = jsonRespuesta.getInt("edad");

                                Intent bienvenido = new Intent(MainActivity.this, Bienvenido.class); // si se encuentra bien registrado se devuelve al login
                                // se ása ña informacion...
                                bienvenido.putExtra("nombre", nombre);
                                bienvenido.putExtra("edad", edad);

                                MainActivity.this.startActivity(bienvenido);
                                MainActivity.this.finish(); // cierra el Activity o Layout Login
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage("Fallo en el Login")
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
                LoginRequest r = new LoginRequest(usuario, clave, respuesta);

                // se define la cola de registros con el volley
                RequestQueue cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);
            }
        });
    }
}