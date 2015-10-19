package com.example.cse.secretsafe;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class lockerHomeActivity extends Activity{

    protected static final int RESULT_SPEECH = 1;
    private ImageButton speakerButton;
    private TextView speechToText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locker_home);
        //speechToText will hold the phrase said by the user, and prints it to the screen.
        speechToText = (TextView) findViewById(R.id.speechToText);
        speakerButton = (ImageButton) findViewById(R.id.speakerButton);
        //sets the listener for the speaker
        speakerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                //Prompts user to say a password.
                 intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say the Password...");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    speechToText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Device does not support speech recognition.",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

                    speechToText.setText(text.get(0));
                }
                break;
            }

        }
    }

}
