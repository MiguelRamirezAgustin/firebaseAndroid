package com.example.myfirebaseauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    //variables
    public static final String user="name";
    TextView textView_N, textView_P, textView_C, textView_Ps;
    ListView listView_Usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String user =getIntent().getStringExtra("name");

       // Toast.makeText(this," "+ user,Toast.LENGTH_SHORT).show();
        textView_N = (TextView) findViewById(R.id.Edit_PersonaNombre);
        textView_P = (TextView) findViewById(R.id.Edit_PersonaApellidos);
        textView_C = (TextView) findViewById(R.id.Edit_Correo);
        textView_Ps = (TextView) findViewById(R.id.Edit_Contrasena);

        listView_Usuarios =(ListView) findViewById(R.id.List_datosPerson);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.ico_add:
                Toast.makeText(this,"Agregar",Toast.LENGTH_SHORT).show();
                break;


            case R.id.ico_save:
                Toast.makeText(this, "Guardar", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ico_delete:
                Toast.makeText(this, "eliminar", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
