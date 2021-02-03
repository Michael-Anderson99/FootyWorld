package com.example.footyworld.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.footyworld.R;
import com.example.footyworld.Squad.Team;

import java.util.ArrayList;

public class TeamDisplay extends AppCompatActivity {


    //Team team2 = (Team) getIntent().getSerializableExtra("Team2");
    ListView listView1;
    ListView listView2;
    ArrayList<String> names1, names2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);

        Team team1 = (Team) this.getIntent().getSerializableExtra("Team1");



        listView1 = findViewById(R.id.team1);
        listView2 = findViewById(R.id.team2);



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team1.getDefenders());
     //   ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(TeamDisplay.this, android.R.layout.simple_list_item_1, team2.getDefenders());

        listView1.setAdapter(adapter1);
     //   listView2.setAdapter(adapter2);




    }
}