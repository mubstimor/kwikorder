package mubstimor.android.kwikorder.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import mubstimor.android.kwikorder.R;
import mubstimor.android.kwikorder.adapter.TableListAdapter;
import mubstimor.android.kwikorder.entity.Table;
import mubstimor.android.kwikorder.viewmodel.TableViewModel;

public class RecordOrderActivity extends AppCompatActivity {

    private TableViewModel mTableViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_order);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TableListAdapter adapter = new TableListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTableViewModel = ViewModelProviders.of(this).get(TableViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTableViewModel.getAllTables().observe(this, new Observer<List<Table>>() {
            @Override
            public void onChanged(@Nullable final List<Table> tables) {
                // Update the cached copy of the tables in the adapter.
                adapter.setTables(tables);
            }
        });
    }
}
