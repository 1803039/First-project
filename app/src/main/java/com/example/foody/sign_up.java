package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foody.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sign_up extends AppCompatActivity {

    EditText editphone, editpassword, editname;
     Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editname = (EditText) findViewById(R.id.name);
        editpassword = (EditText) findViewById(R.id.password);
        editphone = (EditText) findViewById(R.id.phonenumber);

        btnsignup = (Button) findViewById(R.id.signupbutton);

        FirebaseDatabase  mdatabase = FirebaseDatabase.getInstance("https://foody-app-194b1-default-rtdb.firebaseio.com");
        final DatabaseReference t_user =  mdatabase.getReference("User");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(sign_up.this);
                mDialog.setMessage("Please wait..");
                mDialog.show();

                t_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(editphone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(sign_up.this,"Phone number already exists..",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            user user = new user(editname.getText().toString(),editpassword.getText().toString());
                            t_user.child(editphone.getText().toString()).setValue(user);
                            Toast.makeText(sign_up.this,"Sign up successful !!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}