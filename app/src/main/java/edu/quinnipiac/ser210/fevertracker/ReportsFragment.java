package edu.quinnipiac.ser210.fevertracker;
/*
Authors: Tim Carta, Ryan Hayes, Neel Bains
Class Name: ReportsFragment
Description: This class is the Fragment that holds the database and the ablitiy to view your data.
also has the funcionality to move between the pages.
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ReportsFragment extends Fragment {

    private ListView listReports;
    private TextView temp, date, time, feeling;

    public ReportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        listReports = view.findViewById(R.id.listReports);

        //variables used to shorten the text view
        temp = view.findViewById(R.id.txtTemp);
        date = view.findViewById(R.id.txtDate);
        time = view.findViewById(R.id.txtTime);
        feeling = view.findViewById(R.id.txtFeeling);

        //button that brings you to the new entry fragment
        view.findViewById(R.id.new_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ReportsFragment.this)
                        .navigate(R.id.action_reportsFragment_to_secondFragment);
            }
        });

        //button that brings you back to the title fragment
        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ReportsFragment.this)
                        .navigate(R.id.action_reportsFragment_to_firstFragment);
            }
        });

        loadListData();
        // Inflate the layout for this fragment
        return view;
    }


    private void loadListData() {
        final DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> reports = db.getAllReports();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, reports);
        listReports.setAdapter(dataAdapter);
        //updates which item in the list view you click
        listReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = Integer.toString(position+1);
                List<String> info = MainActivity.displayInfo(value);

                //sets variables based on the selected data entry
                String tempInfo = info.get(0);
                String dateInfo = info.get(1);
                String timeInfo = info.get(2);
                String feelingInfo = info.get(3);

                //sets the text of the data
                temp.setText("Temperature: " + tempInfo);
                date.setText("Date: " + dateInfo);
                time.setText("Time: " + timeInfo);
                feeling.setText("Feeling: " + feelingInfo);

                db.setSelected(tempInfo, dateInfo, timeInfo, feelingInfo);
            }
        });
    }
}
