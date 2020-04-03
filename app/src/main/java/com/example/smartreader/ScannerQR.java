package com.example.smartreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView escanerZXing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escanerZXing = new ZXingScannerView(this);
        //hacer que el contenido de la actividad sea el escaner
        setContentView(escanerZXing);
    }

    @Override
    public void onResume(){
        super.onResume();
        //El manejador de los resultados es la clase misma
        escanerZXing.setResultHandler(this);
        escanerZXing.startCamera(); //Comenzar la camara al reanudar la app
    }

    @Override
    public void onPause(){
        super.onPause();
        escanerZXing.stopCamera(); //pausar en onPause
    }



    @Override
    public void handleResult(Result result) {
        setContentView(R.layout.activity_scanner_main);

        String contQr = result.getText();

        EjecQr(contQr);

        escanerZXing.resumeCameraPreview(this);
    }

    private void EjecQr(String dato){

        if (dato != null){

            if(dato.contains("TEL")){
                String tel = dato.replace("TEL:", "tel:+");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                startActivity(callIntent);
            }
            else {

                Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dato));
                startActivity(callIntent);
            }

        }

    }
}
