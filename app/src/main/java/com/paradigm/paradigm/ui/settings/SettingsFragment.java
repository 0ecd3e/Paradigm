package com.paradigm.paradigm.ui.settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.paradigm.paradigm.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements PreferenceManager.OnPreferenceTreeClickListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();

        switch (key) {
            case "resetSettings":
                Toast.makeText(getActivity(), "Settings Reset", Toast.LENGTH_LONG).show();
                return true;

            case "eraseUserData":
                Toast.makeText(getActivity(), "User Data Deleted", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}
