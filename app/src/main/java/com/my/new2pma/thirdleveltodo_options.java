package com.my.new2pma;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class thirdleveltodo_options extends Fragment {

    Calendar myCalendar=Calendar.getInstance();
    String DateToday;
    RecyclerView listDrinks;

    List<String> datalist=new ArrayList<String>();
    public thirdleveltodo_options() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for getContext() fragment
        Bundle data=getArguments();
        int position=data.getInt("position");
        View view =inflater.inflate(R.layout.fragment_thirdleveltodo_options, container, false);
        listDrinks = (RecyclerView) view.findViewById(R.id.lister);
        switch(position)
        {

            case 0:
               datalist= getToday();
                break;
            case 1:
                datalist= getTommorow();
                break;
            case 2:
                datalist= getThisWeek();
                break;
            case 3: datalist= getThisMonth();
                break;
            case 5:datalist= getAll();
                break;
        }



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listDrinks.setLayoutManager(mLayoutManager);
        listDrinks.setItemAnimator(new DefaultItemAnimator());

       // adapter_options_card adapter_options_card = new adapter_options_card(imageresources, getContext());
       TodoLister todoLister=new TodoLister(datalist,getContext());

        listDrinks.setAdapter(todoLister);

        return view;
    }

    private List<String> getToday()
    {

        List<String> listdata=new ArrayList<String>();
        try{
            SQLiteOpenHelper dbhelper = new db(getContext());
            // Calendar temp=Calendar.getInstance();
            SQLiteDatabase expdb = dbhelper.getReadableDatabase();

            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            DateToday=sdf.format(myCalendar.getTime());
            Cursor cursor=expdb.query("TODO",new String[]{"DESCRIPTION","DATE_DEADLINE","CREATED_ON"},"DATE_DEADLINE=?",new String[]{DateToday},null,null,null);
            if(cursor.moveToFirst())
            {

                listdata.add("created:"+cursor.getString(2)+cursor.getString(0));
                while(cursor.moveToNext())
                    listdata.add(cursor.getString(0));


            }
            else
            {
                listdata.add("there is nothing for today in the todo's");
            }

            cursor.close();
            expdb.close();
            //  String[] fina= new String[listdata.size()];
            /*ArrayAdapter<String> lister=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,listdata);

            listDrinks.setAdapter(lister);*/


            Toast.makeText(getContext(), "adapter implimented succesfully", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "error in getting todays todos"+e, Toast.LENGTH_LONG).show();
        }
        return listdata;
    }
    private List<String> getAll()
    {

        List<String> listdata=new ArrayList<String>();
        try{
            SQLiteOpenHelper dbhelper = new db(getContext());
            // Calendar temp=Calendar.getInstance();
            SQLiteDatabase expdb = dbhelper.getReadableDatabase();
            Cursor cursor=expdb.query("TODO",new String[]{"DESCRIPTION","DATE_DEADLINE","CREATED_ON"},null,null,null,null,null);
            if(cursor.moveToFirst())
            {

                listdata.add("created:"+cursor.getString(2)+"deadLine"+cursor.getString(1)+":::"+cursor.getString(0));
                while(cursor.moveToNext())
                    listdata.add("created:"+cursor.getString(2)+"deadLine"+cursor.getString(1)+":::"+cursor.getString(0));


            }
            else
            {
                listdata.add("there is nothing in your todo list");
            }

            cursor.close();
            expdb.close();


        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "error in getting todays todos"+e, Toast.LENGTH_LONG).show();
        }
        return listdata;
    }

    private List<String> getTommorow()
    {

        List<String> listdata=new ArrayList<String>();
        try{
            SQLiteOpenHelper dbhelper = new db(getContext());
            // Calendar temp=Calendar.getInstance();
            SQLiteDatabase expdb = dbhelper.getReadableDatabase();

            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            myCalendar.add(Calendar.DATE,1);
            DateToday=sdf.format(myCalendar.getTime());
            Cursor cursor=expdb.query("TODO",new String[]{"DESCRIPTION","DATE_DEADLINE"},"DATE_DEADLINE=?",new String[]{DateToday},null,null,null);
            if(cursor.moveToFirst())
            {

                listdata.add(cursor.getString(1)+":"+cursor.getString(0));
                while(cursor.moveToNext())
                    listdata.add(cursor.getString(1)+":"+cursor.getString(0));

            }
            else
            {
                listdata.add("there is nothing for tommorow in the todo's");
            }
            cursor.close();
            expdb.close();

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "error in getting todays todos"+e, Toast.LENGTH_LONG).show();
        }
        return listdata;
    }
    private List<String> getThisWeek()
    {

        List<String> listdata=new ArrayList<String>();
        try{
            SQLiteOpenHelper dbhelper = new db(getContext());
            // Calendar temp=Calendar.getInstance();
            SQLiteDatabase expdb = dbhelper.getReadableDatabase();
// we will find different approach for getContext() current week thing for now getContext() will work
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            myCalendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            String[] week=new String[7];
            week[0]=sdf.format(myCalendar.getTime());



            for(int i=1;i<7;i++)
            {
                myCalendar.add(Calendar.DATE, 1);
                week[i] = sdf.format(myCalendar.getTime());
            }

            //DateToday=sdf.format(myCalendar.getTime());
            Cursor cursor=expdb.query("TODO",new String[]{"DESCRIPTION","DATE_DEADLINE"},"DATE_DEADLINE IN(?,?,?,?,?,?,?)",week,null,null,null);
            if(cursor.moveToFirst())
            {

                listdata.add("Deadline"+cursor.getString(1)+":"+cursor.getString(0));
                while(cursor.moveToNext())
                    listdata.add("Deadline"+cursor.getString(1)+":"+cursor.getString(0));

            }
            else
            {
                listdata.add("there is nothing for getContext() week in the todo's");
            }
            cursor.close();
            expdb.close();

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "error in getting todays todos"+e, Toast.LENGTH_LONG).show();
        }
        return listdata;
    }
