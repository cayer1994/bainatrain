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

}

