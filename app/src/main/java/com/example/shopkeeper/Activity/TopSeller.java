package com.example.shopkeeper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.shopkeeper.Manager.CallBack;
import com.example.shopkeeper.Manager.OrderHistoryManager;
import com.example.shopkeeper.Manager.ShooperKeeperException;
import com.example.shopkeeper.Model.SellerHistoryRecord;
import com.example.shopkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

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
        setContentView(R.layout.activity_top_seller);

        barchart = (BarChart) findViewById(R.id.bargraph);


        barchart.setTouchEnabled(true);
        barchart.setDragEnabled(true);
        barchart.setScaleEnabled(true);

        OrderHistoryManager.getInstance().getSellerHistory(0, new CallBack<List<SellerHistoryRecord>>() {
            @Override
            public void onResponse(List<SellerHistoryRecord> result, ShooperKeeperException ex) {
                if (null != ex){
                    Toast.makeText(TopSeller.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                Log.d("TopoSeller", "" + result.size());

                final ArrayList<BarEntry> barEntries = new ArrayList<>();
                final ArrayList<String> theDates = new ArrayList<>();
                final BarDataSet barDataSet = new BarDataSet(barEntries,"Seller Performance");

                for (int cnt = 0; cnt < result.size(); cnt++) {
                    SellerHistoryRecord record = result.get(cnt);
                    barEntries.add(new BarEntry((float)record.getTotalSales(), cnt));
                    theDates.add(record.getEmail());
                    Log.d("geztTotalSales", "" + record.getTotalSales() + " " + cnt + " " + record.getEmail());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BarData theData = new BarData(theDates, barDataSet);
                        barchart.setData(theData);

                        barchart.invalidate();
                    }
                });
            }
        });

    }

}
