# 🎓 Mësues AI — Extended MVP Master Document (v3)
> **Gaming Repository** | Project Lead: Dario | Status: Pre-build
> Single Source of Truth (SSOT) for all project decisions, specs, and history.
> *v3 — Stack migrated to Flutter/Dart. Agent: GitHub Copilot. Covers full Albanian pre-university system (Grades 1–13)*

---

# TABLE OF CONTENTS

1. [Albanian Education System — Research Overview](#1-albanian-education-system--research-overview)
2. [Project Specs](#2-project-specs)
3. [SSOT — Single Source of Truth](#3-ssot--single-source-of-truth)
4. [System Sequence Diagrams (SSD)](#4-system-sequence-diagrams-ssd)
5. [App Theme Specs](#5-app-theme-specs)
6. [Builder Instructions — GitHub Copilot Agent (Flutter)](#6-builder-instructions--github-copilot-agent-flutter)
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
| **Platform** | Flutter — Android-first (iOS-ready, same codebase) |
| **Min Android SDK** | 26 (Android 8.0+) |
| **Min iOS** | 14.0 (future release) |
| **Agent** | GitHub Copilot (Agent Mode) |
| **Language** | Dart 3.3+ |
| **UI Framework** | Flutter Material 3 |
| **State Management** | Riverpod 2.x |
| **App Language** | Albanian (Shqip) — primary UI language |
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
- iOS version (same Flutter codebase — post-MVP App Store submission; Android-first)
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
| Free daily questions | 10 | SharedPreferences: `daily_q_count` + `last_reset_date` | Midnight local time |
| Photo max size (pre-ML Kit) | 5MB | Checked before processing | Per upload |
| Photos per session | 1 | Widget state | Per question |
| OCR min confidence output | 50 chars | Checked post-OCR | Per photo |
| OCR max text sent to AI | 8,000 chars | Truncated silently | Per photo |
| Gemini Flash RPD buffer | Stop at 230/day | SharedPreferences: `gemini_calls_today` | Midnight UTC |
| Gemini Pro daily calls | Max 40/day | SharedPreferences: `gemini_pro_calls_today` | Midnight UTC |

> When Gemini Flash daily buffer (230) is hit → auto-switch to Groq Llama 4 Maverick for remainder.
> When Gemini Pro (40) is hit → fall back to Gemini Flash with note: "Analizë standarde (Pro i zënë)".

## 3.4 Education Level Registry

```dart
enum EducationLevel {
  primary('Arsimi Fillor', 1, 5),
  lowerSecondary('Arsimi i Mesëm i Ulët (9-vjeçar)', 6, 9),
  upperSecondaryGeneral('Gjimnaz', 10, 12),
  upperSecondaryArtistic('Shkollë Artistike', 10, 12),
  upperSecondarySports('Shkollë Sportive', 10, 12),
  upperSecondaryLanguage('Shkollë me Drejtim Gjuhësor', 10, 12),
  upperSecondaryVocational('Shkollë Profesionale', 10, 13),
  upperSecondaryMadrasah('Medrese', 10, 12);

  const EducationLevel(this.albanianName, this.minGrade, this.maxGrade);
  final String albanianName;
  final int minGrade;
  final int maxGrade;
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

```dart
// User profile — stored in SharedPreferences (key/value)
// Keys:
//   education_level       : String (EducationLevel.name)
//   school_type           : String? (null for primary / lower secondary)
//   current_grade         : int (1–13)
//   student_name          : String
//   daily_q_count         : int
//   last_reset_date       : String ('yyyy-MM-dd')
//   current_streak        : int
//   last_active_date      : String ('yyyy-MM-dd')
//   is_premium            : bool
//   gemini_calls_today    : int
//   gemini_pro_calls_today: int
//   vocational_years      : int (2 | 3 | 4, default 3 — vocational only)

// Question entity — persisted via Drift (SQLite)
@DataClassName('Question')
class Questions extends Table {
  TextColumn get id => text()();                        // UUID
  TextColumn get educationLevel => text()();            // EducationLevel.name
  TextColumn get subjectId => text()();
  TextColumn get examMode => text().nullable()();       // 'VANAF' | 'PKAB' | 'MATURA' | null
  TextColumn get questionText => text()();
  TextColumn get answerText => text()();
  BoolColumn get isPhotoQuestion => boolean().withDefault(const Constant(false))();
  TextColumn get ocrExtractedText => text().nullable()();
  TextColumn get modelUsed => text()();                 // 'gemini-flash' | 'groq-llama' | 'gemini-pro'
  IntColumn get timestamp => integer()();               // Unix ms
  BoolColumn get isSaved => boolean().withDefault(const Constant(false))();
}

// Exam question entity — persisted via Drift (SQLite)
@DataClassName('ExamQuestion')
class ExamQuestions extends Table {
  TextColumn get id => text()();
  TextColumn get examType => text()();                  // 'VANAF' | 'PKAB' | 'MATURA'
  TextColumn get schoolType => text().nullable()();     // null for VANAF/PKAB
  TextColumn get subjectId => text()();
  IntColumn get year => integer()();
  TextColumn get questionText => text()();
  TextColumn get officialAnswer => text()();
  TextColumn get aiExplanation => text().nullable()();  // cached after first AI call
}

// VKM calculation — in-memory only, no persistence
class VkmCalculation {
  final EducationLevel schoolType;
  final int vocationalYears;           // 2, 3, or 4 for vocational; always 3 for general
  final List<double> annualAverages;   // one per year, e.g. [8.2, 8.5, 8.8]
  final List<double> maturaExamGrades; // always 4 grades

  const VkmCalculation({
    required this.schoolType,
    required this.vocationalYears,
    required this.annualAverages,
    required this.maturaExamGrades,
  });

  double get mSchool =>
      annualAverages.isEmpty ? 0 : annualAverages.reduce((a, b) => a + b) / annualAverages.length;

  double get mMatura =>
      maturaExamGrades.isEmpty ? 0 : maturaExamGrades.reduce((a, b) => a + b) / maturaExamGrades.length;

  double get mVkm => (mSchool + mMatura) / 2;
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

# 6. BUILDER INSTRUCTIONS — GITHUB COPILOT AGENT (FLUTTER)

> Paste each block into GitHub Copilot Agent Mode in order.
> Copilot Agent works best when given one focused task at a time.
> Always commit after each agent session before starting the next.

---

## 6.1 Pre-Build Checklist

- [ ] **Flutter SDK** installed — `flutter doctor` passes (no errors)
- [ ] **Dart SDK** ≥ 3.3.0 (bundled with Flutter 3.22+)
- [ ] **Android Studio** or **VS Code** with Flutter + Dart extensions
- [ ] **GitHub Copilot** subscription active + Agent Mode enabled in VS Code (`"github.copilot.chat.agent.enabled": true`)
- [ ] **Google AI Studio API key** (aistudio.google.com — free, no credit card)
- [ ] **Groq API key** (console.groq.com — free, no credit card)
- [ ] Both keys curl-tested before starting
- [ ] Android emulator ready (API 26+) **or** real Android device in developer mode

---

## 6.2 Project Scaffold — Copilot Agent Prompt 1

Open a new empty folder in VS Code. Open Copilot Chat → switch to **Agent Mode**. Paste:

```
Create a new Flutter project called "mesues_ai" with package name com.gamingrepo.mesuesai.

Use the following project structure:
lib/
  main.dart
  app.dart                        # MaterialApp + GoRouter setup
  core/
    constants/
      app_colors.dart             # Full color palette from theme specs
      app_text_styles.dart        # Typography scale (Inter font)
      app_spacing.dart            # 4dp base unit spacing constants
      api_constants.dart          # Endpoint URLs, model names
    models/
      education_level.dart        # EducationLevel enum (Dart)
      question.dart               # Question model
      exam_question.dart          # ExamQuestion model
      vkm_calculation.dart        # VkmCalculation class
      subject.dart                # Subject model (id, name, icon, color)
    services/
      gemini_service.dart         # Gemini Flash + Pro API calls
      groq_service.dart           # Groq Llama 4 Maverick fallback
      ocr_service.dart            # ML Kit wrapper
      limit_service.dart          # Daily limit + counter logic
    database/
      app_database.dart           # Drift database class
      app_database.g.dart         # (generated)
      daos/
        question_dao.dart
        exam_question_dao.dart
    providers/
      user_profile_provider.dart  # Riverpod: SharedPreferences user state
      chat_provider.dart          # Riverpod: chat state + AI call logic
      exam_provider.dart          # Riverpod: exam questions
      limit_provider.dart         # Riverpod: daily counters
  features/
    onboarding/
      onboarding_screen.dart
      widgets/
        level_selector_card.dart
        school_type_chip.dart
    home/
      home_screen.dart
      widgets/
        subject_grid.dart
        subject_card.dart
        streak_badge.dart
        daily_progress_bar.dart
        quick_access_row.dart
    chat/
      chat_screen.dart
      widgets/
        chat_bubble.dart
        chat_input_bar.dart
        thinking_indicator.dart
        daily_counter_chip.dart
        ocr_preview_sheet.dart
    exams/
      exams_screen.dart
      vanaf_section.dart
      pkab_section.dart
      matura_section.dart
      widgets/
        exam_prep_card.dart
        vkm_calculator.dart
        vkm_result_card.dart
    profile/
      profile_screen.dart
      widgets/
        stats_row.dart
        fletore_section.dart
        premium_bottom_sheet.dart
  shared/
    widgets/
      app_snackbar.dart
      paywall_bottom_sheet.dart
      loading_dots.dart

pubspec.yaml dependencies:
  flutter_riverpod: ^2.5.1
  riverpod_annotation: ^2.3.5
  go_router: ^13.2.0
  drift: ^2.18.0
  sqlite3_flutter_libs: ^0.5.24
  path_provider: ^2.1.2
  path: ^1.9.0
  shared_preferences: ^2.2.3
  http: ^1.2.1
  google_mlkit_text_recognition: ^0.13.0
  camera: ^0.10.5+9
  image_picker: ^1.1.2
  google_fonts: ^6.2.1
  permission_handler: ^11.3.1
  flutter_dotenv: ^5.1.0
  uuid: ^4.4.0

dev_dependencies:
  drift_dev: ^2.18.0
  build_runner: ^2.4.9
  riverpod_generator: ^2.4.0
  flutter_lints: ^3.0.0

assets:
  - .env

Generate the full pubspec.yaml, the folder structure with empty placeholder files,
and the AndroidManifest.xml with camera + internet permissions.
```

---

## 6.3 Theme & Constants — Copilot Agent Prompt 2

```
Implement the full theme system for Mësues AI in Flutter Material 3.

FILE: lib/core/constants/app_colors.dart
Define these as static const Color values:
  primary        = Color(0xFF1A237E)
  primaryLight   = Color(0xFF534BAE)
  primaryDark    = Color(0xFF000051)
  secondary      = Color(0xFFFFC107)
  secondaryDark  = Color(0xFFFF8F00)
  background     = Color(0xFFF5F7FF)
  surface        = Color(0xFFFFFFFF)
  onSurface      = Color(0xFF1A1A2E)
  subtleText     = Color(0xFF6B7280)
  success        = Color(0xFF2E7D32)
  warning        = Color(0xFFFF8F00)
  error          = Color(0xFFC62828)
  info           = Color(0xFF0277BD)
  examVANAF      = Color(0xFF00695C)
  examPKAB       = Color(0xFF1565C0)
  examMatura     = Color(0xFF880E4F)
  vkm            = Color(0xFF4A148C)

  // Dark mode overrides
  darkBackground = Color(0xFF0D0F1A)
  darkSurface    = Color(0xFF1A1D2E)
  darkOnSurface  = Color(0xFFE8EAF6)
  darkSubtle     = Color(0xFF9EA7C4)
  darkAiBubble   = Color(0xFF252840)
  darkCard       = Color(0xFF1E2137)

  // Subject colors
  subjectAlbanian  = Color(0xFF880E4F)
  subjectMath      = Color(0xFF1565C0)
  subjectPhysics   = Color(0xFF6A1B9A)
  subjectChemistry = Color(0xFF2E7D32)
  subjectHistory   = Color(0xFFBF360C)
  subjectBiology   = Color(0xFF00695C)
  subjectGeography = Color(0xFF827717)
  subjectCivic     = Color(0xFF4A148C)
  subjectEconomics = Color(0xFF1B5E20)
  subjectArtHistory= Color(0xFFE65100)
  subjectICT       = Color(0xFF0277BD)

FILE: lib/core/constants/app_text_styles.dart
Use GoogleFonts.inter() for all styles. Define a TextStyles class with static getters:
  displayLarge  — 32sp Bold
  headlineLarge — 28sp SemiBold
  headlineMedium— 24sp SemiBold
  titleLarge    — 22sp Medium
  titleMedium   — 18sp Medium
  bodyLarge     — 16sp Regular
  bodyMedium    — 14sp Regular
  labelLarge    — 14sp Medium
  labelMedium   — 12sp Medium
  labelSmall    — 11sp Regular
Line height: 1.5x for body, 1.2x for headings.

FILE: lib/core/constants/app_spacing.dart
  xs = 4.0, sm = 8.0, md = 12.0, lg = 16.0,
  xl = 20.0, xxl = 24.0, xxxl = 32.0, xxxxl = 48.0

FILE: lib/app.dart
Create the MaterialApp.router with:
  - ThemeData using Material 3, ColorScheme.fromSeed(seedColor: AppColors.primary)
  - Dark theme using dark overrides above
  - Inter font via GoogleFonts.interTextTheme()
  - GoRouter with routes: /onboarding, / (home), /chat, /exams, /profile
  - ScaffoldWithNavBar shell route for bottom navigation (4 tabs)
  - Redirect: if SharedPreferences has no education_level → /onboarding
```

---

## 6.4 Data Layer — Copilot Agent Prompt 3

```
Implement the full data layer for Mësues AI.

FILE: lib/core/models/education_level.dart
Dart enum EducationLevel with values:
  primary('Arsimi Fillor', 1, 5),
  lowerSecondary('Arsimi i Mesëm i Ulët (9-vjeçar)', 6, 9),
  upperSecondaryGeneral('Gjimnaz', 10, 12),
  upperSecondaryArtistic('Shkollë Artistike', 10, 12),
  upperSecondarySports('Shkollë Sportive', 10, 12),
  upperSecondaryLanguage('Shkollë me Drejtim Gjuhësor', 10, 12),
  upperSecondaryVocational('Shkollë Profesionale', 10, 13),
  upperSecondaryMadrasah('Medrese', 10, 12);
Fields: albanianName, minGrade, maxGrade.
Helper: bool get isUpperSecondary => minGrade >= 10;

FILE: lib/core/database/app_database.dart
Drift database with two tables:

Table: questions
  id TEXT PK | educationLevel TEXT | subjectId TEXT | examMode TEXT nullable
  questionText TEXT | answerText TEXT | isPhotoQuestion BOOL default false
  ocrExtractedText TEXT nullable | modelUsed TEXT | timestamp INTEGER | isSaved BOOL default false

Table: exam_questions
  id TEXT PK | examType TEXT | schoolType TEXT nullable | subjectId TEXT
  year INTEGER | questionText TEXT | officialAnswer TEXT | aiExplanation TEXT nullable

Seed exam_questions on first open using a DatabaseInitializer class with at least:
  - 10 Gjimnaz Matura questions (3 Matematikë, 3 Gjuhë-Letërsi, 2 Fizikë, 2 Histori; years 2022–2025)
  - 5 PKAB Matematikë (Grades 6–9)
  - 5 PKAB Gjuhë Shqipe (reading + grammar)
  - 5 VANAF Matematikë (Grades 1–5)
  - 5 VANAF Dituri Natyre (Grade 5 science)
  - 5 Shkollë Profesionale Matematikë (lighter syllabus, practical)
  - 3 Shkollë Artistike Histori Arti

FILE: lib/core/services/limit_service.dart
Using SharedPreferences:
  - dailyQuestionCount getter/increment
  - lastResetDate getter
  - geminiCallsToday getter/increment
  - geminiProCallsToday getter/increment
  - checkAndResetIfNewDay() — compare stored date to DateTime.now(); reset all counters if different date
  - isPremium getter/setter
Call checkAndResetIfNewDay() in main.dart before runApp().

FILE: lib/core/providers/user_profile_provider.dart
Riverpod AsyncNotifier backed by SharedPreferences.
Expose: educationLevel, schoolType, currentGrade, studentName, currentStreak,
        dailyQCount, isPremium, vocationalYears.
Method: updateProfile(educationLevel, schoolType, grade, name).
Method: incrementStreak() — compares lastActiveDate to today; increments if yesterday, resets if gap.
```

---

## 6.5 AI Services — Copilot Agent Prompt 4

```
Implement the AI service layer for Mësues AI.

Load API keys from flutter_dotenv (.env file at root):
  GEMINI_API_KEY=your_key_here
  GROQ_API_KEY=your_key_here

FILE: lib/core/constants/api_constants.dart
  geminiFlashEndpoint = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent'
  geminiProEndpoint   = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-pro:generateContent'
  groqEndpoint        = 'https://api.groq.com/openai/v1/chat/completions'
  groqModel           = 'llama-4-maverick'

FILE: lib/core/services/gemini_service.dart
Method: Future<String> askFlash(String systemPrompt, String userMessage, {List<Map> history = const []})
  - POST to geminiFlashEndpoint with API key in URL param
  - Body: { "system_instruction": {"parts":[{"text": systemPrompt}]},
            "contents": [...history, {"role":"user","parts":[{"text": userMessage}]}] }
  - Timeout: 10 seconds
  - On 429: throw RateLimitException
  - On timeout: throw NetworkException
  - Return response text from candidates[0].content.parts[0].text

Method: Future<String> askPro(String systemPrompt, String userMessage, {List<Map> history = const []})
  - Same as askFlash but uses geminiProEndpoint
  - Wrap in try/catch; on any error re-throw as GeminiProException

FILE: lib/core/services/groq_service.dart
Method: Future<String> ask(String systemPrompt, String userMessage, {List<Map> history = const []})
  - POST to groqEndpoint with Bearer token header
  - Body: OpenAI-compatible format:
    { "model": groqModel,
      "messages": [{"role":"system","content": systemPrompt}, ...history,
                   {"role":"user","content": userMessage}] }
  - Timeout: 10 seconds
  - Return choices[0].message.content

FILE: lib/core/providers/chat_provider.dart
Riverpod StateNotifier managing:
  - List<ChatMessage> messages (user + AI bubbles)
  - bool isLoading
  - String currentSubjectId
  - List<Map> conversationHistory  // last 6 pairs only

Method: Future<void> sendMessage(String text, {required UserProfile profile})
  1. Check daily_q_count >= 10 → emit PaywallState
  2. Add user message to messages list
  3. Build system prompt using canonical prompts from SSOT 3.2
     Inject [LEVEL] = profile.educationLevel.albanianName + ' Klasa ' + profile.currentGrade
     Inject [SCHOOL_TYPE] = profile.schoolType?.albanianName ?? 'N/A'
     Inject [SUBJECT] = currentSubjectId → subject Albanian name
  4. If gemini_calls_today < 230 → geminiService.askFlash(...)
     Else → groqService.ask(...)
  5. On success: add AI message, increment counters, save to Drift DB, trim history to 6 pairs
  6. On RateLimitException: silently switch to Groq, retry once
  7. On NetworkException: show Snackbar 'Nuk ka lidhje interneti. Kontrolloni wifi.'
  8. On any other error: show Snackbar 'Gabim i serverit. Provo pas pak çastesh.'

Method: Future<void> sendPhotoMessage(String ocrText, {required UserProfile profile})
  - Same flow as sendMessage but uses SSOT OCR prompt wrapper
  - prepend 'Nxënësi ka fotografuar detyrën...' before ocrText

Keep conversationHistory as List<Map> with {role, content} for each pair.
Reset conversationHistory when subject changes.
```

---

## 6.6 OCR + Camera — Copilot Agent Prompt 5

```
Implement the photo question flow for Mësues AI.

FILE: lib/core/services/ocr_service.dart
Using google_mlkit_text_recognition:
  Method: Future<OcrResult> extractText(XFile imageFile)
    1. Load image bytes, compute size — if > 5MB compress via resizeImage()
    2. Create InputImage.fromFilePath(imageFile.path)
    3. TextRecognizer(script: TextRecognitionScript.latin).processImage(inputImage)
    4. Extract visionText.text (all blocks joined)
    5. If result.length < 50 → return OcrResult.lowConfidence
    6. If result.length > 8000 → truncate to 8000 chars, flag as truncated
    7. Return OcrResult.success(text, wasTruncated)

  Method: Future<XFile> resizeImage(XFile file)
    Use dart:ui Image + ByteData to decode, resize to max 1024×1024, re-encode as JPEG quality 85.
    Return as XFile. Use path_provider for temp directory.

class OcrResult { final String? text; final bool isLowConfidence; final bool wasTruncated; }

FILE: lib/features/chat/widgets/ocr_preview_sheet.dart
A DraggableScrollableSheet shown after OCR completes:
  - Title: 'Kontrolloni tekstin e nxjerrë'
  - If wasTruncated: amber Banner 'Teksti u shkurtua (>8000 karaktere)'
  - EditableText (multiline TextFormField) pre-filled with ocrText
  - "Dërgo" button → calls chat_provider.sendPhotoMessage(editedText, profile: ...)
  - "Anulo" button → dismisses sheet
  - Show warning Snackbar if text < 50 chars: 'Fotoja nuk është e qartë. Provo sërish.'

FILE: lib/features/chat/widgets/chat_input_bar.dart
Row with:
  - Left: IconButton(Icons.photo_camera_rounded) → opens camera
  - Center: Expanded TextField 'Shkruaj pyetjen tënde...'
  - Right: IconButton(Icons.send_rounded) enabled only when text not empty
    (PRIMARY color when enabled, subtleText when disabled)
  - Camera tap flow:
    1. Request camera permission via permission_handler
    2. ImagePicker().pickImage(source: ImageSource.camera)
    3. Call ocrService.extractText(pickedFile)
    4. If lowConfidence → show Snackbar + return
    5. Else → show OcrPreviewSheet(ocrText)
```

---

## 6.7 Screens — Copilot Agent Prompt 6

```
Implement all 4 main screens for Mësues AI. 
Reference all widget specs from Section 5 (theme specs) and Section 3.6 (navigation).

ONBOARDING SCREEN (lib/features/onboarding/onboarding_screen.dart)
PageView with 4 pages, progress dots, "Tjetër" / "Përfundo" buttons:
  Page 1: 'Cilës nivel i përket?' — 3 LevelSelectorCards (Arsimi Fillor / 9-vjeçar / I Mesëm i Lartë)
  Page 2: 'Çfarë klasë/vit ndjekon?' — NumberPicker wheel (range from selected level)
  Page 3 (upper secondary only): 'Çfarë lloji shkolle ndjek?' — 6 SchoolTypeChips
    If Profesionale selected: show 3 Radio tiles (2 vjet / 3 vjet / 4 vjet)
  Page 4: 'Si të quajmë?' — TextField, "Përfundo" → save all to SharedPreferences → GoRouter.go('/')

HOME SCREEN (lib/features/home/home_screen.dart)
  - Greeting: 'Mirëdita, [name]! 👋' (headlineLarge)
  - Level badge: '[EducationLevel] — Klasa [N]' (chip, PRIMARY fill)
  - StreakBadge widget: '🔥 [N] ditë' amber pill
  - DailyProgressBar: '[N]/10 pyetje sot' — color: success(<8) / warning(8-9) / error(10)
  - SubjectGrid: GridView.builder, 2 columns. Cards show subject icon + Albanian name + colored left border.
    Subjects shown depend on education_level (see SSOT 3.5). Tap → GoRouter.go('/chat') + set subject.
  - QuickAccessRow: horizontal chips. Show correct chip based on grade:
    Grade 5 → '🌿 Përgatitu për VANAF'  | Grade 9 → '📋 Përgatitu për PKAB'
    Grade 10-13 → '🎓 Matura 2025'      | '⭐ Pyetjet e ruajtura' always shown
    Taps navigate to /exams or /profile (Fletore)

CHAT SCREEN (lib/features/chat/chat_screen.dart)
  - AppBar: subject dropdown (left), level chip (center), counter chip (right), camera icon
  - ListView.builder of ChatBubble widgets (user: PRIMARY right-aligned; AI: white card left-aligned)
  - AI avatar emoji: 🧒 primary | 📚 secondary | 🎓 upper secondary
  - ThinkingIndicator: 3 animated dots (600ms pulse, 200ms stagger between dots)
  - ChatInputBar at bottom (see Prompt 6 widget)
  - On limit hit (10/day): show PaywallBottomSheet — DO NOT send API call
  - Use watch(chatProvider) for reactive rebuilds

EXAMS SCREEN (lib/features/exams/exams_screen.dart)
  3 expandable sections driven by education_level:
  VANAF section: teal header, subject chips, LazyColumn of ExamPrepCards, info collapsible
  PKAB section:  blue header, subject chips, LazyColumn with Gjuhë Shqipe + Matematikë + Gjuhë e Huaj cards
  MATURA section: burgundy header, school_type badge, 3 sub-tabs:
    [Pyetje Praktike]: track-aware question cards, Year filter 2021–2025
    [Llogaritësi VKM]: VkmCalculator widget — 100% on-device, zero API calls
    [Programet Orientuese]: external link cards to arsimi.gov.al
  Auto-expand relevant section based on education_level. All sections remain accessible.
  Each ExamPrepCard: 'Shiko Shpjegimin' button → call geminiPro (if < 40) else geminiFlash
    → cache result in Drift exam_questions.aiExplanation
    → never call API if aiExplanation already non-null in DB

PROFILE SCREEN (lib/features/profile/profile_screen.dart)
  - Avatar circle with initials (PRIMARY bg)
  - Name + level badge
  - Premium badge OR 'Kaloni Premium' CTA → PaywallBottomSheet
  - '🔥 [N] ditë radhazi'
  - '✏️ Ndrysho profilin' → bottom sheet: edit name, level, grade, school type
  - StatsRow: 3 cards — total questions | favorite subject | this week count
  - FletoreSection: toggle chips (Të gjitha / by subject / VANAF / PKAB / Matura)
    LazyColumn of saved Question cards from Drift DB where isSaved = true
  - Settings gear icon (AppBar trailing): dark mode toggle, reset counter (debug), app version,
    'Rreth nesh' → 'Gaming Repository — Fier, Albania'

VKMCALCULATOR WIDGET (lib/features/exams/widgets/vkm_calculator.dart)
  - Read vocationalYears + schoolType from UserProfile provider
  - Render N annual grade inputs (N = 3 for general, 2/3/4 for vocational)
    Labels: 'Klasa 10 (nota mesatare)', 'Klasa 11...', etc.
  - 4 Matura exam inputs labeled by exam type (see SSOT for track-aware labels)
  - All inputs: TextFormField, inputType: number, decimal keyboard
  - On any change: recompute VkmCalculation on-device, update VkmResultCard
  - VkmResultCard: animated counter (800ms ease-out), color-coded:
    ≥ 7.0: success green | 6.0–6.99: warning amber | < 6.0: error red
  - Formula disclaimer: Label Small, subtleText color

PAYWALL BOTTOM SHEET (lib/shared/widgets/paywall_bottom_sheet.dart)
  - 'Mësuesi juaj AI, pa kufizime'
  - Feature list (5 bullet points)
  - Monthly: '500 ALL / muaj'
  - Yearly: '4,000 ALL / vit — Kurseni 33%!'
  - 'Pagesa me para në dorë — kontaktoni: [WhatsApp number]'
  - WhatsApp deep link opens wa.me/[number]
```

---

## 6.8 Animations & Polish — Copilot Agent Prompt 7

```
Add animations and final polish to Mësues AI.

1. THINKING INDICATOR (lib/shared/widgets/loading_dots.dart)
   3 dots animating with AnimationController (600ms repeat).
   Each dot staggered by 200ms using Interval curves.
   Dot color: AppColors.subtleText. Size: 8dp. Gap: 6dp.

2. SCREEN TRANSITIONS in GoRouter:
   All routes use CustomTransitionPage with:
     transitionDuration: Duration(milliseconds: 300)
     FadeTransition + SlideTransition(Offset(0, 0.05) → Offset.zero)

3. SUBJECT CARD PRESS ANIMATION:
   Wrap each SubjectCard in GestureDetector + AnimatedScale:
     onTapDown: scale to 0.97 (150ms curve: Curves.easeOut)
     onTapUp/Cancel: scale back to 1.0 (150ms)

4. STREAK BADGE INCREMENT:
   When streak changes, animate with AnimatedScale: 1.0 → 1.3 → 1.0
   Duration: 400ms. Curve: Curves.elasticOut.

5. AI BUBBLE APPEARANCE:
   Each new AI chat bubble slides up + fades in:
     FadeTransition + SlideTransition(Offset(0, 0.08) → Offset.zero)
     Duration: 250ms. Curve: Curves.easeOut.

6. VKM RESULT COUNTER:
   Use TweenAnimationBuilder<double> with 800ms Curves.easeOut
   to animate M_VKM from previous value to new value.

7. HAPTIC FEEDBACK:
   - Message send: HapticFeedback.lightImpact()
   - Streak increment: HapticFeedback.mediumImpact()
   - Paywall trigger: HapticFeedback.heavyImpact()

8. SKELETON LOADERS:
   While subject grid is loading from SharedPreferences:
   Show shimmer-like animated containers (use AnimatedContainer with opacity cycle 0.3→0.7)
   for each expected subject card slot.
```

---

## 6.9 Environment & Security Setup

```
FILE: .env (root — NEVER commit to git)
  GEMINI_API_KEY=your_gemini_key_here
  GROQ_API_KEY=your_groq_key_here

FILE: .gitignore — ensure these lines are present:
  .env
  *.env
  .dart_tool/
  build/
  .flutter-plugins
  .flutter-plugins-dependencies

FILE: lib/main.dart
  void main() async {
    WidgetsFlutterBinding.ensureInitialized();
    await dotenv.load(fileName: '.env');
    await LimitService.instance.checkAndResetIfNewDay();
    runApp(ProviderScope(child: MesuesAiApp()));
  }

Access keys everywhere via:
  dotenv.env['GEMINI_API_KEY']!
  dotenv.env['GROQ_API_KEY']!

NEVER hardcode keys in source files. NEVER commit .env.
```

---

## 6.10 Post-Build Verification Checklist

1. `flutter analyze` — zero errors, zero warnings
2. `flutter test` — all unit tests pass (write tests for VkmCalculation, LimitService, OcrResult)
3. Run on emulator — cold start < 2s
4. Test OCR on a real printed Albanian math problem photo
5. Test VKM calculator with Example A and Example B from Section 1.4 — must match exactly
6. Test daily limit: ask 10 questions → paywall appears; change device date → counter resets
7. Test Gemini → Groq fallback: set Gemini buffer to 229 manually → next question uses Groq
8. Test all 6 school types on onboarding — correct subjects shown on Home
9. Test dark mode (system toggle)
10. Verify `.env` is in `.gitignore` before first push

---

# 7. SPRINT TRACKER

## Sprint 0 — Setup (Current)
**Goal:** Flutter project scaffolded, keys working, onboarding renders correctly

| Task | Status | Notes |
|------|--------|-------|
| Scaffold Flutter project with Copilot Agent Prompt 1 | ⬜ Todo | Use Section 6.2 |
| Add `.env` with API keys, add to `.gitignore` | ⬜ Todo | Gemini + Groq — never commit |
| Run `flutter pub get` + `flutter pub run build_runner build` | ⬜ Todo | Generates Drift + Riverpod code |
| Verify Gemini Flash responds in Albanian (level-aware) | ⬜ Todo | Test with Gr 5 and Gr 12 prompts |
| Verify Groq Llama responds in Albanian | ⬜ Todo | |
| Onboarding flow renders and saves to SharedPreferences | ⬜ Todo | Test all 6 school types |
| First `flutter run` on emulator succeeds | ⬜ Todo | |
| Git repo initialized (private), first commit pushed | ⬜ Todo | |

## Sprint 1 — Core AI Chat (Week 1)
**Goal:** Working Albanian AI tutor, text only, level-aware

| Task | Status | Acceptance Criteria |
|------|--------|-------------------|
| Chat screen renders | ⬜ | Bubbles, input, level chip render in Flutter |
| Level + school type passed to system prompt | ⬜ | AI adapts to grade 5 vs grade 12 |
| Gemini Flash API call works | ⬜ | Albanian response in < 5s |
| Daily limit counter works | ⬜ | Stops at 10, resets midnight (SharedPreferences) |
| Groq fallback at 230 | ⬜ | Seamless switch |
| Paywall at limit | ⬜ | Shows PaywallBottomSheet |
| Errors as SnackBar | ⬜ | No crashes |

**DoD:** 5th-grader can ask a Dituri Natyre question AND a 12th-grader can ask a Matura Matematikë question, each getting correctly pitched answers.

## Sprint 2 — Photo + Home + VANAF/PKAB (Week 2)
**Goal:** Camera flow + VANAF and PKAB exam prep working

| Task | Status | Acceptance Criteria |
|------|--------|-------------------|
| camera plugin + ML Kit OCR | ⬜ | Text extracted from printed problem |
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
| Gemini Pro explanations + cache (Drift DB) | ⬜ | No duplicate API calls |
| VKM Calculator — Gjimnaz (3yr) | ⬜ | Matches manual formula |
| VKM Calculator — Vocational (2/3/4yr) | ⬜ | Dynamic year fields |
| VKM Calculator — live update | ⬜ | Updates on every keystroke |
| VKM result color coding | ⬜ | Green/amber/red thresholds |
| Pro fallback banner | ⬜ | Shows when Pro limit hit |
| Profile stats accurate | ⬜ | Correct totals from Drift DB |
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
- Flutter project scaffolded (`flutter create mesues_ai --org com.gamingrepo`)
- `pubspec.yaml` configured with all dependencies (Riverpod, Drift, go_router, ML Kit, etc.)
- `build_runner` run, Drift + Riverpod generated code committed
- `.env` configured with API keys; `.gitignore` verified
- Base theme applied (Inter font via google_fonts, full AppColors palette, Material 3)
- GoRouter setup with shell route (bottom nav) + onboarding redirect
- SharedPreferences-backed LimitService initialized on app start

## v0.2.0 — Core Chat
**Sprint:** 1 | **Date:** *(fill when Sprint 1 complete)*
- Gemini 2.5 Flash integration via `http` package with level-aware Albanian system prompt
- Groq Llama 4 Maverick fallback (auto at 230 Gemini calls/day)
- Daily question limit (10/day) with midnight reset via SharedPreferences
- Education level + school type injected into every API call
- Paywall bottom sheet (manual WhatsApp payment)
- Error SnackBars (network timeout, 429, 500)

## v0.3.0 — Photo + Home + Exam Prep Foundations
**Sprint:** 2 | **Date:** *(fill when Sprint 2 complete)*
- Flutter `camera` plugin + `google_mlkit_text_recognition` (on-device OCR, latin script)
- Bitmap compression (5MB limit) via dart:ui image resize before ML Kit processing
- Editable OCR preview sheet (`DraggableScrollableSheet`) before sending to AI
- Dynamic Home subject grid (adapts to education_level + school_type via Riverpod)
- VANAF section: 15+ seeded questions in Drift DB, Gemini Flash explanations cached
- PKAB section: 15+ seeded questions (Gjuhë Shqipe, Matematikë, Gjuhë e Huaj)
- Streak badge + daily progress bar with color thresholds

## v0.4.0 — Matura + VKM + Profile
**Sprint:** 3 | **Date:** *(fill when Sprint 3 complete)*
- Full Matura prep screen — all 6 school types (Gjimnaz, Profesionale, Artistike, Sportive, Gjuhësor, Medrese)
- Track-aware exam questions and subject labels
- VKM Calculator: Gjimnaz (3yr) and Vocational (2/3/4yr) — 100% on-device (`VkmCalculation` Dart class)
- VKM animated result card with TweenAnimationBuilder, color-coded + disclaimer
- Gemini 2.5 Pro for Matura + PKAB explanations (cached in Drift DB `exam_questions.ai_explanation`)
- Gemini Pro fallback to Flash when daily Pro limit hit
- Fletore notebook with VANAF/PKAB/Matura filter chips (Drift DB query)
- Profile stats + level/school type edit bottom sheet
- Dark mode support (system-follows, Material 3 dynamic color)

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
*Last updated: May 2026 | Version: 3.0 (Pre-build, Flutter migration, GitHub Copilot Agent)*
*Sources: Eurydice Albania (March 2026), QSHA/ASCAP, arsimi.gov.al, maturashteterore.com*
