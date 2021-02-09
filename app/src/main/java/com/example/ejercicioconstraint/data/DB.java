package com.example.ejercicioconstraint.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "usuarios";
    public static final String[] COLUMNS_TABLE_USUARIOS = {"_id", "nombre", "contrasenia", "telefono", "email", "pais"};
    Context context;
    public static final String DATABASE_NAME = "dbUsuarios";
    public static final int DATABSE_VERSION = 1;

    private static final String SCRIPT_TABLE_USUARIOS =
            "CREATE TABLE usuarios (" +
                    "_id integer primary key autoincrement," +
                    "nombre text," +
                    "contrasenia text," +
                    "telefono text," +
                    "email text," +
                    "pais text)";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
