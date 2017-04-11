package com.example.shopkeeper.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.OrderHistoryManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.ProductHistoryRecord;
import com.example.shopkeeper.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class TopProduct extends AppCompatActivity {

    PieChart PieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_product);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String CoName = preferences.getString("CoName", "");
        setTitle(CoName);

        PieChart = (PieChart) findViewById(R.id.PieChart);
        PieChart.setRotationEnabled(true);
        PieChart.setHoleRadius(25f);
        PieChart.setTransparentCircleAlpha(0);
        PieChart.setCenterText("Sells Statistics");
        PieChart.setCenterTextSize(10);
        PieChart.setDescriptionTextSize(10);




        //PieChart.setTouchEnabled(true);
        //PieChart.setDragEnabled(true);
        //PieChart.setScaleEnabled(true);

        OrderHistoryManager.getInstance().getTopTenProduct(0, new CallBack<List<ProductHistoryRecord>>() {
            @Override
            public void onResponse(List<ProductHistoryRecord> result, ShooperKeeperException ex) {
                if (null != ex) {
                    Toast.makeText(TopProduct.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                final ArrayList<Entry> pieEntries = new ArrayList<>();//y
                final ArrayList<String> theDates = new ArrayList<>();//x
                final PieDataSet pieDataSet = new PieDataSet(pieEntries, "Product Sells");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                for (int cnt = 0; cnt < result.size(); cnt++) {
                    ProductHistoryRecord record = result.get(cnt);
                    pieEntries.add(new BarEntry((float)record.getTotal(), cnt));
                    theDates.add(record.getProduct().getmName());

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PieData theData = new PieData(theDates, pieDataSet);
                        PieChart.setData(theData);
                        PieChart.animateY(1000);
                        PieChart.invalidate();
                    }
                });
            }
        });

    }

}