package com.example.smartreader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.smartreader.FragmentTranslated;


public class TraductorMain extends AppCompatActivity {
    FragmentTranslated fragmentTranslated;
    FragmentList fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traductor_main);

        fragmentTranslated = new FragmentTranslated();
        fragmentList = new FragmentList();
        getSupportFragmentManager().beginTransaction().add(R.id.Fragment, fragmentTranslated).commit();

    }
}
