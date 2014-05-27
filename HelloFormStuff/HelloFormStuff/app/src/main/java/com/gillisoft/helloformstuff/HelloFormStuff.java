package com.gillisoft.helloformstuff;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HelloFormStuff extends Activity {
	
	//for DatePicker stuff
	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear, mMonth, mDay;
	static final int DATE_DIALOG_ID = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);      
        
        //Button
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(HelloFormStuff.this, "Beep Bop", Toast.LENGTH_SHORT).show();
            }
        });
        
        //EditText
        final EditText editText = (EditText) findViewById(R.id.edittext);
        editText.setOnKeyListener(new OnKeyListener() {
        	public boolean onKey(View v, int keyCode, KeyEvent event) {
        		//if key-down even on the "enter" button
        		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
        			Toast.makeText(HelloFormStuff.this, editText.getText(), Toast.LENGTH_LONG).show();
        			return true;
        		}//if
        		return false;
        	}//onKey()
        });//OnKeyListener
        
        
        //CheckBox
        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks, depending on whether it's now checked
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(HelloFormStuff.this, "Selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HelloFormStuff.this, "Not selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        //RadioButtons
        final RadioButton radio_red = (RadioButton) findViewById(R.id.radio_red);
        final RadioButton radio_blue = (RadioButton) findViewById(R.id.radio_blue);
        radio_red.setOnClickListener(radioListener);
        radio_blue.setOnClickListener(radioListener);
        
        //ToggleButton
        final ToggleButton togglebutton = (ToggleButton) findViewById(R.id.togglebutton);
        togglebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (togglebutton.isChecked()) {
                    Toast.makeText(HelloFormStuff.this, "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HelloFormStuff.this, "Not checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        //RatingBar
        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(HelloFormStuff.this, "New Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
        
        //DatePicker stuff
        // capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();

        
        //Spinner of planets
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //set the OnItemSelectedListener (defined as a nested class below)
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
        
        //AutoCompleteTextView
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> tvAdapter = new ArrayAdapter<String>(this, R.layout.list_item, countries);
        textView.setAdapter(tvAdapter);

        
        
    }//onCreate()
    //*******************************************************************************************
    
    
    //nested class for Spinner selection
    public class MyOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {
          Toast.makeText(parent.getContext(), "The planet is " +
              parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }
        public void onNothingSelected(AdapterView parent) {
          // Do nothing.
        }
    }
    
    
    // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
        
    // the callback received when the user "sets" the date in the DatePickerDialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
    
    //Activity callback method -- passed integer ID given to showDialog(int) (called by the button's View.OnClickListener)
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
        	//creates a new DatePickerDialog with DatePickerDialog.OnDateSetListener mDateSetListener (defined above)
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
                
            
    //OnClickListener for RadioButtons
    private OnClickListener radioListener = new OnClickListener() {
    	public void onClick(View v) {
    		RadioButton rb = (RadioButton)v;
    		Toast.makeText(HelloFormStuff.this, rb.getText(), Toast.LENGTH_SHORT).show();
    	}
    };
    
}








