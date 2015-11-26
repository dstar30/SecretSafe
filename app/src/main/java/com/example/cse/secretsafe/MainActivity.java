package com.example.cse.secretsafe;

import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;
    private ImageButton speakerButton1;
    private TextView speechToText1;
    String ps = "test";
    String currentPs;
    EditText email;


    // public static final String PREFS_NAME = "PREFERENCE_FILE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //speechToText will hold the phrase said by the user
        speechToText1 = (TextView) findViewById(R.id.speechToText1);
        speakerButton1 = (ImageButton) findViewById(R.id.speakerButton1);
        email = (EditText) findViewById(R.id.email);
        //sets the listener for the speaker
        speakerButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                //Prompts user to say a password.
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the Password...");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    speechToText1.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Device does not support speech recognition.",
                            Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });


        //listener for the register button
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });


        //listener for the login button
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, lockerHomeActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Processes the data after the user finishes saying a phrase.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    //Gathers the data and enters it into speechToText.
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                     currentPs = text.get(0);


                    if(email.getText().toString().equals("email") && currentPs.equals(ps)) {
                        Intent intent1 = new Intent(MainActivity.this, lockerHomeActivity.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials",Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            }

        }
    }
}


