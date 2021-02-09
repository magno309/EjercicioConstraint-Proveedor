package com.example.ejercicioconstraint.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {

    Context context;
    DB db;
    SQLiteDatabase ad;

    public DAOUsuario(Context context) {
        this.context = context;
        db = new DB(context);
        ad = db.getWritableDatabase();
    }

    public long insert(Usuario nuevo) {
        ContentValues cv = new ContentValues();
        cv.put(DB.COLUMNS_TABLE_USUARIOS[1], nuevo.getNombre());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[2], nuevo.getContrasenia());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[3], nuevo.getTelefono());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[4], nuevo.getEmail());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[5], nuevo.getPais());
        return ad.insert(DB.TABLE_NAME, null, cv);
    }

    public long insert(ContentValues cv) {
        return ad.insert(DB.TABLE_NAME, null, cv);
    }

    public boolean update(Usuario nuevo) {
        ContentValues cv = new ContentValues();
        cv.put(DB.COLUMNS_TABLE_USUARIOS[1], nuevo.getNombre());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[2], nuevo.getContrasenia());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[3], nuevo.getTelefono());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[4], nuevo.getEmail());
        cv.put(DB.COLUMNS_TABLE_USUARIOS[5], nuevo.getPais());
        return ad.update(DB.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(nuevo.getID())}) > 0;
    }

    public boolean update(ContentValues cv) {
        return ad.update(DB.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(cv.get(DB.COLUMNS_TABLE_USUARIOS[0]))}) > 0;
    }

    public boolean delete(long id){
        return ad.delete(DB.TABLE_NAME, "_id=?", new String[]{String.valueOf(id)}) > 0;
    }

    public List<Usuario> getAll(){
        List<Usuario> lista = new ArrayList<>();
        Cursor c = ad.query(DB.TABLE_NAME, DB.COLUMNS_TABLE_USUARIOS,
                null,null,null,null,null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                lista.add(new Usuario(c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)));
            }
        }
        return lista;
    }

    /*public Cursor getAllByName(String nombre){

        return  ad.rawQuery("Select * from " + DB.TABLE_NAME +
                        " where nombre like  ? " ,
                new String[]{ "\\%"+ nombre+ "\\%"}
        );

    }*/

    public Cursor getAllByCursor(){
        return ad.query(DB.TABLE_NAME, DB.COLUMNS_TABLE_USUARIOS,
                null,null,null,null,null);
    }

    public Usuario getOneById(long id){
        Usuario obtenido = null;
        Cursor cursor = ad.rawQuery("select * from " + DB.TABLE_NAME + " where " + DB.COLUMNS_TABLE_USUARIOS[0] + "=?",
                new String[]{String.valueOf(id)});
        if(cursor != null){
            if(cursor.moveToFirst()){
                obtenido = new Usuario(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
            }
        }
        return obtenido;
    }

    public Cursor getOneByIdCursor(long id){
        return ad.rawQuery("select * from " + DB.TABLE_NAME + " where " + DB.COLUMNS_TABLE_USUARIOS[0] + "=?",
                new String[]{String.valueOf(id)});
    }

    public Cursor getAllByNames(String nombre){
        return ad.rawQuery("select * from " + DB.TABLE_NAME + " where nombre like ?" ,
                new String[]{nombre});

    }

    public Usuario autentificar(Usuario usuario){
        Cursor c = ad.query(DB.TABLE_NAME,
                DB.COLUMNS_TABLE_USUARIOS,
                "email = ? and contrasenia = ?",
                new String[]{usuario.getEmail(), usuario.getContrasenia()},
        null,null,null);
        if(c.moveToFirst()){
            usuario.setID(c.getLong(0));
            usuario.setNombre(c.getString(1));
            usuario.setTelefono(c.getString(3));
            usuario.setPais(c.getString(5));
        }
        return usuario;
    }

}
