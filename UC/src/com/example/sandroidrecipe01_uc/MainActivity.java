
///Problem Statement: UC(an acronym for Units Converter) is an app that lets you convert between types of units. For example, you can convert a specific number of degree Celsius to its equivalent number of degrees Fahrenheit, a specific number of pounds to its equivalent number of kilograms, and so on
package com.example.sandroidrecipe01_uc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private int position = 0;
	private double[] multipliers = { 
			0.0015625,
			101325.0,
			100000.0,
			746.0,
			0.833,
			1/0.833,
			640.0,
			1/746.0,
			1/735.499
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText etUnits = (EditText) findViewById(R.id.units);
		final Spinner spnConversions = (Spinner) findViewById(R.id.conversions);
		ArrayAdapter<CharSequence> aa;
		aa = ArrayAdapter.createFromResource(this, R.array.conversions, android.R.layout.simple_spinner_item);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnConversions.setAdapter(aa);
		AdapterView.OnItemSelectedListener oisl;
		oisl = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				position = pos;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				System.out.println("nothing");
			}
		};
		spnConversions.setOnItemSelectedListener(oisl);
	
	
	final Button btnClear = (Button)findViewById(R.id.clear);
	AdapterView.OnClickListener ocl;
	ocl = new AdapterView.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			etUnits.setText("");
		}
	};
	btnClear.setOnClickListener(ocl);
	btnClear.setEnabled(false);
	
	final Button btnConvert = (Button)findViewById(R.id.convert);
	ocl = new AdapterView.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			String text = etUnits.getText().toString();
			double input = Double.parseDouble(text);
			double result = 0;
			if(position == 3)
				result = input*9.0/5.0 + 32;
			else
				if(position == 4)
					result = (input - 32) * 5.0/9.0;
				else
					result = input*multipliers[position];
			etUnits.setText(""+result);
		}
	};
	btnConvert.setOnClickListener(ocl);
	btnConvert.setEnabled(false);
	
	Button btnClose = (Button)findViewById(R.id.close);
	ocl = new AdapterView.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			finish();
		}
	};
	btnClose.setOnClickListener(ocl);
	TextWatcher tw;
	tw = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			if(etUnits.getText().length() == 0)
			{
				btnClear.setEnabled(false);
				btnConvert.setEnabled(false);
			}
			else
			{
				btnClear.setEnabled(true);
				btnConvert.setEnabled(true);
			}
			}
		
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};
	etUnits.addTextChangedListener(tw);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
