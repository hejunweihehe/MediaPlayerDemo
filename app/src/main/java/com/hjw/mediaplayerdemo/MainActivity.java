package com.hjw.mediaplayerdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @BindView(R.id.btn_select_media)
    Button mSelectBtn;

    @BindView(R.id.txt_media_name)
    TextView mMediaNameView;

    @BindView(R.id.txt_all_info)
    TextView txt_all_info;
    Listener listener = new Listener();
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSelectBtn.setOnClickListener(listener);
    }

    class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_select_media: {
                    Intent intent = new Intent(MainActivity.this, AudioActivity.class);
                    startActivityForResult(intent, 1);
                    break;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1 && data != null) {
            uri = data.getData();
            StringBuilder sb = new StringBuilder();
            sb.append("getAuthority = " + uri.getAuthority());
            sb.append("getEncodedAuthority = " + uri.getEncodedAuthority());
            sb.append("getEncodedFragment = " + uri.getEncodedFragment());
            sb.append("getEncodedPath = " + uri.getEncodedPath());
            sb.append("getEncodedQuery = " + uri.getEncodedQuery());
            sb.append("getEncodedSchemeSpecificPart = " + uri.getEncodedSchemeSpecificPart());
            sb.append("getEncodedUserInfo = " + uri.getEncodedUserInfo());
            sb.append("getFragment = " + uri.getFragment());
            sb.append("getHost = " + uri.getHost());
            sb.append("getLastPathSegment = " + uri.getLastPathSegment());
            sb.append("getPath = " + uri.getPath());
            sb.append("getQuery = " + uri.getQuery());
            sb.append("getScheme = " + uri.getScheme());
            sb.append("getSchemeSpecificPart = " + uri.getSchemeSpecificPart());
            sb.append("getUserInfo = " + uri.getUserInfo());
            sb.append("getPort = " + uri.getPort());

            mMediaNameView.setText(sb.toString());
        }
    }

}
