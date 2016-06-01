package com.example.sample2;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

public class DBMethod {
	SQLiteDatabase db;
	
    DBHelper dbh;
    
    public DBMethod(Context context)
    {
    	dbh=new DBHelper(context);
    	db=dbh.getWritableDatabase();
    }
    
  //���ӹ��ܣ������г�
  // ��id�Ѵ��ڣ��򷵻�Id�Ѵ���
    //��ʼ��վ�����ڻ��յ�վ�����ڣ��򷵻ػ�վ������
    //�ɹ��򷵻سɹ�
    public String InsertTrain(String id,String name,String type,String start,String stop)
    {
    	
       boolean flag=this.IdIsIn(id);
       if(flag)//id�Ƿ����
    	   return "Id�Ѵ���";
       else
       {
    	   int sid1=this.SnameIsIn(start);
    	   int sid2=this.SnameIsIn(stop);
    	   if(sid1<=0 | sid2<=0)
    		   return "��վ������";
    	   else
    	   {
    		   String sql="insert into train values(?,?,?,?,?)";
        	   Object[] obj=new Object[]{id,name,start,stop,type};
        	   db.execSQL(sql,obj);//����
        	   int num=this.NumOfTrain();
        	   return "�ɹ�";
    	   }
    			
       }
    }
    
    //���ӹ��ܣ������վ
    public boolean InsertStation(String name,String shorted)
    {
        int num=this.SnameIsIn(name);
        if(num>0)
        	return false;//��վ�����ڣ�����
        else
        {
        	String sql="insert into station values(null,?,?)";
        	Object[] obj=new Object[]{name,shorted};
        	db.execSQL(sql,obj);
        	num=this.NumOfStation();
        	return true;      	
        }
    }
    
    //���ӹ��ܣ�����һ���г���վ
    //����վ������ʱ���س�վ������
    //����ϵ�Ѵ���ʱ���ع�ϵ�Ѵ���
   public String InsertRelation(String id,String name,String arrivetime,String starttime)
   {
	  int sid=this.SnameIsIn(name);
	  if(sid<=0)
		  return "��վ������";
	  else
	  {
		  if(this.TSisIn(id, String.valueOf(sid)))
				  return "��ϵ�Ѵ���";
		  else
		  {
			  String sql="insert into relation values(null,?,?,?,?)";
			  Object[] obj=new Object[]{id,sid,arrivetime,starttime};
			  db.execSQL(sql, obj);
			  int num=this.NumOfRelation();
			  return "�ɹ�";
		  }
	  }
	  
   }
   
   //�������ܣ�
   //�Ƿ����Tid=id���г�
   public boolean IdIsIn(String id)
   {
	   String sql="select * from train where Tid=? ";
	   String[] s=new String[]{id};
	   Cursor c=db.rawQuery(sql, s);
	   int num=c.getCount();
	   if(num>0)
		   return true;
	   else
		   return false;
   }
   
   //�Ƿ����վ��Ϊname�ĳ�վ�����򷵻�Sid�������򷵻�-1
   public int SnameIsIn(String name)
   {
	   String sql="select * from station where Sname=?";
	   String[] s=new String[]{name};
	   Cursor c=db.rawQuery(sql, s);
	   int num=c.getCount();
	   if(num>0)
	   {
		   c.moveToFirst();
		   String sid=c.getString(c.getColumnIndex("Sid"));
		   return Integer.parseInt(sid);
	   }
	   else
		   return num;
   }
   
   //�Ƿ����TidΪId��SidΪsid�Ĺ�ϵ�����򷵻�true�������򷵻�false
   public boolean TSisIn(String id,String sid)
   {
	   String sql="select * from relation where Tid=? and Sid=?";
	   String[] s=new String[]{id,sid};
	   Cursor c=db.rawQuery(sql,s);
	   int num=c.getCount();
	   if(num>0)
		   return true;
	   else
		   return false;
   }
   
