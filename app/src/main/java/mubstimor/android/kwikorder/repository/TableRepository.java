package mubstimor.android.kwikorder.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import mubstimor.android.kwikorder.dao.TableDao;
import mubstimor.android.kwikorder.entity.Table;
import mubstimor.android.kwikorder.util.KwikRoomDatabase;


public class TableRepository {

    private TableDao mTableDao;
    private LiveData<List<Table>> mAllTables;

    // Note that in order to unit test the TableRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public TableRepository(Application application) {
        KwikRoomDatabase db = KwikRoomDatabase.getDatabase(application);
        mTableDao = db.tableDao();
        mAllTables = mTableDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Table>> getAllTables() {
        return mAllTables;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(Table table) {
        new insertAsyncTask(mTableDao).execute(table);
    }

    private static class insertAsyncTask extends AsyncTask<Table, Void, Void> {

        private TableDao mAsyncTaskDao;

        insertAsyncTask(TableDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Table... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
