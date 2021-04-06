package com.example.footyworld.User;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.footyworld.R;
import com.example.footyworld.Squad.Team;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TeamDisplay extends AppCompatActivity {

    ListView listView1;
    ListView listView2;
    ArrayList<String> names1, names2;
    List<String> team1 = new ArrayList<>();
    List<String> team2 = new ArrayList<>();
    ArrayList<Team> team;
    Team teamobj;
    String[] arr1 = new  String[20];
    String[] arr2 = new String[20];
    Button fair, unfair;
    DatabaseReference databaseSquads;
    public DatabaseReference databasePlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);

        Bundle b = new Bundle();
        b = this.getIntent().getExtras();

        if(b!=null)
        {
            team = (ArrayList<Team>) b.get("team");
        }

        fair = findViewById(R.id.fairButton);
        unfair = findViewById(R.id.unfairButton);
        listView1 = findViewById(R.id.team1);
        listView2 = findViewById(R.id.team2);
        //Firebase Var
        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");


        //arr1 left side list view
        for(int i=0; i<team.get(0).getChemAttackers().size(); i++)
        {
             arr1 = team.get(0).getChemAttackers().toArray(arr1);

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }
        for(int i = 0; i<team.get(0).getListMidfielders().size(); i++)
        {
            arr1 = (String[]) team.get(0).getListMidfielders().toArray(arr1);

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }
        for(int i = 0; i<team.get(0).getListDefenders().size(); i++)
        {
            arr1 = (String[]) team.get(0).getListDefenders().toArray(arr1);

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }
        team1.add(team.get(0).getGk());

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //arr2 right side list view
        for(int i=0; i<team.get(1).getAttackers().length; i++)
        {
            arr2 = team.get(1).getAttackers();

            for(i = 0; i<arr2.length; i++)
            {
                if(arr2[i] != null)
                {
                    team2.add(arr2[i]);
                }
            }
        }
        for(int i=0; i<team.get(1).getMidfielders().length; i++)
        {
            arr2 = team.get(1).getMidfielders();

            for(i = 0; i<arr2.length; i++)
            {
                if(arr2[i] != null)
                {
                    team2.add(arr2[i]);
                }
            }
        }
        for(int i=0; i<team.get(1).getDefenders().length; i++)
        {
            arr2 = team.get(1).getDefenders();

            for(i = 0; i<arr2.length; i++)
            {
                if(arr2[i] != null)
                {
                    team2.add(arr2[i]);
                }
            }
        }


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team1){
            //overriding get view to keep list layout but change text color
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //get list view item
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.RED);


                return view;
            }
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team2){
           //overriding get view to keep list layout but change text color
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               //get list view item
               View view = super.getView(position, convertView, parent);
               TextView tv = (TextView) view.findViewById(android.R.id.text1);
               tv.setTextColor(Color.RED);
               return view;
           }
       };

        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        fair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseSquads.child("fairTeams").setValue(team1);
                databaseSquads.child("fairTeams").setValue(team2);


            }
        });

        unfair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseSquads.child("fairTeams").setValue(team1);
                databaseSquads.child("fairTeams").setValue(team2);
            }
        });



    }
}