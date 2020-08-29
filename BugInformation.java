package com.example.bugtracking;

import java.util.ArrayList;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BugInformation extends Activity {
	ImageView out;
	TextView Bug;
	SQLiteDatabase db;
	ListView l;
	EditText t1;
	 ArrayList<String> list1;
	 ArrayAdapter adapter;
	 String tid,bid,ssid,uemail,ty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bug_status);
		final Globalclass globalvariabel = (Globalclass)getApplicationContext();
		Bug=(TextView)findViewById(R.id.textView2);
		Bug.setText(globalvariabel.GetUserName().toString());
		out=(ImageView)findViewById(R.id.imageView1);
		t1=(EditText)findViewById(R.id.search);
		db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists develop(Id varchar,Password varchar,Email varchar,MobileNumber vachar)");
        
		l = (ListView) findViewById(R.id.listView1);
		final ArrayList<String> list = new ArrayList<String>();
		 list1 = new ArrayList<String>();
		 Cursor res=db.rawQuery("SELECT * FROM bugdess", null);
		if(res.getCount()!=0)
		{
			while (res.moveToNext())
			{
				list.add("TESTER Id :   "+res.getString(0)+"\n"+"BUG ID:"+res.getString(1)+"\n"+"TYPE OF BUG "+res.getString(2)+"\n"+"BUG DESCRIPTION: "+res.getString(3)+"\n"+"status "+res.getString(4)+"\n");
	            list1.add(res.getString(1));
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
				BugInformation.this.adapter.getFilter().filter(s); 
				
			}
			
		});
		l.setAdapter(adapter);
		l.setOnItemClickListener(new OnItemClickListener()
		{
			  public void onItemClick(AdapterView<?> parent, View view,
	                     int position, long id) {
                  
				  		String  itemValue    = (String) l.getItemAtPosition(position);
				  		String bid = itemValue.substring(0, itemValue.indexOf('\n'));
	                    int itemPosition     = position;
	                    String item=list1.get(itemPosition);
	                    
	                    Intent i = new Intent(getApplicationContext(),Developer.class);
						Bundle b = new Bundle();
						b.putString("Name", item);
						i.putExtras(b);
						startActivity(i);
	                  }
	    
	             });
		
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

