package com.example.myfirebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private ProgressDialog progressDialog;
    private Button buttonRegistrar, buttonLogin;


    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        TextEmail= (EditText) findViewById(R.id.Edit_Correo);
        TextPassword= (EditText) findViewById(R.id.Edit_Contrasena);
        buttonRegistrar = (Button) findViewById(R.id.btn_Registro);
        buttonLogin = (Button) findViewById(R.id.btn_Login);


        progressDialog = new ProgressDialog(this);
        buttonRegistrar.setOnClickListener(this);

    }



    public void registroUsuario(){
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "El campo de email no debe de estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "El campo de password no debe de estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro...");
        progressDialog.show();

        // Crear usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "El registro fue exitios del usuaruio: " + TextEmail.getText(), Toast.LENGTH_SHORT).show();
                            TextPassword.getText().clear();
                            TextEmail.getText().clear();
                        } else {
                            // FirebaseAuthUserCollisionException valida si el usuario existe
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(MainActivity.this, "Fallo al realizar el usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Fallo al realizar registro", Toast.LENGTH_SHORT).show();

                            }

                        }
                        progressDialog.dismiss();

                    }
                });
   }


    public void loginUsuario(){
        final String email = TextEmail.getText().toString();
        String password = TextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "El campo de email no debe de estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "El campo de password no debe de estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Cargando.....");
        progressDialog.show();

        //Login usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Eliminar datos despues del @
                            int post = email.indexOf("@");
                            String userParam = email.substring(0,post);

                            Toast.makeText(MainActivity.this, "Bienvenido " + TextEmail.getText(), Toast.LENGTH_SHORT).show();
                            TextPassword.getText().clear();
                            TextEmail.getText().clear();

                            //Evento que manda a la actividad home , inicia la actividad=startActivity
                            Intent intent = new Intent(getApplication(), Home.class);
                            intent.putExtra(Home.user , userParam);
                            //pasar parametro email
                            startActivity(intent);
                        } else {
                            // FirebaseAuthUserCollisionException valida si el usuario existe
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(MainActivity.this, "Fallo intente mas tarde", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Fallo intente mas tarde", Toast.LENGTH_SHORT).show();

                            }

                        }
                        progressDialog.dismiss();

                    }
                });
    }



//validacion de eventos
 @Override
  public void onClick(View view) {

     switch (view.getId()) {

         case R.id.btn_Registro:
             registroUsuario();
             break;

         case R.id.btn_Login:
             loginUsuario();
             break;
      }
   }
}










