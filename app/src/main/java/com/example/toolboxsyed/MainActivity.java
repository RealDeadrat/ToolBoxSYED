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

        Switch styleSwitch = (Switch) findViewById(R.id.styleChange);

        styleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b && !(getTheme().getResources().toString().equals("android.content.res.Resources@444596e")))
                {

                        setTheme(android.R.style.Theme_DeviceDefault_Light_DarkActionBar);
                        //Toast.makeText(getApplicationContext(),"big brain", Toast.LENGTH_LONG).show();
                        recreate();

                }
                else if(b  && !(getTheme().getResources().toString().equals("android.content.res.Resources@444596e")))
                {
                    setTheme(android.R.style.Theme_DeviceDefault_DayNight);
                    //Toast.makeText(getApplicationContext(),"big brain", Toast.LENGTH_LONG).show();
                    recreate();
                }
            }
        });

       /* if(!styleSwitch.isChecked() && !(getTheme().getResources().toString().equals("android.content.res.Resources@3da4bf3")))
        {
            setTheme(android.R.style.Theme_DeviceDefault_Light_DarkActionBar);
            //Toast.makeText(getApplicationContext(),"big brain", Toast.LENGTH_LONG).show();
            this.recreate();
        }
        else if(styleSwitch.isChecked() && !(getTheme().getResources().toString().equals("")))
        {
            setTheme(android.R.style.Theme_DeviceDefault_DayNight);
            //Toast.makeText(getApplicationContext(),"big brain", Toast.LENGTH_LONG).show();
            this.recreate();
        }
        */
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
    {
        ImageView textImg = (ImageView) findViewById(R.id.fancyImg);
        Toast.makeText(getApplicationContext(),getTheme().getResources().toString(),Toast.LENGTH_LONG).show();

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

        Intent intent = new Intent(this, FancyDisplayActivity.class);

        if(smallBox.isChecked())
        {
            //inputTextStr = makeSmall(inputTextStr);
            intent.putExtra(FancyDisplayActivity.SMALL_CHECKED,"true");
        }
        else
        {
            //inputTextStr = makeLarge(inputTextStr);
            intent.putExtra(FancyDisplayActivity.SMALL_CHECKED,"false");

        }
        if(largeBox.isChecked())
        {
            intent.putExtra(FancyDisplayActivity.LARGE_CHECKED,"true");
        }
        else
        {
            intent.putExtra(FancyDisplayActivity.LARGE_CHECKED,"false");
        }

        if(redBtn.isChecked())
        {
            inputTextStr = getColoredSpanned(inputTextStr, red);
        }
        else if(blueBtn.isChecked())
        {
            inputTextStr = getColoredSpanned(inputTextStr, blue);
        }
        else if(greenBtn.isChecked())
        {
            inputTextStr = getColoredSpanned(inputTextStr, green);
        }
        else if(blackBtn.isChecked())
        {
            inputTextStr = getColoredSpanned(inputTextStr, black);
        }



        intent.putExtra(FancyDisplayActivity.TEXT_INPUT, inputTextStr);

        startActivity(intent);

    }

    public String getColoredSpanned(String text, String color)
    {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}
