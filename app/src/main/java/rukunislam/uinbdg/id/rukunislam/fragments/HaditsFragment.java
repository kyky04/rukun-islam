package rukunislam.uinbdg.id.rukunislam.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rukunislam.uinbdg.id.rukunislam.MainActivity;
import rukunislam.uinbdg.id.rukunislam.R;
import rukunislam.uinbdg.id.rukunislam.adapter.HaditsAdapter;
import rukunislam.uinbdg.id.rukunislam.model.SumberHadits;
import rukunislam.uinbdg.id.rukunislam.model.SumberQuran;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaditsFragment extends Fragment implements MainActivity.OnDataPicked{
    RecyclerView recyclerSumberQuran;
    HaditsAdapter adapter;
    TextView tvEmpty;
    public HaditsFragment() {
        // Required empty public constructor
    }

    private MainActivity.OnDataPicked mCallbackDisp;
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mCallbackDisp = (MainActivity.OnDataPicked) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement IFragmentToActivity");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        mCallbackDisp = null;
//        super.onDetach();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quran, container, false);

        recyclerSumberQuran = (RecyclerView)v.findViewById(R.id.recycler_sumber);
        tvEmpty = (TextView) v.findViewById(R.id.tv_empty);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSumberQuran.setLayoutManager(llm);

        return v;
    }

    @Override
    public void OnDataSumberPicked(List<SumberQuran> sumberQuranList) {

    }
     public void getData(List<SumberHadits> sumberQuranList){
         if(sumberQuranList.size()>0){
             adapter = new HaditsAdapter(sumberQuranList,getActivity());
             recyclerSumberQuran.setAdapter(adapter);
             adapter.notifyDataSetChanged();
             recyclerSumberQuran.setVisibility(View.VISIBLE);
             tvEmpty.setVisibility(View.GONE);
         }else {
             recyclerSumberQuran.setVisibility(View.GONE);
             tvEmpty.setVisibility(View.VISIBLE);
         }
     }
}
