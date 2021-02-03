package com.example.footyworld;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footyworld.User.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateProfile extends AppCompatActivity
 {
        DatabaseReference databaseUsers;
        EditText textUserName;
        EditText textPassword;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_create_profile);

         databaseUsers = FirebaseDatabase.getInstance().getReference("users");
         textUserName = findViewById(R.id.userName);
         textPassword = findViewById(R.id.userPassword);

         Button b1 = (findViewById(R.id.b1));
         b1.setOnClickListener((new View.OnClickListener()
         {
             @Override
             public void onClick(View view)
             {
                 defineUser();
             }
         }));

     }

     public void defineUser()
     {
         String userName = textUserName.getText().toString().trim();
         String password = textPassword.getText().toString().trim();
         String userId = databaseUsers.push().getKey();
         User user = new User(userId, userName, password);

         databaseUsers.child(userId).setValue(user);
         Toast.makeText(this, "added", Toast.LENGTH_LONG).show();
     }


 }


