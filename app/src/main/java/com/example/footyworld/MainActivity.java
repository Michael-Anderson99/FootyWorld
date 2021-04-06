package com.example.footyworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

 import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.footyworld.CreateProfile;
import com.example.footyworld.CreateSquad;
//import com.example.footyworld.AppDatabase;
import com.example.footyworld.R;

        public class MainActivity extends AppCompatActivity
        {

            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                //attach listeners to home screen tabs
                TextView profileTab =  findViewById(R.id.profileTab);
                profileTab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openProfileActivity();
                    }
                });

                TextView squadTab =  findViewById(R.id.squadTab);
                squadTab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSquadActivity();
                    }
                });

                TextView pickTeams =  findViewById(R.id.pickTeams);
                pickTeams.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openPickTeamsActivity();
                    }
                });

                TextView preGame = findViewById(R.id.preGameOrg);
                preGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openPreGame();
                    }
                });

                TextView statsSection = findViewById(R.id.statstab);
                statsSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openStatsActivity();
                    }
                });



            }

            //Activities called by clicking the home screen tabs
            public void openProfileActivity()
            {

                Intent intent = new Intent(this, CreateProfile.class);
                startActivity(intent);
            }


            public void openSquadActivity()
            {
                Intent intent = new Intent(this, CreateSquad.class);
                startActivity(intent);

            }

            public void openPickTeamsActivity()
            {
                Intent intent = new Intent(this, pickTeams.class);
                startActivity(intent);

            }
            public void openPreGame(){
                Intent intent = new Intent(this, preGame.class);
                startActivity(intent);

        }

            public void openStatsActivity()
            {
                Intent intent = new Intent(this, StatsSection.class);
                startActivity(intent);
            }

        }