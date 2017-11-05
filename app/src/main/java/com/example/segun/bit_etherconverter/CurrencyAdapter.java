package com.example.segun.bit_etherconverter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Segun on 11/5/2017.
 */

public class CurrencyAdapter  extends ArrayAdapter<Currencies> {


    public CurrencyAdapter(Activity context, List<Currencies> currs) {
        super(context, 0, currs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.curr_list_item, parent, false);
        }

        // Find the devs at the given position in the list of earthquakes
        Currencies currentCurrs = getItem(position);


        TextView btcView = listItemView.findViewById(R.id.btc_view);

        assert currentCurrs != null;
        btcView.setText( String.valueOf (  currentCurrs.getmBtc()) );


        TextView ethView = listItemView.findViewById(R.id.eth_view);

        ethView.setText( String.valueOf (  currentCurrs.getmEth()) );


        TextView currView = listItemView.findViewById(R.id.curr_view);

        currView.setText ( currentCurrs.getmCurr () );

        return listItemView;
    }

}
