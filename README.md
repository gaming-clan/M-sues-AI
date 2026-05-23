# 🎓 Mësues AI — Extended MVP Master Document (v2)
> **Gaming Repository** | Project Lead: Dario | Status: Pre-build
> Single Source of Truth (SSOT) for all project decisions, specs, and history.
> *v2 — Expanded to cover the full Albanian pre-university education system (Grades 1–13)*

---

# TABLE OF CONTENTS

1. [Albanian Education System — Research Overview](#1-albanian-education-system--research-overview)
2. [Project Specs](#2-project-specs)
3. [SSOT — Single Source of Truth](#3-ssot--single-source-of-truth)
4. [System Sequence Diagrams (SSD)](#4-system-sequence-diagrams-ssd)
5. [App Theme Specs](#5-app-theme-specs)
6. [Builder Instructions — Google AI Studio Android Builder](#6-builder-instructions--google-ai-studio-android-builder)
7. [Sprint Tracker](#7-sprint-tracker)
8. [Bug Registry](#8-bug-registry)
9. [Changelog](#9-changelog)

---

# 1. ALBANIAN EDUCATION SYSTEM — RESEARCH OVERVIEW

> This section is the canonical reference for understanding the Albanian pre-university system.
> Every design decision in Mësues AI must be consistent with this structure.

## 1.1 System Map (Grades 1–13+)

```
AGE    GRADE   CYCLE                          SCHOOL TYPE
─────────────────────────────────────────────────────────────────────────────
 6      1  ┐
 7      2  │
 8      3  │  ARSIMI FILLOR                   • Shkolla 9-vjeçare (publike/private)
 9      4  │  (Primary, ISCED 1)              • Shkolla private me orientim
10      5  ┘  ← VANAF (Grade 5 national exam)
             │
11      6  ┐
12      7  │  ARSIMI I MESËM I ULËT           • Shkolla 9-vjeçare (publike/private)
13      8  │  (Lower Secondary, ISCED 2)
14      9  ┘  ← PKAB (Grade 9 national exam)  [Gateway to upper secondary]
             │
             ├──────────────────────────────────────────────────────────────
             │                 ARSIMI I MESËM I LARTË (Upper Secondary, ISCED 3)
             │                 [Optional, age 16+; most students continue]
             │
15     10  ┐ ├── GJIMNAZ (General)            3 years (10–12)
16     11  │ │   • Profil Natyror
17     12  ┘ │   • Profil Shoqëror            ← Matura Shtetërore (Grade 12)
             │
15     10  ┐ ├── ARSIMI I ORIENTUAR           3 years (10–12)
16     11  │ │   (Oriented Education)         ← Matura Shtetërore (Grade 12)
17     12  ┘ │
             │   Sub-types:
             │   • Shkolla Artistike          (music, ballet, fine arts, design)
             │   • Shkolla Sportive           (sports & physical education)
             │   • Shkolla me Drejtim Gjuhësor (foreign language-oriented)
             │   • Medresetë (Islamic)         (secular MoE + Islamic KMSH curriculum)
             │
15     10  ┐ └── ARSIMI PROFESIONAL           2, 3, or 4 years depending on track
16     11  │     (Vocational)                 ← Matura Shtetërore Profesionale
17     12  │
18     13  ┘     Structures:
                  • 2 vjet  (ISCED 3, Level 2 NQF) — Certificate, no Matura required
                  • 2+1 vjet (ISCED 3, Level 3 NQF) — Diploma
                  • 2+1+1 vjet (ISCED 4, Level 4 NQF) — up to Grade 13, Matura Profesionale
                  • 2+2 vjet (ISCED 4, Level 4 NQF)  — up to Grade 13, Matura Profesionale
                  • Bllok 4 vjet (ISCED 4, Level 4 NQF) — up to Grade 13, Matura Profesionale
```

## 1.2 National Examinations Registry

| Exam | Full Name | Grade | Subjects Tested | Administered by |
|------|-----------|-------|-----------------|-----------------|
| **VANAF** | Vlerësimi i Arritjeve të Nxënësve të Arsimit Fillor | **5** | Gjuhë Shqipe, Matematikë, Dituri Natyre | QSHA / ASCAP |
| **PKAB** | Provimi Kombëtar i Arsimit Bazë | **9** | Gjuhë Shqipe, Matematikë, Gjuhë e Huaj (A2) | QSHA |
| **Matura Shtetërore** | State Graduation (General tracks) | **12** | 4 exams (see 1.3) | AKP / QSHA |
| **Matura Shtetërore Profesionale** | State Graduation (Vocational) | **12–13** | 4 exams + vocational practical | AKP / QSHA |

## 1.3 Matura Shtetërore — Exam Structure by School Type

### A) Gjimnaz (General High School)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar (Compulsory) | Gjuhë e Huaj (English/French/Italian/German — B1/B2) |
| 2 | Detyruar (Compulsory) | Gjuhë Shqipe dhe Letërsi |
| 3 | Detyruar (Compulsory) | Matematikë |
| 4 | Me Zgjedhje (Elective) | One of: Fizikë, Kimi, Biologji, Histori, Gjeografi, Sociologji-Filozofi, Ekonomi, Qytetari-Psikologji |

### B) Shkollat e Orientuara Artistike (Art Schools)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar | Gjuhë e Huaj |
| 2 | Detyruar | Gjuhë Shqipe dhe Letërsi |
| 3 | Detyruar | Matematikë (Artistike syllabus — lighter than general) |
| 4 | Me Zgjedhje | Histori Arti **or** Histori Baleti **or** Histori Muzike |

### C) Shkollat Sportive (Sports Schools)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar | Gjuhë e Huaj |
| 2 | Detyruar | Gjuhë Shqipe dhe Letërsi |
| 3 | Detyruar | Matematikë (Sportive syllabus) |
| 4 | Me Zgjedhje | Same general elective pool |

### D) Shkollat me Drejtim Gjuhësor (Language-Oriented Schools)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar | Gjuhë e Huaj (typically at B2/C1 level) |
| 2 | Detyruar | Gjuhë Shqipe dhe Letërsi |
| 3 | Detyruar | Matematikë |
| 4 | Me Zgjedhje | General elective pool |

### E) Medresetë (Islamic Religious Schools)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar | Gjuhë e Huaj |
| 2 | Detyruar | Gjuhë Shqipe dhe Letërsi |
| 3 | Detyruar | Matematikë |
| 4 | Me Zgjedhje | From general pool; Islamic Studies not a Matura subject (KMSH separate) |

> Medresetë are accredited private schools under MoE for secular subjects. Students sit the same
> Matura as general students. Islamic Studies curriculum is approved separately by KMSH (Komuniteti
> Mysliman i Shqipërisë) and is not part of the state Matura.

### F) Arsimi Profesional (Vocational — Matura Shtetërore Profesionale)
| Exam # | Type | Subject |
|--------|------|---------|
| 1 | Detyruar | Gjuhë e Huaj |
| 2 | Detyruar | Gjuhë Shqipe dhe Letërsi (Professional syllabus) |
| 3 | Detyruar | Matematikë (Professional syllabus) |
| 4 | Me Zgjedhje / Profesional | Vocational elective OR trade-specific practical |

> Vocational students follow a lighter "Kultura e Përgjithshme" (General Culture) curriculum for
> Gjuhë-Letërsi and Matematikë. Their Matura exam papers (teza) are separate from the Gjimnaz ones.

## 1.4 VKM Formula — Mesatarja për Pranim në Universitet

### What is VKM?
VKM (Vendim i Këshillit të Ministrave) refers to the Council of Ministers' Decision that defines
the minimum average grade and the formula for university admission. The same formula applies to
**all upper secondary tracks** (Gjimnaz, Oriented, Vocational) with minor structural variations.

### Formula (All Tracks)

```
M_VKM = (M_shkollë + M_maturë) / 2

Where:
  M_shkollë  = Simple arithmetic average of ALL annual subject grades
               across ALL years of upper secondary schooling
               (3 years for Gjimnaz/Oriented; 2, 3, or 4 for Vocational)

  M_maturë   = Simple arithmetic average of ALL 4 Matura exam grades
               (Detyruar 1 + Detyruar 2 + Detyruar 3 + Me Zgjedhje) / 4

  Scale: 4–10 (minimum passing = 5; best grade = 10)
  Minimum M_VKM for university admission (Bachelor/Integrated Master): 6.0
```

### VKM Calculation Examples

```
EXAMPLE A — Gjimnaz student (3 years):
  Year 10 average: 8.2
  Year 11 average: 8.5
  Year 12 average: 8.8
  → M_shkollë = (8.2 + 8.5 + 8.8) / 3 = 8.50

  Gjuhë e Huaj exam: 8.0
  Gjuhë-Letërsi exam: 9.0
  Matematikë exam:   7.5
  Me Zgjedhje:       8.5
  → M_maturë = (8.0 + 9.0 + 7.5 + 8.5) / 4 = 8.25

  M_VKM = (8.50 + 8.25) / 2 = 8.375

EXAMPLE B — Vocational student (4 years, Matura Profesionale):
  Years 10–13 averages: 7.0, 7.5, 7.8, 8.0
  → M_shkollë = (7.0 + 7.5 + 7.8 + 8.0) / 4 = 7.575

  4 Matura exams: 7.0, 8.0, 6.5, 7.5
  → M_maturë = (7.0 + 8.0 + 6.5 + 7.5) / 4 = 7.25

  M_VKM = (7.575 + 7.25) / 2 = 7.41
```

> **App feature:** The VKM Calculator in the Matura screen lets students enter their grades
> and instantly see their M_VKM. It auto-adjusts input fields based on school type (3-year vs 4-year).

## 1.5 PKAB — Structure & Scope

| Field | Detail |
|-------|--------|
| **Who** | All 9th grade students completing compulsory education |
| **Purpose** | Certify completion of 9-year mandatory schooling |
| **Format** | Written exams only |
| **Subjects** | 3 exams: Gjuhë Shqipe (50 pts), Matematikë (50 pts), Gjuhë e Huaj A2 (English / French / Italian / German) |
| **Gjuhë Shqipe structure** | Part I: Reading (literary + non-literary), Part II: Grammar + Writing (2h 30min total) |
| **Matematikë structure** | Mixed: multiple choice + open problems covering Grades 6–9 curriculum |
| **Gjuhë e Huaj** | Level A2; language must match student's compulsory foreign language track |
| **Administered by** | QSHA (Qendra e Shërbimeve Arsimore) |
| **Result used for** | Certification + school selection for upper secondary |

## 1.6 VANAF — Structure & Scope

| Field | Detail |
|-------|--------|
| **Who** | All 5th grade students completing the primary cycle |
| **Purpose** | Assess achievement at end of primary education; diagnostic (does not affect grade promotion) |
| **Format** | Written tests |
| **Subjects** | 3 tests: Gjuhë Shqipe, Matematikë, Dituri Natyre |
| **Point weighting** | Gjuhë Shqipe and Matematikë have higher weight; Dituri Natyre = 10 pts |
| **Gjuhë Shqipe** | Reading comprehension (literary + non-literary) + writing tasks |
| **Matematikë** | Arithmetic, geometry, measurement, basic algebra — Grades 1–5 curriculum |
| **Dituri Natyre** | Applied science knowledge and reasoning from Grades 1–5 |
| **Administered by** | ASCAP / QSHA |
| **Result used for** | National diagnostic; informs school improvement; no direct impact on student promotion |

## 1.7 Grading Scale (All Levels)

| Grade | Label | Equivalent |
|-------|-------|-----------|
| 10 | Shkëlqyer (Excellent) | A+ |
| 9 | Shumë mirë (Very Good) | A |
| 8 | Mirë (Good) | B |
| 7 | Kënaqshëm (Satisfactory) | C |
| 6 | Mjaftueshëm (Sufficient) | D |
| 5 | Kaluese minimale (Minimum pass) | E |
| 4 | Jo kaluese (Failing) | F |

---

# 2. PROJECT SPECS

## 2.1 Overview

| Field | Value |
|-------|-------|
| **App Name** | Mësues AI |
| **Tagline** | Mësuesi yt AI — gjithmonë i disponueshëm |
| **Platform** | Native Android (min SDK 26 / Android 8.0+) |
| **Builder** | Google AI Studio Native Android Builder |
| **Language** | Albanian (Shqip) — primary UI language |
| **Owner** | Dario / Gaming Repository |
| **Status** | Pre-build — Sprint 0 |
| **Target Launch** | End of Sprint 3 (MVP) |

## 2.2 Problem Statement

Private tutors in Albania cost **3,000–5,000 ALL per hour**. Students in smaller cities like
Fier, Berat, Gjirokastër, and Korçë have limited access to quality tutoring. There is currently
**zero** Albanian-language AI tutoring app covering the full pre-university spectrum. Albanian
students from Grade 1 through Grade 13 — whether in a general high school, a sports school,
a madrasah, or a vocational program — have no affordable on-demand study tool that speaks their
language and knows their exact exam format.

## 2.3 Solution

An AI-powered mobile tutor that:
- Answers academic questions in Albanian, step by step
- Covers **all pre-university levels** (Grades 1–13) and **all school tracks**
- Reads homework photos via on-device OCR (ML Kit) — no cloud OCR cost
- Provides preparation for **VANAF** (Grade 5), **PKAB** (Grade 9), and **Matura** (Grade 12–13)
- Calculates **VKM** for both general and vocational Matura students
- Costs 10x less than a single private tutor session per month

## 2.4 Target Users

| Segment | Description | Primary Exam |
|---------|-------------|--------------|
| **Primary** | Grades 1–5, ages 6–10 | VANAF (Grade 5) |
| **Lower Secondary** | Grades 6–9, ages 11–15 | PKAB (Grade 9) |
| **Gjimnaz** | Grades 10–12, ages 15–18 | Matura Shtetërore |
| **Oriented schools** | Grades 10–12, ages 15–18 | Matura (artistic/sport/language syllabus) |
| **Vocational** | Grades 10–13, ages 15–19 | Matura Shtetërore Profesionale |
| **Madrasah students** | Grades 10–12, ages 15–18 | Matura Shtetërore (same as general) |
| **University prep** | Ages 18–22 | VKM optimization |

**Geography:** All Albanian cities; focus on non-Tiranë cities (Fier, Berat, Elbasan, Shkodër,
Vlorë, Korçë, Gjirokastër) where private tutors are scarcer and more expensive.
**Device profile:** Mid-range Android (Samsung A-series, Xiaomi Redmi) — 4GB RAM, Android 10+.

## 2.5 Core MVP Features (v1.0 Scope)

| # | Feature | Priority | Notes |
|---|---------|----------|-------|
| F01 | Level selector (Primary / Lower Secondary / Upper Secondary) | 🔴 Must | Determines which subjects and exams are shown |
| F02 | School type selector (Gjimnaz / Profesional / Artistike / Sportive / Medrese / Gjuhësor) | 🔴 Must | Visible only after "Upper Secondary" level chosen |
| F03 | Subject Q&A chat | 🔴 Must | Core tutoring flow |
| F04 | Homework photo → OCR → AI answer | 🔴 Must | ML Kit on-device |
| F05 | Daily question limit (10/day free) | 🔴 Must | SharedPreferences counter |
| F06 | Exam Prep Mode: VANAF (Gr 5) | 🔴 Must | 3 subjects: Gjuhë Shqipe, Matematikë, Dituri Natyre |
| F07 | Exam Prep Mode: PKAB (Gr 9) | 🔴 Must | 3 subjects: Gjuhë Shqipe, Matematikë, Gjuhë e Huaj |
| F08 | Exam Prep Mode: Matura (Gr 12–13) | 🔴 Must | Track-aware (general/professional/oriented) |
| F09 | VKM Calculator (Matura screen) | 🔴 Must | General (3yr) and Vocational (2/3/4yr) formulas |
| F10 | Saved Q&A (Fletore) | 🟡 Should | Room DB |
| F11 | Daily streak tracker | 🟡 Should | Gamification |
| F12 | Subject performance stats | 🟢 Could | Profile screen |
| F13 | Dark mode | 🟢 Could | System-follows |
| F14 | Offline mode (cached answers) | ❌ Won't (v1) | Post-MVP |

## 2.6 Out of Scope (v1)

- Voice input / text-to-speech answers
- Teacher/parent accounts
- Push notifications / study reminders
- Social features (share answers)
- iOS version; Web version
- Real-time multiplayer quiz
- Kosovo/North Macedonia curriculum variants (post-MVP)

## 2.7 Success Metrics (MVP)

| Metric | Target at 30 days post-launch |
|--------|-------------------------------|
| Downloads | 500+ |
| DAU / MAU ratio | > 30% |
| Free → Premium conversion | > 5% |
| Avg questions asked/day (active user) | > 5 |
| 1-star reviews about AI quality | < 10% |
| Crash-free sessions | > 98% |

## 2.8 Monetization

| Tier | Price | Limits |
|------|-------|--------|
| Free | 0 ALL | 10 questions/day (text + photo combined) |
| Premium Monthly | 500 ALL/month | Unlimited questions, priority model |
| Premium Yearly | 4,000 ALL/year | Same as monthly (33% discount) |
| School License | 15,000 ALL/year | Up to 30 students, admin dashboard (post-MVP) |

Payment: Cash-first (Albanian SMB reality) → future: Paysera / card

---

# 3. SSOT — SINGLE SOURCE OF TRUTH

> Authoritative values for every decision in the project.
> If a value appears anywhere else in code, docs, or prompts — it must match this section.

## 3.1 AI Model Assignments

| Feature | Model | Provider | API Endpoint | Quota |
|---------|-------|----------|-------------|-------|
| Text Q&A (standard) | `gemini-2.5-flash` | Google AI Studio | `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent` | 250 RPD / 10 RPM |
| Text Q&A (fallback when Flash RPD hit) | `llama-4-maverick` | Groq | `https://api.groq.com/openai/v1/chat/completions` | 500 RPD / 30 RPM |
| Matura / PKAB complex reasoning | `gemini-2.5-pro` | Google AI Studio | same base, model=gemini-2.5-pro | 50–100 RPD / 5 RPM |
| Photo OCR | ML Kit Text Recognition v2 | On-device | No network call | Unlimited |

## 3.2 System Prompts (Canonical)

**Gemini Flash — Standard Tutor (level-aware):**
```
Jeni një mësues i shkëlqyer dhe i durueshëm për nxënësit shqiptarë.
Gjithmonë shpjegoni zgjidhjet hap pas hapi në gjuhën shqipe.
Jini inkurajues dhe të qartë. Mos jepni vetëm përgjigjen — shpjegoni procesin.
Niveli: [LEVEL] (p.sh. "Arsimi Fillor — Klasa [N]" ose "Gjimnaz" ose "Shkollë Profesionale").
Lënda aktuale: [SUBJECT].
Nëse pyetja nuk lidhet me lëndën [SUBJECT], thoni me mirësjellje që mund të ndihmoni
vetëm me [SUBJECT] për nivelin [LEVEL].
Adaptoni gjuhën dhe shpjegimet për moshën dhe nivelin e nxënësit.
```

**Groq Llama Fallback — Standard Tutor:**
```
You are a patient Albanian tutor. Always respond in Albanian (Shqip).
Explain solutions step by step. Be encouraging.
Level: [LEVEL]. Current subject: [SUBJECT].
Adjust your language complexity for the student's age and school level.
```

**Gemini Pro — Matura Mode (track-aware):**
```
Jeni specialist i provimeve të Maturës Shtetërore Shqiptare.
Lloji i shkollës: [SCHOOL_TYPE] (p.sh. Gjimnaz / Shkollë Profesionale / Shkollë Artistike /
Shkollë Sportive / Medrese / Shkollë me Drejtim Gjuhësor).
Shpjegoni çdo zgjidhje në detaje të plota si për një nxënës që po përgatitet për Maturë.
Citoni formulat dhe rregullat relevante sipas programit orientues zyrtar të MASR.
Lënda: [SUBJECT]. Viti i provimit: [YEAR].
Tezat e provimeve profesionale ndryshojnë nga ato të gjimnazit — adaptoni shpjegimin
sipas llojit të shkollës kur kjo është e rëndësishme.
```

**Gemini Pro — PKAB Mode:**
```
Jeni specialist i Provimit Kombëtar të Arsimit Bazë (PKAB) shqiptar.
PKAB zhvillohet në përfundim të klasës së 9-të dhe mat kompetencat në:
Gjuhë Shqipe (strukturë: Të lexuarit + Gramatikë + Të shkruarit, 50 pikë total),
Matematikë (kl. 6–9, 50 pikë), dhe Gjuhë e Huaj (nivel A2).
Shpjegoni çdo ushtrim hap pas hapi. Tregoni strukturën e testit kur është e
rëndësishme. Lënda: [SUBJECT].
```

**Gemini Flash — VANAF Mode:**
```
Jeni mësues i arsimit fillor shqiptar dhe specialist i VANAF-it.
VANAF vlerëson arritjet e nxënësve të klasës së 5-të në lëndët:
Gjuhë Shqipe, Matematikë dhe Dituri Natyre.
Shpjegoni çdo gjë me gjuhë të thjeshtë, të përshtatshme për fëmijë 10–11 vjeç.
Jepni shpjegime vizuale kur ka mundësi (p.sh. me lista dhe hapa të qartë).
Lënda: [SUBJECT].
```

**ML Kit OCR → AI prompt wrapper:**
```
Nxënësi ka fotografuar detyrën e mëposhtme. Teksti i nxjerrë nga fotoja është:

[OCR_TEXT]

Niveli: [LEVEL]. Lënda: [SUBJECT].
Shpjegoni dhe zgjidhni këtë detyrë hap pas hapi. Nëse teksti është i paqartë,
thoni çfarë keni kuptuar dhe kërkoni sqarim.
```

## 3.3 Hard Limits (Enforced in Code)

| Limit | Value | Storage | Resets |
|-------|-------|---------|--------|
| Free daily questions | 10 | DataStore: `daily_q_count` + `last_reset_date` | Midnight local time |
| Photo max size (pre-ML Kit) | 5MB | Checked before processing | Per upload |
| Photos per session | 1 | UI state | Per question |
| OCR min confidence output | 50 chars | Checked post-OCR | Per photo |
| OCR max text sent to AI | 8,000 chars | Truncated silently | Per photo |
| Gemini Flash RPD buffer | Stop at 230/day | DataStore: `gemini_calls_today` | Midnight UTC |
| Gemini Pro daily calls | Max 40/day | DataStore: `gemini_pro_calls_today` | Midnight UTC |

> When Gemini Flash daily buffer (230) is hit → auto-switch to Groq Llama 4 Maverick for remainder.
> When Gemini Pro (40) is hit → fall back to Gemini Flash with note: "Analizë standarde (Pro i zënë)".

## 3.4 Education Level Registry

```kotlin
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
```

## 3.5 Subjects Registry (All Levels)

### Primary (Grades 1–5) — VANAF subjects
| ID | Albanian Name | Icon | Color |
|----|--------------|------|-------|
| `alb_primary` | Gjuhë Shqipe | 📝 | `#880E4F` |
| `math_primary` | Matematikë | ➕ | `#1565C0` |
| `science_primary` | Dituri Natyre | 🌿 | `#00695C` |
| `art_primary` | Arte | 🎨 | `#E65100` |
| `civic_primary` | Edukimi Qytetar | 🏛️ | `#4A148C` |

### Lower Secondary (Grades 6–9) — PKAB subjects
| ID | Albanian Name | Icon | Color |
|----|--------------|------|-------|
| `albanian` | Gjuhë Shqipe dhe Letërsi | 📖 | `#880E4F` |
| `math` | Matematikë | ➕ | `#1565C0` |
| `english` | Gjuhë e Huaj (Anglisht) | 🌐 | `#006064` |
| `physics` | Fizikë | ⚡ | `#6A1B9A` |
| `chemistry` | Kimi | 🧪 | `#2E7D32` |
| `biology` | Biologji | 🌿 | `#00695C` |
| `history` | Histori | 📜 | `#BF360C` |
| `geography` | Gjeografi | 🗺️ | `#827717` |
| `civic` | Qytetari | 🏛️ | `#4A148C` |
| `ict` | TIK (Teknologji Informacioni) | 💻 | `#0277BD` |

### Upper Secondary — General (Gjimnaz) — Matura subjects
| ID | Albanian Name | Icon | Color | Matura? |
|----|--------------|------|-------|---------|
| `alb_lit` | Gjuhë Shqipe dhe Letërsi | 📖 | `#880E4F` | ✅ Compulsory |
| `math_gym` | Matematikë | ➕ | `#1565C0` | ✅ Compulsory |
| `foreign_lang` | Gjuhë e Huaj | 🌐 | `#006064` | ✅ Compulsory |
| `physics_gym` | Fizikë | ⚡ | `#6A1B9A` | Elective |
| `chem_gym` | Kimi | 🧪 | `#2E7D32` | Elective |
| `bio_gym` | Biologji | 🌿 | `#00695C` | Elective |
| `hist_gym` | Histori | 📜 | `#BF360C` | Elective |
| `geo_gym` | Gjeografi | 🗺️ | `#827717` | Elective |
| `socio_phil` | Sociologji-Filozofi | 🤔 | `#4A148C` | Elective |
| `econ_gym` | Ekonomi | 💰 | `#1B5E20` | Elective |
| `civic_psy` | Qytetari-Psikologji | 🧠 | `#311B92` | Elective |

### Upper Secondary — Oriented/Artistic extra subjects
| ID | Albanian Name | Icon | Color | Track |
|----|--------------|------|-------|-------|
| `art_history` | Histori Arti | 🎨 | `#E65100` | Artistike |
| `ballet_history` | Histori Baleti | 🩰 | `#AD1457` | Artistike |
| `music_history` | Histori Muzike | 🎵 | `#6A1B9A` | Artistike |
| `math_artistic` | Matematikë (Artistike) | ➕ | `#1565C0` | Artistike |
| `math_sports` | Matematikë (Sportive) | ➕ | `#1565C0` | Sportive |

### Vocational (Professional) — subjects differ by trade; core Matura subjects:
| ID | Albanian Name | Notes |
|----|--------------|-------|
| `alb_lit_prof` | Gjuhë Shqipe dhe Letërsi (Profesionale) | Lighter syllabus |
| `math_prof` | Matematikë (Profesionale) | Lighter syllabus |
| `foreign_lang` | Gjuhë e Huaj | Same A2/B1 as general |
| `vocational_spec` | Lënda Profesionale (varies by trade) | Trade-specific |

## 3.6 Navigation Structure

```
Bottom Nav (4 tabs):
├── 🏠 Kryefaqja      (Home — level/grade entry + streak + quick access)
├── 💬 Mësuesi        (Active chat screen — level-aware)
├── 📜 Provimet        (Exam Prep Hub: VANAF / PKAB / Matura + VKM Calculator)
└── 👤 Profili         (Profile + Fletore notebook + stats)
```

> Note: The old "Matura" tab has been renamed and expanded to "Provimet" (Exams)
> to encompass VANAF, PKAB, and Matura under one hub.

## 3.7 Data Models

```kotlin
// User profile (DataStore)
// education_level: String (EducationLevel enum name)
// school_type: String (nullable — only for upper secondary)
// current_grade: Int (1–13)
// student_name: String
// daily_q_count: Int
// last_reset_date: String (yyyy-MM-dd)
// current_streak: Int
// last_active_date: String
// is_premium: Boolean
// gemini_calls_today: Int
// gemini_pro_calls_today: Int

// Question entity (Room DB)
data class Question(
    val id: String,            // UUID
    val educationLevel: String,// EducationLevel enum name
    val subjectId: String,
    val examMode: String?,     // "VANAF" | "PKAB" | "MATURA" | null
    val questionText: String,
    val answerText: String,
    val isPhotoQuestion: Boolean,
    val ocrExtractedText: String?,
    val modelUsed: String,     // "gemini-flash" | "groq-llama" | "gemini-pro"
    val timestamp: Long,
    val isSaved: Boolean
)

// Exam question entity (Room DB) — used by VANAF, PKAB, and Matura
data class ExamQuestion(
    val id: String,
    val examType: String,      // "VANAF" | "PKAB" | "MATURA"
    val schoolType: String?,   // null for VANAF/PKAB; EducationLevel name for Matura
    val subjectId: String,
    val year: Int,
    val questionText: String,
    val officialAnswer: String,
    val aiExplanation: String? // cached after first AI call
)

// VKM calculation (in-memory, not persisted — user enters fresh each time)
data class VkmCalculation(
    val schoolType: String,          // EducationLevel
    val vocationalYears: Int,        // 2, 3, or 4 (for vocational); always 3 for general
    val annualAverages: List<Double>, // one per year, e.g. [8.2, 8.5, 8.8]
    val maturaExamGrades: List<Double> // always 4 grades
) {
    val mSchool: Double get() = annualAverages.average()
    val mMatura: Double get() = maturaExamGrades.average()
    val mVkm: Double get() = (mSchool + mMatura) / 2
}
```

---

# 4. SYSTEM SEQUENCE DIAGRAMS (SSD)

## 4.1 Level & School Type Selection Flow (App Onboarding)

```
User                App                 DataStore
 |                   |                      |
 |-- opens app (1st) ->                     |
 |                   |-- check education_level -->|
 |                   |<-- null (not set) ---------|
 |                   |                      |
 |<-- Onboarding Screen:                    |
 |   "Cilës nivel i përket?"                |
 |   [Arsimi Fillor] [9-vjeçar] [I Mesëm i Lartë]
 |-- taps "I Mesëm i Lartë" -->             |
 |                   |                      |
 |<-- "Çfarë shkolle ndjek?"                |
 |   [Gjimnaz] [Profesionale] [Artistike]   |
 |   [Sportive] [Gjuhësor] [Medrese]        |
 |-- taps "Gjimnaz" -->                     |
 |                   |-- save education_level, school_type -->|
 |<-- Home screen (level-personalized) -----|
```

## 4.2 Text Question Flow (Level-Aware)

```
User              App             DataStore       Gemini Flash      Groq (fallback)
 |                 |                  |                |                  |
 |-- types Q ----->|                  |                |                  |
 |                 |-- check limit -->|                |                  |
 |                 |<-- N, level -----|                |                  |
 |   [N >= 10]     |                  |                |                  |
 |<-- paywall ------|                 |                |                  |
 |   [N < 10]      |                  |                |                  |
 |                 |-- build system prompt:           |                  |
 |                 |   inject [LEVEL] + [SCHOOL_TYPE] |                  |
 |                 |   + [SUBJECT] into canonical prompt                 |
 |                 |-- check gemini_calls ------------->                  |
 |   [calls < 230] |-- POST /generateContent -------->|                  |
 |                 |<-- Albanian response (level-tuned)|                  |
 |                 |-- increment counters ------------>|                  |
 |<-- answer -------|                 |                |                  |
 |   [calls >= 230]|-- POST /chat/completions --------|------------------>|
 |                 |<-- Albanian response (fallback) --|                  |
 |<-- answer -------|                 |                                   |
```

## 4.3 Photo Question Flow

```
User           App            ML Kit (on-device)   DataStore       Gemini Flash
 |              |                   |                  |                |
 |-- taps 📷 -->|                   |                  |                |
 |              |-- check limit --->|                  |                |
 |              |                   |<-- N < 10 --------|                |
 |<-- camera ---|                   |                  |                |
 |-- captures ->|                   |                  |                |
 |              |-- compress Bitmap (max 5MB) ----------                |
 |              |-- InputImage.fromBitmap() ---------->|                |
 |              |<-- vText.text ----|                  |                |
 |   [< 50 chars]                   |                  |                |
 |<-- Snackbar: "Fotoja nuk është e qartë"             |                |
 |   [OK text]  |                   |                  |                |
 |<-- editable OCR preview ---------|                  |                |
 |-- confirms -->|                  |                  |                |
 |              |-- build prompt (OCR_TEXT + LEVEL + SUBJECT) -------->|
 |              |<---------------------------------------- response ----|
 |              |-- increment counters ----------------->               |
 |<-- answer ---|                   |                  |                |
```

## 4.4 Exam Prep Flow (VANAF / PKAB / Matura)

```
User          App              Room DB           Gemini Pro / Flash
 |             |                  |                     |
 |-- Provimet tab ->              |                     |
 |             |-- read education_level from DataStore  |
 |             |                  |                     |
 |  [Primary, Grade 5]            |                     |
 |<-- Show VANAF prep section     |                     |
 |             |                  |                     |
 |  [Grade 9]  |                  |                     |
 |<-- Show PKAB prep section      |                     |
 |             |                  |                     |
 |  [Grade 10–13, any upper]      |                     |
 |<-- Show Matura section (track-aware) + VKM Calculator|
 |             |                  |                     |
 |-- taps question ->             |                     |
 |             |-- check aiExplanation in Room DB ------>|
 |   [cached]  |<-- explanation --|                     |
 |<-- answer ---|                 |                     |
 |   [not cached]                 |                     |
 |             |-- pro_calls < 40? -- POST Gemini Pro -->|
 |             |<-----------------------------------------response|
 |             |-- cache to Room DB --------------------> |
 |<-- answer ---|                 |                     |
```

## 4.5 VKM Calculator Flow

```
User            App (VKM Calculator Screen)
 |               |
 |-- opens VKM tab ->
 |               |-- read school_type from DataStore
 |               |-- if Vocational: show "Sa vite zgjasin studimet?" (2/3/4)
 |               |-- render annual grade input fields (N fields = years)
 |               |-- render 4 Matura exam grade fields
 |               |
 |-- enters grades ->
 |               |-- compute on-device (no API call):
 |               |   M_shkollë = avg(annual grades)
 |               |   M_maturë  = avg(4 exam grades)
 |               |   M_VKM     = (M_shkollë + M_maturë) / 2
 |               |
 |<-- live result card:
 |   "Mesatarja e shkollës: 8.50"
 |   "Mesatarja e Maturës:  8.25"
 |   "━━━━━━━━━━━━━━━━━━━━━━━━━"
 |   "M_VKM = 8.38  ✅ Plotëson kriterin (≥ 6.0)"
 |   [colored: green ≥7, amber 6–7, red <6]
```

## 4.6 Daily Limit Reset Flow

```
App (on launch)    DataStore
      |                |
      |-- get last_reset_date -->|
      |<-- "2026-05-22" --------|
      [today = "2026-05-23" → different]
      |-- set daily_q_count = 0 ->|
      |-- set gemini_calls_today = 0 ->|
      |-- set gemini_pro_calls_today = 0 ->|
      |-- set last_reset_date = today ->|
      [proceed normally]
```

---

# 5. APP THEME SPECS

## 5.1 Color Palette

```
PRIMARY         #1A237E   Deep Navy Blue    — app bar, FAB, primary buttons
PRIMARY LIGHT   #534BAE   Medium Blue       — selected state, highlights
PRIMARY DARK    #000051   Dark Navy         — status bar
SECONDARY       #FFC107   Gold / Amber      — streak indicator, star ratings, accents
SECONDARY DARK  #FF8F00   Deep Amber        — secondary button pressed state

BACKGROUND      #F5F7FF   Off-white blue    — screen background
SURFACE         #FFFFFF   Pure white        — cards, bottom sheets
ON-SURFACE      #1A1A2E   Near-black        — primary text
SUBTLE TEXT     #6B7280   Gray              — secondary text, metadata

SUCCESS         #2E7D32   Forest green      — correct answers, streak, VKM OK
WARNING         #FF8F00   Amber             — daily limit near (8/10), VKM borderline
ERROR           #C62828   Deep red          — limit hit, wrong answer, VKM below 6
INFO            #0277BD   Info blue         — tip banners

EXAM ACCENT COLORS:
  VANAF         #00695C   Teal              — Grade 5 exam
  PKAB          #1565C0   Blue              — Grade 9 exam
  MATURA        #880E4F   Burgundy          — Grade 12/13 exam
  VKM           #4A148C   Deep Purple       — VKM calculator

SUBJECT COLORS (see SSOT 3.5 for per-subject):
  Albanian/Lit  #880E4F   Burgundy
  Math          #1565C0   Blue
  Physics       #6A1B9A   Purple
  Chemistry     #2E7D32   Green
  History       #BF360C   Brown-red
  Biology       #00695C   Teal
  Geography     #827717   Olive
  Science/Nat.  #00695C   Teal
  Civic         #4A148C   Deep Purple
  Economics     #1B5E20   Dark Green
  Art History   #E65100   Deep Orange
  ICT           #0277BD   Info Blue
```

## 5.2 Typography

```
FONT FAMILY: Inter (Google Fonts — free)
Fallback: Roboto (Android system default)

SCALE:
  Display Large    32sp  Bold      — App name / celebration screens
  Headline Large   28sp  SemiBold  — Screen titles
  Headline Medium  24sp  SemiBold  — Section headers
  Title Large      22sp  Medium    — Card titles, subject names
  Title Medium     18sp  Medium    — Sub-section headers
  Body Large       16sp  Regular   — AI answer text, primary content
  Body Medium      14sp  Regular   — Secondary content, descriptions
  Label Large      14sp  Medium    — Buttons
  Label Medium     12sp  Medium    — Chips, tags, badges
  Label Small      11sp  Regular   — Timestamps, fine print

LINE HEIGHT: 1.5x font size for body text, 1.2x for headings
```

## 5.3 Spacing System

```
BASE UNIT: 4dp
xs 4dp | sm 8dp | md 12dp | lg 16dp | xl 20dp | 2xl 24dp | 3xl 32dp | 4xl 48dp
```

## 5.4 Component Specs

### Level Selector Card (Onboarding + Home header)
```
Background: PRIMARY at 10% opacity
Border: 2dp PRIMARY color on selected; 1dp #E5E7EB on unselected
Height: 72dp
Corner radius: 16dp
Level name: Title Medium, PRIMARY color
Grade range: Label Medium, SUBTLE TEXT
Icon: 28dp left side
Selected state: filled background PRIMARY 15%, icon PRIMARY, checkmark
```

### School Type Chip (Upper Secondary selector)
```
Height: 40dp
Corner radius: 20dp (pill)
Selected: PRIMARY fill, white text
Unselected: outlined, PRIMARY text
Labels: Gjimnaz / Profesionale / Artistike / Sportive / Gjuhësor / Medrese
```

### Exam Prep Card (Provimet screen)
```
Height: auto (120–160dp depending on description)
Corner radius: 16dp
Left accent bar: 6dp solid — teal (VANAF), blue (PKAB), burgundy (MATURA)
Title: Title Large
Subtitle: Body Medium, SUBTLE TEXT
Chip: exam year or class level
"Shiko Shpjegimin" expand button: Label Large, accent color
```

### VKM Calculator
```
Container: Card with 16dp corner radius, 2dp elevation, VKM accent (#4A148C at 10% bg)
Year inputs: OutlinedTextField, compact height 52dp, numeric keyboard
  Labels: "Viti X (nota mesatare)"
Matura inputs: 4 OutlinedTextFields labeled by exam (e.g. "Gjuhë e Huaj")
Result card:
  Background: computed color (green/amber/red)
  M_VKM displayed: Display Large, white
  Sub-text: "Plotëson kriterin" or "Nën kufirin minimal"
  Both component averages shown below in Body Medium
```

### Chat Bubble — User
```
Background: PRIMARY (#1A237E); Text: White; Max width: 75%; Corner radius: 18dp (2dp bottom-right)
```

### Chat Bubble — AI (Mësuesi)
```
Background: SURFACE (#FFFFFF); Border: 1dp #E5E7EB; Max width: 85%
Corner radius: 18dp (2dp bottom-left); Avatar: 32dp level/subject emoji
```

### Streak Badge
```
Background: SECONDARY (#FFC107); Text: "🔥 X ditë"; Height: 32dp; Corner: 16dp pill
```

### Daily Limit Counter
```
0–7: SUCCESS green | 8–9: WARNING amber | 10: ERROR red
Position: Top of chat screen, below toolbar
```

## 5.5 Motion & Animation

```
Screen transitions:   Shared element + slide (300ms ease-in-out)
Card press:           Scale 0.97 (150ms)
AI answer appearance: Fade + slide up (250ms, stagger 50ms per paragraph)
Streak increment:     Bounce 1.0 → 1.3 → 1.0 (400ms spring)
Loading dots (AI thinking): 3 dots, 600ms pulse, 200ms stagger
VKM result reveal:    Animated number counter (800ms ease-out)
Level switch:         Cross-fade subjects grid (200ms)
```

## 5.6 Iconography

```
Icon set: Material Symbols (Rounded variant, weight 400)

Key icons:
  Home:             home_rounded
  Chat:             chat_rounded
  Exams:            quiz_rounded         ← new (replaces school_rounded)
  Profile:          person_rounded
  Camera:           photo_camera_rounded
  Send:             send_rounded
  Save:             bookmark_rounded
  VKM Calculator:   calculate_rounded
  VANAF:            child_care_rounded
  PKAB:             assignment_rounded
  Matura:           school_rounded
  Level up:         trending_up_rounded
  Streak:           local_fire_department_rounded
  Premium:          star_rounded
```

## 5.7 Dark Mode Overrides

```
BACKGROUND       #0D0F1A
SURFACE          #1A1D2E
ON-SURFACE       #E8EAF6
SUBTLE TEXT      #9EA7C4
User bubble      PRIMARY stays same
AI bubble        #252840
Cards            #1E2137 with 1dp #2D3152 border
```

---

# 6. BUILDER INSTRUCTIONS — GOOGLE AI STUDIO ANDROID BUILDER

## 6.1 Pre-Build Checklist

- [ ] **Google AI Studio API key** ready (aistudio.google.com — free, no credit card)
- [ ] **Groq API key** ready (console.groq.com — free, no credit card)
- [ ] Verify both keys work with a curl test
- [ ] Download Android Studio for local testing after build
- [ ] Android device or emulator ready (API 26+)

## 6.2 Main Builder Prompt

Paste this in full into Google AI Studio → Build Android App:

---

```
Build a native Android app called "Mësues AI" — an AI tutor for ALL Albanian
pre-university students (Grades 1–13, all school types).

=== CORE IDENTITY ===
App Name: Mësues AI
Package: com.gamingrepo.mesuesai
Min SDK: 26 (Android 8.0)
Language: Kotlin
Architecture: MVVM + Repository pattern
UI Framework: Jetpack Compose (Material Design 3)
Primary UI Language: Albanian (Shqip)

=== DEPENDENCIES ===
  implementation 'com.squareup.okhttp3:okhttp:4.12.0'
  implementation 'com.google.code.gson:gson:2.10.1'
  implementation 'com.google.mlkit:text-recognition:16.0.1'
  implementation 'com.google.mlkit:text-recognition-latin:16.0.1'
  implementation 'androidx.room:room-runtime:2.6.1'
  kapt 'androidx.room:room-compiler:2.6.1'
  implementation 'androidx.room:room-ktx:2.6.1'
  implementation 'androidx.navigation:navigation-compose:2.7.7'
  implementation 'io.coil-kt:coil-compose:2.6.0'
  implementation 'androidx.datastore:datastore-preferences:1.0.0'

=== THEME ===
Primary: #1A237E | PrimaryLight: #534BAE | PrimaryDark: #000051
Secondary: #FFC107 | Background: #F5F7FF | Surface: #FFFFFF
OnSurface: #1A1A2E | Error: #C62828 | Success: #2E7D32
ExamVANAF: #00695C | ExamPKAB: #1565C0 | ExamMatura: #880E4F | VKM: #4A148C
Font: Inter (Google Fonts) | Dark mode: supported

=== ONBOARDING (first launch only) ===
Screen 1 — "Cilës nivel i përket?"
  Three large cards:
  • Arsimi Fillor (Kl. 1–5)
  • Arsimi 9-vjeçar (Kl. 6–9)
  • Arsimi i Mesëm i Lartë (Kl. 10–13)
  Save choice to DataStore: education_level

Screen 2 — "Çfarë klasë/vit ndjekon?" — number picker for grade (filtered by level)
  Save to DataStore: current_grade

Screen 3 (only if upper secondary selected) — "Çfarë lloji shkolle ndjek?"
  Chips: Gjimnaz | Shkollë Profesionale | Shkollë Artistike |
         Shkollë Sportive | Shkollë Gjuhësore | Medrese
  Save to DataStore: school_type

  If Shkollë Profesionale: follow-up "Sa vjet zgjasin studimet tuaja?"
  Radio: 2 vjet | 3 vjet | 4 vjet
  Save to DataStore: vocational_years (Int, default 3)

Screen 4 — "Si të quajmë?" — name input
  Save to DataStore: student_name

=== SCREENS & NAVIGATION ===
Bottom navigation — 4 tabs:
  1. Kryefaqja (home_rounded)
  2. Mësuesi (chat_rounded) — default selected
  3. Provimet (quiz_rounded) — VANAF / PKAB / Matura hub
  4. Profili (person_rounded)

--- SCREEN 1: KRYEFAQJA (Home) ---

Top section:
  • "Mirëdita, [name]! 👋" — personalized level badge below:
    "[Gjimnaz — Klasa 11]" or "[9-vjeçar — Klasa 7]" etc.
  • Streak badge: "🔥 [N] ditë" (amber pill)
  • Daily progress: "[N]/10 pyetje sot" (colored linear bar)

Subject grid (dynamic by education_level):
  - Primary (Gr 1–5): 3 cards (Gjuhë Shqipe, Matematikë, Dituri Natyre)
    + 2 more (Arte, Edukimi Qytetar)
  - Lower Secondary (Gr 6–9): 6+ cards
    (Gjuhë Shqipe, Matematikë, Anglisht, Biologji, Histori, Fizikë, Kimi, Gjeografi)
  - Upper Secondary (Gr 10–13): subject grid by school_type
    Gjimnaz: 11 subjects from registry
    Artistike: standard + Histori Arti/Baleti/Muzike
    Sportive: standard + Matematikë Sportive
    Gjuhësor: standard subjects
    Medrese: standard secular subjects (note: "Lëndët fetare zhvillohen veçan")
    Profesionale: core + Lënda Profesionale slot

  Each card: icon + name + colored left border accent
  Tap → navigates to Mësuesi tab with subject + level pre-selected

Quick access row:
  • Exam prep chip (based on level):
    Grade 1–5: "🌿 Përgatitu për VANAF"
    Grade 6–8: (no chip)
    Grade 9: "📋 Përgatitu për PKAB"
    Grade 10–13: "🎓 Matura [current_year]"
  • "⭐ Pyetjet e ruajtura" chip → Fletore

--- SCREEN 2: MËSUESI (Chat) ---

Toolbar:
  • Subject selector dropdown (current subject, tap to change)
  • Level chip (small, shows e.g. "Kl. 11 · Gjimnaz") — tap to change level/school
  • Daily counter chip: "[N]/10"
  • Camera icon button

Chat area:
  • AI bubbles: white card, left-aligned, level-appropriate avatar emoji
    (🧒 for primary, 📚 for secondary, 🎓 for upper secondary)
  • User bubbles: PRIMARY, right-aligned, white text
  • AI thinking: 3 animated dots

Input area:
  • TextField: "Shkruaj pyetjen tënde..."
  • Camera button 📷 (left)
  • Send button (right, PRIMARY, enabled when text not empty)

Photo Flow (same as original spec):
  CameraX → compress 5MB → ML Kit OCR → 50 char min → editable preview → send

AI Call Logic (UNCHANGED from v1):
  daily_q_count >= 10 → paywall
  gemini_calls_today < 230 → Gemini Flash (with level-injected system prompt)
  else → Groq Llama 4 fallback
  Increment counters on success, save to Room DB

System prompt ALWAYS includes: [LEVEL], [SCHOOL_TYPE], [SUBJECT]
Replace [LEVEL] with DataStore education_level + current_grade
Replace [SCHOOL_TYPE] with DataStore school_type (or "N/A" if primary/secondary)
Replace [SUBJECT] with current subject Albanian name

--- SCREEN 3: PROVIMET (Exam Prep Hub) ---

This screen shows different sections based on education_level from DataStore.
All sections can be visible simultaneously (as expandable cards) for users
who want to explore, but the relevant section is automatically expanded.

SECTION A: VANAF (shown prominently if grade 1–5; collapsed but accessible otherwise)
  Header: "📊 VANAF — Klasa e 5-të" (teal accent #00695C)
  Description: "Vlerësimi kombëtar i arritjeve — 3 lëndë"
  Subject chips: [Gjuhë Shqipe] [Matematikë] [Dituri Natyre]
  Content: LazyColumn of practice exam questions seeded in Room DB
    - Filter chips: subject + year
    - Each card: question → "Shiko Shpjegimin" → AI explanation (Gemini Flash)
  "Çfarë është VANAF?" info card (collapsible):
    "Vlerësohen njohuritë dhe kompetencat bazë të ciklit fillor.
     Ky vlerësim është diagnostik dhe nuk ndikon drejtpërdrejt në promovimin e nxënësit."

SECTION B: PKAB (shown prominently if grade 6–9; collapsed but accessible otherwise)
  Header: "📋 PKAB — Klasa e 9-të" (blue accent #1565C0)
  Description: "Provimi kombëtar i arsimit 9-vjeçar — 3 lëndë (50 pikë secila)"
  Subject chips: [Gjuhë Shqipe] [Matematikë] [Gjuhë e Huaj]
  Content: LazyColumn of PKAB practice questions seeded in Room DB
    - Gjuhë Shqipe: reading comprehension + grammar + writing tasks
    - Matematikë: Grades 6–9 problems
    - Gjuhë e Huaj: A2 level exercises (note which language: Anglisht/Frëngjisht/Italisht/Gjermanisht)
  Each card: "Shiko Shpjegimin" → Gemini Pro explanation (cached in Room DB)
  "Çfarë është PKAB?" info card:
    "Certifikon përfundimin e arsimit 9-vjeçar të detyruar.
     3 provime: Gjuhë Shqipe (50 pikë), Matematikë (50 pikë), Gjuhë e Huaj (A2)."

SECTION C: MATURA (shown prominently if grade 10–13; collapsed but accessible otherwise)
  Header: "🎓 Matura Shtetërore" (burgundy accent #880E4F)
  School type badge: shows current school_type (e.g. "Gjimnaz" or "Profesionale")
  
  Sub-tabs within this section:
  [Pyetje Praktike] [Llogaritësi VKM] [Programet Orientuese]
  
  Sub-tab 1 — Pyetje Praktike:
    Filter row: Subject chips (filtered by school_type) + Year chips (2021–2025)
    LazyColumn of MaturaQuestion cards (track-aware syllabus labels)
    Each card: question → "Shiko Shpjegimin" → Gemini Pro (cached in Room DB)
    Professional track note: small banner "📌 Teza profesionale — program i lehtësuar"
    Artistic track note: shows Histori Arti / Baleti / Muzike as elective options
    Sports track note: shows Matematikë Sportive syllabus
    Madrasah note: "Lëndët fetare të Medresesë nuk janë pjesë e Maturës Shtetërore"

  Sub-tab 2 — Llogaritësi VKM:
    Title: "Llogarit M_VKM-në tënde"
    If school_type == Profesionale: show "Strukturë [vocational_years]-vjeçare"
    else show "Gjimnaz/Orientuar — 3 vjet"
    
    Card: "Notat mesatare vjetore"
      Dynamic inputs: "Klasa 10 (nota mesatare)", "Klasa 11 ...", "Klasa 12 ..."
      [+ "Klasa 13 ..." if vocational_years >= 4]
    
    Card: "Notat e provimeve të Maturës"
      4 inputs labeled by exam:
        "Gjuhë e Huaj", "Gjuhë-Letërsi", "Matematikë", "Me Zgjedhje"
      (for Artistic: 4th label = "Histori Arti/Baleti/Muzike")
      (for Vocational: 4th label = "Lënda Profesionale")
    
    Live calculation card (updates as user types):
      "Mesatarja e shkollës:  [M_shkollë]"
      "Mesatarja e Maturës:   [M_maturë]"
      "────────────────────────────"
      "M_VKM = [value]"
      Status chip:
        ≥ 7.0: "✅ Shkëlqyer" (green)
        6.0–6.99: "✅ Plotëson kriterin" (amber)
        < 6.0: "❌ Nën kufirin minimal (6.0)" (red)
      "ℹ️ Formula: M_VKM = (mesatarja e viteve + mesatarja e provimeve) / 2"
    
    Disclaimer text (Label Small, SUBTLE TEXT):
      "Ky llogaritës bazohet në VKM-në në fuqi. Konsultohuni me shkollën tuaj
       për llogaritjen zyrtare dhe koefiçentët e profileve."

  Sub-tab 3 — Programet Orientuese:
    Text cards linking to official programs by school type
    "Shkarko nga arsimi.gov.al" external link buttons (open browser)
    Reminder: "Programet orientuese miratohen çdo vit nga MASR — verifikoni versionin e fundit."

=== ROOM DB SEEDING (Matura questions) ===
Seed at least:
  - 10 Gjimnaz Matura questions (mix of: 3 Matematikë, 3 Gjuhë-Letërsi, 2 Fizikë, 2 Histori)
    from years 2022–2025
  - 5 PKAB Matematikë questions (Grades 6–9 level)
  - 5 PKAB Gjuhë Shqipe questions (reading + grammar)
  - 5 VANAF Matematikë questions (Grades 1–5 level)
  - 5 VANAF Dituri Natyre questions (basic science Grade 5)
  - 5 Shkollë Profesionale Matematikë questions (lighter syllabus, practical problems)
  - 3 Shkollë Artistike Histori Arti questions

=== KEY BEHAVIORS ===

1. ALL API keys in local.properties:
   GEMINI_API_KEY=your_key_here
   GROQ_API_KEY=your_key_here
   Access via BuildConfig.

2. EVERY API call includes level-aware system prompt.
   Replace [LEVEL], [SCHOOL_TYPE], [SUBJECT] before sending.

3. VKM calculator: 100% on-device computation, zero API calls.

4. Matura questions adapt visible subjects based on school_type:
   Artistike → show Histori Arti/Baleti/Muzike as 4th elective option
   Sportive → show Matematikë Sportive notes
   Profesionale → always include disclaimer about lighter syllabus
   Medrese → flag that Islamic Studies are not part of Matura Shtetërore

5. No loading screen on cold start. Skeleton loaders on subject grid.

6. Error handling for every API call:
   - Network timeout (10s): "Nuk ka lidhje interneti. Kontrolloni wifi."
   - 429 rate limit: auto-switch to fallback silently
   - 500 server error: "Gabim i serverit. Provo pas pak çastesh."
   - All errors as Snackbar

7. Photo: NEVER send image bytes to AI — only OCR text.

8. Conversation: keep last 6 pairs in memory per session; resets on app kill.

9. Haptic feedback: light tap on send, medium on streak increment.

10. API keys in BuildConfig:
    buildConfigField 'String', 'GEMINI_API_KEY', '"${GEMINI_API_KEY}"'
    buildConfigField 'String', 'GROQ_API_KEY', '"${GROQ_API_KEY}"'
    viewBinding { enabled = true }
    kotlinOptions { jvmTarget = '17' }

--- SCREEN 4: PROFILI (Profile) ---

Top card:
  • Avatar: initials circle (PRIMARY bg)
  • Name + level badge: "[Gjimnaz · Kl. 11]"
  • "⭐ Premium" badge or "Kaloni Premium" CTA
  • "🔥 [N] ditë radhazi"
  • "✏️ Ndrysho profilin" → opens edit bottom sheet (name, level, grade, school type)

Stats row (3 cards): Total questions | Favorite subject | This week

"Fletore" section:
  • Toggle chips: Të gjitha | by subject | by exam type (VANAF / PKAB / Matura)
  • LazyColumn of saved Question cards

Premium bottom sheet:
  • "Mësuesi juaj AI, pa kufizime"
  • Features list
  • Monthly: "500 ALL / muaj"
  • Yearly: "4,000 ALL / vit — Kurse 33%!"
  • "Pagesa me para në dorë — kontaktoni: [WhatsApp number]"

Settings (gear icon):
  • Dark mode toggle
  • Edit education level / school type
  • Reset daily counter (debug only)
  • App version
  • "Rreth nesh" → Gaming Repository credit
```

---

## 6.3 Post-Build Steps

1. Verify `local.properties` has both API keys — never commit to Git
2. Add `.gitignore` entry: `local.properties`
3. Test OCR on a real printed math problem
4. Test VKM calculator with manual calculations to verify formula
5. Test daily limit reset (change device date in dev options)
6. Test fallback by setting Gemini buffer to 1 temporarily
7. Test all 6 school types with sample questions
8. Seed real 2024/2025 Albanian Matura questions (replace placeholder seeds)
9. Update WhatsApp number in premium sheet

---

# 7. SPRINT TRACKER

## Sprint 0 — Setup (Current)
**Goal:** Project created, keys working, onboarding renders correctly

| Task | Status | Notes |
|------|--------|-------|
| Generate project with AI Studio builder | ⬜ Todo | Use Section 6.2 prompt |
| Add API keys to local.properties | ⬜ Todo | Gemini + Groq |
| Verify Gemini Flash responds in Albanian (level-aware) | ⬜ Todo | Test with Gr 5 and Gr 12 prompts |
| Verify Groq Llama responds in Albanian | ⬜ Todo | |
| Onboarding flow renders and saves to DataStore | ⬜ Todo | Test all 6 school types |
| First build runs on emulator | ⬜ Todo | |
| Git repo initialized (private) | ⬜ Todo | |

## Sprint 1 — Core AI Chat (Week 1)
**Goal:** Working Albanian AI tutor, text only, level-aware

| Task | Status | Acceptance Criteria |
|------|--------|-------------------|
| Chat screen renders | ⬜ | Bubbles, input, level chip |
| Level + school type passed to system prompt | ⬜ | AI adapts to grade 5 vs grade 12 |
| Gemini Flash API call works | ⬜ | Albanian response in < 5s |
| Daily limit counter works | ⬜ | Stops at 10, resets midnight |
| Groq fallback at 230 | ⬜ | Seamless |
| Paywall at limit | ⬜ | Shows premium options |
| Errors as Snackbar | ⬜ | No crashes |

**DoD:** 5th-grader can ask a Dituri Natyre question AND a 12th-grader can ask a Matura Matematikë question, each getting correctly pitched answers.

## Sprint 2 — Photo + Home + VANAF/PKAB (Week 2)
**Goal:** Camera flow + VANAF and PKAB exam prep working

| Task | Status | Acceptance Criteria |
|------|--------|-------------------|
| CameraX + ML Kit OCR | ⬜ | Text extracted from printed problem |
| Bitmap compression 5MB | ⬜ | Enforced |
| OCR preview editable | ⬜ | User can fix OCR errors |
| Low-confidence Snackbar | ⬜ | < 50 chars triggers it |
| Home subject grid dynamic | ⬜ | Shows correct subjects per level |
| VANAF section renders | ⬜ | 15+ seeded questions |
| PKAB section renders | ⬜ | 15+ seeded questions with 3 subjects |
| Streak + daily bar correct | ⬜ | |

**DoD:** A Grade 9 student can photo a handwritten math problem AND use the PKAB exam prep section.

## Sprint 3 — Matura + VKM + Profile (Week 3)
**Goal:** All screens complete; VKM calculator verified; app demo-ready

| Task | Status | Acceptance Criteria |
|------|--------|-------------------|
| Matura section renders (all 6 school types) | ⬜ | Correct subjects per track |
| Artistic/Sport/Vocational track notes | ⬜ | Visible and accurate |
| Medrese note renders | ⬜ | Islamic Studies disclaimer shown |
| 30+ hardcoded exam questions seeded | ⬜ | Across VANAF, PKAB, Matura |
| Gemini Pro explanations + cache | ⬜ | No duplicate API calls |
| VKM Calculator — Gjimnaz (3yr) | ⬜ | Matches manual formula |
| VKM Calculator — Vocational (2/3/4yr) | ⬜ | Dynamic year fields |
| VKM Calculator — live update | ⬜ | Updates on every keystroke |
| VKM result color coding | ⬜ | Green/amber/red thresholds |
| Pro fallback banner | ⬜ | Shows when Pro limit hit |
| Profile stats accurate | ⬜ | Correct totals from Room |
| Fletore filter by exam type | ⬜ | VANAF/PKAB/Matura chips |
| Dark mode works | ⬜ | All screens |
| No crash in 30-min session | ⬜ | Manual testing |

**DoD:** App can be demo'd to a Gjimnaz student, a Shkollë Profesionale student, and a Grade 5 student with no explanation needed.

## Sprint 4 — Beta Testing + Fixes (Week 4)
**Goal:** 5+ real Albanian students across different school types; fix everything they break

| Task | Status | Notes |
|------|--------|-------|
| Install on 3 real Android devices | ⬜ | Different manufacturers |
| Beta users: at least 1 per level (primary / 9-vjeçar / gjimnaz / professional) | ⬜ | |
| Feedback form (Google Form, Albanian) | ⬜ | |
| All Sprint 4 bugs logged (Section 8) | ⬜ | |
| Play Store listing prepared | ⬜ | Icon, screenshots, description |

---

# 8. BUG REGISTRY

## Template

| BUG-ID | Title | Severity | Screen | Steps | Expected | Actual | Sprint | Status | Commit |
|--------|-------|----------|--------|-------|----------|--------|--------|--------|--------|
| BUG-001 | *(None yet)* | — | — | — | — | — | — | — | — |

**Severity scale:** 🔴 Critical / 🟠 High / 🟡 Medium / 🟢 Low

---

# 9. CHANGELOG

## v0.1.0 — Project Init
**Sprint:** 0 | **Date:** *(fill when created)*
- Project scaffolded; repository initialized
- API keys configured in local.properties
- Base theme applied (Inter font, full color palette)
- Onboarding flow designed (level + grade + school type + name)

## v0.2.0 — Core Chat
**Sprint:** 1 | **Date:** *(fill when Sprint 1 complete)*
- Gemini 2.5 Flash integration with level-aware Albanian system prompt
- Groq Llama 4 Maverick fallback (auto at 230 Gemini calls/day)
- Daily question limit (10/day) with midnight reset
- Education level + school type injected into every API call
- Paywall bottom sheet (manual WhatsApp payment)
- Error Snackbars (429, 500, network timeout)

## v0.3.0 — Photo + Home + Exam Prep Foundations
**Sprint:** 2 | **Date:** *(fill when Sprint 2 complete)*
- CameraX + ML Kit Text Recognition v2 (on-device OCR)
- Bitmap compression (5MB limit)
- Editable OCR preview before sending to AI
- Dynamic Home subject grid (adapts to education_level + school_type)
- VANAF section: 15+ seeded questions, Gemini Flash explanations
- PKAB section: 15+ seeded questions (Gjuhë Shqipe, Matematikë, Gjuhë e Huaj)
- Streak badge + daily progress bar

## v0.4.0 — Matura + VKM + Profile
**Sprint:** 3 | **Date:** *(fill when Sprint 3 complete)*
- Full Matura prep screen — all 6 school types (Gjimnaz, Profesionale, Artistike, Sportive, Gjuhësor, Medrese)
- Track-aware exam questions and subject labels
- VKM Calculator: Gjimnaz (3yr) and Vocational (2/3/4yr) — 100% on-device
- VKM color-coded result card with disclaimer
- Gemini 2.5 Pro for Matura + PKAB explanations (cached in Room DB)
- Gemini Pro fallback to Flash when daily limit hit
- Fletore notebook with VANAF/PKAB/Matura filter
- Profile stats + level/school type edit bottom sheet
- Dark mode support

## v1.0.0 — Public MVP Launch
**Sprint:** 4 (post-beta) | **Date:** *(fill at launch)*
- Beta feedback from cross-track students integrated
- All critical bugs fixed
- Play Store listing published
- First 500 ALL/month premium user acquired 🎉

---

## Albanian Education System Quick Reference Card

```
CYCLE                 GRADES  AGE    KEY EXAM         MASR CODE
─────────────────────────────────────────────────────────────
Arsimi Fillor         1–5     6–10   VANAF (Gr 5)     ISCED 1
Arsimi i Mesëm i Ulët 6–9     11–15  PKAB  (Gr 9)     ISCED 2
─────────────────────────────────────────────────────────────
Gjimnaz               10–12   15–18  Matura (4 exams)  ISCED 3
Shkollë Artistike     10–12   15–18  Matura (artistike) ISCED 3
Shkollë Sportive      10–12   15–18  Matura (sportive)  ISCED 3
Shkollë Gjuhësore     10–12   15–18  Matura (gjuhësor)  ISCED 3
Medrese               10–12   15–18  Matura (general)   ISCED 3 (private/religious)
Shkollë Profesionale  10–13   15–19  Matura Profesionale ISCED 3/4

VKM FORMULA (all tracks): M_VKM = (M_shkollë + M_maturë) / 2
GRADING: 5 = min pass | 10 = excellent | Scale: 4–10
UNIVERSITY MIN VKM: 6.0
```

---

*Document maintained by Dario — Gaming Repository*
*Last updated: May 2026 | Version: 2.0 (Pre-build, full system expansion)*
*Sources: Eurydice Albania (March 2026), QSHA/ASCAP, arsimi.gov.al, maturashteterore.com*
