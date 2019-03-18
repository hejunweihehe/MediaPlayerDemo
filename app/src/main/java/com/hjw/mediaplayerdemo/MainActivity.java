package com.hjw.mediaplayerdemo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    Listener listener = new Listener();
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSelectBtn.setOnClickListener(listener);
        mMediaNameView.setMovementMethod(ScrollingMovementMethod.getInstance());
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
//            sb.append("getAuthority = " + uri.getAuthority());
//            sb.append("\ngetEncodedAuthority = " + uri.getEncodedAuthority());
//            sb.append("\ngetEncodedFragment = " + uri.getEncodedFragment());
            sb.append("\ngetEncodedPath = " + uri.getEncodedPath());
//            sb.append("\ngetEncodedQuery = " + uri.getEncodedQuery());
            sb.append("\ngetEncodedSchemeSpecificPart = " + uri.getEncodedSchemeSpecificPart());
//            sb.append("\ngetEncodedUserInfo = " + uri.getEncodedUserInfo());
//            sb.append("\ngetFragment = " + uri.getFragment());
//            sb.append("\ngetHost = " + uri.getHost());
            sb.append("\ngetLastPathSegment = " + uri.getLastPathSegment());//文件路径的最后一个
            sb.append("\ngetPath = " + uri.getPath());
//            sb.append("\ngetQuery = " + uri.getQuery());
//            sb.append("\ngetScheme = " + uri.getScheme());
            sb.append("\ngetSchemeSpecificPart = " + uri.getSchemeSpecificPart());
//            sb.append("\ngetUserInfo = " + uri.getUserInfo());
            sb.append("\ngetPort = " + uri.getPort());
            sb.append("\ngetPathSegments:");
            for (String s : uri.getPathSegments()) {
                sb.append("\n\t" + s);
            }
            sb.append("\ngetQueryParameterNames:");
            for (String s : uri.getQueryParameterNames()) {
                sb.append("\n\t" + s);
            }
            mMediaNameView.setText(sb.toString());
        }
    }

}
