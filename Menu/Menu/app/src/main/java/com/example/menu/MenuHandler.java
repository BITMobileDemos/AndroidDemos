package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
// class to handle the menu item clicks
public class MenuHandler extends Activity {
	
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
