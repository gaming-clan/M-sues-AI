import 'package:flutter/material.dart';

class ExamsScreen extends StatelessWidget {
  const ExamsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const Text('Provimet', style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold)),
        const SizedBox(height: 16),
        Card(
          child: ListTile(
            leading: const Icon(Icons.school_rounded, color: Colors.teal),
            title: const Text('VANAF — Klasa 5'),
            subtitle: const Text('Përgatitje për testin kombëtar të arsimit fillor.'),
            trailing: const Icon(Icons.arrow_forward_ios_rounded),
            onTap: () {},
          ),
        ),
        const SizedBox(height: 12),
        Card(
          child: ListTile(
            leading: const Icon(Icons.school_rounded, color: Colors.blueAccent),
            title: const Text('PKAB — Klasa 9'),
            subtitle: const Text('Përgatitje për provimin kombëtar të arsimit bazë.'),
            trailing: const Icon(Icons.arrow_forward_ios_rounded),
            onTap: () {},
          ),
        ),
        const SizedBox(height: 12),
        Card(
          child: ListTile(
            leading: const Icon(Icons.school_rounded, color: Colors.deepPurple),
            title: const Text('Matura'),
            subtitle: const Text('Përgatitje për Maturën Shtetërore dhe VKM kalkulator.'),
            trailing: const Icon(Icons.arrow_forward_ios_rounded),
            onTap: () {},
          ),
        ),
      ],
    );
  }
}
