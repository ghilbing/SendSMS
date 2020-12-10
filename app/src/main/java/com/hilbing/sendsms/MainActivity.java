package com.hilbing.sendsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hilbing.sendsms.adapters.ViewPagerAdapter;
import com.hilbing.sendsms.fragments.FragmentCalls;
import com.hilbing.sendsms.fragments.FragmentContacts;
import com.hilbing.sendsms.fragments.FragmentFav;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private final int[] ICONS = {R.drawable.ic_call, R.drawable.ic_contacts, R.drawable.ic_fav};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentCalls(), getString(R.string.calls));
        adapter.addFragment(new FragmentContacts(), getString(R.string.contacts));
        adapter.addFragment(new FragmentFav(), getString(R.string.favorites));

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount() ; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }

    }

    private void askPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 123);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, 123);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 123);
        }

    }


}