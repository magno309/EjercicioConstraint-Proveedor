package com.example.ejercicioconstraint;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioconstraint.data.DAOUsuario;
import com.example.ejercicioconstraint.data.DB;
import com.example.ejercicioconstraint.data.Usuario;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_REGISTRO = 1000;
    TextView lblContrasenia;
    TextView txtUsuario, txtPass;
    Button btnAcceder;
    Spinner spnUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lblContrasenia = (TextView) findViewById(R.id.lblContrasenia);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        btnAcceder = (Button) findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener( v -> {
            //Toast.makeText(this, "Click", Toast.LENGTH_LONG).show();
            DAOUsuario dao = new DAOUsuario(getApplicationContext());
            Usuario usuarioAutenticado = dao.autentificar(new Usuario(txtUsuario.getText().toString(), txtPass.getText().toString()));
            if(usuarioAutenticado.getID() != 0 ){
                Toast.makeText(this, "Bienvenido " + usuarioAutenticado.getNombre() + "!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, AcvtividadDos.class);
                intent.putExtra("usuario", usuarioAutenticado);
                startActivity(intent);
            }else{
                Toast.makeText(this, "No se encontró al usuario!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void lblRegistro_click(View view) {
        Intent intent = new Intent(MainActivity.this, registrarUsuario.class);
        startActivityForResult(intent, ACTIVITY_REGISTRO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ACTIVITY_REGISTRO:
                if(resultCode == RESULT_OK){
                    Toast.makeText(this, "Usuario registrado!", Toast.LENGTH_LONG).show();
                    Usuario nuevo = (Usuario) data.getSerializableExtra("nuevoUsuario");
                    txtUsuario.setText(nuevo.getEmail());

                }else{
                    Toast.makeText(this, "Registro cancelado!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}