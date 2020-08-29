package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class THAtributes extends Activity {
	Button submit;
	EditText bugdes;
	TextView tid,bid;
	SQLiteDatabase db;
	Spinner sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thatributes);
		final Globalclass globalvariable = (Globalclass)getApplicationContext();
		
		tid=(TextView)findViewById(R.id.textView3);
		tid.setText(globalvariable.GetUserName().toString());
		bid=(TextView)findViewById(R.id.textView4);
		bid.setText(generatePIN());
		
		
		sp=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> ar=ArrayAdapter.createFromResource(this,R.array.week, android.R.layout.simple_expandable_list_item_1);
		ar.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(ar);
	
	
	
		bugdes=(EditText)findViewById(R.id.Bd);
		submit=(Button)findViewById(R.id.Submit);
		
		db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists bugdess(tid varchar,bid varchar,sp varchar,bd varchar,sta varchar) ");
	    submit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub 
				if(bugdes.getText().toString().trim().length()==0)
				{
					Toast.makeText(THAtributes.this,"enter all the fields",Toast.LENGTH_SHORT).show();
					return;
					
				}
				String sta="waiting for developer reply";
				db.execSQL("insert into bugdess values('"+tid.getText()+"','"+bid.getText()+"','"+sp.getSelectedItem()+"','"+bugdes.getText()+"','"+sta+"');");
				Toast.makeText(THAtributes.this,"submit",Toast.LENGTH_SHORT).show();
				clr();
				}
				});
			}
	
			public void clr() {
				bugdes.setText("");
				
			}
			
			public String generatePIN()
			{
				int randomPIN=(int)(Math.random()*90000000)+10000000;
				final String spin=String.valueOf(randomPIN);
				return spin;
}
}
