package org.pursuit.unit_02_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /**
         * This textview doesn't exist in your second activity it should be second_textView
         */
        TextView textView = (TextView) findViewById(R.id.info_textview);
        Intent intent = getIntent();
        result = "";
        intent.getStringExtra("result");
        textView.setText(result);
    }
}
