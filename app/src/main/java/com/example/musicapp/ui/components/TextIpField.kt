package com.example.musicapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TextIPField(
    label: String,
    initialText: String="",
    onTextChange: (String) -> Unit,
){

    var textFieldValue by remember {
        mutableStateOf(initialText)

    }
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onTextChange(it)
        },
        label = {
            Text("label")},
        modifier= Modifier.fillMaxWidth()


    )
}