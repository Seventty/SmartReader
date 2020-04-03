package com.example.smartreader;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ocr.DataBase.SQLiteConn;
import com.example.ocr.DataBase.utilidades;
import com.example.smartreader.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarScannerMain extends AppCompatActivity{

    TextView scannerView;
    Button scannerButton;
    Button registroButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barscanner_main);
        SQLiteConn conn = new SQLiteConn(this, "object_DataBase", null, 1);
        scannerView = (TextView) findViewById(R.id.scannerView);
        scannerButton = (Button) findViewById(R.id.scannerButton);
        registroButton = (Button) findViewById(R.id.registroButton);
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarScanner();
            }
        });
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verRegistro();
            }
        });
    }


    public void BarScanner() {
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        intent.setPrompt("Escaneando código...");
        intent.setCameraId(0);
        intent.setBeepEnabled(true);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Acción cancelada.", Toast.LENGTH_SHORT).show();
            }else{
                SQLiteConn conn = new SQLiteConn(this, "object_DataBase", null, 1);
                SQLiteDatabase db = conn.getReadableDatabase();
                String[] parametros = {result.getContents().toString()};
                try{
                    Cursor cursor = db.rawQuery("SELECT "+utilidades.id+" FROM "+utilidades.tabla_objetos+" WHERE "+utilidades.id+" =?", parametros);
                    cursor.moveToFirst();
                    String codigo = cursor.getString(0);

                    if (result.getContents().toString() != codigo){
                        Toast.makeText(this, "Se encontró un codigo nuevo", Toast.LENGTH_SHORT).show();
                        resultado(result.getContents().toString());
                    }else{
                        Toast.makeText(this, "Este código ya existía", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(this, "Nuevo objeto detectado.", Toast.LENGTH_SHORT).show();
                    registroView(result.getContents().toString());
                }
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void resultado(String ID) {
        Intent intent = new Intent(this, com.example.ocr.Resultado.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    private void registroView(String ID) {
        Intent intent = new Intent(this, com.example.ocr.Registro.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }
    private void verRegistro() {
        Intent intent = new Intent(this, com.example.ocr.ObjetosEscaneados.class);
        startActivity(intent);
    }
}

