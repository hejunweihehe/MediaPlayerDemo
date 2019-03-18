package com.hjw.mediaplayerdemo;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AudioCursorAdapter extends CursorAdapter {
    public AudioCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_audio, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameView = view.findViewById(R.id.txt_audio_name);
        TextView authorView = view.findViewById(R.id.txt_audio_author);

        nameView.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        authorView.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
    }
}
