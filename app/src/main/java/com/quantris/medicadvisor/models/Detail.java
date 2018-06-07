package com.quantris.medicadvisor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail implements Parcelable {

    @JsonCreator
    public Detail() {
        this.substanzen = new ArrayList<>();
    }

    @JsonProperty("Anwendungsform")
    public String Anwendungsform;

    @JsonProperty("SwissmedicNummer")
    public String SwissmedicNummer;

    @JsonProperty("registrierungsdatum")
    public CustomDate registrierungsdatum;

    @JsonProperty("Name")
    public String Name;

    @JsonProperty("IndexTherapeuticus")
    public String IndexTherapeuticus;

    @JsonProperty("Zulassungsinhaber")
    public String Zulassungsinhaber;

    @JsonProperty("Selbstbehalt")
    public String Selbstbehalt;

    @JsonProperty("Pharmacode")
    public String Pharmacode;

    @JsonProperty("Dosierung")
    public String Dosierung;

    @JsonProperty("Packungsgrösse")
    public String Packungsgrösse;

    @JsonProperty("Abgabekategorie")
    public String Abgabekategorie;

    @JsonProperty("Preis")
    public String Preis;

    @JsonProperty("substanzen")
    public List<String> substanzen;

    @JsonProperty("Wirkstoff")
    public String Wirkstoff;

    @JsonProperty("gtinCode")
    public String gtinCode;

    protected Detail(Parcel in) {
        Anwendungsform = in.readString();
        SwissmedicNummer = in.readString();
        Name = in.readString();
        IndexTherapeuticus = in.readString();
        Zulassungsinhaber = in.readString();
        Selbstbehalt = in.readString();
        Pharmacode = in.readString();
        Dosierung = in.readString();
        Packungsgrösse = in.readString();
        Abgabekategorie = in.readString();
        Preis = in.readString();
        substanzen = in.createStringArrayList();
        Wirkstoff = in.readString();
        gtinCode = in.readString();
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Anwendungsform);
        parcel.writeString(SwissmedicNummer);
        parcel.writeString(Name);
        parcel.writeString(IndexTherapeuticus);
        parcel.writeString(Zulassungsinhaber);
        parcel.writeString(Selbstbehalt);
        parcel.writeString(Pharmacode);
        parcel.writeString(Dosierung);
        parcel.writeString(Packungsgrösse);
        parcel.writeString(Abgabekategorie);
        parcel.writeString(Preis);
        parcel.writeStringList(substanzen);
        parcel.writeString(Wirkstoff);
        parcel.writeString(gtinCode);
    }
}
