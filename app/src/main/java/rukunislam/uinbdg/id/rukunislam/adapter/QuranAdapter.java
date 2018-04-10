package rukunislam.uinbdg.id.rukunislam.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rukunislam.uinbdg.id.rukunislam.DetailQuranActivity;
import rukunislam.uinbdg.id.rukunislam.R;
import rukunislam.uinbdg.id.rukunislam.model.SumberQuran;

/**
 * Created by Comp on 11/5/2017.
 */

public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.MyHolder> {
    List<SumberQuran> listSumberQuran;
    Context context;


    public QuranAdapter(List<SumberQuran> listSumberQuran, Context context) {
        this.listSumberQuran = listSumberQuran;
        this.context = context;
    }

    @Override
    public QuranAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_sumber_quran,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(QuranAdapter.MyHolder holder, final int position) {
        holder.tvQuranSurat.setText("(QS."+String.valueOf(listSumberQuran.get(position).getSuratId())+":"+String.valueOf(listSumberQuran.get(position).getAyatId())+")");
        holder.tvIsiAyat.setText(listSumberQuran.get(position).getTerjemah());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailQuranActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("sumberquran",listSumberQuran.get(position));
                i.putExtras(data);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSumberQuran.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvIsiAyat;
        TextView tvQuranSurat;
        public MyHolder(View itemView) {
            super(itemView);
            tvIsiAyat = (TextView)itemView.findViewById(R.id.tv_isi_quran);
            tvQuranSurat = (TextView)itemView.findViewById(R.id.tv_quran_surat);
        }
    }
}
