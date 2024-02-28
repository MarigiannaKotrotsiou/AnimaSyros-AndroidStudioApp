package com.example.ergasia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {

            R.drawable.animasyros_logo,
            R.drawable.onboardone,
            R.drawable.onboardtwo,
            R.drawable.onboardthree
    };

    int headings[] = {

            R.string.headOb1,
            R.string.headOb2,
            R.string.headOb3,
            R.string.headOb4
    };

    int descriptions [] = {

            R.string.descOb,
            R.string.descOb2,
            R.string.descOb3,
            R.string.descOb4
    };

    public ViewPagerAdapter(Context context){

        this.context = context;
    }


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.imgOb);
        TextView slideHeading = (TextView) view.findViewById(R.id.txtTitleOb);
        TextView slideDescription = (TextView) view.findViewById(R.id.txtDescOb);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(descriptions[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout ) object);
    }

}
