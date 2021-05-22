package com.example.android.miwok;

import android.content.Context;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class WordAdaptor extends ArrayAdapter<Word> {
private int BACKGROUND_COLOR;
Context mcontext;
    public WordAdaptor(@NonNull Context context, ArrayList<Word> Words,int BACKGROUNG_COLOR) {
super(context,0,Words);
this.BACKGROUND_COLOR=BACKGROUNG_COLOR;
mcontext=context;
    }



    @NonNull

    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      View ListViewItem= convertView;

      if(ListViewItem==null){
          ListViewItem= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
      }

        Word currentword= (Word) getItem(position);
        LinearLayout parentTxt=(LinearLayout) ListViewItem.findViewById(R.id.parentTxt);
        parentTxt.setBackgroundResource(BACKGROUND_COLOR);
        TextView txtEnglish= (TextView) ListViewItem.findViewById(R.id.Txt2);
        TextView txtMiwok= (TextView) ListViewItem.findViewById(R.id.Txt1);

        txtEnglish.setText(currentword.getDefaultTranslation());
        txtMiwok.setText(currentword.getMiwokTranslation());


            ImageView ImageObject=(ImageView) ListViewItem.findViewById(R.id.ImageObject);

      if(currentword.hasImage())  {
          ImageObject.setImageResource(currentword.getmImageResourceId());
      ImageObject.setVisibility(View.VISIBLE);
      }

       else {
           ImageObject.setVisibility(View.GONE);

      }



       return ListViewItem;

    }
}
