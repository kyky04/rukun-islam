package rukunislam.uinbdg.id.rukunislam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import rukunislam.uinbdg.id.rukunislam.model.SumberQuran;

public class DetailQuranActivity extends AppCompatActivity {
    TextView tvQuranSurat,tvArab,tvTerjemah;
    SumberQuran sumberQuran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quran);

        tvQuranSurat = (TextView)findViewById(R.id.tv_quran_surat);
        tvArab = (TextView)findViewById(R.id.tv_quran_arab);
        tvTerjemah = (TextView)findViewById(R.id.tv_terjemah);

        sumberQuran = (SumberQuran) getIntent().getSerializableExtra("sumberquran");

        tvQuranSurat.setText("(QS."+String.valueOf(sumberQuran.getSuratId())+":"+String.valueOf(sumberQuran.getAyatId())+")");
        tvArab.setText(sumberQuran.getIsiArab());
        tvTerjemah.setText(sumberQuran.getTerjemah());
    }
}