   //train���ڹ��ж���������������Ŀ
   public int NumOfTrain()
   {
	   String sql="select * from train";
	   Cursor c=db.rawQuery(sql, null);
	   return c.getCount();
   }
   
   //station���ڴ��ڶ��ٳ�վ��������Ŀ
   public int NumOfStation()
   {
	   String sql="select * from station";
	   Cursor c=db.rawQuery(sql, null);
	   return c.getCount();
   }
 //relation���ڴ��ڶ��ٹ�ϵ��������Ŀ
   public int NumOfRelation()
   {
	   String sql="select * from relation";
	   Cursor c=db.rawQuery(sql, null);
	   return c.getCount();
   }
   
   //ɾ��train����ȫ������
   public boolean DeleteOfTrain()
   {
	   String sql="delete from train";
	   db.execSQL(sql);
	   int num=this.NumOfTrain();
	   if(num<=0)
		   return true;
	   else
		   return false;
   }
   //ɾ��station����ȫ������
   public boolean DeleteOfStation()
   {
	   String sql="delete from station";
	   db.execSQL(sql);
	   int num=this.NumOfStation();
	   if(num<=0)
		   return true;
	   else
		   return false;
   }
   //ɾ��relation����ȫ������
   public boolean DeleteOfRelation()
   {
	   String sql="delete from relation";
	   db.execSQL(sql);
	   int num=this.NumOfRelation();
	   if(num<=0)
		   return true;
	   else
		   return false;
   }
   
   
   
   
   
   //���β�ѯ
   public ViewModel IdSelect(String id)
   {
	   ViewModel vm=new ViewModel();
	   boolean falg=this.IdIsIn(id);//���л��Ƿ����
	   if(!falg)
		   return vm;
	   else
	   {
		   String sql="select * from train where Tid=?";
		   String[] s=new String[]{id};
		   Cursor c=db.rawQuery(sql, s);//��train�в�ѯ������Ϣ
		   c.moveToFirst();
		   vm.id=id;
		   vm.type=c.getString(c.getColumnIndex("Ttype"));
		   vm.name=c.getString(c.getColumnIndex("Tname"));
		   vm.startstation=c.getString(c.getColumnIndex("Tstartstation"));
		   vm.arrivestation=c.getString(c.getColumnIndex("Tterminus"));
		   vm.beginstation=vm.startstation;
		   vm.stopstation=vm.arrivestation;
		   int sid1=this.SnameIsIn(vm.startstation);
		   int sid2=this.SnameIsIn(vm.arrivestation);
		   sql="select * from relation where Tid=? and Sid=?";
		   s=new String[]{id,String.valueOf(sid1)};
		   c=db.rawQuery(sql, s);//��ѯʼ��վ����ʱ��
		   c.moveToFirst();
		   vm.begintime=c.getString(c.getColumnIndex("Rstarttime"));
		   s=new String[]{id,String.valueOf(sid2)};
		   c=db.rawQuery(sql, s);//��ѯ��վʱ��
		   c.moveToFirst();
		   vm.stoptime=c.getString(c.getColumnIndex("Rarrivetime"));
		   return vm;
		   
	   }
		   
   }
   
   //��վ��ѯ
   public List<ViewModel> StationSelect(String station)
   {
	   List<ViewModel> vml=new ArrayList<ViewModel>(); 
	   int sid=this.SnameIsIn(station);
	   if(sid<=0)
		   return vml;//�޳�վ�����ؿ��б�
	   else
	   {
		   String sql="select Tid from relation where Sid=? ";
		   String[] s=new String[]{String.valueOf(sid)};
		   Cursor c=db.rawQuery(sql, s);//��relation�в�ѯ;����վ���г���
		   for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		   {
			ViewModel vm=this.IdSelect(c.getString(0));//�����г��Ų�ѯ�г���Ϣ
			vml.add(vm);
		   }
		   return vml;
		   
	   }
		   
	    
   }
   
