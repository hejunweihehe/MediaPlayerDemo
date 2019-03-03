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
//                    Intent intent = new Intent(MainActivity.this, AudioActivity.class);
//                    startActivity(intent);
                    updateMediaView();
                    break;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
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

    private void updateMediaView() {
        ContentResolver cp = getContentResolver();
        Cursor cursor = cp.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        String content = "";

        content += "EXTERNAL_CONTENT_URI:\n" + Arrays.toString(cursor.getColumnNames()) + "\n";

        cursor = cp.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        content += "\nINTERNAL_CONTENT_URI:\n" + Arrays.toString(cursor.getColumnNames());

        content += "\n\n";
        while (cursor.moveToNext()) {
            //文件名
            int displayNameColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            int contentTypeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.CONTENT_TYPE);
            int titleKeyColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
            int entryContentTypeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ENTRY_CONTENT_TYPE);
            int countColumn = cursor.getColumnIndex(MediaStore.Audio.Media._COUNT);
            int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            //歌曲名称
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            //歌手
            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            //大小,单位byte
            int sizeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            //专辑
            int albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            //时长，单位毫秒
            int durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int dataColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            //类型
            int mimeTypeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);

            if (displayNameColumn != -1) {
                content += MediaStore.Audio.Media.DISPLAY_NAME + ":" + cursor.getString(displayNameColumn) + "\n";
            }
            if (contentTypeColumn != -1) {
                content += MediaStore.Audio.Media.CONTENT_TYPE + ":" + cursor.getString(contentTypeColumn) + "\n";
            }
            if (titleKeyColumn != -1) {
                content += MediaStore.Audio.Media.DEFAULT_SORT_ORDER + ":" + cursor.getString(titleKeyColumn) + "\n";
            }
            if (entryContentTypeColumn != -1) {
                content += MediaStore.Audio.Media.ENTRY_CONTENT_TYPE + ":" + cursor.getString(entryContentTypeColumn) + "\n";
            }
            if (countColumn != -1) {
                content += MediaStore.Audio.Media._COUNT + ":" + cursor.getInt(countColumn) + "\n";
            }
            if (idColumn != -1) {
                content += MediaStore.Audio.Media._ID + ":" + cursor.getInt(idColumn) + "\n";
            }
            if (titleColumn != -1) {
                content += MediaStore.Audio.Media.TITLE + ":" + cursor.getString(titleColumn) + "\n";
            }
            if (artistColumn != -1) {
                content += MediaStore.Audio.Media.ARTIST + ":" + cursor.getString(artistColumn) + "\n";
            }
            if (sizeColumn != -1) {
                content += MediaStore.Audio.Media.SIZE + ":" + cursor.getInt(sizeColumn) + "\n";
            }
            if (albumColumn != -1) {
                content += MediaStore.Audio.Media.ALBUM + ":" + cursor.getString(albumColumn) + "\n";
            }
            if (durationColumn != -1) {
                content += MediaStore.Audio.Media.DURATION + ":" + cursor.getLong(durationColumn) + "\n";
            }
            if (dataColumn != -1) {
                content += MediaStore.Audio.Media.DATA + ":" + cursor.getString(dataColumn) + "\n";
            }
            if (mimeTypeColumn != -1) {
                content += MediaStore.Audio.Media.MIME_TYPE + ":" + cursor.getString(mimeTypeColumn) + "\n";
            }
            content += "\n";
        }

        txt_all_info.setText(content);
    }

}
