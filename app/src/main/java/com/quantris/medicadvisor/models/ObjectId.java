package com.quantris.medicadvisor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class ObjectID implements Parcelable {

    @JsonCreator
    public ObjectID() {
    }

    @JsonProperty("$oid")
    public String $oid;

    protected ObjectID(Parcel in) {
        $oid = in.readString();
    }

    public static final Creator<ObjectID> CREATOR = new Creator<ObjectID>() {
        @Override
        public ObjectID createFromParcel(Parcel in) {
            return new ObjectID(in);
        }

        @Override
        public ObjectID[] newArray(int size) {
            return new ObjectID[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString($oid);
    }
}