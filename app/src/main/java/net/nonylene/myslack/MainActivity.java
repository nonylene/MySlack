package net.nonylene.myslack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView 前に行うと View が表示されていないのでエラー
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.post_edit);
        Button submitButton = (Button) findViewById(R.id.post_button);

//        editText.setHint("KMC");
//        editText.setVisibility(View.GONE);
//        // pixel
//        editText.setWidth(300);

        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "clicked!", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}
