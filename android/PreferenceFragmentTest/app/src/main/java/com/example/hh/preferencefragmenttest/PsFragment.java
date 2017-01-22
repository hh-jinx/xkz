package com.example.hh.preferencefragmenttest;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by hh on 16/12/19.
 */

public class PsFragment extends PreferenceFragment {

    private Preference pf_date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_ps);
        pf_date = findPreference("pf_date");
        pf_date.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean onPreferenceClick(Preference preference) {

                showDateChoose();
//                showDialogPick(pf_date);
                return false;
            }
        });
    }

    private void showDateChoose() {
        DatePickerFragment datePickerFragment = new DatePickerFragment(pf_date);
        datePickerFragment.show(getFragmentManager(), "");
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private String date;
        private Preference preference;

        public DatePickerFragment(Preference preference) {
            this.preference = preference;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            preference.setSummary(date);
        }
    }


    private void showDialogPick(final Preference preference) {
        final StringBuffer time = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                time.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                preference.setSummary(time);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

}
