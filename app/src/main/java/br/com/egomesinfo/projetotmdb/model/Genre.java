package br.com.egomesinfo.projetotmdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Genre implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();

    public Genre(Integer id, String name, List<Integer> genreIds){
        this.id = id;
        this.name = name;
        this.genreIds = genreIds;
    }

    public Genre(){

    }

    public static final Comparator<Genre> BY_ID = new Comparator<Genre>() {
        @Override
        public int compare(Genre genre, Genre g1) {

            return genre.id.compareTo(g1.id);
        }
    };

    public Integer getId(){
        return id;
    }

    public void setKey(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
            }

    protected Genre(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.genreIds = new ArrayList<Integer>();

    }

    public static final Parcelable.Creator<Genre> CREATOR = new Parcelable.Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel source) {
            return new Genre(source);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}

