package com.example.ramsesdiezgalvan.act4pmdm.firebase;

//import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by ramsesdiezgalvan on 30/11/17.
 */

public interface FireBaseAdminListener {


    public void logInOk(boolean ok);
    //public void registerOk(boolean ok);
    public void signOutOk(boolean ok);
    public void fireBaseDownloadBranch(String branch, DataSnapshot dataSnapshot);

}

