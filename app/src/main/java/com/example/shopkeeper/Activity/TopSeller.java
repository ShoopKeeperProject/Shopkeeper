package com.example.shopkeeper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shopkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/*
public class TopSeller extends AppCompatActivity {

    //for the databasSeller
    EditText userN;
    String id;
    TextView amounts, results;
    DatabaseSeller SellerDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_seller);

        //for the databaseSeller
        userN = (EditText) findViewById(R.id.userName);
        amounts = (TextView) findViewById(R.id.FinalPrice2);
        SellerDb = new DatabaseSeller(this);
        results = (TextView) findViewById(R.id.tv_results);

        Cursor res = SellerDb.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(this, "No record in the Seller Database." ,Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("userName :"+ res.getString(1)+"\n");
            buffer.append("amounts :"+ res.getString(2)+"\n");
        }
        results.setText(buffer.toString());
        //Double cnyRate = SellerDb.getDouble("CNY");
    }
}
*/
public class TopSeller extends AppCompatActivity {

    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barchart = (BarChart) findViewById(R.id.bargraph);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44,0));
        barEntries.add(new BarEntry(88,1));
        barEntries.add(new BarEntry(66,2));
        barEntries.add(new BarEntry(12,3));
        barEntries.add(new BarEntry(19,4));
        barEntries.add(new BarEntry(91,5));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");
        theDates.add("July");
        theDates.add("Au");
        theDates.add("Sep");

        BarData theData = new BarData(theDates, barDataSet);
        barchart.setData(theData);

        barchart.setTouchEnabled(true);
        barchart.setDragEnabled(true);
        barchart.setScaleEnabled(true);

    }

}
