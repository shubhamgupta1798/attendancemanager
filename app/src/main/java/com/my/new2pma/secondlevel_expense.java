package com.my.new2pma;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


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
                if(position==4){


                  /*  intent= new Intent(second_level_expenses.this,thirdLevelTotal.class);
                    startActivity(intent);*/
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
                    startActivity(intent);*/

                    Bundle data=new Bundle();
                    data.putInt("position",position);
                    Fragment fragment= (Fragment)new thirdlevel_lendplus_borrow();
                    fragment.setArguments(data);
                    getFragmentManager().beginTransaction().replace(R.id.contentdefault,fragment).addToBackStack(null).commit();

                }

            }
        };
        ListView list= (ListView) view.findViewById(R.id.list_item);
        list.setOnItemClickListener(listener);
        return view;

    }
    }


