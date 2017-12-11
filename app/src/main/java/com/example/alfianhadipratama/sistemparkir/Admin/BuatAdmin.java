package com.example.alfianhadipratama.sistemparkir.Admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alfianhadipratama.sistemparkir.DataHelper;
import com.example.alfianhadipratama.sistemparkir.R;

public class BuatAdmin extends AppCompatActivity {

    DataHelper dbHelper;
    ImageButton ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_admin);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.txt_edit_plat_cus);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);

        ton1 = (ImageButton) findViewById(R.id.btn_buatadmin_save);
        ton2 = (ImageButton) findViewById(R.id.btn_buatadmin_cancel);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor a = db.rawQuery("SELECT * FROM adminnya WHERE id_admin='" + text1.getText().toString() + "'", null);
                a.moveToFirst();
                if (a.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), "Username in use !", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    db.execSQL("INSERT INTO adminnya VALUES('" +
                            text1.getText().toString() + "','" +
                            text2.getText().toString() + "','" +
                            text3.getText().toString() + "','" +
                            text4.getText().toString() + "','" +
                            text5.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_LONG).show();
                    Admin.ma.RefreshList();
                    finish();
                }
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}