package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footyworld.User.Statistics;
import com.example.footyworld.User.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StatsSection extends AppCompatActivity {

    DatabaseReference userReference;
    DatabaseReference statsReference;

    TextView UserInput, GoodPlayMomentsInput;
    EditText GoalsInput, AssistsInput, SavesInput;
    Button addStatsButton,viewStatsButton;

    User dbUser;
    public String id;
    int z = 1;
    Statistics madeStats;
    String statsID;


    //POPUP
    private AlertDialog.Builder dlogBuilder;
    private AlertDialog dlog;
    EditText goals, assists, saves, gpm;
    Button submitBtn;

    ArrayList<Statistics> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_section);

        //initialize UI elements
        UserInput = findViewById(R.id.UserNameInput);


        addStatsButton = findViewById(R.id.addStatsButton);
        viewStatsButton = findViewById(R.id.viewstats_button);

        list = new ArrayList<>();

        //database references for connecting to Firebase
        userReference = FirebaseDatabase.getInstance().getReference("users");
        statsReference = FirebaseDatabase.getInstance().getReference("statistics");

        addStatsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String userName = UserInput.getText().toString();

                //make sure user has typed a name and make sure user exists
                if(userName.equals(""))
                {
                    Toast t = Toast.makeText(StatsSection.this, "PLEASE ENTER YOUR USERNAME", Toast.LENGTH_LONG);
                    t.show();
                }
                else
                {
                    userReference.addValueEventListener(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            for(DataSnapshot userSnapshot: dataSnapshot.getChildren())
                            {
                                User u = userSnapshot.getValue(User.class);
                                if (u.getUserName().equals(userName))
                                {
                                    createStatPopup(userName);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });
                }
            }//end on click
        });//end listener

        viewStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsSection.this, ViewStats.class);
                startActivity(intent);
            }
        });

    }//end oncreate

    public void createStatPopup(String username)
    {
        dlogBuilder = new AlertDialog.Builder(this);
        final View newStatsPopup = getLayoutInflater().inflate(R.layout.stats_popup, null);
        goals = newStatsPopup.findViewById(R.id.goals_input);
        assists = newStatsPopup.findViewById(R.id.assists_input);
        saves = newStatsPopup.findViewById(R.id.saves_input);
        gpm = newStatsPopup.findViewById(R.id.goodPlay_input);
        submitBtn = newStatsPopup.findViewById(R.id.submitButton);

        dlogBuilder.setView(newStatsPopup);
        dlog = dlogBuilder.create();
        dlog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dlog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dlog.getWindow().setAttributes(layoutParams);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String g = goals.getText().toString();
                int goalsi = Integer.parseInt(g);

                String a = assists.getText().toString();
                int assistsi = Integer.parseInt(a);

                String s = saves.getText().toString();
                int savesi = Integer.parseInt(s);

                String goodPlay = gpm.getText().toString();

                // from here need to find out what old methods work and send to db
            }
        });
    }



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