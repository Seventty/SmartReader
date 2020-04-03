package com.example.itlavision;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public interface GoogleCloudService {

    String GoogleAccessKeyTranslate = "https://translation.googleapis.com/language/translate/v2?key=AIzaSyDGQf9i5GiY9YeXGlDrv9CXWgMve3t70Vw";
    String GooogleAccessKeylanguages = "https://translation.googleapis.com/language/translate/v2/languages?key=AIzaSyDGQf9i5GiY9YeXGlDrv9CXWgMve3t70Vw";
    String GooogleAccessKeyDetect  = "https://translation.googleapis.com/language/translate/v2/detect?key=AIzaSyDGQf9i5GiY9YeXGlDrv9CXWgMve3t70Vw";

    String languageOrigin = null;
    String languageOriginCode = null;

    String languageDestiny = null;
    String languageDestinyCode = null;



    void TranslateThisText(String languageOrigin, String languageDestiny, String textToTranlate, Context context, final VolleyCallback callback);
    void POSTRequest(JSONObject jsonObjectToTranslate);

}
