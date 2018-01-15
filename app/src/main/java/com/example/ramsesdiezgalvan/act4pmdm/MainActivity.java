package com.example.ramsesdiezgalvan.act4pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdmin;

public class MainActivity  extends AppCompatActivity {
    MainActivityEvents events;
    Button btnGo;
    EditText txtUser;
    EditText txtPass;
    FireBaseAdmin fireBaseAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fireBaseAdmin = new FireBaseAdmin();
        setContentView(R.layout.activity_main);
        events = new MainActivityEvents(this);
        fireBaseAdmin.setFireBaseAdminListener(events);



        btnGo=this.findViewById(R.id.btnGo);
        txtUser=this.findViewById(R.id.txtUser);
        txtPass=this.findViewById(R.id.txtPass);
        btnGo.setOnClickListener(events);


        if (fireBaseAdmin.mAuth.getCurrentUser() != null) {

            fireBaseAdmin.fireBaseAdminListener.logInOk(true);
        }


    }



}
