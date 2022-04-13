package com.example.android.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int cpt;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaBaseManager mDatabase = new MaBaseManager(getApplicationContext());
        SharedPreferences prefs = getSharedPreferences("Score",getBaseContext().MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();


        mDatabase.addword("main");
        mDatabase.addword("code");
        mDatabase.addword("test");
        mDatabase.addword("plan");
        mDatabase.addword("duel");
        mDatabase.addword("stop");
        mDatabase.addword("issu");
        mDatabase.addword("mort");
        mDatabase.addword("donc");
        mDatabase.addword("menu");
        mDatabase.addword("kilo");
        mDatabase.addword("fait");
        mDatabase.addword("site");
        mDatabase.addword("item");
        ArrayList<String> words = new ArrayList<>() ;
        Cursor cursor = mDatabase.getAllWords();
        if (cursor.moveToFirst()) {
            do {
                words.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }


          //  super.onDestroy();
          //  mDatabase.close();
        int size = words.size();
        Random  r = new Random();
        cpt = r.nextInt(size-1) ;
        TextView t1 = findViewById(R.id.T1);
        EditText t2 = findViewById(R.id.T2);
        EditText t3 = findViewById(R.id.T3);
        TextView t4 = findViewById(R.id.T4);
        score = findViewById(R.id.score);
        Button btn = findViewById(R.id.button);
        t1.setText(String.valueOf(words.get(cpt).charAt(0)));
        t4.setText(String.valueOf(words.get(cpt).charAt(3)));
        score.setText(String.valueOf(prefs.getInt("val" , 0)));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                char a = t2.getText().charAt(0);
                char b = t3.getText().charAt(0);
                if (words.get(cpt).charAt(1) == a && words.get(cpt).charAt(2) == b){
                    Toast.makeText(MainActivity.this,"+1",Toast.LENGTH_SHORT).show();
                    if(prefs.getInt("val",0) < 10 ){
                        editor.putInt("val",prefs.getInt("val",0) + 1);
                        editor.commit();
                        score.setText(String.valueOf(prefs.getInt("val" , 0)));
                    }else{
                        editor.putInt("val",0);
                        editor.commit();
                        score.setText(String.valueOf(prefs.getInt("val" , 0)));
                    }

                }else {
                    Toast.makeText(MainActivity.this,"le mot est " + words.get(cpt) ,Toast.LENGTH_SHORT).show();
                }
                cpt++;
                if (cpt == size) cpt = 0;
                else {
                    t1.setText(String.valueOf(words.get(cpt).charAt(0)));
                    t4.setText(String.valueOf(words.get(cpt).charAt(3)));
                    t2.setText("");
                    t3.setText("");
                    t2.requestFocus();
                }


            }
        });


        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 1 ){
                    t3.requestFocus();
                }
            }
        });



    }
}