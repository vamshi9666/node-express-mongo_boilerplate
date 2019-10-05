package com.example.bugtracking;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DevelopRegistration extends Activity {
	EditText Id,Password,Email,MobileNumber;
	Button sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_develop_registration);
		
		 Id=(EditText) findViewById(R.id.Edid);
		 Password=(EditText) findViewById(R.id.Pass);
		 Email=(EditText) findViewById(R.id.Eadd);
		 MobileNumber=(EditText) findViewById(R.id.Mno);
		 sub=(Button) findViewById(R.id.s);
		 
		 db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		 db.execSQL("create table if not exists develop(Id varchar,Password varchar,Email varchar,MobileNumber vachar)");
		 sub.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 
					if(Id.getText().toString().trim().length()==0||Password.getText().toString().trim().length()==0||Email.getText().toString().trim().length()==0||MobileNumber.getText().toString().trim().length()==0)
					{
					Toast.makeText(DevelopRegistration.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
					
					return;
					}
					else if(MobileNumber.getText().toString().trim().length()!=10)
					{
					Toast.makeText(DevelopRegistration.this, "Enter 10 digit Mobile No", Toast.LENGTH_SHORT).show();
					return;
					}
					db.execSQL("insert into develop values('"+Id.getText()+"','"+Password.getText()+"','"+Email.getText()+"','"+MobileNumber.getText()+"')");
					Toast.makeText(DevelopRegistration.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
					
					Intent i=new Intent(DevelopRegistration.this,Homepage.class);
					startActivity(i);
				}
	
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.develop_registration, menu);
		return true;
	}

}
