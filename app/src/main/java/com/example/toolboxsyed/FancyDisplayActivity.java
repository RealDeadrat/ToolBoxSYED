package com.example.toolboxsyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

public class FancyDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy_display);

        Intent intent = getIntent();

        String textInput = intent.getStringExtra(TEXT_INPUT);
        String smallChecked = intent.getStringExtra(SMALL_CHECKED);
        String largeChecked = intent.getStringExtra(LARGE_CHECKED);

        TextView displayView = (TextView) findViewById(R.id.display);


        displayView.setText(Html.fromHtml(textInput));
        displayView.setTextSize(20);
        if(smallChecked.equals("true") && largeChecked.equals("false") )
        {
            displayView.setTextSize(5);
        }
        else if(smallChecked.equals("false") && largeChecked.equals("true"))
        {
            displayView.setTextSize(50);
        }
        else if(smallChecked.equals("true") && largeChecked.equals("true"))
        {
             Toast.makeText(getApplicationContext(),
                 "Choosing Large Text and Small Text results in normal sized text"
                     ,Toast.LENGTH_LONG).show();
        }

    }

    public static final String TEXT_INPUT = "input";
    public static final String LARGE_CHECKED = "large";
    public static final String SMALL_CHECKED = "small";
}
