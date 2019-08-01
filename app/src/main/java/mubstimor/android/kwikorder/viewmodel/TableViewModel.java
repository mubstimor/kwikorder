package mubstimor.android.kwikorder.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mubstimor.android.kwikorder.entity.Table;
import mubstimor.android.kwikorder.repository.TableRepository;


public class TableViewModel extends AndroidViewModel {

    private TableRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Table>> mAllTables;

    public TableViewModel(Application application) {
        super(application);
        mRepository = new TableRepository(application);
        mAllTables = mRepository.getAllTables();
    }

    public LiveData<List<Table>> getAllTables() {
        return mAllTables;
    }

    void insert(Table table) {
        mRepository.insert(table);
    }
}
