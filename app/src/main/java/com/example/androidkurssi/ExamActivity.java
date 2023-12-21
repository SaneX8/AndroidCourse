package com.example.androidkurssi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;

public class ExamActivity extends AppCompatActivity {

    public static final String TAG ="Valuuttamuunnin: ";
    public double mUunnin ;
    public double sEK = 0.09968;
    public double nOK = 0.10288;
    public double dKK = 0.13440;
    public double Annettuluku;
    String p;
    Double numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        final MaterialButtonToggleGroup materialButtonToggleGroup = findViewById(R.id.toggleBtn1);
        final TextView Summa = findViewById(R.id.Loppusumma);
        final TextInputEditText Euroja = findViewById(R.id.ValuutanMaara);

        Summa.setText("0.000 €");





        materialButtonToggleGroup.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {

                           /* if(Euroja.length()==0){
                                Euroja.setError("Syötä luku");
                            } */

                            if (checkedId == R.id.SEK) {
                                Log.e(TAG,"Muunnetaan sek:sta euroon");

                                String arvo = Euroja.getText().toString();
                                int valuuttamaa = Integer.parseInt(arvo);

                                mUunnin = sEK*valuuttamaa;
                                Summa.setText(mUunnin+" €");



                            } else if (checkedId == R.id.NOK) {
                                Log.e(TAG,"Muunnetaan nok:sta euroon");

                                String arvo = Euroja.getText().toString();
                                int valuuttamaa = Integer.parseInt(arvo);
                                mUunnin = nOK*valuuttamaa;
                                Summa.setText(mUunnin+" €");

                            } else if (checkedId == R.id.DKK) {
                                Log.e(TAG,"Muunnetaan dkk:sta euroon");

                                String arvo = Euroja.getText().toString();
                                int valuuttamaa = Integer.parseInt(arvo);
                                mUunnin = dKK*valuuttamaa;
                                Summa.setText(mUunnin+" €");
                            }


                        }
                    }
                });

        Euroja.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Euroja.length() > 0){
                    Euroja.setError(null);
                }


            }
        });
    }
}