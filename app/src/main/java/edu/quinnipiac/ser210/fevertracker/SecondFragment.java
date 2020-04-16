package edu.quinnipiac.ser210.fevertracker;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondFragment extends Fragment {

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

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                createReport();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_reportsFragment);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createReport() {
        String temp = ((EditText) getView().findViewById(R.id.txtTemp)).getText().toString();

        DatePicker datePicker = getView().findViewById(R.id.date_picker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String date = sdf.format(calendar.getTime());

        TimePicker timePicker = getView().findViewById(R.id.timePicker);
        int hourInt = timePicker.getHour();
        int minuteInt = timePicker.getMinute();
        String meridian = "";

        if (hourInt < 12) {
            meridian = "AM";
            if (hourInt == 0) {
                hourInt = 12;
            }
        } else if (hourInt >= 12) {
            meridian = "PM";
            hourInt = hourInt - 12;
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

        String time = hour + ":" + minute + " " + meridian;
        MainActivity.insert(temp, date, time);
    }
}
