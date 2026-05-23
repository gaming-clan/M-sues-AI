package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val level by viewModel.levelFlow.collectAsState()
    val name by viewModel.nameFlow.collectAsState()
    val grade by viewModel.gradeFlow.collectAsState()
    val schoolType by viewModel.schoolTypeFlow.collectAsState()

    val levelDisplay = when {
        level == "PRIMARY" -> "Arsimi Fillor — Klasa $grade"
        level == "LOWER_SECONDARY" -> "9-vjeçar — Klasa $grade"
        level?.startsWith("UPPER") == true -> "$schoolType — Klasa $grade"
        else -> ""
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Mirëdita, ${name ?: "Nxënës"}! 👋", style = MaterialTheme.typography.headlineMedium)
                Text(levelDisplay, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
            ) {
                Text(
                    "🔥 0 ditë", 
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), 
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            "Progresi Ditor", 
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), 
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Text("0/10", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { 0f }, 
                    modifier = Modifier.fillMaxWidth().height(6.dp), 
                    trackColor = Color.White.copy(alpha = 0.5f),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "LËNDËT", 
                style = MaterialTheme.typography.labelMedium, 
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        
        val subjects = viewModel.getSubjectsForCurrentLevel()
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(subjects) { subj ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable {
                        viewModel.selectSubject(subj)
                        navController.navigate("tutor")
                    },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.CardBorderLight)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(), 
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(subj.icon, style = MaterialTheme.typography.titleLarge)
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(subj.albName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                        }
                        Text("❯", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
                    }
                }
            }
        }
    }
}
