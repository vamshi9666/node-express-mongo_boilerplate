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

public class DevelopmentHome extends Activity {
	 Button Bi,Proj,Pro,Lo;
	 TextView  DID;
	 SQLiteDatabase db;
		 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_development_home);
		final Globalclass globalvariable=(Globalclass)getApplicationContext();
 	   DID=(TextView)findViewById(R.id.textView2);
	DID.setText(globalvariable.GetUserName().toString());	   
	
	
	Bi=(Button)findViewById(R.id.Bi);
	Proj=(Button)findViewById(R.id.Projection);
	Pro=(Button)findViewById(R.id.Profile);
	Lo=(Button)findViewById(R.id.Logout);
	db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
	 db.execSQL("create table if not exists develop(Id varchar,Password varchar,Email varchar,MobileNumber vachar)");
	
	Bi.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(DevelopmentHome.this,BugInformation.class);
			startActivity(i);
		}
	});
	Proj.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(DevelopmentHome.this,Projection.class);
			startActivity(i);
		}
	});
	Lo.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(DevelopmentHome.this,Loginpage.class));
	}
		});


Pro.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
Cursor b=db.rawQuery("SELECT  * FROM develop WHERE Id='"+DID.getText()+"'", null);
      if(b.getCount()==0)
      {
    	     showMessage("Error","No records found");
    	     return;
      }
      StringBuffer buffer=new StringBuffer();
      while(b.moveToNext())
      {
    	    buffer.append("Id:" +b.getString(0)+"\n");	
    	    buffer.append("Password:" +b.getString(0)+"\n");
    	    buffer.append("Email:" +b.getString(0)+"\n");
    	    buffer.append("Mobilenumber:" +b.getString(0)+"\n");
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
   
















	