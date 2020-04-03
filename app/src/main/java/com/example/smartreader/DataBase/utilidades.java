package com.example.ocr.DataBase;

public class utilidades {
    public static final String tabla_objetos = "objetos";
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String precio = "precio";
    public static final String fecha = "fecha_creacion";
    public static final String descripcion = "descripcion";
    public static final String crear_tabla = "CREATE TABLE objetos("+id+" INTEGER PRIMARY KEY, "+nombre+" TEXT DEFAULT 'Sin nombre', "+precio+" INTEGER NOT NULL DEFAULT 'O', "+fecha+" TEXT, "+descripcion+" TEXT DEFAULT 'Sin descripcion.')";
    public static final String drop = "DROP TABLE IF EXISTS objetos";
}
