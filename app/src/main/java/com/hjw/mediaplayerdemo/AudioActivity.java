package com.hjw.mediaplayerdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        list = findViewById(R.id.list);
        ContentResolver cp = getContentResolver();
        Cursor cursor = cp.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_audio, cursor,
                new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST},
                new int[]{R.id.txt_media_name, R.id.txt_audio_author},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list.setAdapter(adapter);
    }
}
