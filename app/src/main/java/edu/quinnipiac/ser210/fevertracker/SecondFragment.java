package edu.quinnipiac.ser210.fevertracker;
/*
Authors: Tim Carta, Ryan Hayes, Neel Bains
Class Name: SecondFragment
Description: Fragment used to create a new entry to the database you can also access the Report Fragment
 */
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondFragment extends Fragment {

    private TextView currentFeeling;
    private double temperature;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentFeeling = getView().findViewById(R.id.txtCurrentFeelingLabel);
        temperature = 0.0;
        SeekBar feelingBar = getView().findViewById(R.id.seekBar);
        feelingBar.setProgress(0);
        updateSeekBarText(feelingBar, feelingBar.getProgress(), false);

        //progress bar to so you can select on a scale how you feel
        feelingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSeekBarText(seekBar, progress, fromUser);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //button that takes you to reports fragment
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                createReport();
                if (temperature >= 102) { //Dangerous temperature
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_warning);
                } else {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_reportsFragment);
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createReport() {
        String temp = ((EditText) getView().findViewById(R.id.txtTemp)).getText().toString();
        temperature = Double.parseDouble(temp); //Creates a value that can be compared to 102 degrees. If it is greater than the temp is dangerous.

        //Date picker that shows as a calendar that you may change
        DatePicker datePicker = getView().findViewById(R.id.date_picker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String date = sdf.format(calendar.getTime());

        //time picker shows up as a clock that you may change
        TimePicker timePicker = getView().findViewById(R.id.timePicker);
        int hourInt = timePicker.getHour();
        int minuteInt = timePicker.getMinute();
        String meridian = "";

        if (hourInt < 12) {
            meridian = "AM";
            if (hourInt == 0) {
                hourInt = 12;
            }
        } else if (hourInt > 12) {
            meridian = "PM";
            hourInt = hourInt - 12;
        } else if (hourInt == 12) {
            meridian = "PM";
        }

        String hour = "";
        if (hourInt < 10) {
            hour = "0" + hourInt;
        } else {
            hour = "" + hourInt;
        }

        String minute = "";
        if (minuteInt < 10) {
            minute = "0" + minuteInt;
        } else {
            minute = "" + minuteInt;
        }

        SeekBar feelingBar = getView().findViewById(R.id.seekBar);
        String feeling = String.valueOf(feelingBar.getProgress()+1);

        String time = hour + ":" + minute + " " + meridian;
        MainActivity.insert(temp, date, time, feeling);
    }


    //update method for the update bar.
    public void updateSeekBarText(SeekBar seekBar, int progress, boolean fromUser) {
        int value = (progress * (seekBar.getWidth() - 3 * seekBar.getThumbOffset()))
                / seekBar.getMax();
        progress = progress + 1;
        currentFeeling.setText("" + progress);
        currentFeeling.setX(seekBar.getX() + value + seekBar.getThumbOffset() / 2);
    }
}
