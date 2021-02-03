package com.example.footyworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.footyworld.Squad.Player;
import com.example.footyworld.Squad.Squad;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateSquad extends AppCompatActivity {


    DatabaseReference databaseSquads;
    DatabaseReference databasePlayerList;
    Button addButton;
    Button CreateSquadButton;
    EditText squadName;
    EditText userInput;
    Spinner spinner;
    Spinner spinner2;
    List myPlayers;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_squad);

        //database references
        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");

        //UI elements
        squadName = findViewById(R.id.squadName);
        userInput = findViewById(R.id.playerName);
        addButton = findViewById(R.id.button);
        CreateSquadButton = findViewById(R.id.button2);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);

        //Variables needed for activity
        final ArrayList<Player> myList = new ArrayList<>();
        final String playerListId = databasePlayerList.child("playerList").push().getKey();
        //String playerListId = databaseSquads.push().getKey();

        addButton.setOnClickListener(new View.OnClickListener()
        {
            //code that runs when the add button is clicked
            @Override
            public void onClick(View view)
            {
                String playerName = userInput.getText().toString().trim();
                String position1 = spinner.getSelectedItem().toString();
                String position2 = spinner2.getSelectedItem().toString();

                Player player = new Player(playerName, position1, position2,playerListId);
                myList.add(player);

                databasePlayerList.child(playerListId).setValue(myList);
            }
        });

        CreateSquadButton.setOnClickListener(new View.OnClickListener() {
            //code that runs when the create squad button is clicked
            @Override
            public void onClick(View view)
            {
                defineSquad(playerListId);

            }
        });

    }//END OF ONCREATE()

    //insert the squad
    public void defineSquad(String playerListId)
    {
        String teamName = squadName.getText().toString().trim();
        Squad squad = new Squad(teamName, playerListId);

        databaseSquads.child(teamName).setValue(squad);
    }
}