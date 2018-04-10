package rukunislam.uinbdg.id.rukunislam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rukunislam.uinbdg.id.rukunislam.model.SumberHadits;

public class DetailHaditsActivity extends AppCompatActivity {
    TextView tvKatHadits, tvHadits,tvTerjemah;
    SumberHadits sumberQuran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hadits);

        tvKatHadits = (TextView)findViewById(R.id.tv_kat_hadits);
        tvHadits = (TextView)findViewById(R.id.tv_isi_hadits);
        tvTerjemah = (TextView)findViewById(R.id.tv_terjemah_hadits);
        sumberQuran = (SumberHadits) getIntent().getSerializableExtra("sumberhadits");


        tvKatHadits.setText(sumberQuran.getKategori());
        tvHadits.setText(sumberQuran.getIsi_hadits());
        tvTerjemah.setText(sumberQuran.getTerjemah_hadits());
    }
}
