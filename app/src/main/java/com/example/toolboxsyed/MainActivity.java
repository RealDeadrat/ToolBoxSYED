package com.example.toolboxsyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Resources res = getResources();
    //String[] textType = res.getStringArray(R.array.img_spn_array);


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
        Toast.makeText(getApplicationContext(), (CharSequence) arg0.getItemAtPosition(position),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        //placeholder so app doesn't freak and ask mme to make MainActivity Abstract
    }
}
