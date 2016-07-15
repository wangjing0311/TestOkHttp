package com.wj.testokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tvHeader;
    private Button tvPostStringHttp;
    private Button tvPostStreamHttp;
    private Button tvPostFileHttp;
    private Button tvPostParams;
    private Button tvMulRequest;
    private Button tvParseResponse;
    private Button tvResponseCache;
    private Button tvCancleCall;
    private Button tvTimeOut;
    private Button tvCallConfigure;
    private Button tvHandleAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHeader = (Button) findViewById(R.id.tv_one_header);
        tvHeader.setOnClickListener(this);
        tvPostStringHttp = (Button) findViewById(R.id.tv_two_post_string);
        tvPostStringHttp.setOnClickListener(this);
        tvPostStreamHttp = (Button) findViewById(R.id.tv_three_post_stream);
        tvPostStreamHttp.setOnClickListener(this);
        tvPostFileHttp = (Button) findViewById(R.id.tv_post_file);
        tvPostFileHttp.setOnClickListener(this);
        tvPostParams = (Button) findViewById(R.id.tv_post_params);
        tvPostParams.setOnClickListener(this);
        tvMulRequest = (Button) findViewById(R.id.tv_post_multipart_request);
        tvMulRequest.setOnClickListener(this);
        tvParseResponse = (Button) findViewById(R.id.tv_parse_response);
        tvParseResponse.setOnClickListener(this);
        tvResponseCache = (Button) findViewById(R.id.tv_response_cache);
        tvResponseCache.setOnClickListener(this);
        tvCancleCall = (Button) findViewById(R.id.tv_cancle_calls);
        tvCancleCall.setOnClickListener(this);
        tvTimeOut = (Button) findViewById(R.id.tv_time_out);
        tvTimeOut.setOnClickListener(this);
        tvCallConfigure = (Button) findViewById(R.id.tv_per_call_con);
        tvCallConfigure.setOnClickListener(this);
        tvHandleAuth = (Button) findViewById(R.id.tv_handle_auth);
        tvHandleAuth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one_header:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        okHttpReq();
                    }
                }).start();
                Toast.makeText(this, "tv_one_header--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_two_post_string:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStringOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_two_post_string--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_three_post_stream:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStreamOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_three_post_stream--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_post_file:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postFileOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_post_file--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_post_params:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postParamsOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_post_params--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_post_multipart_request:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postMulParamsOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_post_multipart_request--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_parse_response:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        responseParseGsonOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_parse_response--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_response_cache:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStreamOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_one_header--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_cancle_calls:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStreamOkHttp();
                    }
                }).start();
                Toast.makeText(this, "tv_cancle_calls--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_time_out:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        timeOutOkHttp();
                    }
                }).start();
                Toast.makeText(this, "----tv_time_out--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_per_call_con:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStreamOkHttp();
                    }
                }).start();
                Toast.makeText(this, "----tv_per_call_con--调用--", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_handle_auth:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postStreamOkHttp();
                    }
                }).start();
                Toast.makeText(this, "----tv_handle_auth--调用--", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private final OkHttpClient client = new OkHttpClient();

    public void okHttpReq() {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server: " + response.header("Server"));
        System.out.println("Date: " + response.header("Date"));
        System.out.println("Vary: " + response.headers("Vary"));
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public void postStringOkHttp() {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void postStreamOkHttp() {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void postFileOkHttp() {
        File file = new File("README.md");

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void postParamsOkHttp() {
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String IMGUR_CLIENT_ID = "...";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public void postMulParamsOkHttp() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Gson gson = new Gson();
    public void responseParseGsonOkHttp() {
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

    public void timeOutOkHttp() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            System.out.println("Response completed: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
