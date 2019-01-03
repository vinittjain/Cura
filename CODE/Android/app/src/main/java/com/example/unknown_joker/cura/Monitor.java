package com.example.unknown_joker.cura;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class Monitor extends AppCompatActivity {

  LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_monitor);


        String method="angle_data";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method);
        String result = function.getDefaults("val",this);
       // Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        double y,x;
        x = 0;
        GraphView graphView =(GraphView)findViewById(R.id.graphview);
        series = new LineGraphSeries<DataPoint>();
        String angle = function.getDefaults("val",this);

        int counter = 0;
        for( int i=0; i<result.length(); i++ ) {
            if( result.charAt(i) == '=' ) {
                counter++;
            }
        }

        for(int i=0;i<counter;i++)
        {
            x = x+0.5;
            y =Math.abs(find_angle(result)[i]);
            series.appendData(new DataPoint(x,y),true,counter);

        }
        graphView.addSeries(series);
    }

    public void fab(View view) {
       // fullscreen();
       // String result = function.getDefaults("val",this);
        //Toast.makeText(this, Float.toString(find_angle(result)[5]), Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(Monitor.this, Popup.class);

         {
            String method = "angle_data";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method);
            String result = function.getDefaults("val", this);

            double x, y;
            x = 0;

            GraphView graphView = (GraphView) findViewById(R.id.graphview);
            series = new LineGraphSeries<DataPoint>();


            int counter = 0;
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == '=') {
                    counter++;
                }
            }

            for (int i = 0; i < counter; i++) {
                x = x + 0.5;
                y = Math.abs(find_angle(result)[i]);
                series.appendData(new DataPoint(x, y), true, counter);

            }
            graphView.addSeries(series);
            /// /startActivity(intent);
        }
    }

    public void fullscreen()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public float[] find_angle(String raw)
    {
        int counter = 0;
        for( int i=0; i<raw.length(); i++ ) {
            if( raw.charAt(i) == '=' ) {
                counter++;
            }
        }


        float[] angle = new float[counter];
        String after= raw;

        for( int i=0; i<counter; i++ ) {

             angle[i]=Float.parseFloat(after.substring(0, after.indexOf('=')));
             String v = after.substring(after.indexOf('|')+1);
             after = v;
        }


        return angle;
    }
}
