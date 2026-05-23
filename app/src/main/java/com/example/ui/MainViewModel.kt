package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.Subject
import com.example.domain.SubjectRegistry
import com.example.domain.EducationLevel
import com.example.repository.AppRepository
import com.example.data.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: AppRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    val levelFlow = userPreferences.educationLevel.stateIn(viewModelScope, SharingStarted.Lazily, null)
    val gradeFlow = userPreferences.currentGrade.stateIn(viewModelScope, SharingStarted.Lazily, null)
    val schoolTypeFlow = userPreferences.schoolType.stateIn(viewModelScope, SharingStarted.Lazily, null)
    val nameFlow = userPreferences.studentName.stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _currentSubject = MutableStateFlow<Subject?>(null)
    val currentSubject: StateFlow<Subject?> = _currentSubject

    fun saveOnboarding(level: String, grade: Int, type: String?, name: String, vocYears: Int?) {
        viewModelScope.launch {
            userPreferences.saveOnboardingData(level, grade, type, name, vocYears)
        }
    }

    fun getSubjectsForCurrentLevel(): List<Subject> {
        val lvlStr = levelFlow.value ?: return emptyList()
        val level = EducationLevel.entries.find { it.name == lvlStr } ?: EducationLevel.PRIMARY
        return SubjectRegistry.getSubjectsForLevel(level)
    }

    fun selectSubject(subject: Subject) {
        _currentSubject.value = subject
    }

    // Example AI ask
    private val _chatResponse = MutableStateFlow<String?>(null)
    val chatResponse: StateFlow<String?> = _chatResponse

    fun askQuestion(prompt: String, ocrText: String? = null) {
        viewModelScope.launch {
            val subj = _currentSubject.value?.albName ?: "Përgjithshme"
            val response = repository.askTutor(prompt, subj, base64Image = ocrText)
            _chatResponse.value = response
        }
    }
}

class MainViewModelFactory(
    private val repository: AppRepository,
    private val userPreferences: UserPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
