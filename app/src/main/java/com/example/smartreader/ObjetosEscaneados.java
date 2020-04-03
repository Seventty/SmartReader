package com.example.ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ocr.DataBase.SQLiteConn;
import com.example.ocr.DataBase.utilidades;
import com.example.ocr.entidades.Objetos;

import java.util.ArrayList;

public class ObjetosEscaneados extends AppCompatActivity {

    Button BorraTodoButton;
    Button atrasIButton;
    ListView ListViewObjetos;
    ArrayList<String> ListaInformacion;
    ArrayList<Objetos> ListaObjetos;
    SQLiteConn conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos_escaneados);
        conn = new SQLiteConn(this, "object_DataBase", null, 1);
        ListViewObjetos = (ListView) findViewById(R.id.ListViewObjetos);
        ConsultarObjetos();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListaInformacion);
        ListViewObjetos.setAdapter(adaptador);
        BorraTodoButton = (Button) findViewById(R.id.BorraTodoButton);
        atrasIButton = (Button) findViewById(R.id.atrasIButton);
        ListViewObjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "id: "+ListaObjetos.get(position).getId()+"\n";
                        informacion+="Nombre: "+ListaObjetos.get(position).getNombre()+"\n";
                        informacion+=":Precio "+ListaObjetos.get(position).getPrecio()+"\n";
                        informacion+=":Fecha:  "+ListaObjetos.get(position).getFecha()+"\n";
                        informacion+=":Descripcion:  "+ListaObjetos.get(position).getDescripcion()+"\n";
                        Toast.makeText(ObjetosEscaneados.this, informacion, Toast.LENGTH_SHORT).show();
            }
        });
        atrasIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BorraTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TruncarTabla();
            }
        });
    }

    private void TruncarTabla() {
        SQLiteConn conn = new SQLiteConn(this, "object_DataBase", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("delete from "+utilidades.tabla_objetos +";");
        db.execSQL("update sqlite_sequence set seq=0 where name= "+utilidades.tabla_objetos);
        Toast.makeText(this, "Registro borrado.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void ConsultarObjetos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Objetos objetos = null;
        ListaObjetos = new ArrayList<Objetos>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ utilidades.tabla_objetos, null);
        while(cursor.moveToNext()){
            objetos = new Objetos();
            objetos.setId(cursor.getInt(0));
            objetos.setNombre(cursor.getString(1));
            objetos.setPrecio(cursor.getInt(2));
            objetos.setFecha(cursor.getString(3));
            objetos.setDescripcion(cursor.getString(4));
            ListaObjetos.add(objetos);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        ListaInformacion = new ArrayList<String>();
        for (int i = 0; i<ListaObjetos.size(); i++){
            ListaInformacion.add(ListaObjetos.get(i).getId()+" - "+ListaObjetos.get(i).getNombre());

        }
    }
}
