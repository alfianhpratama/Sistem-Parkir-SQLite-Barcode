package com.example.alfianhadipratama.sistemparkir.Scan;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.alfianhadipratama.sistemparkir.DataHelper;
import com.example.alfianhadipratama.sistemparkir.R;

public class MainScan extends AppCompatActivity {

    public static MainScan disini;
    protected Cursor cursor;

    String[] daftar;
    ListView ListView01;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main_scan);
        ImageButton btn= (ImageButton) findViewById(R.id.btn_scan_masuk);
        ImageButton btn1= (ImageButton) findViewById(R.id.btn_scan_keluar);
        ImageButton btn2= (ImageButton) findViewById(R.id.btn_scan_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(MainScan.this, ScanMasuk.class);
                startActivity(inte);
            } });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(MainScan.this, ScanKeluar.class);
                startActivity(inte);
            } });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View arg0) {
                finish();
            }
        });
        disini = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM waktu",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.list_scan_pelanggan);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                SQLiteDatabase db = dbcenter.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM waktu WHERE plat_nomor='" + selection + "'",null);
                c.moveToFirst();
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainScan.this);
                if (c.getCount()>0) {
                    builder.setMessage("Owner " + c.getString(0).toString());
                    builder.setMessage("Check In " + c.getString(2).toString());
                }
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.create().show();
            }
        });
    }
}