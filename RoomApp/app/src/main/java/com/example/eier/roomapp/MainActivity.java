package com.example.eier.roomapp;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Button showBtn, hideBtn;
    private FrameLayout fragContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpFragment();
        initWidgets();
    }



    public void initWidgets(){
        showBtn = (Button) findViewById(R.id.inputBtn);
        hideBtn = (Button) findViewById(R.id.hideBtn);
        fragContainer = (FrameLayout) findViewById(R.id.fragment_container);

    }

    public void setUpFragment(){
        FragmentInput fragmentInput = new FragmentInput();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentInput, null);
        fragmentTransaction.commit();

    }

    public void showInputMethod(View view){

        fragContainer.setVisibility(view.VISIBLE);


    }

    public void hideInputMethod(View view){
        fragContainer.setVisibility(view.INVISIBLE);

    }
}
