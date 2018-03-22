package com.example.przemek.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Flags implements Parcelable {

    private boolean isDotAllowed;
    private boolean isEqualAllowed;
    private boolean isInitialValue;
    private boolean isError;

    public Flags(){
        isDotAllowed = false;
        isEqualAllowed = false;
        isInitialValue = true;
        isError = false;
    }

    protected Flags(Parcel in) {
        isDotAllowed = in.readByte() != 0;
        isEqualAllowed = in.readByte() != 0;
        isInitialValue = in.readByte() != 0;
        isError = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isDotAllowed ? 1 : 0));
        dest.writeByte((byte) (isEqualAllowed ? 1 : 0));
        dest.writeByte((byte) (isInitialValue ? 1 : 0));
        dest.writeByte((byte) (isError ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Flags> CREATOR = new Creator<Flags>() {
        @Override
        public Flags createFromParcel(Parcel in) {
            return new Flags(in);
        }

        @Override
        public Flags[] newArray(int size) {
            return new Flags[size];
        }
    };

    public boolean isDotAllowed() {
        return isDotAllowed;
    }

    public void setDotAllowed(boolean dotAllowed) {
        isDotAllowed = dotAllowed;
    }

    public boolean isEqualAllowed() {
        return isEqualAllowed;
    }

    public void setEqualAllowed(boolean equalAllowed) {
        isEqualAllowed = equalAllowed;
    }

    public boolean isInitialValue() {
        return isInitialValue;
    }

    public void setInitialValue(boolean initialValue) {
        isInitialValue = initialValue;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
