package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footyworld.Squad.Player;
import com.example.footyworld.Squad.Squad;
import com.example.footyworld.Squad.Team;
import com.example.footyworld.User.TeamDisplay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmActivity extends AppCompatActivity {

    //Firebase Ref
    DatabaseReference databaseSquads;
    public DatabaseReference databasePlayerList;

    //list for the data snapshot to be added to
    List<String> playersList;
    List<String> playersFromDb;
    ListView listView;
    ArrayList<Player> arrayList;
    Button button, editButton;
    Spinner makeTeamsSpinner, editPlayerListSpinner;
    List<String> remove = new ArrayList<>();

    //These Arraylists are used to calculate chemistry
    ArrayList<String> firstChoiceD = new ArrayList<>();
    ArrayList<String> secondChoiceD = new ArrayList<>();

    ArrayList<String> firstChoiceM = new ArrayList<>();
    ArrayList<String> secondChoiceM = new ArrayList<>();

    ArrayList<String> firstChoiceA = new ArrayList<>();
    ArrayList<String> secondChoiceA = new ArrayList<>();


    //POPUP
    private AlertDialog.Builder dlogBuilder;
    private AlertDialog dlog;
    private EditText userInput;
    private Spinner spinner;
    private Spinner spinner2;
    private Button addButton,popupDoneButton;

    //This var will be playerlist ID for the team that was clicked
    final String[] PLID = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);

        //passed data (clicked team)
        final String selection = getIntent().getStringExtra("UserSelection");

        //References for the Layout
        final TextView squadName = findViewById(R.id.textViewTeamName);
        squadName.setText(selection);
        listView = (ListView) findViewById(R.id.listViewTeam1);
        button = findViewById(R.id.SortButton);
        editButton = findViewById(R.id.EditButton);
        makeTeamsSpinner = findViewById(R.id.makeTeamsSpinner);
        editPlayerListSpinner = findViewById(R.id.editPlayerlistSpinner);

        //Firebase Var
        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");

        //data returned from db to here.
        playersFromDb = new ArrayList<>();
        arrayList = new ArrayList<>();

        //THIS BUTTON CALLS THE FUNCTION THAT PICKS TEAMS WITH CHEM
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ArrayList<Team> teams = new ArrayList<>();
                ArrayList<String> defenders, mids, attackers;
                String[] g = goalKeeper(arrayList);
                defenders = getDefenders(arrayList);
                mids = getMidfielders(arrayList);
                attackers = getAttackers(arrayList);
                Intent intent = new Intent(AlgorithmActivity.this, TeamDisplay.class);
                String selection = makeTeamsSpinner.getSelectedItem().toString();

                switch (selection)
                {
                    case "Pick Random Teams":
                        teams = makeTeamsSimpleSplit(g, defenders,mids,attackers);
                        intent.putExtra("team", teams);
                        startActivity(intent);
                        break;

                    case "Pick Chemistry Based Teams":
                        teams = makeTeamsChem(g,defenders,mids,attackers);
                        intent.putExtra("team", teams);
                        startActivity(intent);
                        break;

                    case "Build Standard Formation":
                        //buildstandardformation
                        break;
                }
            }
        });

        //EDIT LIST BUTTON. REMOVAL HAS BUGS AND IS STILL TO BE CMOPLETED. ALSO CREATES A POPUP WINDOW TO ADD PLAYERS
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String selection = editPlayerListSpinner.getSelectedItem().toString();
                if(selection.equals("Add A Player"))
                {
                    createNewPlayerPopup();

                }else if(selection.equals("Remove A Player"))
                {
                    System.out.println("");
                }
            }
        });

        //GET THE PLAYERLIST FOR THE SELECTED TEAM
        databaseSquads.addValueEventListener(new ValueEventListener()                                                               //creates a snapshot of the squads db
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot squadsSnapshot : dataSnapshot.getChildren())                                                    //loops through snapshot
                {
                    Squad squad = squadsSnapshot.getValue(Squad.class);                                                            //create squad object for all db entries
                    if (squad.getSquadName().equals(selection))                                                                     //check that the entry now being created is the selected one
                    {
                        PLID[0] = squad.getPlayerListId();
                    }//end if
                }//end for
            }//end on data change

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });//end of Squad DB listener

        //DISPLAY NAMES IN LISTVIEW. Updates everytime playerlist DB is changed
        databasePlayerList.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                playersFromDb.clear();
                if( dataSnapshot.child(PLID[0]).getValue() != null ) {
                    System.out.println("hello");
                    for (DataSnapshot child : dataSnapshot.child(PLID[0]).getChildren()) {
                        Player p = child.getValue(Player.class);
                        //System.out.println(p.getPlayerName()+"line 99");
                        playersFromDb.add(p.getPlayerName());
                        arrayList.add(p);
                    }

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AlgorithmActivity.this, android.R.layout.simple_list_item_1, playersFromDb) {
                        //overriding get view to keep list layout but change text color
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            //get list view item
                            View view = super.getView(position, convertView, parent);

                            TextView tv = (TextView) view.findViewById(android.R.id.text1);
                            tv.setTextColor(-16711936);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });

        squadName.setTextColor(-16776961 );
        squadName.setText(selection);
    }//END OF ON CREATE

    //METHODS USED IN ACTIVITY
    //popup menu that will allow the user to add a squad to their playerlist
    public void createNewPlayerPopup()
    {
        dlogBuilder = new AlertDialog.Builder(this);
        final View newPlayerPopup = getLayoutInflater().inflate(R.layout.popup, null);
        userInput = newPlayerPopup.findViewById(R.id.popupPlayerName);
        spinner = newPlayerPopup.findViewById(R.id.firstPosition);
        spinner2 = newPlayerPopup.findViewById(R.id.secondPosition);
        addButton = newPlayerPopup.findViewById(R.id.addPlayer);
        popupDoneButton = newPlayerPopup.findViewById(R.id.popupFinishedButton);

        dlogBuilder.setView(newPlayerPopup);
        dlog = dlogBuilder.create();
        dlog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dlog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        dlog.getWindow().setAttributes(layoutParams);

        //when button is clicked, new player object is created and added to database.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //take new player data from popup
                String playerName = userInput.getText().toString().trim();
                String position1 = spinner.getSelectedItem().toString();
                String position2 = spinner2.getSelectedItem().toString();

                Player p = new Player(playerName,position1,position2, PLID[0]);
                arrayList.add(p);
                databasePlayerList.child(PLID[0]).setValue(arrayList);
                userInput.setText("");
                arrayList.clear();
            }
        });

        popupDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlog.dismiss();
            }
        });
    }

    //gets the goalkeeper from the playerlist
    public String[] goalKeeper (ArrayList<Player> players)
    {
        System.out.println("methoddddddddddddddddddddddd");
        String gk1, gk2;
        String[] keepersFirstChoice = new String[10 ];
        String[] keepersSecChoice = new String[10];
        String[] keepersDecided = new String[2];
        int k = 0;
        int j = 0;

        for(int i = 0; i<players.size(); i++)
        {
            Player p = players.get(i);
            if(p.getPosition1().equals("Goalkeeper"))
            {
                keepersFirstChoice[j] = p.getPlayerName();
                System.out.println(p.getPlayerName()+" has gk first choice");
                j++;
            }
            else if (p.getPosition().equals("Goalkeeper"))
            {
                keepersSecChoice[k] = p.getPlayerName();
                System.out.println(p.getPlayerName()+" has gk second choice");
                k++;
            }
        }

        gk1 = keepersFirstChoice[0] ;
        gk2 = keepersFirstChoice[1];
        System.out.println("gk1 = "+gk1 + " gk2= " +gk2);
        keepersDecided[0] = gk1;
        keepersDecided[1] = gk2;

        return keepersDecided;
    }

    //gets defenders from the playerlist first and second choice
    public ArrayList<String> getDefenders(ArrayList<Player> players)
    {
        ArrayList<String> defenders = new ArrayList<>();

        for(int i = 0; i<players.size(); i++)
        {
            Player p = players.get(i);
            if(p.getPosition1().equals("Centre Defender") || p.getPosition1().equals("Wide Defender"))
            {
                defenders.add(p.getPlayerName());
                firstChoiceD.add(p.getPlayerName());
            }
            else if(p.getPosition().equals("Centre Defender") || p.getPosition().equals("Wide Defender"))
            {
                defenders.add(p.getPlayerName());
                secondChoiceD.add(p.getPlayerName());
            }
        }
        return defenders;
    }

    //gets first and second choice midfielders
    public ArrayList<String> getMidfielders(ArrayList<Player> players)
    {
        ArrayList<String> mids = new ArrayList<>();
        for(int i=0;i<players.size();i++)
        {
            Player p = players.get(i);
            if(p.getPosition1().equals("Midfielder") )
            {
                mids.add(p.getPlayerName());
                firstChoiceM.add(p.getPlayerName());
            }
            else if (p.getPosition().equals("Midfielder"))
            {
                mids.add(p.getPlayerName());
               secondChoiceM.add(p.getPlayerName());
            }
        }
        return mids;
    }

    //gets first and second choice attackers
    public ArrayList<String> getAttackers(ArrayList<Player> players)
    {
        ArrayList<String> attackers = new ArrayList<>();

        for(int i = 0; i<players.size(); i++)
        {
            Player p = players.get(i);

            if(p.getPosition1().equals("L. Winger") || p.getPosition1().equals("R. Winger"))
            {
                attackers.add(p.getPlayerName());
                firstChoiceA.add(p.getPlayerName());
            }
            else if (p.getPosition().equals("L. Winger") || p.getPosition().equals("R. Winger"))
            {
                attackers.add(p.getPlayerName());
                secondChoiceA.add(p.getPlayerName());
            }
        }

        return attackers;
    }

    //Quick team picker. Splits playerlist in half, doesnt try to build efficient team
    public ArrayList<Team> makeTeamsSimpleSplit(String[] g, ArrayList<String> defenders, ArrayList<String> mids, ArrayList<String> attackers)
    {
        ArrayList<Team> team = new ArrayList<>();
        Team t1 = new Team();
        Team t2 = new Team();
        String gk1 = "";
        String gk2 = "";
        String[] def1 = new String[10];
        String[] mid1 = new String[10];
        String[] atts1 = new String[10];
        String[] def2 = new String[10];
        String[] mid2 = new String[10];
        String[] atts2 = new String[10];


        for (int i=0; i<g.length;i++)
        {
            if(i%2==0)
            {
                gk1 = g[i];
            }
            else
            {
                gk2 = g[i];
            }
        }

        for(int i=0; i<defenders.size(); i++)
        {
            if(i%2 == 0)
            {
                def1[i] = defenders.get(i);
            }
            else
            {
                def2[i] = defenders.get(i);
            }
        }

        for(int i=0; i<mids.size(); i++)
        {
            if(i%2 == 0)
            {
                mid1[i] = mids.get(i);
            }
            else
            {
                mid2[i] = mids.get(i);
            }

        }

        for(int i=0; i<attackers.size(); i++)
        {
            if(i%2 == 0)
            {
                atts1[i] = attackers.get(i);
            }
            else
            {
                atts2[i] = attackers.get(i);
            }
        }

        if(gk1 != null)
        {
            if( !gk1.equals(""))
            {
                t1.setGk(gk1);
            }
        }

        t1.setDefenders(def1);
        t1.setMidfielders(mid1);
        t1.setAttackers(atts1);

        if (gk2 != null)
        {
            if(!gk1.equals(""))
            {
                t2.setGk(gk2);
            }
        }

        t2.setDefenders(def2);
        t2.setMidfielders(mid2);
        t2.setAttackers(atts2);
        team.add(t1);
        team.add(t2);

        return team;
    }

    //PICKS TEAMS BASED OFF CHEM
    public ArrayList<Team> makeTeamsChem(String[] gk, ArrayList<String> defenders, ArrayList<String> mids, ArrayList<String> attackers)
    {
        ArrayList<Team> team = new ArrayList<>();
        Team t1 = new Team();
        Team t2 = new Team();
        int t1Chem = t1.getChem(), t2Chem = t2.getChem();

        //players who will be defenders in the teams. based off their pref position.
        List<String> t1Defenders = new ArrayList<>();
        List<String> t2Defenders = new ArrayList<>();

        List<String> t1Mids = new ArrayList<>();
        List<String> t2Mids = new ArrayList<>();

        List<String> t1Atts = new ArrayList<>();
        List<String> t2Atts = new ArrayList<>();

        //Defence
        // players with defending as pref pos 1
        if(firstChoiceD.size() > secondChoiceD.size())
        {
            //Defence || for every defender who picks defending no1, split into two lists to be given back to the teams. each team will get two defenders.


            for(int i=0; i<firstChoiceD.size();i++)
            {
                if(i%2 == 0 && t1Defenders.size() < 2)
                {
                    t1Defenders.add(firstChoiceD.get(i));

                }
                else
                {
                    t2Defenders.add(firstChoiceD.get(i));

                }
            }

            //players with defending as pref pos 2
            for(int i=0; i<secondChoiceD.size();i++)
            {
                if(i%2 == 0 && t1Defenders.size() < 2)
                {
                    t1Defenders.add(secondChoiceD.get(i));

                }
                else if(t2Defenders.size()<2)
                {
                    t2Defenders.add(secondChoiceD.get(i));

                }
            }


        }

        //Midfield
        if(firstChoiceM.size()>secondChoiceM.size())//first choice
        {
            for(int i =0;i<firstChoiceM.size();i++)
            {
                if(i%2 == 0 && t1Mids.size() < 3)
                {
                    t1Mids.add(firstChoiceM.get(i));

                }
                else if (t2Mids.size() < 3)
                {
                    t2Mids.add(firstChoiceM.get(i));

                }
            }

            //second choice
            for(int i =0;i<secondChoiceM.size();i++)
            {
                if(i%2 == 0 && t1Mids.size() < 3)
                {
                    t1Mids.add(secondChoiceM.get(i));

                }
                else if (t2Mids.size() < 3)
                {
                    t2Mids.add(secondChoiceM.get(i));

                }
            }

        }

        //Attack
        if(firstChoiceA.size() > secondChoiceA.size())
        {
            for(int i =0;i<firstChoiceA.size();i++)
            {
                if(i%2==0 && t1Atts.size() < 1)
                {
                    t1Atts.add(firstChoiceA.get(i));
                }
                else if( t2Atts.size() < 1)
                {
                    t2Atts.add(firstChoiceA.get(i));
                }

            }
        }

        //build Standard formation ----- 1 def | 2 mid | 1 att
        t1 = buildStandardFormation(gk, t1Defenders,t1Mids,t1Atts);
        team.add(t1);
        t2 = buildStandardFormation(gk, t2Defenders, t2Mids, t2Atts);
        team.add(t2);

        return team;
    }

    //ORGANISES TEAM ( right amount of defenders, mids, attackers)
    public Team buildStandardFormation(String[] gk, List<String> def, List<String> mids, List<String> atts)
    {
        List<String> pickedD = new ArrayList<>(5);
        List<String> pickedM = new ArrayList<>(5);
        List<String> pickedA = new ArrayList<>(5);

        //1 def 2 mid 1 att.
        pickedD.add(def.get(0));

        pickedM.add(mids.get(0));
        pickedM.add(mids.get(1));

        pickedA.add(atts.get(0));

        Team team = new Team();
        if(gk[0] != null)
        {
            team.setGk(gk[0]);
        }
        else if(gk[1] != null)
        {
            team.setGk(gk[1]);
        }
        else
        {
            Toast.makeText(this, "NO GOALKEEPER", Toast.LENGTH_LONG).show();
        }
        team.setChemDefenders(Collections.singletonList(pickedD.get(0)));
        team.setChemMidfielders(pickedM);
        team.setChemAttackers(Collections.singletonList(pickedA.get(0)));

        return team;

    }

}//end of class

