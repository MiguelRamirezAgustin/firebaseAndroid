package com.example.myfirebaseauth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirebaseauth.Model.Persona;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Home extends AppCompatActivity {

    private List<Persona> listPersona = new ArrayList<>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    //variables
    public static final String user="name";
     EditText editTextN, editTextA, editTextC, editTextNPs;
     ListView listView_Usuarios;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String user =getIntent().getStringExtra("name");


       // Toast.makeText(this," "+ user,Toast.LENGTH_SHORT).show();
        editTextN = (EditText)findViewById(R.id.PersonaNombre);
        editTextA = (EditText)findViewById(R.id.PersonaApellidos);
        editTextC = (EditText)findViewById(R.id.PersonaCorreo);
        editTextNPs = (EditText)findViewById(R.id.PersonaContrasena);

        listView_Usuarios =(ListView) findViewById(R.id.List_datosPerson);

        inicializarFirebase();
        listaData();


        //seleccionar iten de lista
        listView_Usuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //seleccionar posision
              personaSelect= (Persona) parent.getItemAtPosition(position);
              editTextN.setText(personaSelect.getNombre());
              editTextA.setText(personaSelect.getApellido());
              editTextC.setText(personaSelect.getCorreo());
              editTextNPs.setText(personaSelect.getPassword());
            }

        });

    }

    private void listaData() {
        databaseReference.child("Personas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPersona.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                 Persona p = objSnapshot.getValue(Persona.class);
                 listPersona.add(p);

                 arrayAdapterPersona = new ArrayAdapter<Persona>( Home.this, android.R.layout.simple_list_item_1, listPersona );
                 listView_Usuarios.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void inicializarFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        //Se agrega persistencia de datos mediante clase firebasePersistenciaDatos
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombre = editTextN.getText().toString();
        String apellidos = editTextA.getText().toString();
        String correo = editTextC.getText().toString();
        String password = editTextNPs.getText().toString();
        /*
        String nombre = "Miguel";
        String apellidos = "Ramirez";
        String correo = "agustin@gmail.com";
        String password = "miguel";*/

        System.out.println("Datos de usuario---"+ nombre + apellidos + correo + password);

        switch (item.getItemId()){
            case R.id.ico_add: {
                if (nombre.equals("") || apellidos.equals("") || correo.equals("") || password.equals("")) {
                    validacion();
                    break;
                } else {
                    Persona p = new Persona();
                    p.setUiId(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellidos);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    System.out.println("Datos de usuario registro ---" + p.toString());
                    databaseReference.child("Personas").child(p.getUiId()).setValue(p);
                    LimpiarDatos();
                    Toast.makeText(this, "Se Agrego el Usuarios  "+ nombre, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            case R.id.ico_save: {
                //Acualizar datos

                Persona p = new Persona();
                p.setUiId(personaSelect.getUiId());
                p.setNombre(editTextN.getText().toString().trim());
                p.setApellido(editTextA.getText().toString().trim());
                p.setCorreo(editTextC.getText().toString().trim());
                p.setPassword(editTextNPs.getText().toString().trim());
                databaseReference.child("Personas").child(p.getUiId()).setValue(p);
                LimpiarDatos();
                Toast.makeText(this, "Actualizado...!!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.ico_delete: {
                Persona p = new Persona();
                p.setUiId(personaSelect.getUiId());
                databaseReference.child("Personas").child(p.getUiId()).removeValue();
                Toast.makeText(this, "eliminar", Toast.LENGTH_SHORT).show();
                LimpiarDatos();
                break;
            }
        }
        return true;
    }


    private void LimpiarDatos() {
        editTextN.setText("");
        editTextA.setText("");
        editTextC.setText("");
        editTextNPs.setText("");
    }


    private void validacion() {
        String nombre = editTextN.getText().toString();
        String apellidos = editTextA.getText().toString();
        String correo = editTextC.getText().toString();
        String password = editTextNPs.getText().toString();

        if(nombre.equals("")){
            editTextN.setError("Requerido");
        }else if(apellidos.equals("")){
            editTextA.setError("Requerido");
        }else if(correo.equals("")){
            editTextC.setError("Requerido");
        }else if(password.equals("")){
            editTextNPs.setError("Requerido");
        }
    }


}
