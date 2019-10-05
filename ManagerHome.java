package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ManagerHome extends Activity {
	
	Button Bug,Ap,Mp,Ml;
	TextView MID;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_home);
		final Globalclass globalvariable=(Globalclass)getApplicationContext();
 	   MID=(TextView)findViewById(R.id.textView2);
	MID.setText(globalvariable.GetUserName().toString());	   
		
	
	Bug=(Button)findViewById(R.id.BugSta);
	Ap=(Button)findViewById(R.id.Ap);
	Mp=(Button)findViewById(R.id.Mp);
	Ml=(Button)findViewById(R.id.Ml);
	
	db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
	db.execSQL("create table if not exists Management(Id varchar,Password varchar,Email varchar,MobileNumber vachar)");

	
	Bug.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(ManagerHome.this,BugStatus.class);
			startActivity(i);
		}
	});
	Ap.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(ManagerHome.this,AddProject.class);
			startActivity(i);
		}
	});
	Ml.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ManagerHome.this,Loginpage.class));
	}
		});


Mp.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
Cursor a=db.rawQuery("SELECT  * FROM Management WHERE Id='"+MID.getText()+"'", null);
      if(a.getCount()==0)
      {
    	     showMessage("Error","No records found");
    	     return;
      }
      StringBuffer buffer=new StringBuffer();
      while(a.moveToNext())
      {
    	    buffer.append("Id:" +a.getString(0)+"\n");	
    	    buffer.append("Password:" +a.getString(0)+"\n");
    	    buffer.append("Email:" +a.getString(0)+"\n");
    	    buffer.append("Mobilenumber:" +a.getString(0)+"\n");
      }
    	    			
     showMessage("Id details",buffer.toString());
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
   
















	