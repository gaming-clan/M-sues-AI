import 'package:flutter/material.dart';

import '../../core/models/education_level.dart';
import '../../shared/widgets/level_card.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final levels = EducationLevel.values;

    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Zgjidhni nivelin tuaj',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.w700),
          ),
          const SizedBox(height: 16),
          Wrap(
            spacing: 12,
            runSpacing: 12,
            children: levels
                .map((level) => LevelCard(
                      level: level,
                      onTap: () {},
                    ))
                .toList(),
          ),
          const SizedBox(height: 32),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: const [
                  Text('Mirë se vini në Mësues AI', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                  SizedBox(height: 12),
                  Text(
                    'Përgatituni për VANAF, PKAB dhe Maturë. Pyetni çdo gjë në shqip dhe merrni shpjegime hap pas hapi.',
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
