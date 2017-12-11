package com.example.alfianhadipratama.sistemparkir.Customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.alfianhadipratama.sistemparkir.DataHelper;
import com.example.alfianhadipratama.sistemparkir.R;

public class CustomerView extends AppCompatActivity {

    public static CustomerView disini;
    protected Cursor cursor;
    String[] daftar;
    ListView ListView01;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        dbcenter = new DataHelper(this);

        ImageButton btn= (ImageButton) findViewById(R.id.btn_customer_add);
        ImageButton btn1= (ImageButton) findViewById(R.id.btn_customer_exit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(CustomerView.this, Scanner.class);
                startActivity(inte);
            } });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            } });
        disini = this;
      RefreshList();
    }

    public void RefreshList(){
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kendaraan",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);

            daftar[cc] = cursor.getString(1).toString();
        }

        ListView01 = (ListView) findViewById(R.id.list_customer);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Delete Costumer"};
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerView.this);
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                        db.execSQL("DELETE FROM kendaraan WHERE nama_pemilik = '" + selection + "'");
                        RefreshList();
                    }
                });
                builder.create().show();
            }
        });
    }
}

