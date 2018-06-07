package com.quantris.medicadvisor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bild implements Parcelable {

    @JsonCreator
    public Bild() {}

    @JsonProperty("url")
    public String url;

    @JsonProperty("text")
    public String text;

    protected Bild(Parcel in) {
        url = in.readString();
        text = in.readString();
    }

    public static final Creator<Bild> CREATOR = new Creator<Bild>() {
        @Override
        public Bild createFromParcel(Parcel in) {
            return new Bild(in);
        }

        @Override
        public Bild[] newArray(int size) {
            return new Bild[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(text);
    }
}
