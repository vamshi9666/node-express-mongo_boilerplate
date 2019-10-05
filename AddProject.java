package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddProject extends Activity {
	Button Sub;
	TextView MID,EID;
	EditText Pname,Type,pdes;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project);
		final Globalclass globalvariable=(Globalclass)getApplicationContext();
	 	   MID=(TextView)findViewById(R.id.textView1);
		MID.setText(globalvariable.GetUserName().toString());	   
			
	Sub=(Button)findViewById(R.id.Ps);
	pdes=(EditText)findViewById(R.id.editText1);
	MID=(TextView)findViewById(R.id.MID);

	Pname=(EditText)findViewById(R.id.Pn);
	Type=(EditText)findViewById(R.id.Type);
	
	db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
	db.execSQL("create table if not exists ap(Pname varchar,Type varchar);");
	db.execSQL("create table if not exists Managemenst(Id varchar,Password varchar,Email varchar,MobileNumber vachar)");
	
	Sub.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub 
		if(Pname.getText().toString().trim().length()==0)
		{
			Toast.makeText(AddProject.this,"enter all fields",Toast.LENGTH_SHORT).show();
			return;
			
		}
		db.execSQL("insert into ap values('"+Pname.getText()+"','"+Type.getText()+"')");
		Toast.makeText(AddProject.this,"Submit",Toast.LENGTH_SHORT).show();
		}
		});
	}
	    		
		
		


	public void showMessage(String title,String message)
	{
		Builder builder=new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}
	}
	   



















	
	
		
	

	