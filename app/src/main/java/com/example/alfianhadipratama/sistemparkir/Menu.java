package com.example.alfianhadipratama.sistemparkir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.alfianhadipratama.sistemparkir.Admin.Admin;
import com.example.alfianhadipratama.sistemparkir.Customer.CustomerView;
import com.example.alfianhadipratama.sistemparkir.Scan.MainScan;

public class Menu extends AppCompatActivity {
    public static Menu hereis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton btn=(ImageButton)findViewById(R.id.btn_menu_admin);
        ImageButton btn1=(ImageButton)findViewById(R.id.btn_menu_add);
        ImageButton btn2=(ImageButton)findViewById(R.id.btn_menu_customer);
        ImageButton btn3=(ImageButton)findViewById(R.id.btn_menu_history);
        ImageButton btn4=(ImageButton)findViewById(R.id.btn_menu_about);
        ImageButton btn5=(ImageButton)findViewById(R.id.btn_menu_exit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(Menu.this, Admin.class);
                startActivity(inte);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(Menu.this, CustomerView.class);
                startActivity(inte);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(Menu.this, MainScan.class);
                startActivity(inte);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(Menu.this, Histori.class);
                startActivity(inte);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        hereis = this;
    }
}
