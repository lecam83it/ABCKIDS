package com.example.admin.fragment;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.admin.abckids.R;
import com.example.admin.abckids.SplashActivity;
import com.example.admin.adapter.ImageAdapter;
import com.example.admin.defines.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ABCFragment extends Fragment implements AdapterView.OnItemClickListener {


    private GridView gridView;
    private List<Integer> listImage;
    private ImageAdapter adapter;
    private TextToSpeech textToSpeech;

    public ABCFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_abc, container, false);

        addControls(view);

        gridView.setOnItemClickListener(this);
        return view;
    }

    private void addControls(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView);
        listImage = new ArrayList<>();
        listImage = Arrays.asList(Const.listLetters);

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(new Locale("vi"));
                }
            }
        });

        adapter = new ImageAdapter(getActivity(), R.layout.item, listImage);
        gridView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        textToSpeech.speak(Const.readLetters[position], TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    public void onPause() {
        if(textToSpeech != null) {
            textToSpeech.stop();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(textToSpeech != null) {
            textToSpeech.shutdown();
            Log.d("Destroyed", "SUCCESS");
        }
        super.onDestroy();
    }
}
