package com.example.ergasia;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class list_detailed extends AppCompatActivity {

    TextView dtltxtEventTitle;
    TextView dtltxtEventType;
    TextView dtltxtLocation;
    TextView dtltxxTime;
    TextView dtltxtDisclaimer;
    TextView dtltxtEventDetails;

    ShapeableImageView dtlimgEvent;
    ImageView dtlimgTime;
    ImageButton dtlbtnBack;
    Button dtlbtnShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detailed);

        initializeViews();

        Intent i = getIntent();
        Gson gson = new Gson();
        String eventFromIntent = getIntent().getStringExtra(ProgramActivity.EVENT_KEY);
        Event event = gson.fromJson(getIntent().getStringExtra("event selected"), Event.class);


        dtltxtEventTitle.setText(event.getTitle());
        dtltxtLocation.setText(event.getLocation());
        dtltxxTime.setText(event.getTime());
        dtltxtEventType.setText(event.getType());
        dtltxtEventDetails.setText(event.getDetails());
        dtltxtEventDetails.setText(event.getDetails());
        dtlimgEvent.setImageResource(event.getImage());

        dtlbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dtlbtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(dtltxtEventTitle.getText().toString());
            }
        });
    }

    private void shareData(String dtltxtEventTitle) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        String strEventTitle = String.valueOf(dtltxtEventTitle);

        intent.putExtra(Intent.EXTRA_TEXT, "Your friend sent you an AnimaSyros event: " + strEventTitle);
        intent.putExtra(Intent.EXTRA_SUBJECT, "AnimaSyros Event");

        startActivity(Intent.createChooser(intent, "Choose apropriate app"));
    }

    private void initializeViews() {

        dtlimgEvent = findViewById(R.id.imgEventDet);
        dtltxtEventDetails = findViewById(R.id.txtEventDetailsDet);
        dtltxtEventType = findViewById(R.id.txtTypeDet);
        dtltxtLocation = findViewById(R.id.txtLocationDet);
        dtltxtEventTitle = findViewById(R.id.txtEventTitleDet);
        dtltxtDisclaimer = findViewById(R.id.txtDisclaimerDet);
        dtltxxTime = findViewById(R.id.txtTimeDet);
        dtlbtnBack = findViewById(R.id.imgbtnBackDet);
        dtlbtnShare = findViewById(R.id.btnShareDet);
    }
}