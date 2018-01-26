package com.example.eier.fragment2fragmentcommunication;


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

    private EditText editText;
    private Button button;
    OnMessageReadListener onMessageReadListener;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        editText = view.findViewById(R.id.txt_message);
        button = view.findViewById(R.id.b1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageReadListener.onMessage(editText.getText().toString());
            }
        });
        
        return view;
    }


    public interface OnMessageReadListener{

        public void onMessage(String message);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            onMessageReadListener = (OnMessageReadListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(activity.toString() + " Must implement onMessagesent");
        }
    }

    @Override

    public void onResume(){
        super.onResume();
        editText.setText("");
    }


}
