package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foody.Common.Common;
import com.example.foody.model.Catagory;
import com.example.foody.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class signin extends AppCompatActivity {

    EditText editphone, editpassword;
    private Button btnsignin;
    private FirebaseDatabase mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editpassword = (EditText) findViewById(R.id.password);
        editphone = (EditText) findViewById(R.id.phonenumber);
        btnsignin = (Button) findViewById(R.id.signinbutton);


       mdatabase = FirebaseDatabase.getInstance("https://foody-app-194b1-default-rtdb.firebaseio.com");
       final DatabaseReference t_user =  mdatabase.getReference("User");



       btnsignin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               ProgressDialog mDialog = new ProgressDialog(signin.this);
               mDialog.setMessage("Please wait..");
               mDialog.show();


              t_user.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      if(dataSnapshot.child(editphone.getText().toString()).exists()){

                          mDialog.dismiss();

                    user user = dataSnapshot.child(editphone.getText().toString()).getValue(user.class);

                      if ( user.getPassword().equals(editpassword.getText().toString()))
                      {
                          Toast.makeText(signin.this, "Signing in successful..!!", Toast.LENGTH_SHORT).show();
                          Intent homeintent = new Intent(signin.this, Home.class);
                          Common.currentuser = user;
                          startActivity(homeintent);
                          finish();
                      }
                      else
                      {
                          Toast.makeText(signin.this, "Wrong password..!!", Toast.LENGTH_SHORT).show();
                      }}
                      else
                      {
                          mDialog.dismiss();
                          Toast.makeText(signin.this,"User does not exists...!",Toast.LENGTH_SHORT).show();
                      }
                  }


                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
           }
       });


        ///FirebaseDatabase mDatabase = FirebaseDatabase.getInstance().getReference();
        ///mDatabase.child("user").child("sadman").setValue("admin");
    }
}