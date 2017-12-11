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

public class UpdateAdmin extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    ImageButton ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.txt_edit_plat_cus);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM adminnya WHERE nama_admin = '" + getIntent().getStringExtra("nama_admin") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }
        ton1 = (ImageButton) findViewById(R.id.btn_update_save);
        ton2 = (ImageButton) findViewById(R.id.btn_update_cancel);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE adminnya SET nama_admin='"+
                        text2.getText().toString() +"', password='" +
                        text3.getText().toString()+"', alamat='"+
                        text4.getText().toString() +"', no_hp='" +
                        text5.getText().toString() + "' where id_admin='" +
                        text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Admin.ma.RefreshList();
                finish();
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
