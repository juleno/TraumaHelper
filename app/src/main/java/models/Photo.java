package models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "photos", foreignKeys = @ForeignKey(entity = CategoryContent.CategoryItem.class,
        parentColumns = "id",
        childColumns = "idCategory",
        onDelete = ForeignKey.NO_ACTION), indices = {
        @Index(value = {"idCategory"}, name = "catIndex")
})

public class Photo implements Parcelable  {
    @PrimaryKey() @NonNull
    private String name;
    private String description;
    private String image;
    @ColumnInfo(name = "idCategory")
    private int idCategory;

    public Photo(String name, String description, String image, int idCategory) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.idCategory = idCategory;
    }

    protected Photo(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
        idCategory = in.readInt();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeInt(idCategory);
    }
}
