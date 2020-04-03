/*
package com.example.smartreader;

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



public class TranslateLanguage implements com.example.itlavision.GoogleCloudService {

    private static TranslateLanguage instancia;
    public static TranslateLanguage getInstance() {
        if (instancia == null){
            instancia = new TranslateLanguage();
        }
        return instancia;
    }

    public JSONObject jsonObject = new JSONObject();
    public Context contexto;
    public com.example.itlavision.VolleyCallback Callback;


    @Override
    public void TranslateThisText(String languageOrigin, String languageDestiny, String textToTranlate, Context context, final com.example.itlavision.VolleyCallback callback){

        this.contexto = context;
        this.Callback = callback;

        try {
            jsonObject.put("q", textToTranlate);
            jsonObject.put("source", languageOrigin);
            jsonObject.put("target", languageDestiny);
            jsonObject.put("format","text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        POSTRequest(jsonObject);
    }

    @Override
    public void POSTRequest(JSONObject jsonObjectToTranslate) {

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, com.example.itlavision.GoogleCloudService.GoogleAccessKeyTranslate, jsonObjectToTranslate,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray translations = data.getJSONArray("translations");
                            String translatedText = translations.getJSONObject(0).getString("translatedText");

                            Callback.onSuccessResponse(translatedText, null);

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
}*/
