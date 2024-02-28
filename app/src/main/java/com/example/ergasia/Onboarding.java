package com.example.ergasia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Onboarding extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button skipbtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdaptor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        skipbtn = findViewById(R.id.btnSkipOnB);

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Onboarding.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdaptor = new ViewPagerAdapter(this);

        mSlideViewPager.setAdapter(viewPagerAdaptor);

        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    private void setUpindicator(int position) {
        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.tertiary_dark,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.secondary,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}