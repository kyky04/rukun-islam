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

import rukunislam.uinbdg.id.rukunislam.DetailHaditsActivity;
import rukunislam.uinbdg.id.rukunislam.R;
import rukunislam.uinbdg.id.rukunislam.model.SumberHadits;

/**
 * Created by Comp on 11/5/2017.
 */

public class HaditsAdapter extends RecyclerView.Adapter<HaditsAdapter.MyHolder> {
    List<SumberHadits> listSumberHadits;
    Context context;


    public HaditsAdapter(List<SumberHadits> listSumberQuran, Context context) {
        this.listSumberHadits = listSumberQuran;
        this.context = context;
    }

    @Override
    public HaditsAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_sumber_hadits,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(HaditsAdapter.MyHolder holder, final int position) {
        holder.tvIsiAyat.setText(listSumberHadits.get(position).getTerjemah_hadits());
        holder.tvKathadits.setText(listSumberHadits.get(position).getKategori());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailHaditsActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("sumberhadits", listSumberHadits.get(position));
                i.putExtras(data);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSumberHadits.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvKathadits;
        TextView tvIsiAyat;
        public MyHolder(View itemView) {
            super(itemView);

            tvIsiAyat = (TextView)itemView.findViewById(R.id.tv_isi_hadits);
            tvKathadits = (TextView)itemView.findViewById(R.id.tv_kat_hadits);
        }
    }
}
