package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.footyworld.User.Statistics;
import com.example.footyworld.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StatsSection extends AppCompatActivity {

    DatabaseReference userReference;
    DatabaseReference statsReference;

    TextView UserInput, GoodPlayMomentsInput;
    EditText GoalsInput, AssistsInput, SavesInput;
    Button submitButton,searchButton;

    User dbUser;
    public String id;
    int z = 1;
    Statistics madeStats;
    String statsID;

    ArrayList<Statistics> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_section);

        //initialize UI elements
        UserInput = findViewById(R.id.UserNameInput);

        GoalsInput = findViewById(R.id.goalsInput);
        AssistsInput = findViewById(R.id.AssistsInput);
        SavesInput = findViewById(R.id.SavesInput);
        GoodPlayMomentsInput = findViewById(R.id.gpmInput);
        GoodPlayMomentsInput.setText("");
        submitButton = findViewById(R.id.submitButton);
        searchButton = findViewById(R.id.searchButton);

        list = new ArrayList<>();

        //database references for connecting to Firebase
        userReference = FirebaseDatabase.getInstance().getReference("users");
        statsReference = FirebaseDatabase.getInstance().getReference("statistics");

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String userName = UserInput.getText().toString();
                getUser(userName);
            }//end on click
        });//end listener

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StatsSection.this, ViewStats.class);
                startActivity(intent);
            }
        });

    }//end oncreate

    public void statListInit(final Statistics stats)
    {
        list.clear();
        statsReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot statsSnapshot : dataSnapshot.getChildren())
                {
                    list.add(statsSnapshot.child(id).getValue(Statistics.class));
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        list.add(stats);
        statsReference.child(id).setValue(list);
    }


    public void getUser(final String userName)
    {
        userReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren())
                {
                    dbUser = userSnapshot.getValue(User.class);

                    if(dbUser.userName.equals(userName))
                    {
                        id = dbUser.getUserId();
                        System.out.println("I found user " +userName+ ". His ID is " + id);
                        z = 2;
                        makeStats();
                    }//end if
                }

                if(z==1)
                {
                    System.out.println("Sorry We could not find your userID");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });//endlistener

    }//end method

    public void makeStats()
    {
       int g = Integer.parseInt(GoalsInput.getText().toString());
       int a = Integer.parseInt(AssistsInput.getText().toString());
       int s = Integer.parseInt(SavesInput.getText().toString());
       String gpm = GoodPlayMomentsInput.getText().toString();

       Date c = Calendar.getInstance().getTime();
       System.out.println("Current time => " + c);

       Statistics stats = new Statistics(id, c, g, a, s, gpm);
       System.out.println("goals " + stats.getGoals());
       System.out.println("assists " + stats.getAssists());
       System.out.println("saves " + stats.getSaves());
       System.out.println("gpm " + stats.getGooodPlayMoments());
       System.out.println(stats.getUserID()+ "  this is from make stats");

       statListInit(stats);


    }


}//end class