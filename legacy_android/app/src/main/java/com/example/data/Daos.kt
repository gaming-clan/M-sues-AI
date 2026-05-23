package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY timestamp DESC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Query("UPDATE questions SET isSaved = :isSaved WHERE id = :id")
    suspend fun updateSavedStatus(id: String, isSaved: Boolean)

    @Query("DELETE FROM questions WHERE id = :id")
    suspend fun deleteQuestion(id: String)
}

@Dao
interface ExamQuestionDao {
    @Query("SELECT * FROM exam_questions WHERE examType = :examType")
    fun getExamQuestions(examType: String): Flow<List<ExamQuestionEntity>>

    @Query("SELECT * FROM exam_questions WHERE examType = :examType AND (schoolType = :schoolType OR schoolType IS NULL)")
    fun getMaturaQuestions(examType: String, schoolType: String?): Flow<List<ExamQuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExamQuestions(questions: List<ExamQuestionEntity>)

    @Query("UPDATE exam_questions SET aiExplanation = :explanation WHERE id = :id")
    suspend fun updateExplanation(id: String, explanation: String)
}
