<div align="center">

# 🎓 Mësues AI

### Mësuesi yt AI — gjithmonë i disponueshëm
**Your AI Tutor — always available**

[![Flutter](https://img.shields.io/badge/Flutter-3.22+-02569B?style=flat&logo=flutter&logoColor=white)](https://flutter.dev)
[![Dart](https://img.shields.io/badge/Dart-3.3+-0175C2?style=flat&logo=dart&logoColor=white)](https://dart.dev)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat&logo=android&logoColor=white)](https://play.google.com/store)
[![Status](https://img.shields.io/badge/Status-Pre--build%20Sprint%200-orange?style=flat)]()
[![Made in Albania](https://img.shields.io/badge/Made%20in-Albania%20🇦🇱-CC0000?style=flat)]()

**Built by [Gaming Repository](https://github.com/gamingrepo) · Fier, Albania**

</div>

---

> Legacy Android/Kotlin module has been preserved under `legacy_android/` while the main app is converted to Flutter.

## 📖 About

**Mësues AI** is a free Albanian-language AI tutoring app for all pre-university students — from **Grade 1 through Grade 13**, across every school track in the Albanian education system.

Private tutors in Albania cost **3,000–5,000 ALL per hour**. Students outside Tiranë — in Fier, Berat, Gjirokastër, Korçë, Shkodër — have limited access to quality academic support. Mësues AI changes that.

> Ask any academic question in Albanian. Get a step-by-step answer instantly. Prepare for VANAF, PKAB, or Matura. Calculate your VKM. All for free.

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 💬 **AI Tutor Chat** | Ask questions in Albanian across all subjects. AI adapts to your grade level and school track. |
| 📷 **Homework Photo** | Snap a photo of your homework. On-device OCR (ML Kit) extracts text — no image sent to cloud. |
| 🌿 **VANAF Prep** | Practice questions for the Grade 5 national assessment (Gjuhë Shqipe, Matematikë, Dituri Natyre). |
| 📋 **PKAB Prep** | Preparation for the Grade 9 national exam — 3 subjects, 50 pts each. |
| 🎓 **Matura Prep** | Track-aware exam prep for all 6 school types: Gjimnaz, Profesionale, Artistike, Sportive, Gjuhësor, Medrese. |
| 🧮 **VKM Calculator** | Calculate your university admission average (M_VKM) on-device. Supports Gjimnaz (3yr) and Vocational (2/3/4yr). |
| 📚 **Fletore** | Save any answer to your personal notebook. Filter by subject or exam type. |
| 🔥 **Streak Tracker** | Daily study streak to keep you consistent. |
| 🌙 **Dark Mode** | Full dark mode support. |

---

## 🏫 Supported School Tracks

| Track | Grades | Exam |
|-------|--------|------|
| Arsimi Fillor (Primary) | 1–5 | VANAF (Grade 5) |
| Arsimi 9-vjeçar (Lower Secondary) | 6–9 | PKAB (Grade 9) |
| Gjimnaz (General High School) | 10–12 | Matura Shtetërore |
| Shkollë Artistike | 10–12 | Matura (artistic syllabus) |
| Shkollë Sportive | 10–12 | Matura (sport syllabus) |
| Shkollë me Drejtim Gjuhësor | 10–12 | Matura (language-oriented) |
| Shkollë Profesionale (Vocational) | 10–13 | Matura Shtetërore Profesionale |
| Medrese | 10–12 | Matura Shtetërore (secular subjects) |

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Framework** | [Flutter](https://flutter.dev) 3.22+ |
| **Language** | Dart 3.3+ |
| **State Management** | [Riverpod](https://riverpod.dev) 2.x |
| **Navigation** | [go_router](https://pub.dev/packages/go_router) |
| **Local Database** | [Drift](https://drift.simonbinder.eu/) (SQLite) |
| **Preferences** | [shared_preferences](https://pub.dev/packages/shared_preferences) |
| **Networking** | [http](https://pub.dev/packages/http) |
| **OCR (on-device)** | [google_mlkit_text_recognition](https://pub.dev/packages/google_mlkit_text_recognition) |
| **Camera** | [camera](https://pub.dev/packages/camera) + [image_picker](https://pub.dev/packages/image_picker) |
| **Fonts** | [google_fonts](https://pub.dev/packages/google_fonts) (Inter) |
| **Env** | [flutter_dotenv](https://pub.dev/packages/flutter_dotenv) |
| **AI — Primary** | Gemini 2.5 Flash (Google AI Studio — free tier) |
| **AI — Reasoning** | Gemini 2.5 Pro (Google AI Studio — free tier, limited) |
| **AI — Fallback** | Llama 4 Maverick via [Groq](https://groq.com) (free tier) |
| **Agent** | GitHub Copilot (Agent Mode) |

---

## 🏗️ Architecture

```
lib/
├── main.dart                    # App entry, dotenv load, limit reset
├── app.dart                     # MaterialApp + GoRouter shell
├── core/
│   ├── constants/               # Colors, typography, spacing, API endpoints
│   ├── models/                  # EducationLevel enum, Question, ExamQuestion, VkmCalculation
│   ├── services/                # GeminiService, GroqService, OcrService, LimitService
│   ├── database/                # Drift DB, DAOs, seeder
│   └── providers/               # Riverpod: UserProfile, Chat, Exam, Limit
├── features/
│   ├── onboarding/              # 4-step level/school/grade/name setup
│   ├── home/                    # Subject grid, streak, quick access
│   ├── chat/                    # AI chat, OCR flow, paywall
│   ├── exams/                   # VANAF / PKAB / Matura / VKM Calculator
│   └── profile/                 # Stats, Fletore, premium, settings
└── shared/
    └── widgets/                 # SnackBar, PaywallSheet, LoadingDots
```

**Pattern:** MVVM + Repository · Riverpod providers as ViewModels · Drift DAOs as Repositories

---

## 🤖 AI Architecture

```
User question
      │
      ▼
daily_q_count >= 10? ──YES──► PaywallBottomSheet (stop)
      │NO
      ▼
gemini_calls_today < 230?
      │YES                          │NO
      ▼                             ▼
Gemini 2.5 Flash              Groq Llama 4 Maverick
(Google AI Studio)            (fallback — auto, silent)
      │
      ▼
level-aware system prompt
[LEVEL] + [SCHOOL_TYPE] + [SUBJECT] injected
      │
      ▼
Albanian response → UI + Drift DB cache + counter increment
```

**Photo flow — zero cloud OCR cost:**
```
Camera capture
      │
      ▼
Bitmap resize (max 1024×1024, 5MB)  ← on-device
      │
      ▼
ML Kit Text Recognition v2          ← on-device, free, offline
      │
      ▼
Editable text preview (user confirms/corrects)
      │
      ▼
Plain text → Gemini Flash           ← only API call (text, not image)
```

---

## 🚀 Getting Started

### Prerequisites

- Flutter 3.22+ (`flutter doctor` must pass)
- Android device or emulator (API 26+ / Android 8.0+)
- [Google AI Studio API key](https://aistudio.google.com) — free, no credit card
- [Groq API key](https://console.groq.com) — free, no credit card
- GitHub Copilot subscription (for development)

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/gamingrepo/mesues-ai.git
cd mesues-ai

# 2. Create your .env file (NEVER commit this)
cp .env.example .env
# Edit .env and add your keys:
#   GEMINI_API_KEY=your_key_here
#   GROQ_API_KEY=your_key_here

# 3. Install dependencies
flutter pub get

# 4. Generate Drift + Riverpod code
flutter pub run build_runner build --delete-conflicting-outputs

# 5. Run the app
flutter run
```

### Environment Variables

Create a `.env` file at the project root:

```env
GEMINI_API_KEY=your_google_ai_studio_key
GROQ_API_KEY=your_groq_key
```

> ⚠️ **Never commit `.env` to version control.** It's in `.gitignore` by default.

---

## 💰 Free API Usage

This app is designed to run entirely on free API tiers:

| API | Free Limit | Used For | Daily App Limit |
|-----|-----------|----------|-----------------|
| Gemini 2.5 Flash | 250 RPD | Standard tutoring | Stops at 230 (buffer) |
| Gemini 2.5 Pro | ~50–100 RPD | Matura/PKAB reasoning | Stops at 40 |
| Groq Llama 4 Maverick | 500 RPD | Flash fallback | Unlimited user switch |
| ML Kit OCR | Unlimited | Photo text extraction | No limit (on-device) |

**Result:** App runs 100% free for normal student usage. No hidden cloud costs.

---

## 📱 Screenshots

> *Screenshots will be added after Sprint 3 build*

| Onboarding | Home | Chat | Exam Prep | VKM Calculator |
|-----------|------|------|-----------|----------------|
| *(soon)* | *(soon)* | *(soon)* | *(soon)* | *(soon)* |

---

## 🗺️ Roadmap

### v1.0 — Public MVP (Sprint 3)
- [x] Albanian education system research + SSOT documentation
- [ ] Flutter project scaffold (Sprint 0)
- [ ] Core AI chat — level-aware, Albanian (Sprint 1)
- [ ] Homework photo OCR flow (Sprint 2)
- [ ] VANAF + PKAB exam prep (Sprint 2)
- [ ] Matura prep — all 6 tracks (Sprint 3)
- [ ] VKM Calculator (Sprint 3)
- [ ] Fletore + Profile (Sprint 3)
- [ ] Play Store release (post Sprint 4 beta)

### v1.x — Post-MVP
- [ ] Voice input / TTS answers
- [ ] Push notification study reminders
- [ ] iOS App Store submission (same Flutter codebase)
- [ ] Kosovo / North Macedonia curriculum support
- [ ] Teacher / school admin dashboard
- [ ] Offline mode (cached answers)

---

## 🇦🇱 Albanian Education System

Mësues AI covers the full Albanian pre-university system:

```
VANAF  → Grade 5  (Gjuhë Shqipe, Matematikë, Dituri Natyre)
PKAB   → Grade 9  (Gjuhë Shqipe, Matematikë, Gjuhë e Huaj A2)
Matura → Grade 12–13 (4 exams, track-specific)

VKM Formula: M_VKM = (M_shkollë + M_maturë) / 2
Minimum for university admission: 6.0
Scale: 4 (fail) → 10 (excellent)
```

See [`docs/albanian-education-system.md`](docs/albanian-education-system.md) for the full research document.

---

## 💡 Contributing

This is a Gaming Repository project. Contributions welcome.

```bash
# Fork the repo, create a feature branch
git checkout -b feature/your-feature-name

# Make changes, then
flutter analyze          # zero errors required
flutter test             # all tests must pass
git commit -m "feat: your change description"
git push origin feature/your-feature-name
# Open a Pull Request
```

**Before submitting:**
- Run `flutter analyze` — zero warnings
- Run `flutter test`
- Ensure `.env` is not committed
- Add/update tests for any new service logic

---

## 📄 License

MIT License — see [LICENSE](LICENSE) for details.

---

## 🙏 Acknowledgments

- [Eurydice Albania](https://eacea.ec.europa.eu/national-policies/eurydice/content/albania_en) — Albanian education system research
- [QSHA / ASCAP](https://qsha.gov.al) — National exam structure and VANAF/PKAB data
- [arsimi.gov.al](https://arsimi.gov.al) — Official MASR curriculum resources
- [maturashteterore.com](https://maturashteterore.com) — Past Matura exam archive
- [Google AI Studio](https://aistudio.google.com) — Free Gemini API
- [Groq](https://groq.com) — Free Llama inference

---

<div align="center">

**Made with ❤️ in Fier, Albania 🇦🇱**

[Gaming Repository](https://github.com/gamingrepo) · [Report a Bug](https://github.com/gamingrepo/mesues-ai/issues) · [Request a Feature](https://github.com/gamingrepo/mesues-ai/issues)

</div>
