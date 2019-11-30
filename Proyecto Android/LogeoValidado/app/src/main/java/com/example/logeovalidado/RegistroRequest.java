package com.example.logeovalidado;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest{
    private static final String ruta = "http://localhost/ConexionesAndroid/Registroconexion.php"; // se define la ruta a la base de datos
    private Map<String, String> parametros;

    //CONSTRUCTOR
    public RegistroRequest(String nombre, String usuario, String clave, int edad, Response.Listener<String> listener) {
        super(Request.Method.POST, ruta, listener,null);
        parametros = new HashMap<>();
        parametros.put("nombre", nombre+"");
        parametros.put("usuario", usuario+"");
        parametros.put("clave", clave+"");
        parametros.put("edad", edad+"");
    }
    @Override
    protected Map<String, String> getParams() { // se declara el manejador
        return parametros;
    }
}

// manipula la informacion para llevar a la base de datos












