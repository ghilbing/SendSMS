package com.hilbing.sendsms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hilbing.sendsms.R;
import com.hilbing.sendsms.models.ModelCalls;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallsRVAdapter extends RecyclerView.Adapter<CallsRVAdapter.CallsViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private ArrayList<ModelCalls> mListCalls;

    public CallsRVAdapter(Context mContext, ArrayList<ModelCalls> mListCalls) {
        this.mContext = mContext;
        this.mListCalls = mListCalls;
    }

    @NonNull
    @Override
    public CallsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_calls, parent, false);
        return new CallsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallsViewHolder holder, int position) {
        holder.name.setText(mListCalls.get(position).getName());
        holder.duration.setText(mListCalls.get(position).getDuration());
        holder.date.setText(mListCalls.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mListCalls.size();
    }


    public class CallsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_call_contact_name)
        TextView name;
        @BindView(R.id.tv_call_duration)
        TextView duration;
        @BindView(R.id.tv_call_date)
        TextView date;

        public CallsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
