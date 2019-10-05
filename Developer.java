package com.example.bugtracking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Developer extends Activity {
	 Button UpdateBugSta;
	 EditText EntBugDes;
	 TextView Bid,Did;
	 SQLiteDatabase db;
	 
	 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_developer);
		final Globalclass Globalvariable = (Globalclass)getApplicationContext();
		Did=(TextView)findViewById(R.id.textView2);
		Did.setText(Globalvariable.GetUserName().toString());
		Bundle b=getIntent().getExtras();
		Bid=(TextView)findViewById(R.id.textView4);
		Bid.setText(b.getCharSequence("Name"));
		EntBugDes=(EditText)findViewById(R.id.editText1);
		UpdateBugSta=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists bugdess(tid varchar,bid varchar,sp varchar,bd varchar,sta varchar) ");
		UpdateBugSta.setOnClickListener(new OnClickListener() {
		
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			db.execSQL("update bugdess set sta='"+EntBugDes.getText()+"' where bid='"+Bid.getText()+"'");
			Toast.makeText(Developer.this,"Bug Solved Successfully",Toast.LENGTH_SHORT).show();
	        Intent a=new Intent(Developer.this,DevelopmentHome.class);
	        startActivity(a);
		}
	});
	};
	
}
	
		