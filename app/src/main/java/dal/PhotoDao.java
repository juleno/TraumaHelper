package dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import models.Photo;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photos WHERE idCategory = :idCategory")
    List<Photo> getAllByIdCategory(int idCategory);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Photo... photos);

    @Delete
    void delete(Photo photo);
}
