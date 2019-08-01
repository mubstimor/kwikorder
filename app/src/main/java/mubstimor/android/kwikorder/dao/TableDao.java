package mubstimor.android.kwikorder.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mubstimor.android.kwikorder.entity.Table;

@Dao
public interface TableDao {

    @Query("SELECT * from seating_table ORDER BY number ASC")
    LiveData<List<Table>> getAlphabetizedWords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Table table);

    @Query("DELETE FROM seating_table")
    void deleteAll();
}
