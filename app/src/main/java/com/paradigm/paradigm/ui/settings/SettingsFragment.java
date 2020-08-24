package com.paradigm.paradigm.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

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
                return true;

            case "eraseUserData":
                Toast.makeText(getActivity(), "User data deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
