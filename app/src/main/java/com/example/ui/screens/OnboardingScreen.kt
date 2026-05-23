package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@Composable
fun OnboardingScreen(viewModel: MainViewModel, onFinish: () -> Unit) {
    var step by remember { mutableIntStateOf(1) }
    var level by remember { mutableStateOf("") }
    var grade by remember { mutableIntStateOf(1) }
    var schoolType by remember { mutableStateOf<String?>(null) }
    var studentName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (step) {
            1 -> {
                Text("Cilës nivel i përket?", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { level = "PRIMARY"; step = 2 }) { Text("Arsimi Fillor (Kl. 1–5)") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { level = "LOWER_SECONDARY"; step = 2 }) { Text("Arsimi 9-vjeçar (Kl. 6–9)") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { level = "UPPER_SECONDARY_GENERAL"; step = 2 }) { Text("Arsimi i Mesëm i Lartë (Kl. 10–13)") }
            }
            2 -> {
                Text("Çfarë klasë/vit ndjekon?", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                // Dummy picker for grade
                OutlinedTextField(
                    value = grade.toString(),
                    onValueChange = { grade = it.toIntOrNull() ?: grade },
                    label = { Text("Klasa (1-13)") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { 
                    if (level.startsWith("UPPER_SECONDARY")) step = 3 else step = 4 
                }) { Text("Vazhdo") }
            }
            3 -> {
                Text("Çfarë lloji shkolle ndjek?", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                val types = listOf("Gjimnaz", "Profesionale", "Artistike", "Sportive", "Gjuhësore", "Medrese")
                types.forEach { t ->
                    Button(onClick = { 
                        schoolType = t
                        if (t == "Profesionale") step = 35 else step = 4
                    }) { Text(t) }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            35 -> {
                Text("Sa vjet zgjasin studimet tuaja?", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { step = 4 }) { Text("2 vjet") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { step = 4 }) { Text("3 vjet") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { step = 4 }) { Text("4 vjet") }
            }
            4 -> {
                Text("Si të quajmë?", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Emri yt") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    viewModel.saveOnboarding(level, grade, schoolType, studentName, if (schoolType == "Profesionale") 3 else null)
                    onFinish()
                }) { Text("Përfundo") }
            }
        }
    }
}
