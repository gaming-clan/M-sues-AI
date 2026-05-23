package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val EDUCATION_LEVEL = stringPreferencesKey("education_level")
        val CURRENT_GRADE = intPreferencesKey("current_grade")
        val SCHOOL_TYPE = stringPreferencesKey("school_type")
        val STUDENT_NAME = stringPreferencesKey("student_name")
        val DAILY_Q_COUNT = intPreferencesKey("daily_q_count")
        val LAST_RESET_DATE = stringPreferencesKey("last_reset_date")
        val CURRENT_STREAK = intPreferencesKey("current_streak")
        val LAST_ACTIVE_DATE = stringPreferencesKey("last_active_date")
        val IS_PREMIUM = booleanPreferencesKey("is_premium")
        val GEMINI_CALLS_TODAY = intPreferencesKey("gemini_calls_today")
        val GEMINI_PRO_CALLS_TODAY = intPreferencesKey("gemini_pro_calls_today")
        val VOCATIONAL_YEARS = intPreferencesKey("vocational_years")
    }

    val educationLevel: Flow<String?> = dataStore.data.map { it[EDUCATION_LEVEL] }
    val currentGrade: Flow<Int?> = dataStore.data.map { it[CURRENT_GRADE] }
    val schoolType: Flow<String?> = dataStore.data.map { it[SCHOOL_TYPE] }
    val studentName: Flow<String?> = dataStore.data.map { it[STUDENT_NAME] }
    val dailyQCount: Flow<Int> = dataStore.data.map { it[DAILY_Q_COUNT] ?: 0 }
    val lastResetDate: Flow<String?> = dataStore.data.map { it[LAST_RESET_DATE] }
    val currentStreak: Flow<Int> = dataStore.data.map { it[CURRENT_STREAK] ?: 0 }
    val lastActiveDate: Flow<String?> = dataStore.data.map { it[LAST_ACTIVE_DATE] }
    val isPremium: Flow<Boolean> = dataStore.data.map { it[IS_PREMIUM] ?: false }
    val geminiCallsToday: Flow<Int> = dataStore.data.map { it[GEMINI_CALLS_TODAY] ?: 0 }
    val geminiProCallsToday: Flow<Int> = dataStore.data.map { it[GEMINI_PRO_CALLS_TODAY] ?: 0 }
    val vocationalYears: Flow<Int> = dataStore.data.map { it[VOCATIONAL_YEARS] ?: 3 }

    suspend fun saveOnboardingData(level: String, grade: Int, type: String?, name: String, vocYears: Int?) {
        dataStore.edit { preferences ->
            preferences[EDUCATION_LEVEL] = level
            preferences[CURRENT_GRADE] = grade
            if (type != null) preferences[SCHOOL_TYPE] = type else preferences.remove(SCHOOL_TYPE)
            preferences[STUDENT_NAME] = name
            if (vocYears != null) preferences[VOCATIONAL_YEARS] = vocYears else preferences.remove(VOCATIONAL_YEARS)
        }
    }

    suspend fun updateCounters(
        dailyQ: Int, geminiCalls: Int, proCalls: Int,
        lastReset: String, streak: Int, lastActive: String
    ) {
        dataStore.edit { preferences ->
            preferences[DAILY_Q_COUNT] = dailyQ
            preferences[GEMINI_CALLS_TODAY] = geminiCalls
            preferences[GEMINI_PRO_CALLS_TODAY] = proCalls
            preferences[LAST_RESET_DATE] = lastReset
            preferences[CURRENT_STREAK] = streak
            preferences[LAST_ACTIVE_DATE] = lastActive
        }
    }
}
