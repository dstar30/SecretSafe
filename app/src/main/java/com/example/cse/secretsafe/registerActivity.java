package com.example.cse.secretsafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;


public class registerActivity extends Activity{

    public static final String PREFS_NAME = "PREFERENCE_FILE";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button registerButton = (Button)findViewById(R.id.registerButton2);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);
        //setSilent(silent);
        setContentView(R.layout.register);
    }



}
