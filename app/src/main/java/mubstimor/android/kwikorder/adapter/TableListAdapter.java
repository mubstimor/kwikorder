package mubstimor.android.kwikorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import mubstimor.android.kwikorder.R;
import mubstimor.android.kwikorder.entity.Table;

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.TableViewHolder> {

    class TableViewHolder extends RecyclerView.ViewHolder {
        TextView tableItemView;

        public TableViewHolder(View itemView) {
            super(itemView);
            tableItemView = (TextView) itemView.findViewById(R.id.tableNumber);
        }
    }

    private final LayoutInflater mInflater;
    private List<Table> mTables = Collections.emptyList(); // Cached copy of words

    public TableListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.table_card_item, parent, false);
        return new TableViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        Table current = mTables.get(position);
        holder.tableItemView.setText(Integer.toString(current.getNumber()));
    }

    public void setTables(List<Table> tables) {
        mTables = tables;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTables.size();
    }
}