package com.example.cse.secretsafe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class lockerHomeActivity extends Activity{

    private ListView listOfSecrets;
    private  ArrayList<String> secrets;
    private ArrayAdapter<String> secretsAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locker_home);
        //setupListViewListener();
         listOfSecrets = (ListView)findViewById(R.id.bucket);
        secrets = new ArrayList<>();


        secretsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,secrets);

        listOfSecrets.setAdapter(secretsAdapter);
        secrets.add("Secret One");
        secrets.add("Secret Two!");
        setupListViewListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Add secrets to the list
    public void onAddSecret(View v){
        EditText newSecret = (EditText)findViewById(R.id.newSecret);
        String secretText = newSecret.getText().toString();
        secretsAdapter.add(secretText);
        newSecret.setText("");


    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        listOfSecrets.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        secrets.remove(pos);
                        // Refresh the adapter
                        secretsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });
    }


}