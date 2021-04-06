package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.footyworld.Squad.Squad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class preGame extends AppCompatActivity
{

    DatabaseReference databaseSquads;
    DatabaseReference databasePlayerList;
    List<String> squadsFromDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game2);

        final ListView listSquads = (ListView) findViewById(R.id.listForSquads);
        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");

        squadsFromDb = new ArrayList<>();
        databaseSquads.addValueEventListener(new ValueEventListener()
        {
            //listens for data being changed@Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {          //creates snapshot of the database that has been referenced above
                squadsFromDb.clear();                                              //makes sure list is empty as data persists and we dont want duplicates
                for (DataSnapshot squadsSnapshot : dataSnapshot.getChildren())     //loops through snapshot
                {
                    Squad squad = squadsSnapshot.getValue(Squad.class);              //creates a squad object based on position in loop
                    squadsFromDb.add(squad.getSquadName());                          //adds this squad to  a list of squads that wil populate the screen
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(preGame.this, android.R.layout.simple_list_item_1, squadsFromDb);
                    listSquads.setAdapter(adapter);
                } }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }

        } );

        listSquads.setClickable(true);
        listSquads.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String selection = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(preGame.this, intstructionInput.class);
                intent.putExtra("UserSelection", selection);
                startActivity(intent);


    }//create



});}}   //class