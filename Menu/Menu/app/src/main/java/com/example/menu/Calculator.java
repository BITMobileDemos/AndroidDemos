package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Calculator extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.  
		// It populates using the res/menu/{classname}.xml file.
		getMenuInflater().inflate(R.menu.activity_calculator, menu);
		return true;
	}

	/**
	 * Handling menu item clicks.  If you successfully handle an item click
	 * return true, else return false.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.activity1:
			startActivity(new Intent(this, MainActivity.class));
			return true;
		case R.id.calculator:
			startActivity(new Intent(this, Calculator.class));
			return true;
		case R.id.activity2:
			startActivity(new Intent(this, Second.class));
			return true;
		default:
            return super.onOptionsItemSelected(item);
		}
	}
}
