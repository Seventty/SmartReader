package com.example.itlavision;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListLanguage implements GoogleCloudService{

    private static ListLanguage instancia;
    public static ListLanguage getInstance() {
        if (instancia == null){
            instancia = new ListLanguage();
        }
        return instancia;
    }

    public JSONObject jsonObject = new JSONObject();
    public Context contexto;
    public VolleyCallback Callback;

    @Override
    public void TranslateThisText(String languageOrigin, String languageDestiny, String textToTranlate, Context context, VolleyCallback callback) {
        this.contexto = context;
        this.Callback = callback;

        try {
            jsonObject.put("target", languageDestiny);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        POSTRequest(jsonObject);
    }

    @Override
    public void POSTRequest(JSONObject jsonObjectToTranslate) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, GoogleCloudService.GooogleAccessKeylanguages, jsonObjectToTranslate,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray languages = data.getJSONArray("languages");
                            ArrayList<JSONObject> listLenguage= new ArrayList<JSONObject>();

                            for (int i=0; i<languages.length(); i++){
                                JSONObject jsonObject = languages.getJSONObject(i);
                                listLenguage.add(jsonObject);
                            }

                            Callback.onSuccessResponse(null, listLenguage);

                        } catch (JSONException e) {
                            Toast.makeText(contexto, String.valueOf(e), Toast.LENGTH_LONG).show();
                            Log.e("Error", e + "");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(objectRequest);

    }
}
