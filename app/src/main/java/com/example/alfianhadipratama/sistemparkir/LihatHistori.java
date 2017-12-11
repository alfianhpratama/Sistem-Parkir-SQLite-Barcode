package com.example.alfianhadipratama.sistemparkir;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LihatHistori extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    ImageButton ton2;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_histori);

        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.txtnya_histori_owner);
        text2 = (TextView) findViewById(R.id.txtnya_histori_plat);
        text3 = (TextView) findViewById(R.id.txtnya_histori_masuk);
        text4 = (TextView) findViewById(R.id.txtnya_histori_keluar);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM histori WHERE plat_nomor = '" + getIntent().getStringExtra("plat_nomor") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
        }
        ton2 = (ImageButton) findViewById(R.id.btn_lihatadmin_back);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
