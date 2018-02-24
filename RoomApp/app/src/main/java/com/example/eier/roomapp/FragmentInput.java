package com.example.eier.roomapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInput extends Fragment {
    private EditText inputName;
    private EditText inputAge;

    public FragmentInput() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_fragment_input, container, false);
         inputName = (EditText) view.findViewById(R.id.editText);
         inputAge = (EditText) view.findViewById(R.id.editText2);
        return view;
    }

}
