package com.example.whatsapp.firstdrawe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.whatsapp.R;

public class FragmentTwo extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        ivIcon = view.findViewById(R.id.frag2_icon);
        tvItemName = view.findViewById(R.id.frag2_text);

        // Check if the arguments are not null before accessing them
        if (getArguments() != null) {
            // Retrieve the arguments passed to the fragment
            String itemName = getArguments().getString(ITEM_NAME);
            int imageResId = getArguments().getInt(IMAGE_RESOURCE_ID);

            // Set the TextView and ImageView based on the passed data
            tvItemName.setText(itemName);
            ivIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), imageResId));
        }

        return view;
    }
}
