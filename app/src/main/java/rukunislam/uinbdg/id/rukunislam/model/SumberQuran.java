package rukunislam.uinbdg.id.rukunislam.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Comp on 11/5/2017.
 */

public class SumberQuran implements Serializable {
    int id;
    int suratId;
    int ayatId;
    String terjemah;
    String isiArab;

    public SumberQuran() {
    }

    public String getIsiArab() {
        return isiArab;
    }

    public void setIsiArab(String isiArab) {
        this.isiArab = isiArab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuratId() {
        return suratId;
    }

    public void setSuratId(int suratId) {
        this.suratId = suratId;
    }

    public int getAyatId() {
        return ayatId;
    }

    public void setAyatId(int ayatId) {
        this.ayatId = ayatId;
    }

    public String getTerjemah() {
        return terjemah;
    }

    public void setTerjemah(String terjemah) {
        this.terjemah = terjemah;
    }



    private SumberQuran(Parcel in){
        this.id = in.readInt();
        this.ayatId = in.readInt();
        this.suratId = in.readInt();
        this.terjemah = in.readString();
        this.isiArab = in.readString();
    }

    public static final Parcelable.Creator<SumberQuran> CREATOR = new Parcelable.Creator<SumberQuran>() {

        @Override
        public SumberQuran createFromParcel(Parcel source) {
            return new SumberQuran(source);
        }

        @Override
        public SumberQuran[] newArray(int size) {
            return new SumberQuran[size];
        }
    };
}
