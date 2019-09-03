package com.example.myfirebaseauth;

import com.google.firebase.database.FirebaseDatabase;

public class firebasePersistenciaDatos extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //Persistencia de datos
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
