package mubstimor.android.kwikorder.util;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import mubstimor.android.kwikorder.dao.TableDao;
import mubstimor.android.kwikorder.entity.Table;

@Database(entities = {Table.class}, version = 1, exportSchema = false)
public abstract class KwikRoomDatabase extends RoomDatabase {

    public abstract TableDao tableDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile KwikRoomDatabase INSTANCE;

    public static KwikRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (KwikRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KwikRoomDatabase.class, "kwik_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TableDao mDao;

        PopulateDbAsync(KwikRoomDatabase db) {
            mDao = db.tableDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mDao.deleteAll();

            Table table = new Table(
                    1,
                    "First table near the counter",
                    "https://res.cloudinary.com/andela-valkyrie/image/upload/v1563865326/food/table1.jpg"
            );
            mDao.insert(table);
            return null;
        }
    }

}