package com.example

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.AppDatabase
import com.example.data.UserPreferences
import com.example.repository.AppRepository

import com.example.data.dataStore

class MesuesApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val userPreferences by lazy { UserPreferences(dataStore) }
    val repository by lazy { AppRepository(database.questionDao(), database.examQuestionDao(), userPreferences) }
}
