package com.easygrocery.shivam.easygrocery;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Final extends Activity {

    ImageView back;
    TextView actionBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);


        back = findViewById(R.id.back);
        actionBarText = findViewById(R.id.actionbarText);
        actionBarText.setText("Easy Grocery");
        back.setOnClickListener(onClickListener);
    }
    public  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
