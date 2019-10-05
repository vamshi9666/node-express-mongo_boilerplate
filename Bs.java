package com.example.bugtracking;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Bs extends Activity {
	ImageView out;
	TextView Bug;
	SQLiteDatabase db;
	ListView l;
	EditText t1;
	 ArrayList<String> list1;
	 ArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bs);
		final Globalclass globalvariabel = (Globalclass)getApplicationContext();
		Bug=(TextView)findViewById(R.id.tid);
		Bug.setText(globalvariabel.GetUserName().toString());
		out=(ImageView)findViewById(R.id.imageView1);
		t1=(EditText)findViewById(R.id.search);
		db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists bugdes(tid varchar,bid varchar,sp varchar,bd varchar) ");
		
		l = (ListView) findViewById(R.id.listView1);
		final ArrayList<String> list = new ArrayList<String>();
		 list1 = new ArrayList<String>();
		 Cursor res=db.rawQuery("SELECT * FROM bugdes", null);
		if(res.getCount()!=0)
		{
			while (res.moveToNext())
			{
				list.add("Tester Id :   "+res.getString(0)+"\n"+" Bug Id:"+res.getString(1)+"\n"+"Type:   "+res.getString(2)+"\n"+"deatils:   "+res.getString(3)+"\n");
				list1.add(res.getString(0));
			}
		}
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		l.setAdapter(adapter);
		
		
		// (close)
		
		/// search (start)
		
		t1.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Bs.this.adapter.getFilter().filter(s); 
				
			}
			
		});
		l.setAdapter(adapter);
		
		
	}
		//
		///logout

	 public void showMessage(String title,String message)
	    {
	    	Builder builder=new Builder(this);
	    	builder.setCancelable(true);
	    	builder.setTitle(title);
	    	builder.setMessage(message);
	    	builder.show();
		}
	 
	 
}

