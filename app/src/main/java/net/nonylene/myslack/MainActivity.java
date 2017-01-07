package net.nonylene.myslack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                        sendRequest();
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

    public void sendRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add("token", getString(R.string.token))
                .add("channel", "#android-project")
                .add("text", "こんにちはこんにちは")
                .build();

        Request slackRequest = new Request.Builder()
                .url("https://slack.com/api/chat.postMessage")
                .post(formBody)
                .build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // do nothing
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String body = response.body().string();
                new Handler(Looper.getMainLooper()).post(
                        new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, body, Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        };

        new OkHttpClient().newCall(slackRequest).enqueue(callback);
    }
}
