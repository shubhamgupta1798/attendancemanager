package com.my.new2pma;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.SimpleTimeZone;

import static android.content.Context.ALARM_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 *
 * hello
 *
 *
 */
public class thirdlevel_todoadd extends Fragment {
    Calendar myCalendar=Calendar.getInstance();
    EditText editText;
    int id=0;

    // new
    EditText editTex;

    //new

    //Comparator<Long> comparator=new longComparator();

   // PriorityQueue<Long> notification_queue=new PriorityQueue<Long>(10, comparator);

    View view;
    public thirdlevel_todoadd() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                 view =inflater.inflate(R.layout.fragment_thirdlevel_todoadd, container, false);
        try {
            // myCalendar = Calendar.getInstance();
            editText = (EditText) view.findViewById(R.id.date);

            editTex = (EditText) view.findViewById(R.id.time);
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();

                }

            };

            editText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            // new timepicker dialog

            //new








            editTex.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                   // final Calendar currenttime= myCalendar;// there may be error
                    int hour =myCalendar.get(Calendar.HOUR_OF_DAY), minute = myCalendar.get(Calendar.MINUTE);
                    TimePickerDialog mtimepicker;
                    mtimepicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                            editTex.setText(selectedhour + ":" + selectedminute);
                            myCalendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                            myCalendar.set(Calendar.MINUTE,timePicker.getMinute());
                        }
                    }, hour, minute,false);
                    mtimepicker.setTitle("select Time");
                    mtimepicker.show();

                }});
            //editTex.setOnClickListener(new view.onClickListener);



            //new




















            Button add=(Button)view.findViewById(R.id.button);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // update(v);
                    try{
                    db dbhelper = new db(getContext());
                    SQLiteDatabase expdb = dbhelper.getWritableDatabase();
                    EditText dateview = (EditText) view.findViewById(R.id.date);
                    String a = dateview.getText().toString();
                    EditText description= (EditText) view.findViewById(R.id.description);
                    String b=description.getText().toString();
                    RadioButton notify=(RadioButton)view.findViewById(R.id.notify);
                    RadioButton setal=(RadioButton)view.findViewById(R.id.alarm);
                    boolean noti=notify.isChecked();
                    boolean setalarm=setal.isChecked();
                    ContentValues addtodo=new ContentValues();
                    addtodo.put("DATE_DEADLINE",a);
                    addtodo.put("DESCRIPTION",b);
                    addtodo.put("SETALARM",setalarm);
                    addtodo.put("NOTIFICATION",noti);

                    expdb.insert("TODO", null, addtodo);
                    expdb.close();
                    if(noti) {





                     /*old time picker   TimePicker timer=(TimePicker)view.findViewById(R.id.timer);
                        myCalendar.set(Calendar.HOUR_OF_DAY,timer.getHour());
                        myCalendar.set(Calendar.MINUTE,timer.getMinute());
                        */
                        Intent temp = new Intent(getContext(), Notification_receivor.class);

                       //old notification system
                       temp.putExtra("des",b);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), id++, temp, 0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), pendingIntent);

                        /*while(notification_queue.size()!=0)
                        {

                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, temp, PendingIntent.FLAG_UPDATE_CURRENT);
                            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                            Long settedtime=notification_queue.remove();
                            alarmManager.setExact(AlarmManager.RTC_WAKEUP,settedtime , pendingIntent);
                            Toast.makeText(getContext(),"setted notification for the time="+settedtime.toString(),Toast.LENGTH_SHORT);
                        }*/
                        Toast.makeText(getContext(),"notification set successfully",Toast.LENGTH_SHORT).show();
                    }

                    Toast tk = Toast.makeText(getContext(), "update called successfully", Toast.LENGTH_SHORT);
                    tk.show();


                } catch (Exception e) {
                    Toast dblocha=Toast.makeText(getContext(),"Database having some locha"+e,Toast.LENGTH_LONG);
                    dblocha.show();
                }
                }
            });

        } catch (Exception e) {
            Toast tb = Toast.makeText(getContext(), "issue in oncreate function " + e, Toast.LENGTH_SHORT);
            tb.show();
        }
        return view;
    }
    private void updateLabel() {
        try {
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            editText.setText(sdf.format(myCalendar.getTime()));
        } catch (Exception e) {
            Toast tb = Toast.makeText(getContext(), "issue is in update label function and the issue is\n\n" + e, Toast.LENGTH_LONG);
            tb.show();
        }
    }
   /* public void update(View view) {

        try {

            db dbhelper = new db(getContext());
            SQLiteDatabase expdb = dbhelper.getWritableDatabase();
            EditText dateview = (EditText) view.findViewById(R.id.date);
            String a = dateview.getText().toString();
            EditText description= (EditText) view.findViewById(R.id.description);
            String b=description.getText().toString();
            RadioButton notify=(RadioButton)view.findViewById(R.id.notify);
            RadioButton setal=(RadioButton)view.findViewById(R.id.alarm);
            boolean noti=notify.isChecked();
            boolean setalarm=setal.isChecked();
            ContentValues addtodo=new ContentValues();
            addtodo.put("DATE_DEADLINE",a);
            addtodo.put("DESCRIPTION",b);
            addtodo.put("SETALARM",setalarm);
            addtodo.put("NOTIFICATION",noti);

            expdb.insert("TODO", null, addtodo);
            expdb.close();
            if(noti) {
               /* Calendar calender = Calendar.getInstance();
                calender.set(Calendar.HOUR_OF_DAY, 21);
                calender.set(Calendar.MINUTE, 5);
                */
               /* TimePicker timer=(TimePicker)view.findViewById(R.id.timer);
                myCalendar.set(Calendar.HOUR_OF_DAY,timer.getHour());
                myCalendar.set(Calendar.MINUTE,timer.getMinute());
                /*
               i put this to check wheather the adding
                // todo will work or not

                */
               /* Intent temp = new Intent(getContext(), Notification_receivor.class);
                temp.putExtra("des",b);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, temp, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), pendingIntent);

                Toast.makeText(getContext(),"notification set successfully",Toast.LENGTH_SHORT).show();
            }

            Toast tk = Toast.makeText(getContext(), "update called successfully", Toast.LENGTH_SHORT);
            tk.show();


        } catch (Exception e) {
            Toast dblocha=Toast.makeText(getContext(),"Database having some locha"+e,Toast.LENGTH_LONG);
            dblocha.show();
        }
    }*/

}
/*
class longComparator implements Comparator<Long>
{

    @Override
    public int compare(Long o1, Long o2) {

        if(o1>o2)
        {
            return -1;
        }
        if (o2>o1)
        {
            return 1;
        }
        return 0;
    }
}*/
