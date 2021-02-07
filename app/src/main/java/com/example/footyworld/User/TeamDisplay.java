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
    String[] arr = new  String[20];

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

        for(int i=0; i<team.get(0).getAttackers().length; i++)
        {
             arr = team.get(0).getAttackers();

            for(i = 0; i<arr.length; i++)
            {
                if(arr[i] != null)
                {
                    team1.add(arr[i]);
                }
            }
        }
        for(int i=0; i<team.get(0).getMidfielders().length; i++)
        {
            arr = team.get(0).getMidfielders();

            for(i = 0; i<arr.length; i++)
            {
                if(arr[i] != null)
                {
                    team1.add(arr[i]);
                }
            }
        }
        for(int i=0; i<team.get(0).getDefenders().length; i++)
        {
            arr = team.get(0).getDefenders();

            for(i = 0; i<arr.length; i++)
            {
                if(arr[i] != null)
                {
                    team1.add(arr[i]);
                }
            }
        }


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team1);
     // ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team2.getDefenders());

        listView1.setAdapter(adapter1);
     // listView2.setAdapter(adapter2);




    }
}