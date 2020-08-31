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

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;

import java.io.File;
import java.io.IOException;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        SwitchPreferenceCompat newsFeedSwitch = findPreference("newsFeedSwitch");
        assert newsFeedSwitch != null;
        newsFeedSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                requireActivity().recreate();
                return true;
            }
        });
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
            case "displayLicenseButton":
                ((MainActivity) requireActivity()).displayLicenses();
                return true;
            case "about":
                ((MainActivity) requireActivity()).displayApacheLicense();
                return true;
            default:
                return false;
        }
    }

    public void deleteUserData() {
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setUserProfile(null);

        File file = new File(mainActivity.getFilesDir(), "userProfile.ser");
        if (file.delete()) {
            Toast.makeText(getActivity(), "User data deleted", Toast.LENGTH_SHORT).show();
        } else {
            try {
                throw new IOException("Data not deleted.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetUserSettings() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(requireContext());
        sp.edit().clear().apply();
        PreferenceManager.setDefaultValues(requireContext(), R.xml.root_preferences, true);

        SwitchPreferenceCompat news = findPreference("newsFeedSwitch");
        boolean state = sp.getBoolean("newsFeedSwitch", false);
        assert news != null;
        news.setChecked(state);

        Toast.makeText(getActivity(), "Settings reset", Toast.LENGTH_SHORT).show();
    }
}
