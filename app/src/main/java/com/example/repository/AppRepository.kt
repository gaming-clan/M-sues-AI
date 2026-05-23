package com.example.repository

import com.example.BuildConfig
import com.example.api.Content
import com.example.api.GenerateContentRequest
import com.example.api.Part
import com.example.api.RetrofitClient
import com.example.data.ExamQuestionDao
import com.example.data.QuestionDao
import com.example.data.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class AppRepository(
    private val questionDao: QuestionDao,
    private val examQuestionDao: ExamQuestionDao,
    private val userPreferences: UserPreferences
) {
    suspend fun askTutor(
        prompt: String,
        subjectName: String,
        isPro: Boolean = false,
        base64Image: String? = null
    ): String = withContext(Dispatchers.IO) {
        val levelName = userPreferences.educationLevel.first() ?: "N/A"
        val grade = userPreferences.currentGrade.first() ?: 0
        val schoolType = userPreferences.schoolType.first() ?: "N/A"

        val systemPromptStr = if (isPro) {
            "Jeni specialist i provimeve. Lloji i shkollës: $schoolType. Niveli: $levelName (Klasa $grade). Lënda: $subjectName. Shpjegoni detajet e plota."
        } else {
            "Jeni një mësues i shkëlqyer për nxënësit shqiptarë. Adaptoni gjuhën për moshën. Niveli: $levelName (Klasa $grade). Lënda: $subjectName. Shpjegoni hap pas hapi shqip. Nëse ka foto, lexoni tekstin e dhënë."
        }

        val parts = mutableListOf<Part>()
        if (base64Image != null && base64Image.isNotEmpty()) {
            parts.add(Part(text = "Nxënësi ka fotografuar këtë (teksti me OCR): \n$base64Image\n\nPyetja: $prompt"))
        } else {
            parts.add(Part(text = prompt))
        }

        val request = GenerateContentRequest(
            systemInstruction = Content(parts = listOf(Part(text = systemPromptStr))),
            contents = listOf(Content(parts = parts))
        )

        try {
            val response = if (isPro) {
                RetrofitClient.geminiService.generateProContent(BuildConfig.GEMINI_API_KEY, request)
            } else {
                RetrofitClient.geminiService.generateFlashContent(BuildConfig.GEMINI_API_KEY, request)
            }
            response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "Nuk u mor asnjë përgjigje."
        } catch (e: Exception) {
            "Gabim komunikimi: ${e.message}"
        }
    }
}
