package com.example.alfianhadipratama.sistemparkir.Admin;

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

public class Admin extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Admin ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageButton btn=(ImageButton)findViewById(R.id.btn_admin_add);
        ImageButton btn2=(ImageButton)findViewById(R.id.btn_admin_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(Admin.this, BuatAdmin.class);
                startActivity(inte);
            } });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM adminnya",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.list_admin);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Admin Info", "Change Admin", "Delete Admin"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatAdmin.class);
                                i.putExtra("nama_admin", selection); startActivity(i);break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), UpdateAdmin.class);
                                in.putExtra("nama_admin", selection); startActivity(in); break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM adminnya WHERE nama_admin = '" + selection + "'");
                                RefreshList();
                                break;
                        } } });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}
