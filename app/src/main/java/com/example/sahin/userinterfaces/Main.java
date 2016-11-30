package com.example.sahin.userinterfaces;

import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Sahin on 29/11/2016.
 */

public class Main extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,RatingBar.OnRatingBarChangeListener{
    TextView textView;
    Button button;
    ToggleButton toggleButton;
    CheckBox checkBox;
    RadioButton radioButton;
    CheckedTextView checkedTextView;
    Spinner spinner;
    ProgressBar progressBar;
    ProgressBar progressBar2;
    SeekBar seekBar1,seekBar2;
    QuickContactBadge quickContactBadge;
    RatingBar ratingBar;
    Switch aSwitch;

    ArrayList<String> liste;
    ArrayAdapter<String> adapter;
    Thread thread;
    boolean flag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        install_elements();
    }

    private void load_elements_functions() {
        textView.setOnClickListener(this);
        button.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        radioButton.setOnClickListener(this);
        checkedTextView.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    private void install_elements()
    {
        //region yüklenen bileşenler
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        checkedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);
        spinner = (Spinner) findViewById(R.id.spinner);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        quickContactBadge = (QuickContactBadge) findViewById(R.id.quickContactBadge);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        aSwitch = (Switch) findViewById(R.id.switch1);


        liste = new ArrayList<>();
        liste.add("liste elemanı 1");
        liste.add("liste elemanı 2");
        liste.add("liste elemanı 3");
        liste.add("liste elemanı 4");
        liste.add("liste elemanı 5");
        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,liste);
        spinner.setAdapter(adapter);


        progressBar.setMax(150);

        //endregion
        load_elements_functions();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.textView:
                Toast.makeText(getApplicationContext(),"Text View Tıklandı",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                Toast.makeText(getApplicationContext(),"Button Tıklandı- Progress Bar 1 Çalıştırıldı..",Toast.LENGTH_SHORT).show();
                //region progressBar1_Thread
                if (thread != null)
                {
                    flag = !flag;
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setProgress(0);
                }
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (flag)
                        {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (progressBar.getProgress() == progressBar.getMax())
                                progressBar.setProgress(0);
                            progressBar.setProgress(progressBar.getProgress()+1);
                        }
                    }
                });
                thread.start();
                //endregion
                break;
            case R.id.toggleButton:
                Toast.makeText(getApplicationContext(),toggleButton.getText(),Toast.LENGTH_SHORT).show();
                if (progressBar2.getVisibility() == View.VISIBLE)
                    progressBar2.setVisibility(View.GONE);
                else progressBar2.setVisibility(View.VISIBLE);

                break;
            case R.id.checkBox:
                Toast.makeText(getApplicationContext(),checkBox.isChecked()==true?"Aktif":"Pasif",Toast.LENGTH_SHORT).show();
                checkBox.setText(checkBox.isChecked()==true?"Aktif":"Pasif");
                break;
            case R.id.radioButton:
                Toast.makeText(getApplicationContext(),"Radio Button Tıklandı",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkedTextView:
                if (checkedTextView.isChecked())
                    checkedTextView.setChecked(false);
                else
                    checkedTextView.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.spinner:
                Toast.makeText(getApplicationContext(),liste.get(i),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        switch (ratingBar.getId())
        {
            case R.id.ratingBar:
                Toast.makeText(getApplicationContext(),String.valueOf(v),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}