package com.mohamed.halim.essa.weather.preference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.*
import com.mohamed.halim.essa.weather.R


class PreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val preferenceScreen: PreferenceScreen = preferenceScreen
        val sharedPreferences: SharedPreferences = preferenceScreen.sharedPreferences
        val count: Int = preferenceScreen.preferenceCount
        for (i in 0 until count) {
            val p: Preference = preferenceScreen.getPreference(i)
            if (p !is CheckBoxPreference) {
                val value = sharedPreferences.getString(p.key, "")
                setPreferenceSummary(p, value)
            }
        }
    }

    private fun setPreferenceSummary(preference: Preference?, value: String?) {
        if (preference is EditTextPreference) {
            preference.summary = value
        } else if (preference is ListPreference) {
            val i = preference.findIndexOfValue(value)
            if (i > -1)
                preference.summary = preference.entries[i]
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key != null) {
            val preference: Preference? = findPreference(key)
            val value = sharedPreferences?.getString(key, "")
            setPreferenceSummary(preference, value)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }


}