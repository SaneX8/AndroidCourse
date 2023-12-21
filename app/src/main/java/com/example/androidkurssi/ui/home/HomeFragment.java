package com.example.androidkurssi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidkurssi.ExamActivity;
import com.example.androidkurssi.GameActivity;
import com.example.androidkurssi.R;
import com.example.androidkurssi.YtjActivity;
import com.example.androidkurssi.databinding.FragmentHomeBinding;
import com.google.android.material.textfield.TextInputEditText;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static final String TAG ="MyAppMessage";
    public String value1;
    public String value2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        Log.e(TAG, "activating play view");

        final Button button = root.findViewById(R.id.about_button);
        final View someText = root.findViewById(R.id.textView2);
        final Button gameButton = root.findViewById(R.id.gameStart);
        final Button yTJBtn = root.findViewById(R.id.Searchbutton);
        final TextInputEditText searchSth = root.findViewById(R.id.Ytjtxt);
        final Button ExamBtnn = root.findViewById(R.id.TentBtnn);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (someText.getVisibility() == View.VISIBLE){
                    someText.setVisibility(View.INVISIBLE);
                } else {
                    someText.setVisibility(View.VISIBLE);
                }


            }
        });


        gameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), GameActivity.class);
                startActivity(i);


            }
        });


        yTJBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), YtjActivity.class);
                i.putExtra("Value1", searchSth.getText().toString());
                Log.e(TAG, "etsitään yrityksiä");
                startActivity(i);


            }
        });

        ExamBtnn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), ExamActivity.class);
                startActivity(i);


            }
        });

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}