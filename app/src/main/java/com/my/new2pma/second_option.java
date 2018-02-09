/*package com.my.new2pma;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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
        RecyclerView options;
        options=(RecyclerView) view.findViewById(R.id.expenseoption);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getContext());
        viewPager.setAdapter(pagerAdapter);
        List<Integer> imageresources=new ArrayList<Integer>();
        imageresources.add(R.drawable.attendance);
        imageresources.add(R.drawable.expense_icon_final);
        imageresources.add(R.drawable.todo_final);
        imageresources.add(R.drawable.todo_final);
        FloatingActionButton addexpense=(FloatingActionButton) view.findViewById(R.id.addexpense);
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getChildFragmentManager();
                fm.beginTransaction().replace(R.id.totallister,new expense_add()).addToBackStack(null).commit();
            }

        });



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        options.setLayoutManager(mLayoutManager);
        options.setItemAnimator(new DefaultItemAnimator());

        adapter_options_card Adapter_options_card = new adapter_options_card(imageresources, getContext());
        options.setAdapter(Adapter_options_card);


        Toast.makeText(getContext(),"issue with the adapter",Toast.LENGTH_SHORT).show();

        FragmentManager totallisterManager=getChildFragmentManager();
        totallisterManager.beginTransaction().add(R.id.totallister,new TotalListerFragment()).commit();

        return view;

    }
}
*/


