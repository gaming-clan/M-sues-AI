package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorScreen(viewModel: MainViewModel) {
    val currentSubject by viewModel.currentSubject.collectAsState()
    val chatResponse by viewModel.chatResponse.collectAsState()
    
    var inputText by remember { mutableStateOf("") }
    var isThinking by remember { mutableStateOf(false) }
    
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(currentSubject?.albName ?: "Mësuesi AI", style = MaterialTheme.typography.titleLarge) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            ),
            actions = {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer, 
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        "Kl. ${viewModel.gradeFlow.collectAsState().value}", 
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        )
        
        LazyColumn(
            modifier = Modifier.weight(1f).padding(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AIMessageBubble(text = "Përshëndetje! Jam Mësuesi yt AI. Si mund të të ndihmoj sot?")
            }
            if (chatResponse != null) {
                item {
                    AIMessageBubble(text = chatResponse!!)
                }
            }
            if (isThinking) {
                item {
                    AIMessageBubble(text = "Po mendoj...")
                }
            }
        }
        
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* TODO: MLKit Camera */ }) {
                Text("📷", style = MaterialTheme.typography.headlineSmall)
            }
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Shkruaj pyetjen tënde...") },
                maxLines = 4,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedBorderColor = com.example.ui.theme.CardBorderLight,
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        isThinking = true
                        viewModel.askQuestion(inputText)
                        inputText = ""
                    }
                },
                enabled = inputText.isNotBlank()
            ) {
                Text("Dërgo")
            }
        }
    }
    
    LaunchedEffect(chatResponse) {
        if (chatResponse != null) {
            isThinking = false
        }
    }
}

@Composable
fun AIMessageBubble(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Surface(
            shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 4.dp),
            color = MaterialTheme.colorScheme.surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.CardBorderLight),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(text, modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
fun UserMessageBubble(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Surface(
            shape = RoundedCornerShape(24.dp, 24.dp, 4.dp, 24.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(text, color = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.padding(16.dp))
        }
    }
}