//the following method is not appropriate but as we have stored the date in the string format ihave to do getContext() in the bad way late we will replace string type by
    //long so to select a date from a range will become an easy task to do

    private List<String> getThisMonth()
    {List<String> listdata=new ArrayList<String>();

        try{
            SQLiteOpenHelper dbhelper = new db(getContext());
            // Calendar temp=Calendar.getInstance();
            SQLiteDatabase expdb = dbhelper.getReadableDatabase();
// we will find different approach for getContext() current week thing for now getContext() will work
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            // myCalendar.add(Calendar.MONTH,1);
            myCalendar.set(Calendar.DAY_OF_MONTH,1);
            // myCalendar.add(Calendar.DATE,-1);
            //temp.set(Calendar.DAY_OF_MONTH,1);
            List<String> week=new ArrayList<String>();
            for(int i=0;i<31;i++)
            {
                week.add(sdf.format(myCalendar.getTime()));
                myCalendar.add(Calendar.DATE, 1);

            }

            String[] weki= new String[week.size()];
            weki=week.toArray(weki);


            //DateToday=sdf.format(myCalendar.getTime());
            Cursor cursor=expdb.query("TODO",new String[]{"DESCRIPTION","DATE_DEADLINE"},"DATE_DEADLINE IN(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",weki,null,null,null);
            if(cursor.moveToFirst())
            {

                listdata.add("Deadline"+cursor.getString(1)+":"+cursor.getString(0));
                while(cursor.moveToNext())
                    listdata.add("Deadline"+cursor.getString(1)+":"+cursor.getString(0));

            }
            else
            {
                listdata.add("there is nothing for getContext() week in the todo's");
            }
            cursor.close();
            expdb.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "error in getting todays todos"+e, Toast.LENGTH_LONG).show();
        }

        return listdata;
    }

}








