package com.example.laborator4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Floare implements Parcelable {
    private String denumire;
    private boolean areSpini;
    private String tipMediu;
    private String culoare;
    private float rating;
    private boolean estePerena;

    public Floare(){

    }

    public Floare(String _denumire, boolean _areSpini, String _tipMediu, String _culoare, float _rating, boolean _estePerena) {
        this.denumire = _denumire;
        this.areSpini = _areSpini;
        this.tipMediu = _tipMediu;
        this.culoare = _culoare;
        this.rating = _rating;
        this.estePerena = _estePerena;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public boolean getAreSpini() {
        return areSpini;
    }

    public void setAreSpini(boolean areSpini) {
        this.areSpini = areSpini;
    }

    public String getTipMediu() {
        return tipMediu;
    }

    public void setTipMediu(String tipMediu) {
        this.tipMediu = tipMediu;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isEstePerena() {
        return estePerena;
    }

    public void setEstePerena(boolean estePerena) {
        this.estePerena = estePerena;
    }

    protected Floare(Parcel in) {
        denumire = in.readString();
        areSpini = in.readByte() != 0; // boolean se citeste ca byte
        tipMediu = in.readString();
        culoare = in.readString();
        rating = in.readFloat();
        estePerena = in.readByte() != 0;
    }

    public static final Creator<Floare> CREATOR = new Creator<Floare>() {
        @Override
        public Floare createFromParcel(Parcel in) {
            return new Floare(in);
        }

        @Override
        public Floare[] newArray(int size) {
            return new Floare[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //aici se scriu atributele in pachet
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeByte((byte) (areSpini ? 1 : 0));
        dest.writeString(tipMediu);
        dest.writeString(culoare);
        dest.writeFloat(rating);
        dest.writeByte((byte) (estePerena ? 1 : 0));
    }


    @Override
    public String toString() {
        return "Floare: " + denumire +
                "\nCuloare: " + culoare +
                "\nMediu: " + tipMediu +
                "\nSpini: " + (areSpini ? "Da" : "Nu") +
                "\nPerenă: " + (estePerena ? "Da" : "Nu") +
                "\nRating: " + rating;
    }

}
