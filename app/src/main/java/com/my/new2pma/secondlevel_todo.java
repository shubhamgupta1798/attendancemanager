package com.my.new2pma;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class secondlevel_todo extends Fragment {


    public secondlevel_todo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secondlevel_todo, container, false);
        ListView lister=(ListView)view.findViewById(R.id.listfirst);
        try {
            Fragment graphfrag = new graph();
            getChildFragmentManager().beginTransaction().replace(R.id.graphfrag, graphfrag).addToBackStack(null).commit();
        }
        catch(Exception e)
        {
            Toast.makeText(getContext(),"there is still an error with the graph"+e,Toast.LENGTH_LONG).show();
        }
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                switch (position) {
                    case 4:
                        Toast.makeText(getContext(),"you clicked the option no 5",Toast.LENGTH_LONG).show();
                        FragmentManager ft = getFragmentManager();
                        ft.beginTransaction().replace(R.id.contentdefault, (Fragment) new thirdlevel_todoadd()).addToBackStack(null).commit();
                        //getActivity().getActionBar().setTitle("TOdoactivity");


                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 0:
                    case 5:

                        Toast.makeText(getContext(),"you wish to use todo options",Toast.LENGTH_LONG).show();
                        FragmentManager fw = getFragmentManager();
                        Bundle data=new Bundle();
                        data.putInt("position",position);
                        Fragment fragment= (Fragment) new thirdleveltodo_options();
                        fragment.setArguments(data);
                        fw.beginTransaction().replace(R.id.contentdefault, fragment ).addToBackStack(null).commit();
                        //getActivity().getActionBar().setTitle("TOdoactivity");
                        break;
                }

            }

        };
        lister.setOnItemClickListener(listener);

return  view;
    }
}
