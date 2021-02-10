package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.footyworld.User.Statistics;
import com.example.footyworld.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewStats extends AppCompatActivity {

    TextView headerText, goalsText, assistText, savesText;
    Button b1;
    String name;
    String id;

    DatabaseReference statsReference,userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        headerText = findViewById(R.id.headerTextView);
        goalsText = findViewById(R.id.goalsTextView);
        assistText = findViewById(R.id.assistsTextView);
        savesText = findViewById(R.id.savesTextView);
        b1 = findViewById(R.id.but1);

        statsReference = FirebaseDatabase.getInstance().getReference("statistics");
        userReference = FirebaseDatabase.getInstance().getReference("users");



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = headerText.getText().toString();
                String id = getUser();

            }
        });


    }

    public String getUser()
    {
        userReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    User user = userSnapshot.getValue(User.class);

                    if(user.getUserName().equals(name))
                    {
                        id = user.getUserId();
                        getStats(id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        return id;
    }

    public void getStats(final String id)
    {
        statsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot statsSnapshot: dataSnapshot.getChildren())
                {

                    //////
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}