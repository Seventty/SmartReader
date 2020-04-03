/*
package com.example.smartreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentTranslated extends Fragment implements View.OnClickListener {
    FloatingActionButton enter;
    com.example.itlavision.TranslateLanguage translateLanguage;

    String languageOrigin;
    String languageOriginCode;

    String languageDestiny;
    String languageDestinyCode;

    TextView editText;

    EditText textToTranslate;
    Button Idioma1;
    Button Idioma2;
    TextView traductorT;
    ImageButton clear;
    ImageButton copy;
    ImageButton invert;

    private static FragmentTranslated instancia;
    public static FragmentTranslated getInstance() {
        if (instancia == null){
            instancia = new FragmentTranslated();
        }
        return instancia;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getString("boton") == "Idioma1"){
                languageOrigin = getArguments().getString("name");
                languageOriginCode = getArguments().getString("language");
            }
            if (getArguments().getString("boton") == "Idioma2"){
                languageDestiny= getArguments().getString("name");
                languageDestinyCode= getArguments().getString("language");
            }
        } else {
            languageOrigin = "Ingles";
            languageOriginCode = "en";

            languageDestiny= "Español";
            languageDestinyCode= "es";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.translated_layout, container, false);

        translateLanguage = TranslateLanguage.getInstance();
        enter = view.findViewById(R.id.enter);

        editText = getActivity().findViewById(R.id.translatedText);

        textToTranslate = view.findViewById(R.id.edittext);
        Idioma1= view.findViewById(R.id.Idioma1);
        Idioma2= view.findViewById(R.id.Idioma2);
        traductorT = getActivity().findViewById(R.id.traduccionT);
        clear = view.findViewById(R.id.clear);
        copy = getActivity().findViewById(R.id.copy);
        invert = view.findViewById(R.id.invert);




        if(languageOrigin != null){
            Idioma1.setText(languageOrigin);
        }

        if (languageDestiny != null) {
            Idioma2.setText(languageDestiny);
            traductorT.setText(languageDestiny);
        }

        enter.setOnClickListener(this);
        Idioma1.setOnClickListener(this);
        Idioma2.setOnClickListener(this);
        clear.setOnClickListener(this);
        copy.setOnClickListener(this);
        invert.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enter:
                translateLanguage.TranslateThisText(languageOriginCode, languageDestinyCode, textToTranslate.getText().toString(), getActivity(), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result, ArrayList listLenguage) {
                        editText.setText(result);
                    }
                });
                break;

            case R.id.Idioma1:
                FragmentList fragmentList = new FragmentList();

                Bundle bundle = new Bundle();
                bundle.putString("boton", "Idioma1");
                fragmentList.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, fragmentList).commit();
                break;

            case R.id.Idioma2:
                fragmentList = new FragmentList();

                bundle = new Bundle();
                bundle.putString("boton", "Idioma2");
                fragmentList.setArguments(bundle);

                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, fragmentList).commit();
                break;

            case R.id.clear:
                editText.setText("");
                textToTranslate.setText("");
                break;

            case R.id.copy:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, editText.getText());
                intent.setType("text/plain");

                Intent chooser = Intent.createChooser(intent, "Selecciona");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;

            case R.id.invert:

                String languageOriginTemp= languageOrigin;
                String languageOriginCodeTemp= languageOriginCode;

                String languageDestinyTemp= languageDestiny;
                String languageDestinyCodeTemp= languageDestinyCode;

                languageOrigin= languageDestinyTemp;
                languageOriginCode= languageDestinyCodeTemp;

                languageDestiny= languageOriginTemp;
                languageDestinyCode= languageOriginCodeTemp;

                if(languageOrigin != null){
                    Idioma1.setText(languageOrigin);
                }

                if (languageDestiny != null) {
                    Idioma2.setText(languageDestiny);
                    traductorT.setText(languageDestiny);
                }
                break;
        }
    }
}
*/
