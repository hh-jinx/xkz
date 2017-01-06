package com.example.hh.snackbartest;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

/**
 * Created by hh on 16/12/23.
 */

public class PreferencesFG extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);


        final SwitchPreference switchPreference = (SwitchPreference) getPreferenceScreen().findPreference("sp");
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                return true;
            }
        });

        getPreferenceScreen().findPreference("pf").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                switchPreference.setChecked(false);
                return true;
            }
        });
    }
}
