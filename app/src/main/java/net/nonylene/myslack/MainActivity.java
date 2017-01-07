package net.nonylene.myslack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit()
                .putInt("hoge", 1)
                .putString("piyo", "piyoipyo")
                .putBoolean("foo", true)
                .apply();

        sharedPreferences.getInt("hoge", 0);
        sharedPreferences.getString("piyo", null);
        sharedPreferences.getBoolean("foo", false);
    }
}
