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

public class ManagementRegistration extends Activity {
	EditText Id,Password,Email,MobileNumber;
	Button sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management_registration);
		
		 Id=(EditText) findViewById(R.id.Emi);
		 Password=(EditText) findViewById(R.id.Pas);
		 Email=(EditText) findViewById(R.id.Ea);
		 MobileNumber=(EditText) findViewById(R.id.Mn);
		 sub=(Button) findViewById(R.id.Sub);
		 
		 db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		 db.execSQL("create table if not exists Management(Id varchar,Password varchar,Email varchar,MobileNumber varchar)");
		 sub.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 
					if(Id.getText().toString().trim().length()==0||Password.getText().toString().trim().length()==0||Email.getText().toString().trim().length()==0||MobileNumber.getText().toString().trim().length()==0)
					{
					Toast.makeText(ManagementRegistration.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
					return;
					}
					else if(MobileNumber.getText().toString().trim().length()!=10)
					{
					Toast.makeText(ManagementRegistration.this, "Enter 10 digit Mobile No", Toast.LENGTH_SHORT).show();
					return;
					}
					db.execSQL("insert into Management values('"+Id.getText()+"','"+Password.getText()+"','"+Email.getText()+"','"+MobileNumber.getText()+"')");
					Toast.makeText(ManagementRegistration.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
					
					Intent i=new Intent(ManagementRegistration.this,Homepage.class);
					startActivity(i);
				}
	
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.management_registration, menu);
		return true;
	}

}
