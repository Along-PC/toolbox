package com.dragon.toolbox.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dragon.toolbox.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}