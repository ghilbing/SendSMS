package com.hilbing.sendsms.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hilbing.sendsms.R;
import com.hilbing.sendsms.adapters.CallsRVAdapter;
import com.hilbing.sendsms.models.ModelCalls;

import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCalls extends Fragment {

    @BindView(R.id.rv_calls)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private View view;

    public FragmentCalls() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calls, container, false);
        ButterKnife.bind(this, view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        CallsRVAdapter adapter = new CallsRVAdapter(getContext(), getCallLogs());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private ArrayList<ModelCalls> getCallLogs() {

        ArrayList<ModelCalls> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALL_LOG}, 123);
        }
        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " ASC");

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date_idx = cursor.getColumnIndex(CallLog.Calls.DATE);

        cursor.moveToFirst();
        while(cursor.moveToNext()){
            Date date = new Date(Long.valueOf(cursor.getString(date_idx)));
            String month_date, week_day, month;

            month_date = (String) DateFormat.format("dd", date);
            week_day = (String) DateFormat.format("EEEE", date);
            month = (String) DateFormat.format("MMMM", date);

            list.add(new ModelCalls(cursor.getString(number), cursor.getString(duration), week_day + " " + month_date + " " + month));
        }
        return list;
    }

}
