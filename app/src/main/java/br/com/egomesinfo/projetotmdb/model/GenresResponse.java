package br.com.egomesinfo.projetotmdb.model;

import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class GenresResponse implements Parcelable {

    @SerializedName("id")
    private int id_genre;

    @SerializedName("results")
    private List<Genre> results;

    public int getIdGenre(){
        return id_genre;
    }

    public void setIdGenre(int id_genre){
        this.id_genre = id_genre;
    }

    public List<Genre> getResults() {
        return results;
    }

    public void setGenre(List<Genre> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id_genre);
        dest.writeTypedList(this.results);

    }

    public GenresResponse() {
    }

    protected GenresResponse(Parcel in) {
        this.id_genre = in.readInt();
        this.results = in.createTypedArrayList(Genre.CREATOR);

    }

    public static final Parcelable.Creator<GenresResponse> CREATOR = new Parcelable.Creator<GenresResponse>() {
        @Override
        public GenresResponse createFromParcel(Parcel source) {
            return new GenresResponse(source);
        }

        @Override
        public GenresResponse[] newArray(int size) {
            return new GenresResponse[size];
        }
    };
}
