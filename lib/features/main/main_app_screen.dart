import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../core/providers/app_providers.dart';
import '../chat/chat_screen.dart';
import '../exams/exams_screen.dart';
import '../home/home_screen.dart';
import '../profile/profile_screen.dart';

class MainAppScreen extends ConsumerWidget {
  const MainAppScreen({super.key});

  static const _titles = [
    'Kryefaqja',
    'Mësuesi',
    'Provimet',
    'Profili',
  ];

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final index = ref.watch(navigationIndexProvider);

    final pages = [
      const HomeScreen(),
      const ChatScreen(),
      const ExamsScreen(),
      const ProfileScreen(),
    ];

    return Scaffold(
      appBar: AppBar(
        title: Text(_titles[index]),
      ),
      body: IndexedStack(
        index: index,
        children: pages,
      ),
      bottomNavigationBar: NavigationBar(
        selectedIndex: index,
        onDestinationSelected: (value) {
          ref.read(navigationIndexProvider.notifier).state = value;
        },
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home_rounded), label: 'Kryefaqja'),
          NavigationDestination(icon: Icon(Icons.chat_rounded), label: 'Mësuesi'),
          NavigationDestination(icon: Icon(Icons.school_rounded), label: 'Provimet'),
          NavigationDestination(icon: Icon(Icons.person_rounded), label: 'Profili'),
        ],
      ),
    );
  }
}
