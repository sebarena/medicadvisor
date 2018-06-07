package com.quantris.medicadvisor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Packing implements Parcelable {

    @JsonCreator
    public Packing() {
        this.details = new Detail();
    }
    @JsonProperty("url")
    public String url;

    @JsonProperty("packing")
    public String packing;

    @JsonProperty("artikelId")
    public int artikelId;

    @JsonProperty("details")
    public Detail details;

    protected Packing(Parcel in) {
        url = in.readString();
        packing = in.readString();
        artikelId = in.readInt();
        details = in.readParcelable(Detail.class.getClassLoader());
    }

    public static final Creator<Packing> CREATOR = new Creator<Packing>() {
        @Override
        public Packing createFromParcel(Parcel in) {
            return new Packing(in);
        }

        @Override
        public Packing[] newArray(int size) {
            return new Packing[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(url);
        parcel.writeString(packing);
        parcel.writeInt(artikelId);
        parcel.writeParcelable(details, i);
    }
}