package com.example.domain

data class Subject(
    val id: String,
    val albName: String,
    val icon: String,
    val colorHex: String,
    val matura: Boolean = false,
    val isVocationalCore: Boolean = false
)

object SubjectRegistry {
    // Primary
    val primarySubjects = listOf(
        Subject("alb_primary", "Gjuhë Shqipe", "📝", "#880E4F"),
        Subject("math_primary", "Matematikë", "➕", "#1565C0"),
        Subject("science_primary", "Dituri Natyre", "🌿", "#00695C"),
        Subject("art_primary", "Arte", "🎨", "#E65100"),
        Subject("civic_primary", "Edukimi Qytetar", "🏛️", "#4A148C")
    )

    // Lower Secondary
    val lowerSecondarySubjects = listOf(
        Subject("albanian", "Gjuhë Shqipe dhe Letërsi", "📖", "#880E4F"),
        Subject("math", "Matematikë", "➕", "#1565C0"),
        Subject("english", "Gjuhë e Huaj (Anglisht)", "🌐", "#006064"),
        Subject("physics", "Fizikë", "⚡", "#6A1B9A"),
        Subject("chemistry", "Kimi", "🧪", "#2E7D32"),
        Subject("biology", "Biologji", "🌿", "#00695C"),
        Subject("history", "Histori", "📜", "#BF360C"),
        Subject("geography", "Gjeografi", "🗺️", "#827717"),
        Subject("civic", "Qytetari", "🏛️", "#4A148C"),
        Subject("ict", "TIK", "💻", "#0277BD")
    )

    // General Matura
    val generalUpperSubjects = listOf(
        Subject("alb_lit", "Gjuhë Shqipe dhe Letërsi", "📖", "#880E4F", true),
        Subject("math_gym", "Matematikë", "➕", "#1565C0", true),
        Subject("foreign_lang", "Gjuhë e Huaj", "🌐", "#006064", true),
        Subject("physics_gym", "Fizikë", "⚡", "#6A1B9A", true),
        Subject("chem_gym", "Kimi", "🧪", "#2E7D32", true),
        Subject("bio_gym", "Biologji", "🌿", "#00695C", true),
        Subject("hist_gym", "Histori", "📜", "#BF360C", true),
        Subject("geo_gym", "Gjeografi", "🗺️", "#827717", true),
        Subject("socio_phil", "Sociologji-Filozofi", "🤔", "#4A148C", true),
        Subject("econ_gym", "Ekonomi", "💰", "#1B5E20", true),
        Subject("civic_psy", "Qytetari-Psikologji", "🧠", "#311B92", true)
    )

    fun getSubjectsForLevel(level: EducationLevel): List<Subject> {
        return when (level) {
            EducationLevel.PRIMARY -> primarySubjects
            EducationLevel.LOWER_SECONDARY -> lowerSecondarySubjects
            EducationLevel.UPPER_SECONDARY_GENERAL -> generalUpperSubjects
            EducationLevel.UPPER_SECONDARY_LANGUAGE -> generalUpperSubjects
            EducationLevel.UPPER_SECONDARY_MADRASAH -> generalUpperSubjects
            EducationLevel.UPPER_SECONDARY_ARTISTIC -> generalUpperSubjects + listOf(
                Subject("art_history", "Histori Arti", "🎨", "#E65100", true),
                Subject("ballet_history", "Histori Baleti", "🩰", "#AD1457", true),
                Subject("music_history", "Histori Muzike", "🎵", "#6A1B9A", true),
                Subject("math_artistic", "Matematikë (Artistike)", "➕", "#1565C0", true)
            )
            EducationLevel.UPPER_SECONDARY_SPORTS -> generalUpperSubjects + listOf(
                Subject("math_sports", "Matematikë (Sportive)", "➕", "#1565C0", true)
            )
            EducationLevel.UPPER_SECONDARY_VOCATIONAL -> listOf(
                Subject("alb_lit_prof", "Gjuhë Shqipe dhe Letërsi (Prof)", "📖", "#880E4F", true, true),
                Subject("math_prof", "Matematikë (Prof)", "➕", "#1565C0", true, true),
                Subject("foreign_lang", "Gjuhë e Huaj", "🌐", "#006064", true, true),
                Subject("vocational_spec", "Lënda Profesionale", "🛠️", "#BF360C", true, true)
            )
        }
    }
}