   //վ��վ��ѯ
   public List<ViewModel> SSSelect(String start,String arrive) throws Exception
   {
	   List<ViewModel> vml=new ArrayList<ViewModel>();
	   int sid1=this.SnameIsIn(start);
	   int sid2=this.SnameIsIn(arrive);
	   if(sid1<=0 | sid2<=0)
		   return vml;//ʼ��վ���յ�վ���ڳ�վ������״�������ؿ��б�
	   else
	   {
		   String sql="select a.Tid,a.Rstarttime,b.Rarrivetime from relation as a,relation as b where a.Sid=? and b.Sid=? and a.Tid=b.Tid";
		   String[] s=new String[]{String.valueOf(sid1),String.valueOf(sid2)};
		   Cursor c=db.rawQuery(sql, s);//��������
		   for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		   {
			   ViewModel vm=new ViewModel();
			   vm.beginstation=start;
			   vm.begintime=c.getString(1);
			   vm.stopstation=arrive;
			   vm.stoptime=c.getString(2);
			   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm");
			   java.util.Date t=sdf.parse(vm.begintime);
			   
			   java.util.Date t2=sdf.parse(vm.stoptime);
			   if(t.before(t2)){
			   String id=c.getString(0);
			   sql="select * from train where Tid=?";
			   s=new String[]{id};
			   Cursor c2=db.rawQuery(sql, s);//����Tid��ѯ�г�������Ϣ
			   c2.moveToFirst();
			   vm.id=c2.getString(c2.getColumnIndex("Tid"));
			   vm.type=c2.getString(c2.getColumnIndex("Ttype"));
			   vm.startstation=c2.getString(c2.getColumnIndex("Tstartstation"));
			   vm.arrivestation=c2.getString(c2.getColumnIndex("Tterminus"));
			   vm.name=c2.getString(c2.getColumnIndex("Tname"));
			   vml.add(vm);
			   }
		   }
		   return vml;
	   }
   }
   //��תվ��ѯ
   public List<ViewModel> Transfer(String start,String transfer,String stop) throws Exception
   {
	   List<ViewModel> vml=new ArrayList<ViewModel>();
	   int sid1=this.SnameIsIn(start);
	   int sid2=this.SnameIsIn(transfer);
	   int sid3=this.SnameIsIn(stop);
	   if(sid1<=0 | sid2<=0 |sid3<=0)
		   return vml;
	   else
	   {
		   String sql="select a.Tid,a.Rstarttime,c.Rarrivetime from relation as a,relation as b,relation as c where "
				   +" a.Tid=b.Tid and b.Tid=c.Tid and a.Sid=? and b.Sid=? and c.Sid=?";
		   String[] s=new String[]{String.valueOf(sid1),String.valueOf(sid2),String.valueOf(sid3)};
		   Cursor c=db.rawQuery(sql, s);
		   for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		   {
			   ViewModel vm=new ViewModel();
			   vm.id=c.getString(0);
			   vm.beginstation=start;
			   vm.begintime=c.getString(1);
			   vm.stopstation=stop;
			   vm.stoptime=c.getString(2);
			   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm");
			   java.util.Date t=sdf.parse(vm.begintime);
			   java.util.Date t2=sdf.parse(vm.stoptime);
			   if(t.before(t2)){
			   sql="select * from train where Tid=?";
			   s=new String[]{vm.id};
			   Cursor c2=db.rawQuery(sql, s);
			   c2.moveToFirst();
			   vm.name=c2.getString(c2.getColumnIndex("Tname"));
			   vm.type=c2.getString(c2.getColumnIndex("Ttype"));
			   vm.startstation=c2.getString(c2.getColumnIndex("Tstartstation"));
			   vm.arrivestation=c2.getString(c2.getColumnIndex("Tterminus"));
			   vml.add(vm);
			   }
		   }
		   return vml;
	   }
		   
		   
   }
   

}

