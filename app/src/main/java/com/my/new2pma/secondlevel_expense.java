package com.my.new2pma;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondlevel_expense extends Fragment {


    public secondlevel_expense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_secondlevel_expense, container, false);









        ViewPager viewPager=(ViewPager) view.findViewById(R.id.expensegraph);
        RecyclerView options,expenselist;
        options=(RecyclerView) view.findViewById(R.id.expenseoption);
        expenselist=(RecyclerView) view.findViewById(R.id.category_expenseLister);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getContext());
        viewPager.setAdapter(pagerAdapter);
        /*List<Subjects> subjects=new ArrayList<Subjects>();
        subjects.add(new Subjects("dbms",2,3,R.drawable.logo));
        subjects.add(new Subjects("dsa",4,5,R.drawable.nit_surat));
        subjects.add(new Subjects("dms",4,5,R.drawable.expense_icon_final));*/
        List<Integer> imageresources=new ArrayList<Integer>();
        imageresources.add(R.drawable.attendance);
        imageresources.add(R.drawable.expense_icon_final);
        imageresources.add(R.drawable.todo_final);
        imageresources.add(R.drawable.todo_final);
        FloatingActionButton addexpense=(FloatingActionButton) view.findViewById(R.id.addexpense);
        addexpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View arg0){
                /*final Dialog openDialog = new Dialog(mcontext);
                openDialog.setContentView(R.layout.todo_add);
                openDialog.setTitle("Custom Dialog Box");*/
                /// openDialog.getWindow().setLayout(1600, 2000);
                // final TextView dialogTextContent = (TextView) openDialog.findViewById(R.id.description);
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View openDialog = inflater.inflate(R.layout.fragment_expense_add, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(openDialog);


                final AlertDialog customAlertDialog = builder.create();
                customAlertDialog.show();

               // final TextView dialogTextContent = (TextView) openDialog.findViewById(R.id.description2);
                final EditText Amount,date,description,name;
                Amount=(EditText) openDialog.findViewById(R.id.amount);
                date=(EditText) openDialog.findViewById(R.id.date);
                description=(EditText) openDialog.findViewById(R.id.description);
                name=(EditText) (EditText) openDialog.findViewById(R.id.name);



                Button close=(Button) openDialog.findViewById(R.id.add);
                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getContext(),"amount:"+Amount.getText().toString()+"\ndate"+date.getText().toString()+"\nname"+name.getText().toString()+"\ndescription"+description.getText().toString(),Toast.LENGTH_LONG).show();
                        customAlertDialog.dismiss();
                    }
                });}
        });


        try{
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            options.setLayoutManager(mLayoutManager);
            options.setItemAnimator(new DefaultItemAnimator());

            adapter_options_card adapter_options_card = new adapter_options_card(imageresources, getContext());
            options.setAdapter(adapter_options_card);
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"issue with the adapter",Toast.LENGTH_SHORT).show();
        }
        FragmentManager totallisterManager=getChildFragmentManager();
        totallisterManager.beginTransaction().add(R.id.totallister,new TotalListerFragment()).commit();

/*
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                Intent intent;
               /* if(position==0) {
                    /*intent = new Intent(second_level_expenses.this,thirdlevel_lendplusborrow.class);
                    startActivity(intent);*/
                   /* getFragmentManager().beginTransaction().replace(R.id.contentdefault,new thirdlevel_lendplus_borrow()).addToBackStack(null).commit();

                }  */
           /*     if(position==4){


                  /*  intent= new Intent(second_level_expenses.this,thirdLevelTotal.class);
                    startActivity(intent);
                     Bundle data=new Bundle();
                        data.putInt("position",position);
                    Fragment fragment=new TotalListerFragment();
                        fragment.setArguments(data);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();

                }
                else
                {
                    /*intent = new Intent(second_level_expenses.this, thirdlevel_lendplusborrow.class);
                    intent.putExtra("position",position);
                    startActivity(intent);

                    Bundle data=new Bundle();
                    data.putInt("position",position);
                    Fragment fragment= (Fragment)new thirdlevel_lendplus_borrow();
                    fragment.setArguments(data);
                    getFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();

                }

            }
        };
        ListView list= (ListView) view.findViewById(R.id.list_item);
        list.setOnItemClickListener(listener);*/
        return view;

    }
    }


