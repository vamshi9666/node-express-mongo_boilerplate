package  com.example.bugtracking;

import android.app.Application;

public class Globalclass extends Application {
	public String UserName;
	public String Password;
	public String GetUserName()
	{
		return UserName;
	}
	public void Setusername(String  _susername)
	{
	UserName =_susername;
		}
	public String GetPassword()
	{
		return Password;
		
	}
	public void SetPassword(String _spassword)
	{
		Password= _spassword;
	}

}
