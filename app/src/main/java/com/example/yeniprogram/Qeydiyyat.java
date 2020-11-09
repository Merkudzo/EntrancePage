package com.example.yeniprogram;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;


public class Qeydiyyat extends AppCompatActivity {

    EditText ad, soyad, email, telefon, login, parol, parol2;
    Button tarixi_sec, save, imtina;
    TextView tarix_goster;
    Boolean cins_qeydi =false;
    RadioButton male, fmale;
    String cins="", name, surname, Email, Phone, Login, Parol1, Parol2, dogum_tarixi;

    Calendar calendar;
    DatePickerDialog datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qeydiyyat);

        ad= findViewById(R.id.ad);
        soyad= findViewById(R.id.soyad);
        email= findViewById(R.id.email);
        telefon=findViewById(R.id.telefon);
        login=findViewById(R.id.login);
        parol=findViewById(R.id.parol);
        parol2=findViewById(R.id.parol2);

        tarixi_sec=findViewById(R.id.tarix_sec);
        tarix_goster=findViewById(R.id.tarix);

        male=findViewById(R.id.kisi);
        fmale=findViewById(R.id.qadin);

        save=findViewById(R.id.save);
        imtina=findViewById(R.id.imtina);



        tarixi_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                int gun=calendar.get(Calendar.DAY_OF_MONTH);
                int ay=calendar.get(Calendar.MONTH);
                int il=calendar.get(Calendar.YEAR);

                datepicker=new DatePickerDialog(Qeydiyyat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        tarix_goster.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                        dogum_tarixi=mDay+"/"+(mMonth+1)+"/"+mYear;
                    }
                },gun,ay,il);
                datepicker.show();

            }
        });

        imtina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainPage=new Intent(Qeydiyyat.this, MainActivity.class);
                startActivity(MainPage);
                overridePendingTransition(R.anim.righttoleft, R.anim.lefttoright);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male.isChecked()){
                    cins="kişi";
                    fmale.setChecked(false);
                }else if(fmale.isChecked()){
                    cins="qadın";
                    male.setChecked(false);
                }
                name=ad.getText().toString();
                surname=soyad.getText().toString();
                Email=email.getText().toString();
                Phone=telefon.getText().toString();
                Login=login.getText().toString();
                Parol1=parol.getText().toString();
                Parol2=parol2.getText().toString();

                if(!name.equals("") && !surname.equals("") && !Login.equals("") &&
                        !Parol1.equals("") && !Parol2.equals("") && !cins.equals("") && !dogum_tarixi.equals("")){
                    if(Email.equals("") && Phone.equals("")){
                        Toasty.error(Qeydiyyat.this, "Email və ya telefon xanalarının ən azı birini doldurun!", Toast.LENGTH_LONG).show();
                    }else{
                        if(!Parol1.equals(Parol2))
                            Toasty.error(Qeydiyyat.this, "Zəhmət olmasa təkrar şifrəni düzgün daxil edin!", Toast.LENGTH_LONG).show();
                        else{
                            QeydiyyatModul qeydiyyatModul = null;
                            try {
                                qeydiyyatModul=new QeydiyyatModul(-1, ad.getText().toString(), soyad.getText().toString(), email.getText().toString(), telefon.getText().toString(),
                                        cins, tarix_goster.getText().toString(), login.getText().toString(), parol.getText().toString());
                            }
                            catch (Exception e){
                                //
                            }

                            DataBaseHelper dataBaseHelper = new DataBaseHelper(Qeydiyyat.this);
                            boolean yaddasa_yazildi = dataBaseHelper.elaveET(qeydiyyatModul);
                            Toasty.success(Qeydiyyat.this, yaddasa_yazildi + "Məlumatlar yadda saxlandı!", Toast.LENGTH_LONG).show();

                            Intent MainPage=new Intent(Qeydiyyat.this, MainActivity.class);
                            startActivity(MainPage);
                            overridePendingTransition(R.anim.righttoleft, R.anim.lefttoright);
                        }
                    }

                }else
                    Toasty.error(Qeydiyyat.this, "Zəhmət olmasa '*' olan bütün xanaları doldurun!", Toast.LENGTH_LONG).show();

            }
        });
        //
    }
}
