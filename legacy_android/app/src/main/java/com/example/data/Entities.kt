package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val educationLevel: String,
    val subjectId: String,
    val examMode: String?,
    val questionText: String,
    val answerText: String,
    val isPhotoQuestion: Boolean,
    val ocrExtractedText: String?,
    val modelUsed: String,
    val timestamp: Long,
    val isSaved: Boolean
)

@Entity(tableName = "exam_questions")
data class ExamQuestionEntity(
    @PrimaryKey val id: String,
    val examType: String, // "VANAF" | "PKAB" | "MATURA"
    val schoolType: String?,
    val subjectId: String,
    val year: Int,
    val questionText: String,
    val officialAnswer: String,
    val aiExplanation: String?
)
