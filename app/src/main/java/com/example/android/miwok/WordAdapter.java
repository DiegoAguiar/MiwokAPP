package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by diego.almeida on 13/04/2017.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorId;

    public WordAdapter(Context context,List<Word> objects, int colorId) {
        super(context, 0, objects);
        mColorId = colorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_iten, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextVier = (TextView) listItemView.findViewById(R.id.default_text_id);
        defaultTextVier.setText(currentWord.getDefaultTranslation());

        ImageView imgMenu = (ImageView) listItemView.findViewById(R.id.ic_menu);
        if(currentWord.hasImage()) {
            imgMenu.setImageResource(currentWord.getImgMenu());
        }else{
            imgMenu.setVisibility(View.GONE);
        }

        View translationList = listItemView.findViewById(R.id.translationList);
        int color = ContextCompat.getColor(getContext(),mColorId);
        translationList.setBackgroundColor(color);
        return listItemView;
    }
}
