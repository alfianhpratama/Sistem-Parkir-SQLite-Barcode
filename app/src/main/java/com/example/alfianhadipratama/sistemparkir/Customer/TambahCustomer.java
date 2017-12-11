package com.example.alfianhadipratama.sistemparkir.Customer;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.alfianhadipratama.sistemparkir.DataHelper;
import com.example.alfianhadipratama.sistemparkir.R;

public class TambahCustomer extends Activity {

    DataHelper dbHelper;
    ImageButton ton1, ton2;
    EditText text1, text2;
    TextView text4;
    private String ambil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_customer);

        dbHelper = new DataHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ambil = extras.getString("barcode");
        }

        text1 = (EditText) findViewById(R.id.txt_edit_plat_cus);
        text2 = (EditText) findViewById(R.id.txt_edit_nama_cus);
        text4 = (TextView) findViewById(R.id.txt_sibarcode);

        text4.setText(ambil);

        ton1 = (ImageButton) findViewById(R.id.btn_save_cus);
        ton2 = (ImageButton) findViewById(R.id.btn_cancel_cus);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO kendaraan VALUES('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        ambil + "')");
                Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_LONG).show();
                CustomerView.disini.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
