package com.example.aavgeensingh.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button simple,medium,difficult;
    static int dimention=8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simple=(Button)findViewById(R.id.simple);
        simple.setOnClickListener(this);
        medium=(Button)findViewById(R.id.medium);
        medium.setOnClickListener(this);
        difficult=(Button)findViewById(R.id.difficult);
        difficult.setOnClickListener(this);
    }
    public void launchSimple(View view){
        Intent intent=new Intent(this,SimpleActivity.class);
        intent.putExtra(EXTRA_MESSAGE, dimention);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        Button btn=(Button)v;
        if(btn.getId()==simple.getId()){
            dimention=6;
            launchSimple(v);
           }
        if(btn.getId()==medium.getId()){
            dimention=10;
            launchSimple(v);
        }
        if(btn.getId()==difficult.getId()){
            dimention=16;
            launchSimple(v);
        }
    }
}
