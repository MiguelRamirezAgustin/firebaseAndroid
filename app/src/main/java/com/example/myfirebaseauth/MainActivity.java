package com.example.myfirebaseauth;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private ProgressDialog progressDialog;
    private Button buttonRegistrar;


    private static int TIME_OUT=400;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        TextEmail= (EditText) findViewById(R.id.Edit_Correo);
        TextPassword= (EditText) findViewById(R.id.Edit_Contrasena);
        buttonRegistrar = (Button) findViewById(R.id.btn_Login);


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

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "El registro fue exitios del usuaruio: " + TextEmail.getText(), Toast.LENGTH_SHORT).show();
                            TextPassword.getText().clear();
                            TextEmail.getText().clear();
                        } else {
                            Toast.makeText(MainActivity.this, "Fallo al realizar registro", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();

                    }
                });
}

 @Override
  public void onClick(View view){
     registroUsuario();
    }
}









