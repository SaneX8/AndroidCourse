package com.example.androidkurssi.ui.notifications;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidkurssi.R;
import com.example.androidkurssi.databinding.FragmentNotificationsBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    public static final String TAG ="Timer: ";
    public int selectedNumber;
    CountDownTimer firstCountDownTimer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final NumberPicker numPicker = root.findViewById(R.id.nmbPickr);
        final TextView nmbTextPicker = root.findViewById(R.id.countTimerTxt);
        //final Button StartTheBtn = root.findViewById(R.id.StartTimer);
        final MaterialButtonToggleGroup materialButtonToggleGroup = root.findViewById(R.id.toggleButton);


        numPicker.setMinValue(0);
        numPicker.setMaxValue(60);

        Ringtone defaultRingtone = RingtoneManager.getRingtone(getActivity(),
                Settings.System.DEFAULT_RINGTONE_URI);

        Uri currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity()
                .getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        Ringtone currentRingtone = RingtoneManager.getRingtone(getActivity(), currentRintoneUri);



        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // Code here executes on main thread after user selects value

               selectedNumber = numberPicker.getValue();

                Log.e(TAG,String.valueOf(numberPicker.getValue()));

            }


        });




        materialButtonToggleGroup.addOnButtonCheckedListener(
                new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                    @Override
                    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                        if (isChecked) {
                            if (checkedId == R.id.StartTimer) {
                                Log.e(TAG,"Käynnistetään ajastin");

                                firstCountDownTimer =  new CountDownTimer (selectedNumber*1000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        Log.e(TAG,String.valueOf(millisUntilFinished));

                                        nmbTextPicker.setText("seconds remaining: " + millisUntilFinished / 1000);



                                    }

                                    public void onFinish() {

                                        nmbTextPicker.setText("done!");
                                        currentRingtone.play();

                                    }

                                }.start();

                            } else if (checkedId == R.id.PauseTimer) {
                                Log.e(TAG,"Pausetetaan ajastin");
                                currentRingtone.stop();
                                //FirstCountDownTimer.pause();
                            } else if (checkedId == R.id.StopTimer) {
                                Log.e(TAG,"Suljetaan ajastin");
                                selectedNumber = 0;
                                numPicker.setValue(0);
                                nmbTextPicker.setText(" ");
                                firstCountDownTimer.cancel();
                                currentRingtone.stop();
                            }
                        }
                    }
                });




       // final TextView textView = binding.textNotifications;
       // notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}