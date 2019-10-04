package com.example.toolboxsyed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = (Spinner) findViewById(R.id.textVarySpinner);
        spin.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,R.array.img_spn_array,
                android.R.layout.simple_spinner_item);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
    {
        ImageView textImg = (ImageView) findViewById(R.id.fancyImg);
        Toast.makeText(getApplicationContext(),arg0.getItemAtPosition(position).toString()
                ,Toast.LENGTH_LONG).show();

        if(position==0)
        {
            textImg.setImageResource(R.drawable.fancytext);
        }
        else if(position==1)
        {
            textImg.setImageResource(R.drawable.cooltext);
        }
        else if(position==2)
        {
            textImg.setImageResource(R.drawable.hottext);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        //placeholder so app doesn't freak and ask me to make MainActivity Abstract
    }

    public void sendText(View v)
    {
        TextView inputText = (TextView) findViewById(R.id.inputText);
        String inputTextStr = inputText.getText().toString();

        if(inputTextStr.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Type something in the textBox",
                    Toast.LENGTH_LONG).show();
        }
        else if(inputTextStr.endsWith(" ") || inputTextStr.startsWith(" "))
        {
            Toast.makeText(getApplicationContext(),
                    "Please don't begin or end your statement with a space",
                    Toast.LENGTH_LONG).show();
        }
        else {

            CheckBox smallBox = (CheckBox) findViewById(R.id.smallBox);
            CheckBox largeBox = (CheckBox) findViewById(R.id.largeBox);

            RadioButton redBtn = (RadioButton) findViewById(R.id.radBtn1);
            RadioButton blueBtn = (RadioButton) findViewById(R.id.radBtn2);
            RadioButton greenBtn = (RadioButton) findViewById(R.id.radBtn3);
            RadioButton blackBtn = (RadioButton) findViewById(R.id.radBtn4);

            String red = "#ff0022";
            String blue = "#0022ff";
            String green = "#22ff00";
            String black = "#000000";

            Switch styleSwitch = (Switch) findViewById(R.id.styleChange);

            Intent intent = new Intent(this, FancyDisplayActivity.class);

            if (smallBox.isChecked()) {
                intent.putExtra(FancyDisplayActivity.SMALL_CHECKED, "true");
            } else {
                intent.putExtra(FancyDisplayActivity.SMALL_CHECKED, "false");

            }
            if (largeBox.isChecked()) {
                intent.putExtra(FancyDisplayActivity.LARGE_CHECKED, "true");
            } else {
                intent.putExtra(FancyDisplayActivity.LARGE_CHECKED, "false");
            }

            if (redBtn.isChecked()) {
                inputTextStr = getColoredSpanned(inputTextStr, red);
            } else if (blueBtn.isChecked()) {
                inputTextStr = getColoredSpanned(inputTextStr, blue);
            } else if (greenBtn.isChecked()) {
                inputTextStr = getColoredSpanned(inputTextStr, green);
            } else if (blackBtn.isChecked()) {
                inputTextStr = getColoredSpanned(inputTextStr, black);
            }

            if (styleSwitch.isChecked()) {
                inputTextStr = makeItalics(inputTextStr);
            }

            intent.putExtra(FancyDisplayActivity.TEXT_INPUT, inputTextStr);

            startActivity(intent);
        }

    }

    public String getColoredSpanned(String text, String color)
    {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    //used prior knowledge of html to apply the bolding source for italics as well
    public String makeItalics(String word)
    {
        String italicWord = "<i>" + word + "</i>";
        return italicWord;
    }
}
