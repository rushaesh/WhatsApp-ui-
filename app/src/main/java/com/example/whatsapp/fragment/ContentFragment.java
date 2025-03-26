package com.example.whatsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.whatsapp.R;

public class ContentFragment extends Fragment {

    private static final String ARG_ITEM_NAME = "item_name";

    public static ContentFragment newInstance(String itemName) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_NAME, itemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        TextView textView = view.findViewById(R.id.textView);
        if (getArguments() != null) {
            String itemName = getArguments().getString(ARG_ITEM_NAME);
            textView.setText(itemName);
        }

        return view;
    }
}
