package com.example.smartreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ocr.DataBase.SQLiteConn;
import com.example.ocr.DataBase.utilidades;
import com.example.smartreader.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Registro extends AppCompatActivity {

    Button registroBoton;
    Button atrasBoton;
    EditText idTag;
    EditText nombreTag;
    EditText precioTag;
    EditText fechaTag;
    EditText descripcionTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        idTag = findViewById(R.id.idTag);
        fechaTag = findViewById(R.id.fechaTag);
        idTag.setKeyListener(null);
        fechaTag.setKeyListener(null);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        fechaTag.setText(currentDate);
        idTag.setText(RecibirData());
        nombreTag = (EditText) findViewById(R.id.nombreTag);
        precioTag = (EditText) findViewById(R.id.precioTag);
        descripcionTag = (EditText) findViewById(R.id.descripcionTag);
        registroBoton = findViewById(R.id.registroBoton);
        atrasBoton = findViewById(R.id.atrasBoton);
        registroBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarObjeto();
            }
        });
        atrasBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAtras();
            }
        });
    }

    private void registrarObjeto() {
        SQLiteConn conn = new SQLiteConn(this, "object_DataBase", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.id, idTag.getText().toString());
        values.put(utilidades.nombre, nombreTag.getText().toString());
        values.put(utilidades.precio, precioTag.getText().toString());
        values.put(utilidades.fecha, fechaTag.getText().toString());
        values.put(utilidades.descripcion, descripcionTag.getText().toString());
        db.insert(utilidades.tabla_objetos, utilidades.id, values);
        Toast.makeText(this, "Objeto registrado", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void volverAtras() {
        Intent intent = new Intent(this, BarScannerMain.class);
        startActivity(intent);
    }

    private String RecibirData() {
        Intent intent = getIntent();
        String BarCode = intent.getStringExtra("ID");
        return BarCode;
    }
}
