package com.example.aavgeensingh.minesweeper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener {
    Cell cell[][];
    LinearLayout[] rows;
    RelativeLayout upper;
    Button newGame;
    LinearLayout grid;
    TextView pts;
    static int dimen;
    static int row=0,col=0;
    boolean over=false;
    int normal,count;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dimen=getIntent().getIntExtra(EXTRA_MESSAGE,8);
        setContentView(R.layout.activity_simple);
        grid=(LinearLayout)findViewById(R.id.grid);
        pts=(TextView)findViewById(R.id.pointText);
        newGame=(Button)findViewById(R.id.newgame);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newGame.setText("\uD83D\uDE42");
                makeMineSweeper();
            }
        });
        normal=(dimen*dimen)-(dimen*2)+1;
        count=0;
        upper=(RelativeLayout)findViewById(R.id.upperBar);
        makeMineSweeper();
    }

    public void makeMineSweeper() {
        //Make the board.

        over=false;
        pts.setText("0");
        cell=new Cell[dimen][dimen];
        rows=new LinearLayout[dimen];
        grid.removeAllViews();
        for (int i = 0; i < dimen; i++) {
            rows[i]=new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            params.setMargins(16/dimen, 16/dimen, 16/dimen, 16/dimen);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            grid.addView(rows[i]);
        }
        for (int j = 0; j < dimen; j++) {
            for (int i = 0; i < dimen; i++) {
                cell[j][i]=new Cell(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(16/dimen, 16/dimen, 16/dimen, 16/dimen);
                cell[j][i].setLayoutParams(params);
                cell[j][i].setText("");
                cell[j][i].setOnClickListener(this);
                cell[j][i].setPadding(0,0,0,0);
                //cell[j][i].setItem(-1);
                cell[j][i].setBackgroundColor(Color.rgb(255, 250, 205));
                cell[j][i].setTextColor(Color.rgb(205, 198, 115));
                rows[i].addView(cell[j][i]);
            }
        }
        //Initialize the Mines.
        Random r=new Random();
        for (int i = 0; i < dimen*2 - 5; i++) {
            row=r.nextInt(dimen-0);
            col=r.nextInt(dimen-0);
            cell[row][col].setItem(-1);
        }
        //Calculate the points for each cell
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if(cell[i][j].getItem()!=-1)
                    cell[i][j].setItem(calPoints(i,j));
            }
        }
    }

    private int calPoints(int i,int j) {
        int points=0;
        //If at boundary
        if((cell[i][j].getItem()!=-1)&&(i==0 || i==dimen-1 || j==0 || j==dimen-1)){
            if(i==0 && j==0){
                if(cell[i][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j].getItem()==-1)
                    points++;
            }
            else if(i==0 && j==dimen-1){
                if(cell[i][j-1].getItem()==-1)
                    points++;
                if(cell[i+1][j-1].getItem()==-1)
                    points++;
                if(cell[i+1][j].getItem()==-1)
                    points++;
            }
            else if(i==dimen-1 && j==dimen-1){
                if(cell[i-1][j-1].getItem()==-1)
                    points++;
                if(cell[i][j-1].getItem()==-1)
                    points++;
                if(cell[i-1][j].getItem()==-1)
                    points++;
            }
            else if(i==dimen-1 && j==0){
                if(cell[i-1][j].getItem()==-1)
                    points++;
                if(cell[i][j+1].getItem()==-1)
                    points++;
                if(cell[i-1][j+1].getItem()==-1)
                    points++;
            }
            else if(i==0){
                if(cell[i][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j].getItem()==-1)
                    points++;
                if(cell[i][j-1].getItem()==-1)
                    points++;
                if(cell[i+1][j-1].getItem()==-1)
                    points++;
            }
            else if(j==0){
                if(cell[i-1][j].getItem()==-1)
                    points++;
                if(cell[i][j+1].getItem()==-1)
                    points++;
                if(cell[i-1][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j+1].getItem()==-1)
                    points++;
                if(cell[i+1][j].getItem()==-1)
                    points++;
            }
            else if(i==dimen-1){
                if(cell[i-1][j].getItem()==-1)
                    points++;
                if(cell[i][j+1].getItem()==-1)
                    points++;
                if(cell[i-1][j+1].getItem()==-1)
                    points++;
                if(cell[i-1][j-1].getItem()==-1)
                    points++;
                if(cell[i][j-1].getItem()==-1)
                    points++;
            }
            else if(j==dimen-1){
                if(cell[i-1][j].getItem()==-1)
                    points++;
                if(cell[i-1][j-1].getItem()==-1)
                    points++;
                if(cell[i][j-1].getItem()==-1)
                    points++;
                if(cell[i+1][j-1].getItem()==-1)
                    points++;
                if(cell[i+1][j].getItem()==-1)
                    points++;

            }
        }
        //If inbetween boundaries.
        else if(cell[i][j].getItem()!=-1){
            if(cell[i-1][j].getItem()==-1)
                points++;
            if(cell[i-1][j-1].getItem()==-1)
                points++;
            if(cell[i][j-1].getItem()==-1)
                points++;
            if(cell[i+1][j-1].getItem()==-1)
                points++;
            if(cell[i+1][j].getItem()==-1)
                points++;
            if(cell[i-1][j+1].getItem()==-1)
                points++;
            if(cell[i][j+1].getItem()==-1)
                points++;
            if(cell[i+1][j+1].getItem()==-1)
                points++;
        }
        return points;
    }

    @Override
    public void onClick(View v) {
        Cell c = (Cell) v;
        int ro=-1,co=-1;

        if(over)
            return;
        if(normal==count){
            over=true;
            Toast.makeText(this,"You Won Bro",Toast.LENGTH_LONG).show();
            return;
        }
        if(c.getText()!="") {
            Toast.makeText(this, "Already Filled", Toast.LENGTH_SHORT).show();
            return;
        }
       /*else if(c.getItem()==0){
            for (int i = 0; i < dimen; i++) {
                for (int j = 0; j < dimen; j++) {
                    if(c==cell[i][j]){
                        ro=i;co=j;
                        break;
                    }
                }
                if(ro!=-1 && co!=-1)
                    break;
            }
            return;
        } */
        else if (c.getItem() == -1) {
            for (int i = 0; i < dimen; i++) {
                for (int j = 0; j < dimen; j++) {
                    if(cell[i][j].getItem()==-1){
                        cell[i][j].setText("@");
                        cell[i][j].setTextColor(Color.GRAY);
                    }
                    else
                        cell[i][j].setText(cell[i][j].getItem()+"");
                }
            }
            over=true;
            newGame.setText("\uD83D\uDE4F\uD83C\uDFFC");
            Toast.makeText(this,"You Lost Bro",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            c.setText(c.getItem()+"");
            pts.setText(Integer.parseInt(pts.getText()+"")+c.getItem()+"");
        }
    }

}
