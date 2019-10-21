package models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class CategoryContent {

    /**
     * An array of sample (Category) items.
     */
    public static final List<CategoryItem> ITEMS = new ArrayList<CategoryItem>();
    private static void addItem(CategoryItem item) {
        ITEMS.add(item);
    }

    @Entity(tableName = "categories")
    public static class CategoryItem implements Parcelable {
        @PrimaryKey
        public final int id;
        @NonNull
        public final String name;
        public final String details;
        public boolean isDone;
        @Ignore
        public List<Photo> photos;

        public CategoryItem(int id, String name, String details) {
            this.id = id;
            this.name = name;
            this.details = details;
            this.isDone = false;
        }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        protected CategoryItem(Parcel in) {
            id = in.readInt();
            name = in.readString();
            details = in.readString();
            photos = in.createTypedArrayList(Photo.CREATOR);
            isDone = in.readBoolean();
        }

        public static final Creator<CategoryItem> CREATOR = new Creator<CategoryItem>() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public CategoryItem createFromParcel(Parcel in) {
                return new CategoryItem(in);
            }

            @Override
            public CategoryItem[] newArray(int size) {
                return new CategoryItem[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(name);
            parcel.writeString(details);
            parcel.writeTypedList(photos);
            parcel.writeBoolean(isDone);
        }
    }
}
