package com.example.actionbarassignmenttwo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.firstappassignmentone.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.actionbarassignmenttwo.app.R.layout.activity_main);

        //enable app icon as the Up button - JG: Seems to work on version 2.3 regardless of whether
        //or not this method is called. Only difference appears to be on the main activity the app
        //icon has a "<" (back symbol) to the left of it when called.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.actionbarassignmenttwo.app.R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handles presses of action bar items
        switch (item.getItemId()) {
            case com.example.actionbarassignmenttwo.app.R.id.action_search:
                openSearch();
                return true;
            case com.example.actionbarassignmenttwo.app.R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearch() {

    }

    private void openSettings() {

    }

    //Called when user clicks Send button (via onClick attribute)
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(com.example.actionbarassignmenttwo.app.R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}
