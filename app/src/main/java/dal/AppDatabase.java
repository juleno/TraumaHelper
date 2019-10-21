package dal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import models.CategoryContent;
import models.Photo;

@Database(entities = {CategoryContent.CategoryItem.class, Photo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract PhotoDao photoDao();
}
