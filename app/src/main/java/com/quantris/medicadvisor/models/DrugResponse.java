package com.quantris.medicadvisor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(as = DrugResponse.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugResponse {

    @JsonCreator
    public DrugResponse() {
        this.items = new ArrayList<>();
        this.request = new Request();
    }

    @JsonProperty("totalPages")
    public int totalPages;

    @JsonProperty("items")
    public List<Drug> items;

    @JsonProperty("request")
    public Request request;

    @JsonProperty("rowCount")
    public int rowCount;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Drug implements Parcelable {

        @JsonCreator
        public Drug() {
            this.packungen = new ArrayList<>();
            this._id = new ObjectID();
        }

        @JsonProperty("anwendungsform")
        public String anwendungsform;

        @JsonProperty("name")
        public String name;

        @JsonProperty("hersteller")
        public String hersteller;

        @JsonProperty("patInfo")
        public int patInfo;

        @JsonProperty("packungen")
        public List<Packing> packungen;

        @JsonProperty("images")
        public List<Bild> images;

        @JsonProperty("_id")
        public ObjectID _id;

        protected Drug(Parcel in) {
            anwendungsform = in.readString();
            name = in.readString();
            hersteller = in.readString();
            patInfo = in.readInt();
            packungen = new ArrayList<>();
            in.readList(packungen, Packing.class.getClassLoader());
            images = new ArrayList<>();
            in.readList(images, Bild.class.getClassLoader());
//            _id = in.readParcelable(ObjectID.class.getClassLoader());
        }

        public static final Creator<Drug> CREATOR = new Creator<Drug>() {
            @Override
            public Drug createFromParcel(Parcel in) {
                return new Drug(in);
            }

            @Override
            public Drug[] newArray(int size) {
                return new Drug[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(anwendungsform);
            parcel.writeString(name);
            parcel.writeString(hersteller);
            parcel.writeInt(patInfo);
            parcel.writeList(packungen);
            parcel.writeList(images);
//            parcel.writeParcelable(_id, i);
        }
    }
}
