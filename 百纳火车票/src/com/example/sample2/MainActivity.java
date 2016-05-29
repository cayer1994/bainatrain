package com.example.sample2;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AliasActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;




public class MainActivity extends Activity {
	DBMethod dbm;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	 super.onCreate(savedInstanceState);
	  dbm=new DBMethod(this);
	  setContentView(R.layout.activity_main);
	  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public void GoToTidSelect(View v)//切换到车次查询页面
	{
		setContentView(R.layout.tid_select);
	}
	
	public void GoBack(View v)//返回主页面
	{
		setContentView(R.layout.activity_main);
	}
	
	public void GoToStationSelect(View v)
	{
		setContentView(R.layout.station_select);//切换到车站查询页面
	}
    
	public void GoToZZSelect(View v)
	{
		setContentView(R.layout.zz_select);
	}
}

