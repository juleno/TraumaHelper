package dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.CategoryContent;

@Dao()
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<CategoryContent.CategoryItem> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(CategoryContent.CategoryItem... categories);

    @Delete
    void delete(CategoryContent.CategoryItem category);

    @Update
    void update(CategoryContent.CategoryItem category);

    @Query("UPDATE categories SET isDone = 0")
    void resetAll();
}
