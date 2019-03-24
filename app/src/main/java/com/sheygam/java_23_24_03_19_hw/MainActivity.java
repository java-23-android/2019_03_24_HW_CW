package com.sheygam.java_23_24_03_19_hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView emailTxt, passwordTxt;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailTxt = findViewById(R.id.email_txt);
        passwordTxt = findViewById(R.id.password_txt);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
//        loginBtn.setVisibility(View.INVISIBLE);
//        loginBtn.setEnabled(false);
        loadData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn){
            Intent intent = new Intent(this, EmailActivity.class);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1){
            String email = data.getStringExtra("EMAIL");
            String password = data.getStringExtra("PASSWORD");
            emailTxt.setText(email);
            passwordTxt.setText(password);
        }
    }

    @Override
    public void onBackPressed() {
        saveData();
        super.onBackPressed();
    }

    private void loadData(){
        SharedPreferences sp = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        String email = sp.getString("EMAIL","");
        String password = sp.getString("PASSWORD","");
        emailTxt.setText(email);
        passwordTxt.setText(password);
    }

    private void saveData(){
        SharedPreferences sp = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("EMAIL",emailTxt.getText().toString());
        editor.putString("PASSWORD",passwordTxt.getText().toString());
        editor.commit();
    }
}
