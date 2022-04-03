package com.example.misterbin.adapter;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.misterbin.R;

public class HelpVoucherSliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public HelpVoucherSliderAdapter(Context context)
    {
        this.context = context;
    }

    //arrays to store the values in the slider
    public int[] slide_images={
            R.drawable.help_voucher_01,
            R.drawable.help_voucher_02,
            R.drawable.help_voucher_03,
            R.drawable.help_voucher_04
    };

    public String[] slide_headings ={
            "Step 1",
            "Step 2",
            "Step 3",
            "Step 4"
    };

    public String[] slide_desc={
            "Click on the Points button to enter voucher page",
            "Browse through the voucher list",
            "Remember to check if you have enough points for that voucher",
            "Purchase and enjoy your voucher!"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.help_page_slide_layout, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.center_icon);
        TextView slideHeading = (TextView) view.findViewById(R.id.headings);
        TextView slideDescription = (TextView) view.findViewById(R.id.descriptions);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
