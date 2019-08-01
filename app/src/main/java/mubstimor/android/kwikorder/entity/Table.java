package mubstimor.android.kwikorder.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "seating_table")
public class Table {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "number")
    private int number;

    private String description;

    @ColumnInfo(name = "table_image")
    private String image;

    public Table(@NonNull int number, String description, String image) {
        this.number = number;
        this.description = description;
        this.image = image;
    }

    @NonNull
    public int getNumber() {
        return this.number;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}