package com.example.footyworld.Squad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.footyworld.R;

import java.util.List;

public class SquadsList extends ArrayAdapter<Squad> {
    public Activity context;
    private List<Squad> squadList;

    public SquadsList (Activity context, List<Squad> squadList)
    {
        super(context, R.layout.activity_pick_teams, squadList);
        this.context = context;
        this.squadList = squadList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_pick_teams, null,true);

        TextView textViewSName = (TextView) listViewItem.findViewById(R.id.textViewSquadName);
        TextView titletext = (TextView) listViewItem.findViewById(R.id.titleText);

        Squad squad = squadList.get(position);
        textViewSName.setText(squad.getSquadName());
        titletext.setText("");

        return listViewItem;
    }
}
