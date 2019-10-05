package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog.Builder;


public class TestHome extends Activity {
	Button Ab,Bs,Pro,Lo;
    TextView tid;
    SQLiteDatabase db;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_home);
		final Globalclass globalvariable=(Globalclass)getApplicationContext();
	    	   tid=(TextView)findViewById(R.id.textView1);
		tid.setText(globalvariable.GetUserName().toString());	   
			
		 Ab=(Button)findViewById(R.id.Ab);
		Bs=(Button)findViewById(R.id.Bs);
		Pro=(Button)findViewById(R.id.Pro);
		Lo=(Button)findViewById(R.id.Lo);
		
		db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists test(Id varchar,Password varchar,Email varchar,MobileNumber varchar)");
		
		 
	 	   Ab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TestHome.this,THAtributes.class));
			
			}
		});
	    Bs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(TestHome.this,BugStatus.class));
			
				
			}
		});
	    Lo.setOnClickListener(new OnClickListener() {
			
	 			@Override
	 			public void onClick(View v) {
	 				// TODO Auto-generated method stub
	 				startActivity(new Intent(TestHome.this,Homepage.class));
	 	}
	 		});
	

        Pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		Cursor c=db.rawQuery("SELECT  * FROM test WHERE Id='"+tid.getText()+"'", null);
		      if(c.getCount()==0)
		      {
		    	     showMessage("Error","No records found");
		    	     return;
		      }
		      StringBuffer buffer=new StringBuffer();
		      while(c.moveToNext())
		      {
		    	    buffer.append("Id:" +c.getString(0)+"\n");	
		    	    buffer.append("Password:" +c.getString(0)+"\n");
		    	    buffer.append("Email:" +c.getString(0)+"\n");
		    	    buffer.append("Mobilenumber:" +c.getString(0)+"\n");
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
	       
        
   
        
        
        
        
        
        
        
        
        
        
        
        
        

			