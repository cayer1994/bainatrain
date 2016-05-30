package com.example.sample2;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public void GoToTidSelect(View v)//�л������β�ѯҳ��
	{
		setContentView(R.layout.tid_select);
	}
	
	public void GoBack(View v)//������ҳ��
	{
		setContentView(R.layout.activity_main);
	}
	
	public void GoToStationSelect(View v)
	{
		setContentView(R.layout.station_select);//�л�����վ��ѯҳ��
	}
    
	public void GoToZZSelect(View v)//�л���վվ��ѯ
	{
		setContentView(R.layout.zz_select);
	}
	
	public void TidSelect(View v)//���β�ѯ
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
	
	
	
	
	//�������ܣ���vmlת��Ϊdata���������ListView��
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

