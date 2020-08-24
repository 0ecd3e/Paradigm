package com.paradigm.paradigm.ui.settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import com.paradigm.paradigm.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    DialogInterface.OnClickListener settingsResetListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                resetUserSettings();
            }
        }
    };
    DialogInterface.OnClickListener dataDeleteListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                deleteUserData();
            }
        }
    };

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();

        switch (key) {
            case "resetSettings":
                AlertDialog.Builder resetSettingsBuilder = new AlertDialog.Builder(requireContext());
                resetSettingsBuilder.setMessage("Reset your settings?")
                        .setPositiveButton("Yes", settingsResetListener)
                        .setNegativeButton("No", settingsResetListener)
                        .show();
                return true;
            case "eraseUserData":
                AlertDialog.Builder deleteDataBuilder = new AlertDialog.Builder(requireContext());
                deleteDataBuilder.setMessage("Delete your progress?")
                        .setPositiveButton("Yes", dataDeleteListener)
                        .setNegativeButton("No", dataDeleteListener)
                        .show();
                return true;
            default:
                return false;
        }
    }

    public void deleteUserData() {
        Toast.makeText(getActivity(), "User data deleted", Toast.LENGTH_SHORT).show();
    }

    public void resetUserSettings() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(requireContext());
        sp.edit().clear().apply();
        PreferenceManager.setDefaultValues(requireContext(), R.xml.root_preferences, true);

        SwitchPreferenceCompat news = (SwitchPreferenceCompat) findPreference("newsFeedSwitch");
        boolean state = sp.getBoolean("newsFeedSwitch", false);
        assert news != null;
        news.setChecked(state);

        SwitchPreferenceCompat dark = (SwitchPreferenceCompat) findPreference("darkModeSwitch");
        boolean state2 = sp.getBoolean("darkModeSwitch", false);
        assert dark != null;
        dark.setChecked(state2);

        Toast.makeText(getActivity(), "Settings reset", Toast.LENGTH_SHORT).show();
    }
}
