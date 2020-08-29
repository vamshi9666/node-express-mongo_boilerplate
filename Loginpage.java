package com.example.bugtracking;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginpage extends Activity {
	EditText Id,Password;
	Button sub;
	SQLiteDatabase db;
	String uvalue,pvalue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpage);
		final Globalclass globalvariable=(Globalclass)getApplicationContext();
		Id=(EditText) findViewById(R.id.Ei);
		 Password=(EditText) findViewById(R.id.Ep);
		 sub=(Button) findViewById(R.id.Es);
		 
		 db=openOrCreateDatabase("bug", Context.MODE_PRIVATE, null);
		 db.execSQL("create table if not exists Management(Id varchar,Password varchar,Email varchar,MobileNumber varchar)");
		 db.execSQL("create table if not exists develop(Id varchar,Password varchar,Email varchar,MobileNumber varchar)");
		 db.execSQL("create table if not exists test(Id varchar,Password varchar,Email varchar,MobileNumber varchar)");
		 sub.setOnClickListener(new View.OnClickListener() {

			 			@Override
			 			public void onClick(View v) {
			 				
			 				// TODO Auto-generated method stub
			 				if(Id.getText().toString().trim().length()==0||Password.getText().toString().trim().length()==0)
			 				{
			 					Toast.makeText(Loginpage.this, "ENTER LOGIN DETAILS", Toast.LENGTH_LONG).show();
			 					return;
			 				}
			 				uvalue=Id.getText().toString();
			 				pvalue=Password.getText().toString();			 				
							if(uvalue.equals("admin")&&pvalue.equals("123"))
			 				{	
			 					scan g=new scan();
			 					g.execute();
			 	 				Toast.makeText(Loginpage.this,"WELCOME ADMIN", Toast.LENGTH_SHORT).show();
			 					//  globalvariable.Setusername(Id.getText().toString());
			 					Intent i= new Intent(Loginpage.this,Adminhome.class);
			 					startActivity(i);
			 					return;
			 				}
			 				
			 				Cursor c= db.rawQuery("select * from Management where Id='"+Id.getText()+"' and Password='"+Password.getText()+"'", null);
			 				if(c.moveToFirst() )
			 				{
			 					scan g=new scan();
			 					g.onPreExecute();
			                     Toast.makeText(Loginpage.this,"WELCOME Manager", Toast.LENGTH_SHORT).show();
			 				     globalvariable.Setusername(Id.getText().toString());
			 					Intent i= new Intent(Loginpage.this,ManagerHome.class);
			 					startActivity(i);
			 					return;
			 				}
			 				Cursor b= db.rawQuery("select * from test where Id='"+Id.getText()+"' and Password='"+Password.getText()+"'", null);
			 				if(b.moveToFirst() )
			 				{
			 					scan g=new scan();
			 					g.onPreExecute();
			                     Toast.makeText(Loginpage.this,"WELCOME Tester", Toast.LENGTH_SHORT).show();
			 				     globalvariable.Setusername(Id.getText().toString());
			 					Intent i= new Intent(Loginpage.this,TestHome.class);
			 					startActivity(i);
			 					return;
			 				}
			 				
			 				Cursor a= db.rawQuery("select * from develop where Id='"+Id.getText()+"' and Password='"+Password.getText()+"'", null);
			 				if(a.moveToFirst() )
			 				{
			 					scan g=new scan();
			 					g.onPreExecute();
			                     Toast.makeText(Loginpage.this,"WELCOME Developer", Toast.LENGTH_SHORT).show();
			 					globalvariable.Setusername(Id.getText().toString());
			 					Intent i= new Intent(Loginpage.this,DevelopmentHome.class);
			 					startActivity(i);
			 					return;
			 				}
			 				
			 			
			 				else
			 				{
			 					Toast.makeText(Loginpage.this,"ENTER VALID LOGIN DETAILS" , Toast.LENGTH_SHORT).show();
			 					return;
			 				}
			 				
			 			}
			 		});
			 		
			 	}
			 	public class scan extends AsyncTask<String, String, String>{

			 		private ProgressDialog pd;

			 		protected void onPreExecute() {
			 			super.onPreExecute();
			 		 pd = new ProgressDialog(Loginpage.this);
			 		 pd.setTitle("Please Wait!!");
			 		 pd.setMessage("Logging you In....");
			 		 pd.setMax(10);
			 		 pd.show();
			 		}

			 		public void execute() {
						// TODO Auto-generated method stub
						
					}

					@Override
			 		protected String doInBackground(String... params) {
			 			// TODO Auto-generated method stub
			 			return null;
			 		}
			 	}
			 }
