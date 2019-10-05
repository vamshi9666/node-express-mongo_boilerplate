package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Adminhome extends Activity {
	Button mi,ti,di,pi,lo;
	TextView aid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_admin_home);
	aid=(TextView)findViewById(R.id.Aid);
	mi=(Button)findViewById(R.id.Mi);
	ti=(Button)findViewById(R.id.Ti);
	di=(Button)findViewById(R.id.Di);
	pi=(Button)findViewById(R.id.Pi);
	lo=(Button)findViewById(R.id.Alogout);
	
mi.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i1=new Intent(Adminhome.this,ManagementInformation.class);
			startActivity(i1);
		}
	});

ti.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i2=new Intent(Adminhome.this,TestInformation.class);
		startActivity(i2);
	}
});
di.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i3=new Intent(Adminhome.this,DevelopmentInformation.class);
		startActivity(i3);
	}
});
pi.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i4=new Intent(Adminhome.this,ProjectInformation.class);
		startActivity(i4);
	}
});
lo.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i5=new Intent(Adminhome.this,Loginpage.class);
		startActivity(i5);
	}
});
	
	}
}

	
