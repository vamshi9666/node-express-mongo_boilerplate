

package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Homepage extends Activity {
	Button mr,dr,tr,log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		
		mr=(Button)findViewById(R.id.Mr);
		dr=(Button)findViewById(R.id.Dr);
		tr=(Button)findViewById(R.id.Tr);
		log=(Button)findViewById(R.id.Li);
		
	    mr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a2=new Intent(Homepage.this,ManagementRegistration.class);
				startActivity(a2);
			}
		});
	    dr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a2=new Intent(Homepage.this,DevelopRegistration.class);
				startActivity(a2);
				
			}
		});
        tr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a2=new Intent(Homepage.this,TestRegistration.class);
				startActivity(a2);
				
			}
		});
log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a2=new Intent(Homepage.this,Loginpage.class);
				startActivity(a2);
				
			}
		});
		
		
	}

	

}
