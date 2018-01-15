package com.example.ramsesdiezgalvan.act4pmdm.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ramsesdiezgalvan on 30/11/17.
 */

public class FireBaseAdmin {
    public FirebaseAuth mAuth;
    public FireBaseAdminListener fireBaseAdminListener;
    public FirebaseDatabase database;
    public DatabaseReference myRef;

    public FireBaseAdmin() {

        mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }




/*
    public void createUserWithEmailAndPassword(String email, String password) {
        System.out.println("EMAIL: "+email+" PASS: "+password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fireBaseAdminListener.registerOk(true);


                        }
                    }


                });
    }
*/
    public void singInWithEmailAndPassword(String email, String password) {

        if (mAuth.getCurrentUser() != null) {
            // Already signed in
            // Do nothing
            System.out.println("    ESTOY LOGUEADO!!!!!!!!!");


        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            System.out.println("ACABO DE LOGUEARME!!!!!!!!");
                            if (task.isSuccessful()) {

                                fireBaseAdminListener.logInOk(true);

                            }
                        }
                    });
        }

    }


    public void signOut() {

        mAuth.signOut();
        fireBaseAdminListener.signOutOk(true);

    }

    public void downAndObserveBranch(final String branch) {
        //Leer y escuchar una rama
        DatabaseReference refBranch = myRef.child(branch);

        refBranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                fireBaseAdminListener.fireBaseDownloadBranch(branch,dataSnapshot);
                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                fireBaseAdminListener.fireBaseDownloadBranch(branch,null);
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


    public void setFireBaseAdminListener(FireBaseAdminListener fireBaseAdminListener) {

        this.fireBaseAdminListener = fireBaseAdminListener;

    }
}
