package com.hilbing.sendsms.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hilbing.sendsms.R;
import com.hilbing.sendsms.models.ModelContacts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsRVAdapter extends RecyclerView.Adapter<ContactsRVAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<ModelContacts> arrayList;

    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;

    public ContactsRVAdapter(Context mContext, ArrayList<ModelContacts> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_contacts, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.contact_name.setText(arrayList.get(position).getName());
        holder.contact_phone.setText(arrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_contact_name)
        TextView contact_name;
        @BindView(R.id.tv_contact_phone)
        TextView contact_phone;
        @BindView(R.id.btn_contacts_call)
        Button sendSMS;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            sendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.SEND_SMS},123);
                    } else {
                        String message = "MESSAGE FROM ARKANHELP";
                        String phoneNumber = contact_phone.getText().toString();
                        if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNumber)){
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber, null,  message, null, null);
                        } else {
                            Toast.makeText(mContext, "Permission denied", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }


}
