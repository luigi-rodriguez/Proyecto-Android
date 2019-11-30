package com.example.logeovalidado;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        final TextView mensaje = (TextView)findViewById(R.id.mensaje);
        Intent i = this.getIntent(); // toma la informacion de la base de datos
        String nombre = i.getStringExtra("nombre");
        int edad = i.getIntExtra("edad", -1);

        mensaje.setText(mensaje.getText()+" "+nombre+" Su edad:"+edad+"");

    }
}
