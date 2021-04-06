package com.example.footyworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.footyworld.User.Instruction;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class intstructionInput extends AppCompatActivity {

    TextView tv = findViewById(R.id.header);
    TextView tv2 = findViewById(R.id.input);
    Button btn = findViewById(R.id.btn);

    DatabaseReference databaseSquads;
    DatabaseReference databasePlayerList;
    DatabaseReference databaseInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intstruction_input);

        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");
        final String dbInstruct = databaseInstruction.child(selection).push().getKey();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player = tv.toString();
                String inp = tv2.toString();
                Instruction i = new Instruction();
                i.setPlayer(player);
                i.setText(inp);
                databaseInstruction.child(dbInstruct).setValue(i);
            }
        });
    }

    final String selection = getIntent().getStringExtra("UserSelection");




}