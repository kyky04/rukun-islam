package rukunislam.uinbdg.id.rukunislam;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rukunislam.uinbdg.id.rukunislam.adapter.HaditsAdapter;
import rukunislam.uinbdg.id.rukunislam.adapter.QuranAdapter;
import rukunislam.uinbdg.id.rukunislam.adapter.ViewPagerSumber;
import rukunislam.uinbdg.id.rukunislam.databases.DBHelper;
import rukunislam.uinbdg.id.rukunislam.fragments.HaditsFragment;
import rukunislam.uinbdg.id.rukunislam.fragments.QuranFragment;
import rukunislam.uinbdg.id.rukunislam.model.SumberHadits;
import rukunislam.uinbdg.id.rukunislam.model.SumberQuran;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    DBHelper dbHelper;
    List<SumberHadits> listSumberHadits;
    List<SumberQuran> listSumberQuran;
    RecyclerView recyclerSumberQuran;
    QuranAdapter adapter;
    HaditsAdapter haditsAdapter;
    EditText etSearch;
    ImageView imgSearch;
    LinearLayout linearUtama;
    ViewPager vpSumber;
    ViewPagerSumber viewPagerSumber;
    TabLayout tabSumber;
    int failure[];

    private OnDataPicked callDataSumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        listSumberHadits = dbHelper.getAllHadits();
        listSumberQuran = dbHelper.getAllSumber();
        recyclerSumberQuran = (RecyclerView) findViewById(R.id.recycler_sumber);
        tabSumber = (TabLayout) findViewById(R.id.tab_sumber);
        vpSumber = (ViewPager) findViewById(R.id.vp_sumber);
        imgSearch = (ImageView) findViewById(R.id.img_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        linearUtama = (LinearLayout) findViewById(R.id.linear);


        tabSumber.addTab(tabSumber.newTab().setText("Quran"));
        tabSumber.addTab(tabSumber.newTab().setText("Hadits"));
        viewPagerSumber = new ViewPagerSumber(getSupportFragmentManager(), tabSumber.getTabCount());
        vpSumber.setAdapter(viewPagerSumber);

        vpSumber.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabSumber));

        tabSumber.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpSumber.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSumberQuran.setLayoutManager(llm);
        recyclerSumberQuran.setAdapter(adapter);
        recyclerSumberQuran.setVisibility(View.VISIBLE);
//        recyclerSumberQuran.setAdapter();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabSumber.setVisibility(View.VISIBLE);
                String hasil = etSearch.getText().toString();
                if (hasil.equals("solat") || hasil.equals("salat") || hasil.equals("sholat")) {
                    hasil = "shalat";
                }
                final Snackbar snackbar = Snackbar.make(linearUtama, hasil, Snackbar.LENGTH_SHORT);
                searchSumberHadits(hasil, listSumberHadits);
                searchSumberQuran(hasil, listSumberQuran);
                snackbar.setAction("Tutup", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();

                    }
                });
                snackbar.show();
            }
        });
    }

    public List<SumberQuran> searchSumberQuran(String textSearch, List<SumberQuran> listSumber) {
        List<SumberQuran> listSumberQuran = new ArrayList<>();
        for (int i = 0; i < listSumber.size(); i++) {
            if (listSumber.get(i).getTerjemah().toLowerCase().contains(textSearch.toLowerCase())) {
                kmp(listSumber.get(i).getTerjemah(), etSearch.getText().toString());
                SumberQuran sumberQuran = listSumber.get(i);
                listSumberQuran.add(sumberQuran);
            }
        }

        Collections.shuffle(listSumberQuran);
        Fragment fragment = viewPagerSumber.getFragment(0);
        ((QuranFragment) fragment).getData(listSumberQuran);
//        callDataSumber.OnDataSumberPicked(listSumberHadits);
        adapter = new QuranAdapter(listSumberQuran, this);
        adapter.notifyDataSetChanged();
//        recyclerSumberQuran.setAdapter(adapter);

        return listSumberQuran;
    }


    public List<SumberHadits> searchSumberHadits(String textSearch, List<SumberHadits> listSumber) {
        List<SumberHadits> listSumberQuran = new ArrayList<>();
        for (int i = 0; i < listSumber.size(); i++) {
            if (listSumber.get(i).getTerjemah_hadits().toLowerCase().contains(textSearch.toLowerCase())) {
                kmp(listSumber.get(i).getTerjemah_hadits(), etSearch.getText().toString());
                SumberHadits sumberQuran = listSumber.get(i);
                listSumberQuran.add(sumberQuran);
            }
        }

        Collections.shuffle(listSumberQuran);
        Fragment fragment = viewPagerSumber.getFragment(1);
        ((HaditsFragment) fragment).getData(listSumberQuran);
//        callDataSumber.OnDataSumberPicked(listSumberHadits);
        haditsAdapter = new HaditsAdapter(listSumberQuran, this);
        haditsAdapter.notifyDataSetChanged();
//        recyclerSumberQuran.setAdapter(adapter);

        return listSumberQuran;
    }

    public interface OnDataPicked {
        void OnDataSumberPicked(List<SumberQuran> sumberQuranList);
    }

    public int kmp(String text, String pat) {
        /** pre construct failure array for a pattern **/
        failure = new int[pat.length()];
        fail(pat);
        /** find match **/
        int pos = posMatch(text, pat);
        if (pos == -1)
            Log.d("Hasil", "\nNo match found");
        else
            Log.d("Hasil", "\nMatch found at index " + pos);

        return pos;
    }

    private void fail(String pat) {
        int n = pat.length();
        failure[0] = -1;
        for (int j = 1; j < n; j++) {
            int i = failure[j - 1];
            while ((pat.charAt(j) != pat.charAt(i + 1)) && i >= 0)
                i = failure[i];
            if (pat.charAt(j) == pat.charAt(i + 1))
                failure[j] = i + 1;
            else
                failure[j] = -1;
        }
    }

    /**
     * Function to find match for a pattern
     **/
    private int posMatch(String text, String pat) {
        int i = 0, j = 0;
        int lens = text.length();
        int lenp = pat.length();
        while (i < lens && j < lenp) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else if (j == 0)
                i++;
            else
                j = failure[j - 1] + 1;
        }
        return ((j == lenp) ? (i - lenp) : -1);
    }

}
