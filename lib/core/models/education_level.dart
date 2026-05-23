enum EducationLevel {
  primary('Arsimi Fillor', 1, 5),
  lowerSecondary('Arsimi i Mesëm i Ulët', 6, 9),
  upperSecondary('Arsimi i Mesëm i Lartë', 10, 13);

  const EducationLevel(this.label, this.minGrade, this.maxGrade);

  final String label;
  final int minGrade;
  final int maxGrade;

  String get gradeRange => '$minGrade–$maxGrade';
}
