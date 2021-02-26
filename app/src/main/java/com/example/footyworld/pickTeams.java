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
import android.widget.Toast;

import com.example.footyworld.Squad.Squad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class pickTeams extends AppCompatActivity {

    DatabaseReference databaseSquads;
    DatabaseReference databasePlayerList;

    List<String> squadsFromDb;//creates a list that gets populated with db response

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_teams);

        final ListView listOfSquads = (ListView) findViewById(R.id.listForSquads);

        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");

        squadsFromDb = new ArrayList<>();

        databaseSquads.addValueEventListener(new ValueEventListener()
        {
                                                                                                                                //listens for data being changed@Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {                                                                       //creates snapshot of the database that has been referenced above
                squadsFromDb.clear();                                                                                            //makes sure list is empty as data persists and we dont want duplicates
                for (DataSnapshot squadsSnapshot : dataSnapshot.getChildren())                                                    //loops through snapshot
                {
                    Squad squad = squadsSnapshot.getValue(Squad.class);                                                             //creates a squad object based on position in loop
                    squadsFromDb.add(squad.getSquadName());                                                                                             //adds this squad to  a list of squads that wil populate the screen
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(pickTeams.this, android.R.layout.simple_list_item_1, squadsFromDb)
                    {
                        //overriding get view to keep list layout but change text color
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent){
                            //get list view item
                            View view = super.getView(position, convertView, parent);

                            //assign it a textview
                            TextView tv = (TextView) view.findViewById(android.R.id.text1);

                            // Set color
                            Color colour = new Color();
                            colour = Color.valueOf(0, 235, 27);

                            tv.setTextColor(-16711936 );

                            // Generate ListView Item using TextView
                            return view;
                        }
                    };
                    listOfSquads.setAdapter(adapter);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        listOfSquads.setClickable(true);
        listOfSquads.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String selection = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(pickTeams.this, AlgorithmActivity.class);
                intent.putExtra("UserSelection", selection);
                startActivity(intent);
            }
        });

    }
    // END OF ON CREATE







    //get users squad selection from above.
    // find this squad in db.
    // get the playerlist id from this squad.
    // use this to find the playerlist.
    // return playerlist as list of player objects


    }
