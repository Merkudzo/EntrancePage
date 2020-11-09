package com.example.yeniprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    Button daxilol;
    EditText login, parol;
    TextView qeydiyyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daxilol=findViewById(R.id.daxil_ol);
        login=findViewById(R.id.login);
        parol=findViewById(R.id.parol);
        qeydiyyat=findViewById(R.id.qeydiyyat);

        daxilol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log=login.getText().toString();
                String shifre=parol.getText().toString();
                    if(log.equalsIgnoreCase("a") && shifre.equalsIgnoreCase("a")){
                        Intent userPage = new Intent(MainActivity.this, user_page.class);
                        startActivity(userPage);
                        overridePendingTransition(R.anim.bounce, R.anim.mixed_anim);
                    }else{
                        Toasty.error(MainActivity.this, "Çox təəssüf daxil etdiyiniz login və ya şifrə bizə məlum deyil!", Toast.LENGTH_LONG).show();
                    }
                }
        });

        qeydiyyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qeydiyyat_page=new Intent(MainActivity.this, Qeydiyyat.class);
                startActivity(qeydiyyat_page);
                overridePendingTransition(R.anim.righttoleft, R.anim.lefttoright);
            }
        });


    }
}
