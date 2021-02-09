package com.example.ejercicioconstraint.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ejercicioconstraint.data.DAOUsuario;

public class MiProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    DAOUsuario dao;
    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */
        uriMatcher.addURI("com.example.ejercicioconstraint.provider", "usuarios", 1);
        uriMatcher.addURI("com.example.ejercicioconstraint.provider", "usuarios/#", 2);
        uriMatcher.addURI("com.example.ejercicioconstraint.provider", "usuarios/*", 3);
    }

    @Override
    public boolean onCreate() {
        dao = new DAOUsuario(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor result = null;

        switch (uriMatcher.match(uri)){
            case 1:
                result = dao.getAllByCursor();
                break;
            case 2:
                String id = uri.getLastPathSegment();
                result = dao.getOneByIdCursor(Long.parseLong(id));
                break;
            case 3:
                break;
            default:
        }
        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String result = "";
        //vnd.android.cursor.dir/vnd.com.example.ejercicioconstraint.provider.usuarios
        switch (uriMatcher.match(uri)){
            case 1:
            case 3:
                result = "vnd.android.cursor.dir/vnd.com.example.ejercicioconstraint.provider.usuarios";
                break;
            case 2:
                result = "vnd.android.cursor.item/vnd.com.example.ejercicioconstraint.provider.usuarios";
                break;
        }
        return result;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri result = null;
        switch(uriMatcher.match(uri)){
            case 1:
                long idRow = dao.insert(contentValues);
                result = Uri.withAppendedPath(uri, String.valueOf(idRow));
                break;
        }
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int result = 0;
        switch(uriMatcher.match(uri)){
            case 2:
                String id = uri.getLastPathSegment();
                result = dao.delete(Long.parseLong(id))?1:0;
                break;
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int result = 0;
        switch(uriMatcher.match(uri)){
            case 2:
                String id = uri.getLastPathSegment();
                result = dao.update(contentValues)?1:0;
                break;
        }
        return result;
    }
}
