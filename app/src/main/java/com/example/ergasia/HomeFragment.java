package com.example.ergasia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class HomeFragment extends Fragment {
    Button btnAboutUs;
    Button btnProgram;
    Button btnContact;
    Button btnSponsors;

    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(null);

        btnAboutUs = view.findViewById(R.id.btn_aboutus);
        btnProgram = view.findViewById(R.id.btn_program);
        btnContact = view.findViewById(R.id.btn_contact);
        btnSponsors = view.findViewById(R.id.btn_sponsors);

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutUsActivity();
            }
        });

        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProgramActivity();
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactActivity();
            }
        });

        btnSponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSponsorsActivity();
            }
        });


        return view;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_profil:
                Toast.makeText(getActivity(), "Επιτυχής σύνδεση", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_language:
                Toast.makeText(getActivity(), "Επιτυχής αλλαγή γλώσσας", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_terms:
                Toast.makeText(getActivity(),"Όροι και προηποθέσεις χρήσης", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_signout:
                Toast.makeText(getActivity(), "Επιτυχής αποσύνδεση", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    public void openAboutUsActivity(){
        Intent intent = new Intent(getActivity(), AboutUsActivity.class);
        startActivity(intent);
    }

    public void openProgramActivity(){
        Intent intent = new Intent(getActivity(), ProgramActivity.class);
        startActivity(intent);
    }
    public void openContactActivity(){
        Intent intent = new Intent(getActivity(), ContactActivity.class);
        startActivity(intent);
    }
    public void openSponsorsActivity(){
        Intent intent = new Intent(getActivity(), SponsorsActivity.class);
        startActivity(intent);
    }

}