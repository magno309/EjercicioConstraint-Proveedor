package com.example.ejercicioconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioconstraint.data.DAOUsuario;
import com.example.ejercicioconstraint.data.DB;

public class AcvtividadDos extends AppCompatActivity {

    Spinner spnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvtividad_dos);

        spnUser = (Spinner) findViewById(R.id.spnUser2);



        DAOUsuario dao = new DAOUsuario(getApplicationContext());
        Cursor c = dao.getAllByCursor();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                c,
                new String[]{DB.COLUMNS_TABLE_USUARIOS[0], DB.COLUMNS_TABLE_USUARIOS[4]},
                new int[]{android.R.id.text1, android.R.id.text2,SimpleCursorAdapter.NO_SELECTION});
        spnUser.setAdapter(simpleCursorAdapter);

        spnUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    adapterView.getItemAtPosition(i);
                    String CadenaID = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                    Toast.makeText(getApplicationContext(), CadenaID, Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Algo pasó!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}