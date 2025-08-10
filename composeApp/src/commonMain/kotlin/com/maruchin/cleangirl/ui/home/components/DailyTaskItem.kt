package com.maruchin.cleangirl.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.maruchin.cleangirl.data.model.DailyTask

@Composable
fun DailyTaskItem(
    task: DailyTask,
    isPlannedForToday: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier.fillMaxWidth().then(modifier)) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.titleMedium,
                textDecoration = if (task.isCompleted) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                },
                modifier = Modifier.weight(1f)
            )
            if (isPlannedForToday) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCompletedChange
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RecordInfo(
                icon = Icons.Rounded.History,
                text = buildAnnotatedString {
                    append("Ostatnio wykonano: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(task.lastCompleted?.toString() ?: "nigdy")
                    }
                },
            )
            RecordInfo(
                icon = Icons.Rounded.FastForward,
                text = buildAnnotatedString {
                    append("Następny raz: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(task.nextPlanned?.toString() ?: "nigdy")
                    }
                }
            )
        }
    }
}

@Composable
private fun RecordInfo(icon: ImageVector, text: AnnotatedString) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(start = 8.dp).weight(1f)
        )
    }
}