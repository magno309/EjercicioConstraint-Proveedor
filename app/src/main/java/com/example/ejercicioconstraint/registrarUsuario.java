package com.example.ejercicioconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioconstraint.data.DAOUsuario;
import com.example.ejercicioconstraint.data.Usuario;

public class registrarUsuario extends AppCompatActivity {

    TextView txtNombre, txtPass, txtEmail, txtTel;
    Button btnAgregar, btnCancelar;
    Spinner spnPaises;
    String [] paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        paises = getResources().getStringArray(R.array.paises);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTel = (TextView) findViewById(R.id.txtTel);

        btnAgregar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        spnPaises = (Spinner) findViewById(R.id.spnPaises);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String pass = txtPass.getText().toString();
                String email = txtEmail.getText().toString();
                String tel = txtTel.getText().toString();
                String pais = spnPaises.getSelectedItem().toString();
                Usuario nuevo = new Usuario(nombre,tel,email,pass,pais);

                DAOUsuario dao = new DAOUsuario(getApplicationContext());

                if(dao.insert(nuevo) != -1){
                    Intent intent = new Intent();
                    intent.putExtra("nuevoUsuario", nuevo);
                    Toast.makeText(getApplicationContext(), "Objecto guardado dentro de la base de datos!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Error al guardar objeto dentro de la base de datos!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                }
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, paises);
        spnPaises.setAdapter(adapter);
    }
}