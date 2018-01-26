package com.example.eier.fragmentcommunication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private Button button;
    private EditText editText;
    OnMessageReadListener onMessageReadListener;
    public MessageFragment() {
        // Required empty public constructor
    }


    public interface OnMessageReadListener{

        public void onMessageRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        editText = (EditText) view.findViewById(R.id.text_message);
        button = (Button) view.findViewById(R.id.b1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageReadListener.onMessageRead(editText.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            onMessageReadListener = (OnMessageReadListener) activity;
        }catch (Exception ex){
            throw new ClassCastException(activity.toString() + " must overrride onMessageRead..");
        }

    }
}
