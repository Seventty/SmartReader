package com.example.smartreader;

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

public class Resultado extends AppCompatActivity {

    TextView idTagResultado;
    TextView descripcionTagResultado;
    TextView fechaTagResultado;
    TextView nombreTagResultado;
    TextView precioTagResultado;
    Button inicioButton;
    Button borrarRegistroButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        idTagResultado = (TextView) findViewById(R.id.idTagResultado);
        descripcionTagResultado = (TextView) findViewById(R.id.descripcionTagResultado);
        fechaTagResultado = (TextView) findViewById(R.id.fechaTagResultado);
        nombreTagResultado = (TextView) findViewById(R.id.nombreTagResultado);
        precioTagResultado = (TextView) findViewById(R.id.precioTagResultado);
        inicioButton = (Button) findViewById(R.id.inicioButton);
        borrarRegistroButton = (Button) findViewById(R.id.borrarRegistroButton);
        recibirData();
        SQLiteConn conn = new SQLiteConn(this, "object_DataBase", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {recibirData()};
        try{
            Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.tabla_objetos+" WHERE "+utilidades.id+" =?", parametros);
            cursor.moveToFirst();
            String codigo = cursor.getString(0);
            idTagResultado.setText("ID: "+codigo);
            nombreTagResultado.setText("Nombre: "+cursor.getString(1));
            precioTagResultado.setText("Precio: "+cursor.getString(2)+ " Pesos");
            fechaTagResultado.setText("Fecha: "+cursor.getString(3));
            descripcionTagResultado.setText("Descripcion: "+cursor.getString(4));

        }catch(Exception e){
            Toast.makeText(this, "Error encontrado: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        inicioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        borrarRegistroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteConn conn = new SQLiteConn(getApplicationContext(), "object_DataBase", null, 1);
                SQLiteDatabase db = conn.getWritableDatabase();
                db.execSQL("DELETE FROM "+utilidades.tabla_objetos+" WHERE "+utilidades.id+" = "+recibirData());
                Toast.makeText(Resultado.this, "Objeto borrado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private String recibirData() {
        Intent intent = getIntent();
        String BarCode = intent.getStringExtra("ID");
        return BarCode;
    }
}
