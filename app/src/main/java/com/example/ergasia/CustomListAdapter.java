package com.example.ergasia;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Event> events;
    ArrayList<Integer> shortlistedIDs;
    public ArrayList<Event> favTemp = new ArrayList<>();

    private SharedPreferences myPreferences;

    private SharedPreferences.Editor myEditor;



    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> events) {
        super(context, resource, events);

        this.context = context;
        this.events = events;

        initializePreferences();
        shortlistedIDs = new ArrayList<>();
    }

    private void initializePreferences() {
        myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        myEditor = myPreferences.edit();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.listview_row, null, true);
        TextView txtTitle = convertView.findViewById(R.id.txtEventTitlerow);
        TextView txtType = convertView.findViewById(R.id.txtEventTyperow);
        TextView txtDate = convertView.findViewById(R.id.txtDaterow);
        ImageView imgEvent = convertView.findViewById(R.id.imgEventrow);
        ImageView imgType = convertView.findViewById(R.id.imgEventTyperow);
        ImageView imgHeart = convertView.findViewById(R.id.imgHeartrow);

        txtTitle.setText(events.get(position).getTitle());
        txtType.setText(events.get(position).getType());
        txtDate.setText(events.get(position).getDate());
        imgType.setImageResource(events.get(position).getIcon());
        imgEvent.setImageResource(events.get(position).getImage());

        if(events.get(position).isShortlist()){
            imgHeart.setImageResource(R.drawable.ic_hearfilled);
        }
        else {
            imgHeart.setImageResource(R.drawable.ic_heartoutline);
        }

        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (events.get(position).isShortlist()) {
                    imgHeart.setImageResource(R.drawable.ic_heartoutline);
                    events.get(position).setShortlist(false);
                    FavArrayList.favEvents.remove(events.get(position));
                    shortlistedIDs.remove((Integer) events.get(position).getID());

                } else {
                    Log.d("lol", "lol"+events.get(position));
                    if(events.get(position) == null){
                        Log.d("lol", "lol1");

                    }else{
                        FavArrayList.favEvents.add(events.get(position));
                        Log.d("lol", "lol2");
                    }
                    imgHeart.setImageResource(R.drawable.ic_hearfilled);
                    events.get(position).setShortlist(true);
                    shortlistedIDs.add(events.get(position).getID());
                }
                Gson gson = new Gson();
                String json2 = gson.toJson(shortlistedIDs);

                myEditor.putString(ProgramActivity.SHORTLISTED_KEY, json2);
                myEditor.apply();
            }
        });

        return convertView;
    }
}
