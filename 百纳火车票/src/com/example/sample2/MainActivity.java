package com.example.sample2;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {
	DBMethod dbm;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	  dbm=new DBMethod(this);
	  
	}

}

