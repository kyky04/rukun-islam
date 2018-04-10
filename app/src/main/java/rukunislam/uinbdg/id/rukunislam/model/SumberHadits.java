package rukunislam.uinbdg.id.rukunislam.model;

import java.io.Serializable;

/**
 * Created by Comp on 11/5/2017.
 */

public class SumberHadits implements Serializable {
    int id;
    String kategori;
    String isi_hadits;
    String terjemah_hadits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIsi_hadits() {
        return isi_hadits;
    }

    public void setIsi_hadits(String isi_hadits) {
        this.isi_hadits = isi_hadits;
    }

    public String getTerjemah_hadits() {
        return terjemah_hadits;
    }

    public void setTerjemah_hadits(String terjemah_hadits) {
        this.terjemah_hadits = terjemah_hadits;
    }
}
