package com.example.domain

enum class EducationLevel(val albanianName: String, val gradeRange: IntRange) {
    PRIMARY("Arsimi Fillor", 1..5),
    LOWER_SECONDARY("Arsimi i Mesëm i Ulët (9-vjeçar)", 6..9),
    UPPER_SECONDARY_GENERAL("Gjimnaz", 10..12),
    UPPER_SECONDARY_ARTISTIC("Shkollë Artistike", 10..12),
    UPPER_SECONDARY_SPORTS("Shkollë Sportive", 10..12),
    UPPER_SECONDARY_LANGUAGE("Shkollë me Drejtim Gjuhësor", 10..12),
    UPPER_SECONDARY_VOCATIONAL("Shkollë Profesionale", 10..13),
    UPPER_SECONDARY_MADRASAH("Medrese", 10..12)
}
