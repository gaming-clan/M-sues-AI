import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import 'colors.dart';

class AppTheme {
  AppTheme._();

  static ThemeData lightTheme() {
    return ThemeData(
      useMaterial3: true,
      colorScheme: ColorScheme.fromSeed(seedColor: AppColors.primary).copyWith(
        surface: AppColors.surface,
        onPrimary: Colors.white,
      ),
      scaffoldBackgroundColor: AppColors.background,
      appBarTheme: const AppBarTheme(
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 0,
        centerTitle: true,
      ),
      fontFamily: GoogleFonts.inter().fontFamily,
      textTheme: Typography.material2021().black.copyWith(
            bodyMedium: const TextStyle(color: AppColors.onSurface),
          ),
      cardTheme: CardThemeData(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(18)),
        elevation: 2,
        color: AppColors.surface,
      ),
    );
  }

  static ThemeData darkTheme() {
    return ThemeData(
      useMaterial3: true,
      brightness: Brightness.dark,
      colorScheme: ColorScheme.fromSeed(seedColor: AppColors.primary, brightness: Brightness.dark),
      scaffoldBackgroundColor: const Color(0xFF0D0F1A),
      appBarTheme: const AppBarTheme(
        backgroundColor: Color(0xFF1A1D2E),
        foregroundColor: Colors.white,
        elevation: 0,
        centerTitle: true,
      ),
      fontFamily: GoogleFonts.inter().fontFamily,
      textTheme: Typography.material2021().white.copyWith(
            bodyMedium: const TextStyle(color: Color(0xFFE8EAF6)),
          ),
      cardTheme: CardThemeData(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(18)),
        elevation: 2,
        color: const Color(0xFF1E2137),
      ),
    );
  }
}
