package com.example.shopkeeper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.OrderHistoryManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.ProductHistoryRecord;
import com.example.shopkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class TopProduct extends AppCompatActivity {

    BarChart PieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_product);


        PieChart = (BarChart) findViewById(R.id.PieChart);


        PieChart.setTouchEnabled(true);
        PieChart.setDragEnabled(true);
        PieChart.setScaleEnabled(true);

        OrderHistoryManager.getInstance().getTopTenProduct(0, new CallBack<List<ProductHistoryRecord>>() {
            @Override
            public void onResponse(List<ProductHistoryRecord> result, ShooperKeeperException ex) {
                if (null != ex) {
                    Toast.makeText(TopProduct.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                final ArrayList<BarEntry> barEntries = new ArrayList<>();//y
                final ArrayList<String> theDates = new ArrayList<>();//x
                final BarDataSet barDataSet = new BarDataSet(barEntries, "Seller Performance");

                for (int cnt = 0; cnt < result.size(); cnt++) {
                    ProductHistoryRecord record = result.get(cnt);
                    barEntries.add(new BarEntry(record.getTotal(), cnt));
                    theDates.add(record.getProduct().getmName());

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BarData theData = new BarData(theDates, barDataSet);
                        PieChart.setData(theData);
                        PieChart.invalidate();
                    }
                });
            }
        });

    }

}