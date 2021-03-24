package com.example.ratinamaze;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int N;
    int[][] maze;
    int flag = 0;
    GridLayout gridLayout;
    RelativeLayout relativeLayout,relativeLayout2;
    Button generate,clear,solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Random r = new Random();
        int[][] a = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 1, 1 } 
        };

        int[][] b = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

        init();
        N=4;

        RatMaze rat = new RatMaze(MainActivity.this,N);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand = r.nextInt(2);
                switch (rand){
                    case 0:
                        maze=a;
                        break;
                    case 1:
                        maze=b;
                        break;
                }
                N = maze.length;
                flag=1;
                rat.printmaze(maze);
            }
        });
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0){
                    Toast.makeText(MainActivity.this, "First generate the problem.", Toast.LENGTH_SHORT).show();
                    return;
                }
                rat.solveMaze(maze);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart = new Intent(MainActivity.this,MainActivity.class);
                startActivity(restart);
                finish();
            }
        });
    }

    private void init() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        relativeLayout = findViewById(R.id.relativelayout);
        relativeLayout2 = findViewById(R.id.relativelayout2);
        generate = findViewById(R.id.generatebtn);
        clear = findViewById(R.id.clearbtn);
        solve = findViewById(R.id.solvebtn);


        gridLayout = (GridLayout) findViewById(R.id.gridview);
        gridLayout.removeAllViews();


        int column = 4;
        int row = 4;

        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row);

        int total = row*column;

        for (int i = 0, c = 0, r = 0; i < total; i++, c++)
        {
            if (c == column)
            {
                //move to next row after all the columns are filled for first row
                c = 0;
                r++;
            }

            //create a new textview
            TextView textView = new TextView(this);
            String id = String.valueOf(r+1) + String.valueOf(c+1);
            textView.setId(Integer.parseInt(id));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.WHITE);
            //textView.setTextSize(19);
            //textView.setTextColor(Color.BLACK);


            //setting the width and height for this textview
            textView.setWidth(
                    (int)width/column
                            - relativeLayout.getPaddingLeft()
                            - relativeLayout2.getPaddingLeft()
            );
            textView.setHeight(
                    (int)width/column
                            - relativeLayout.getPaddingTop()
                            - relativeLayout2.getPaddingTop()
            );



            //setting the row and column number for this textview
            GridLayout.Spec rowSpan = GridLayout.spec(r, 1);
            GridLayout.Spec colspan = GridLayout.spec(c, 1);
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                    rowSpan, colspan);
            textView.setLayoutParams(gridParam);
            gridLayout.addView(textView, gridParam);

            ViewGroup.MarginLayoutParams  parameter = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
            //parameter =  (RelativeLayout.LayoutParams) textView.getLayoutParams();
            parameter.setMargins(2, 2, 2, 2); // left, top, right, bottom
            textView.setLayoutParams(parameter);

        }

        Log.e("Colmn", String.valueOf(gridLayout.getColumnCount()));
        Log.e("Row", String.valueOf(gridLayout.getRowCount()));

        /*
         * To access the text view, use:
         * int id = ID_TO_BE_ACCESSED
         * textview.findViewById(id)
         * */
    }
}