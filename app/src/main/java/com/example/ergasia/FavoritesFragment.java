package com.example.ergasia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    ListView listView;
    ArrayList<Event> events;
    ArrayList<Integer> shortlistedIDs;
    CustomListAdapter adapter;
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    public static final String EVENT_KEY = "event selected";
    public static final String SHORTLISTED_KEY = "the shortlist";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        listView = view.findViewById(R.id.listview2);

        initializePreferences(); // Initialize preferences first
        initializeData(); // Initialize data after preferences are initialized

        adapter = new CustomListAdapter(getActivity(), R.layout.listview_row, FavArrayList.favEvents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(position);
            }
        });
        return view;
    }

    private void initializePreferences() {
        myPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        myEditor = myPreferences.edit();
    }

    private void openActivity(int position) {
        Intent i = new Intent(getActivity(), list_detailed.class);
        Gson gson = new Gson();
        String myJson = gson.toJson(events.get(position));
        i.putExtra(EVENT_KEY, myJson);
        startActivity(i);
    }

    private void initializeData() {
        events = new ArrayList<>();

        events.add(new Event(getString(R.string.paralel1), "2022-09-22", "Παράλληλη Εκδ.", "20:00", "Κιν/φος Ερμής", getString(R.string.paralel1), getString(R.string.disclaimer2), R.drawable.paralel, R.drawable.ic_paralel));

        events.add(new Event(getString(R.string.provoli1), "2022-09-21", "Προβολή", "16:20", "Θέατρο Απόλλων", getString(R.string.provolidet1), getString(R.string.disclaimer1), R.drawable.provoli1, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.provoli2), "2022-09-21", "Προβολή", "18:15", "Θέατρο Απόλλων", getString(R.string.provolidet2), getString(R.string.disclaimer1), R.drawable.provoli2, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.provoli3), "2022-09-21", "Προβολή", "19:30", "Θέατρο Απόλλων", getString(R.string.provolidet3), getString(R.string.disclaimer1), R.drawable.provoli3, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.provoli4), "2022-09-22", "Προβολή", "10:00", "Θέατρο Απόλλων", getString(R.string.provolidet4), getString(R.string.disclaimer1), R.drawable.provoli4, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.provoli5), "2022-09-22", "Προβολή", "18:00", "Θέατρο Απόλλων", getString(R.string.provolidet5), getString(R.string.disclaimer1), R.drawable.provoli5, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.provoli6), "2022-09-22", "Προβολή", "10:00", "Παλλάς Κιν/φος", getString(R.string.provolidet6), getString(R.string.disclaimer1), R.drawable.provoli6, R.drawable.ic_provoli));
        events.add(new Event(getString(R.string.agora1), "2022-09-22", "Αγορά", "09:30", "Πνευμ. Κέντρο", getString(R.string.agoradet1), getString(R.string.disclaimer2), R.drawable.agora1, R.drawable.ic_agora));
        events.add(new Event(getString(R.string.agora2), "2022-09-21", "Αγορά", "10:00", "Παν. Αιγαίου", getString(R.string.agoradet2), getString(R.string.disclaimer3), R.drawable.loc_panepistimio, R.drawable.ic_agora));
        events.add(new Event(getString(R.string.agora3), "2022-09-21", "Αγορά", "17:00", "Πνευμ. Κέντρο", getString(R.string.agoradet3), getString(R.string.disclaimer4), R.drawable.agora3, R.drawable.ic_agora));
        events.add(new Event(getString(R.string.agora4), "2022-09-22", "Αγορά", "17:00", "Πνευμ. Κέντρο", getString(R.string.agoradet4), getString(R.string.disclaimer2), R.drawable.agora4, R.drawable.ic_agora));
        events.add(new Event(getString(R.string.educational1), "2022-09-22", "Εκπαιδ. Προγ.", "17:00", "Πνευμ. Κέντρο", getString(R.string.educationaldet1), getString(R.string.disclaimer3), R.drawable.educational1, R.drawable.ic_educational));
        events.add(new Event(getString(R.string.educational2), "2022-09-22", "Εκπαιδ.Προγ.", "09:00", "ΚΑΠΗ Ερμ/λης", getString(R.string.educationaldet2), getString(R.string.disclaimer3), R.drawable.educational2, R.drawable.ic_educational));

        loadShortlisted();

        Gson gson = new Gson();
        String json = myPreferences.getString(SHORTLISTED_KEY, null);
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        shortlistedIDs = gson.fromJson(json, type);

        if (shortlistedIDs == null) {
            shortlistedIDs = new ArrayList<>();
        } else {
            markShortlisted();
        }
    }

    private void markShortlisted() {
        for (int i = 0; i < shortlistedIDs.size(); i++) {
            for (int j = 0; j < events.size(); j++) {
                if (events.get(j).getID() == shortlistedIDs.get(i)) {
                    events.get(j).setShortlist(true);
                    saveShortlisted();
                }
            }
        }
    }

    private void saveShortlisted() {
        Gson gson = new Gson();
        String json = gson.toJson(shortlistedIDs);
        myEditor.putString(SHORTLISTED_KEY, json);
        myEditor.apply();
    }

    private void loadShortlisted() {
        String savedShortlist = myPreferences.getString(SHORTLISTED_KEY, "");

        if (savedShortlist.length() == 0) {
            shortlistedIDs = new ArrayList<>();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            shortlistedIDs = gson.fromJson(savedShortlist, type);
        }
    }
}
