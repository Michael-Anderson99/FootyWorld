package com.example.footyworld.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.footyworld.R;
import com.example.footyworld.Squad.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamDisplay extends AppCompatActivity {

    ListView listView1;
    ListView listView2;
    ArrayList<String> names1, names2;
    List<String> team1 = new ArrayList<>();
    List<String> team2 = new ArrayList<>();
    ArrayList<Team> team;
    String[] arr1 = new  String[20];
    String[] arr2 = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);

        Bundle b = new Bundle();
        b = this.getIntent().getExtras();

        if(b!=null)
        {
            team = (ArrayList) b.get("team");
        }

        listView1 = findViewById(R.id.team1);
        listView2 = findViewById(R.id.team2);

        //arr1 left side list view
        for(int i=0; i<team.get(0).getAttackers().length; i++)
        {
             arr1 = team.get(0).getAttackers();

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }
        for(int i=0; i<team.get(0).getMidfielders().length; i++)
        {
            arr1 = team.get(0).getMidfielders();

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }
        for(int i=0; i<team.get(0).getDefenders().length; i++)
        {
            arr1 = team.get(0).getDefenders();

            for(i = 0; i<arr1.length; i++)
            {
                if(arr1[i] != null)
                {
                    team1.add(arr1[i]);
                }
            }
        }

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


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team2);

        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);




    }
}