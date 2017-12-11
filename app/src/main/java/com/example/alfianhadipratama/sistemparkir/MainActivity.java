package com.example.alfianhadipratama.sistemparkir;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataHelper dbcenter;
    ImageButton btn1, btn2;
    EditText text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbcenter = new DataHelper(this);
        final SQLiteDatabase db = dbcenter.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM adminnya WHERE id_admin='admin'", null);
        if(x.getCount()==0) {
            db.execSQL("INSERT INTO adminnya VALUES('admin','administrator','admin123','developer','1234567890')");
        }

        btn1 =(ImageButton)findViewById(R.id.btn_login);
        btn2 =(ImageButton)findViewById(R.id.btn_exit);
        text1=(EditText)findViewById(R.id.txt_login);
        text2=(EditText)findViewById(R.id.txt_password);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(text1.getText().toString()==null) {
                    Toast.makeText(getApplicationContext(), "Username cannot Empty !", Toast.LENGTH_LONG).show();
                } else if (text2.getText().toString()==null) {
                    Toast.makeText(getApplicationContext(), "Password cannot Empty !", Toast.LENGTH_LONG).show();
                } else {
                    Cursor al = db.rawQuery("SELECT * FROM adminnya WHERE id_admin='"
                            + text1.getText().toString() + "' AND password='"
                            + text2.getText().toString() + "'",null);
                    if(al.getCount()>0)
                    {
                        Intent inte = new Intent(MainActivity.this, Menu.class);
                        startActivity(inte);
                    } else {
                        Toast.makeText(getApplicationContext(), "Username and Password is Invalid !", Toast.LENGTH_LONG).show();
                    }
                }
            } }
        );

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            } }
        );
    }
}
