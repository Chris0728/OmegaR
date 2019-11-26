package com.example.omegar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class mealHistory extends AppCompatActivity {

    private Button backBtn;
    private Button displayWeekOrMonth;
    private boolean isMonthly;                      // true == graph is currently displaying monthly data. This bool is needed for the displayWeekOrMonth button. // TODO: 11/26/2019 Is this bool really needed?
    LineGraphSeries<DataPoint> totalSeries;
    LineGraphSeries<DataPoint> series;
    TextView graphTitle;
    GraphView graph;                                //The bug that caused FATAL error was due to fact that graph was initialized here and not in onCreate method LMAO!
                                                    //I also removed final type because I want the graph to be dynamic.


    // TODO: 11/26/2019 Methods below were designed to input dummy values. Refine methods when we are in the next semester/implementing DB.
    //  e.g.
    //  (1) retrieve data from input.class
    //  (2) save series into local file to keep record
    //  (3) check local series file against DB to ensure accuracy of data.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_history);

        graph = (GraphView) findViewById(R.id.myGraph);
        graph.setVisibility(View.VISIBLE);

        graphTitle = findViewById(R.id.graphTitle);

        //Displays weekly graph by default. User has to click button if user wants to see monthly history.
        displayWeek();


        displayWeekOrMonth = findViewById(R.id.displayWeekOrMonth);

        // TODO: 11/26/2019. This button is not correctly updating dynamically the graph to monthly.
        displayWeekOrMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isMonthly) {
                    displayWeek();
                } else {
                    displayMonth();
                }
            }
        });


        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mealHistory.super.onBackPressed();
            }
        });

    }


    //  This method might not be needed because the method resetData(E[] data) from jjoe64 will erase and create new data. Look at line 407.
    //  https://github.com/jjoe64/GraphView/blob/master/src/main/java/com/jjoe64/graphview/series/BaseSeries.java
    //
    public LineGraphSeries<DataPoint> makePoints() {
        LineGraphSeries<DataPoint> abc = new LineGraphSeries<>();

        //randomizing 30 data points for the sake of 1st prototype. These data do not in any way represent any other data that exist on other activities.
        for (int i = 0; i < 10; i++) {
            abc.appendData((new DataPoint((int) (Math.random() * 31), (int) (Math.random() * 11))), (true), (30)); //wtf is the 2nd param?
        }

        return abc;
    }

    //pretty useless method if you ask me.
    public void clearGraph() {
        graph.removeAllSeries();
    }

    //I will make a method below this with hardcoded values
    //This method was 1st attempt at chanign graph to monlty

    //2nd attempt at changng graph to monthly
    //Update: Holy shit this actually works.
    // TODO: 11/26/2019 Change this method's name to displayMonth and delete the duplicate method above cuz it dont work.
    public void displayMonth() {
        isMonthly = true;
        setGraphTitle("Month");
        int arraySize = 30;
        DataPoint[] data = new DataPoint[arraySize];

        try {
            clearGraph();

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>
                    (new DataPoint[]{

                            new DataPoint(0, (int) (Math.random() * 11)),
                            new DataPoint(1, (int) (Math.random() * 11)),
                            new DataPoint(2, (int) (Math.random() * 11)),
                            new DataPoint(3, (int) (Math.random() * 11)),
                            new DataPoint(4, (int) (Math.random() * 11)),
                            new DataPoint(5, (int) (Math.random() * 11)),
                            new DataPoint(6, (int) (Math.random() * 11)),
                            new DataPoint(7, (int) (Math.random() * 11)),
                            new DataPoint(8, (int) (Math.random() * 11)),
                            //values past x=8 are not shown cuz it dont fit on the app screen.
                    });
            graph.addSeries(series);

        } catch (IllegalArgumentException e) {
            Toast.makeText(mealHistory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void displayWeek() {

        isMonthly = false;
        setGraphTitle("Week");
        try {
            //to remove existing graph data, if any.
            // TODO: 11/26/2019 Is clearGraph really needed?
            clearGraph();
            graph.removeAllSeries();

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>
                    (new DataPoint[]{
                            new DataPoint(0, (int) (Math.random() * 11)),
                            new DataPoint(1, (int) (Math.random() * 11)),
                            new DataPoint(2, (int) (Math.random() * 11)),
                            new DataPoint(3, (int) (Math.random() * 11)),
                            new DataPoint(4, (int) (Math.random() * 11))
                    });
            graph.addSeries(series);

        } catch (IllegalArgumentException e) {
            Toast.makeText(mealHistory.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setGraphTitle(String name) {
        graphTitle.setText(name);
    }

}
