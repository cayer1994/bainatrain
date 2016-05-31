package com.example.sample2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AliasActivity;
import android.app.Dialog;
import android.app.Notification.Action.Builder;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;




public class MainActivity extends Activity {
	DBMethod dbm;
	List<ViewModel> vml=new ArrayList<ViewModel>();
	List<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
	String[] item=new String[]{"Tid","name","type","startstation","terminus","beginstation","begintime","stopstation","stoptime"};
	int[] list_id=new int[]{R.id.item_tv1,R.id.item_tv2,R.id.item_tv3,R.id.item_tv4,R.id.item_tv5,R.id.item_tv6,R.id.item_tv8,R.id.item_tv9,R.id.item_tv10};
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
    
	public void GoToZZSelect(View v)//切换到站站查询
	{
		setContentView(R.layout.zz_select);
	}
	
	public void TidSelect(View v)//车次查询
	{
		
		EditText tv1=(EditText)findViewById(R.id.tid_et1);
		String tid=tv1.getText().toString().trim();
		ViewModel vm=dbm.IdSelect(tid);
		vml.clear();
		vml.add(vm);
		this.convert();
		SimpleAdapter sa=new SimpleAdapter(this,data,R.layout.list_item,item,list_id);
		setContentView(R.layout.select_result);
		ListView lv=(ListView)findViewById(R.id.result);
		lv.setAdapter(sa);	
	}
	
	//车站查询
	public void StationSelect(View v)
	{
		EditText ed=(EditText)findViewById(R.id.station_ed1);
		String station=ed.getText().toString().trim();
		vml.clear();
		vml=dbm.StationSelect(station);
		this.convert();
		SimpleAdapter sa=new SimpleAdapter(this,data,R.layout.list_item,item,list_id);
		setContentView(R.layout.select_result);
		ListView lv=(ListView)findViewById(R.id.result);
		lv.setAdapter(sa);
		
		
	}
	
	//站站查询
	public void ZZSelect(View v)
	{
		vml.clear();
		EditText et1=(EditText)findViewById(R.id.zz_et1);
		EditText et2=(EditText)findViewById(R.id.zz_et2);
		EditText et3=(EditText)findViewById(R.id.zz_et3);
		CheckBox cb=(CheckBox)findViewById(R.id.zz_cb1);
		if(cb.isChecked())
		{
			String start=et1.getText().toString().trim();
			String transfer=et2.getText().toString().trim();
			String stop=et3.getText().toString().trim();
			try{
			vml=dbm.Transfer(start, transfer, stop);
			}catch(Exception e)
			{
				
			}
		}
		else
		{
			String start=et1.getText().toString().trim();
			String stop=et3.getText().toString().trim();
			try{
			vml=dbm.SSSelect(start, stop);
			}catch(Exception e)
			{
				
			}
		}
		this.convert();
		SimpleAdapter sa=new SimpleAdapter(this,data,R.layout.list_item,item,list_id);
		setContentView(R.layout.select_result);
		ListView lv=(ListView)findViewById(R.id.result);
		lv.setAdapter(sa);
	}
	
	
	//切换到车站添加
	public void GoAddStation(View v)
	{
		setContentView(R.layout.add_station);
	}
	
	public void GoToMethod(View v)//切换到功能页面
	{
		setContentView(R.layout.method);
	}
	
	//车站添加
	public void AddStation(View v)
	{
		EditText et1=(EditText)findViewById(R.id.add_et1);
		EditText et2=(EditText)findViewById(R.id.add_et2);
		String name=et1.getText().toString().trim();
		String shorted=et2.getText().toString().trim();
		boolean flag=dbm.InsertStation(name, shorted);
		android.app.AlertDialog.Builder b=new AlertDialog.Builder(this);
	   if(flag)
		   b.setMessage("添加成功！");
	   else
		   b.setMessage("添加失败！");
	   b.create().show();		
		
	}
	
	//切换到列车添加页面
	public void GoAddTrain(View v)
	{
		setContentView(R.layout.add_train);
		
	}
	
	//列车添加
	public void AddTrain(View v)
	{
		
	}
	
	
	//辅助功能：将vml转换为data，方便存入ListView中
	public void convert()
	{
		data.clear();
		for(ViewModel vm:vml)
		{
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("Tid", vm.id);
			hm.put("name", vm.name);
			hm.put("type", vm.type);
			hm.put("startstation", vm.startstation);
			hm.put("terminus", vm.arrivestation);
			hm.put("beginstation", vm.beginstation);
			hm.put("begintime", vm.begintime);
			hm.put("stopstation", vm.stopstation);
			hm.put("stoptime", vm.stoptime);
			data.add(hm);
		}
	}
	
	

}

