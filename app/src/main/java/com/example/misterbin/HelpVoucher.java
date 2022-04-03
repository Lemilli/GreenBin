package com.example.misterbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.misterbin.adapter.HelpVoucherSliderAdapter;

public class HelpVoucher extends AppCompatActivity {

    private ViewPager helpPageViewPager;
    private LinearLayout helpPageDotsLayout;

    private TextView[] mDots;

    private HelpVoucherSliderAdapter sliderAdapter;

    private Button nextButton;
    private Button backButton;

    private int currentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_voucher);

        helpPageViewPager = (ViewPager) findViewById(R.id.helpPager_viewPager);
        helpPageDotsLayout = (LinearLayout) findViewById(R.id.helpPages_dotsLayout);

        nextButton = (Button)findViewById(R.id.nextButton);
        backButton = (Button)findViewById(R.id.previousButton);

        sliderAdapter = new HelpVoucherSliderAdapter(this);

        helpPageViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        helpPageViewPager.addOnPageChangeListener(viewListener);

        //button onclick listener
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(currentPage+1 ==mDots.length)
                {
                    Intent i = new Intent(HelpVoucher.this, GetHelpPage.class); //back to profile page
                    startActivity(i);
                    finish();
                }
                else
                {
                    helpPageViewPager.setCurrentItem(currentPage+1); //move to next page
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpPageViewPager.setCurrentItem(currentPage-1);
            }
        });
    }

    public void addDotsIndicator(int position)
    {
        //create three dots
        mDots = new TextView[4];

        //remove all the existing dots
        helpPageDotsLayout.removeAllViews();
        for(int i=0;i< mDots.length;i++)
        {
            mDots[i] =new TextView(this);

            //convert the html code into single dots
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(55);
            mDots[i].setTextColor(getResources().getColor(R.color.transparentWhite));

            helpPageDotsLayout.addView(mDots[i]);
        }

        if(mDots.length > 0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    //adding indicator effects for each dot
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            //change the current page value
            currentPage = position;

            if(currentPage==0) //on the first page
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE); //hide the back button

                nextButton.setText("Next");
                backButton.setText("");
            }

            else if(currentPage== mDots.length-1) //last page
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE); //hide the back button

                nextButton.setText("FINISH");
                backButton.setText("Back");
            }
            else
            {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE); //hide the back button

                nextButton.setText("Next");
                backButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}