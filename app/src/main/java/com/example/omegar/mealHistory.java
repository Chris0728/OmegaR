package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class mealHistory extends AppCompatActivity {

    private Button backBtn;
    //private Button displayWeekOrMonth;
    //LineGraphSeries <DataPoint> totalSeries;
    final GraphView graph = (GraphView) findViewById(R.id.myGraph);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_history);


        graph.setVisibility(View.VISIBLE);


        try{
            LineGraphSeries <DataPoint> series = new LineGraphSeries<DataPoint>
                    (new DataPoint[]{
                            new DataPoint(0,1),
                            new DataPoint(1,3),
                            new DataPoint(2,10),
                            new DataPoint(3,0),
                            new DataPoint(4,4),
                    });

            graph.addSeries(series);

        } catch (IllegalArgumentException e){
                Toast.makeText(mealHistory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }



        //displayWeek();

        /*
        displayWeekOrMonth = findViewById(R.id.displayWeekOrMonth);
        displayWeekOrMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayMonth();
            }
        });
         */

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mealHistory.super.onBackPressed();
            }
        });




    }
    /*
    public LineGraphSeries<DataPoint> makePoints(){
        LineGraphSeries<DataPoint> abc = new LineGraphSeries<>();
        for(int i =0 ; i < 30; i++){
            abc.appendData((new DataPoint((int)Math.random()*30 + 0,(int)Math.random()*10 + 0)),(true),(30),(true));
        }
        return abc;
    }


    public void displayMonth(){
        try{
            //to remove existing graph data
            graph.removeAllSeries();
            LineGraphSeries <DataPoint> series = makePoints();
            graph.addSeries(series);

        } catch (IllegalArgumentException e){
            Toast.makeText(mealHistory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void displayWeek(){
        try{

            LineGraphSeries <DataPoint> series = new LineGraphSeries<DataPoint>
                    (new DataPoint[]{
                            new DataPoint(0,1),
                            new DataPoint(1,3),
                            new DataPoint(2,10),
                            new DataPoint(3,0),
                            new DataPoint(4,4),
                    });
            graph.addSeries(series);

        } catch (IllegalArgumentException e){
            Toast.makeText(mealHistory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
     */
}
