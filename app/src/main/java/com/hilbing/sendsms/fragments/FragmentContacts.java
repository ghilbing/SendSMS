package com.hilbing.sendsms.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hilbing.sendsms.R;
import com.hilbing.sendsms.adapters.ContactsRVAdapter;
import com.hilbing.sendsms.models.ModelContacts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentContacts extends Fragment
{

    @BindView(R.id.rv_contacts)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ContactsRVAdapter adapter;


    private View view;


    public FragmentContacts(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactsRVAdapter(getContext(), getContacts());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private ArrayList<ModelContacts> getContacts(){

        ArrayList<ModelContacts> contactsList = new ArrayList<>();

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            contactsList.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
        }
        return contactsList;
    }
}
