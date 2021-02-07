package com.example.footyworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);

        //passed data (clicked team)
        final String selection = getIntent().getStringExtra("UserSelection");

       //This var will be playerlist ID for the team that was clicked
        final String[] PLID = new String[1];

        //References for the Layout
        final TextView squadName = findViewById(R.id.textViewTeamName);
        squadName.setText(selection);

        listView = (ListView) findViewById(R.id.listViewTeam1);
        button = findViewById(R.id.SortButton);


        //Firebase Var
        databaseSquads = FirebaseDatabase.getInstance().getReference("squads");
        databasePlayerList = FirebaseDatabase.getInstance().getReference("playerList");

        //data returned from db to here.
        playersFromDb = new ArrayList<>();
        arrayList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ArrayList<Team> teams = new ArrayList<>();
                ArrayList<String> defenders, mids, attackers;
                String[] g = goalKeeper(arrayList);
                defenders = getDefenders(arrayList);
                mids = getMidfielders(arrayList);
                attackers = getAttackers(arrayList);

                teams = makeTeams(defenders,mids,attackers);

                Intent intent = new Intent(AlgorithmActivity.this, TeamDisplay.class);
                intent.putExtra("team", teams);

               // intent.putExtra("Team2", teams.get(1));
                startActivity(intent);


                System.out.println(g[0] + "line 73" + g[1]);
            }
        });

        //get the playerlist id for selected team
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


        databasePlayerList.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if( dataSnapshot.child(PLID[0]).getValue() != null )
                {
                    System.out.println("hello");
                   for( DataSnapshot child : dataSnapshot.child(PLID[0]).getChildren())
                   {
                        Player p = child.getValue(Player.class);
                        System.out.println(p.getPlayerName()+"line 99");
                        playersFromDb.add(p.getPlayerName());
                        arrayList.add(p);
                   }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AlgorithmActivity.this, android.R.layout.simple_list_item_1, playersFromDb);
                    listView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        squadName.setText(selection);


    }//end of on create

    public String[] goalKeeper (ArrayList<Player> players)
    {
        System.out.println("methoddddddddddddddddddddddd");
        String gk1, gk2;
        String[] keepersFirstChoice = new String[5];
        String[] keepersSecChoice = new String[5];
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

    public ArrayList<String> getDefenders(ArrayList<Player> players)
    {
        ArrayList<String> defenders = new ArrayList<>();

        for(int i = 0; i<players.size(); i++)
        {
            Player p = players.get(i);
            if(p.getPosition1().equals("Centre Defender") || p.getPosition1().equals("Wide Defender"))
            {
                defenders.add(p.getPlayerName());
            }
            else if(p.getPosition().equals("Centre Defender") || p.getPosition().equals("Wide Defender"))
            {
                defenders.add(p.getPlayerName());
            }
        }
        return defenders;
    }

    public ArrayList<String> getMidfielders(ArrayList<Player> players)
    {
        ArrayList<String> mids = new ArrayList<>();
        for(int i=0;i<players.size();i++)
        {
            Player p = players.get(i);
            if(p.getPosition1().equals("Midfielder") || p.getPosition().equals("Midfielder"))
            {
                mids.add(p.getPlayerName());
            }
        }
        return mids;
    }

    public ArrayList<String> getAttackers(ArrayList<Player> players)
    {
        ArrayList<String> attackers = new ArrayList<>();

        for(int i = 0; i<players.size(); i++)
        {
            Player p = players.get(i);

            if(p.getPosition1().equals("L. Winger") || p.getPosition1().equals("R. Winger"))
            {
                attackers.add(p.getPlayerName());
            }
            else if (p.getPosition().equals("L. Winger") || p.getPosition().equals("R. Winger"))
            {
                attackers.add(p.getPlayerName());
            }
        }

        return attackers;
    }

    public ArrayList<Team> makeTeams(ArrayList<String> defenders, ArrayList<String> mids, ArrayList<String> attackers)
    {
        ArrayList<Team> team = new ArrayList<>();
        Team t1 = new Team();
        Team t2 = new Team();
        String[] def1 = new String[10];
        String[] mid1 = new String[10];
        String[] atts1 = new String[10];
        String[] def2 = new String[10];
        String[] mid2 = new String[10];
        String[] atts2 = new String[10];

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

        t1.setDefenders(def1);
        t1.setMidfielders(mid1);
        t1.setAttackers(atts1);
        t2.setDefenders(def2);
        t2.setMidfielders(mid2);
        t2.setAttackers(atts2);
        team.add(t1);
        team.add(t2);

        return team;
    }

}//end of class

