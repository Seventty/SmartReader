/*
package com.example.smartreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentList extends Fragment implements AdapterView.OnItemClickListener {

    com.example.itlavision.ListLanguage listLanguage = com.example.itlavision.ListLanguage.getInstance();
    ArrayList<String> listGrid = new ArrayList<String>();
    ArrayList<String> listCode = new ArrayList<String>();
    GridView gridView;

    String boton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            boton = getArguments().getString("boton");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_layout, container, false);
        gridView = view.findViewById(R.id.grid);

        listLanguage.TranslateThisText(null, "es", null, getActivity(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result, ArrayList listLenguage) {
                for (int i = 0; i < listLenguage.size(); i++) {
                    JSONObject jsonObject = (JSONObject) listLenguage.get(i);
                    try {
                        String name = jsonObject.getString("name");
                        String language = jsonObject.getString("language");
                        listGrid.add(name);
                        listCode.add(language);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setArray();
            }
        });


        return view;
    }

    public void setArray(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listGrid);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        com.example.itlavision.FragmentTranslated fragmentTranslated = com.example.itlavision.FragmentTranslated.getInstance();

        Bundle bundle = new Bundle();
        if(boton == "Idioma1"){
            bundle.putString("name", listGrid.get(position));
            bundle.putString("language", listCode.get(position));
            bundle.putString("boton", "Idioma1");
        }

        if (boton == "Idioma2"){
            bundle.putString("name", listGrid.get(position));
            bundle.putString("language", listCode.get(position));
            bundle.putString("boton", "Idioma2");
        }

        fragmentTranslated.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Fragment, fragmentTranslated).commit();
    }

}
*/
