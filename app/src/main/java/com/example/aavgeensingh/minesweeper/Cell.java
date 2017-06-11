package com.example.aavgeensingh.minesweeper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Aavgeen Singh on 2/2/2017.
 */

public class Cell extends Button {
    int item;
    public Cell(Context context) {
        super(context);
    }
    public void setItem(int points){
        item=points;
    }
    public int getItem(){
        return item;
    }

}
