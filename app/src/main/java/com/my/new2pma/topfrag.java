package com.my.new2pma;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class topfrag extends Fragment {


    public topfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view= inflater.inflate(R.layout.fragment_topfrag, container, false);

        AdapterView.OnItemClickListener listListener =new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                Intent intent;
                switch(position){
                    case 0:
                       // intent=new Intent(First.this,SecondLevel_Attendence.class);
                       // startActivity(intent);
                        FragmentManager fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.contentdefault, (Fragment)new topfrag()).addToBackStack(null).commit();
                      //  getActivity().getActionBar().setTitle("Attendence will come here");
                        break;
                    case 1:
                        //intent=new Intent(First.this,second_level_expenses.class);
                        //startActivity(intent);

                        FragmentManager ft=getFragmentManager();
                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_expense()).addToBackStack(null).commit();
                        //getActivity().getActionBar().setTitle("TOdoactivity");
                        break;
                    case 2:
                       // intent=new Intent(First.this,second_level_todos.class);
                       // startActivity(intent);
                        FragmentManager fp=getFragmentManager();
                        fp.beginTransaction().replace(R.id.contentdefault, (Fragment)new secondlevel_todo()).addToBackStack(null).commit();
                       // getActivity().getActionBar().setTitle("EXPENSEactivity");

                        break;
                }
                TextView text=(TextView) view.findViewById(R.id.textfirst);
                text.setText("you pressed the item no"+Integer.toString(position+1));

            }
        };
        ListView listView=(ListView) view.findViewById(R.id.listfirst);
        listView.setOnItemClickListener(listListener);

        return view;

    }

}
