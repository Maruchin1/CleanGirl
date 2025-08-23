package com.maruchin.cleangirl.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarScrollBehavior
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeToolbar(scrollBehavior: FloatingToolbarScrollBehavior, modifier: Modifier = Modifier) {
    HorizontalFloatingToolbar(
        expanded = true,
        floatingActionButton = {
            FloatingToolbarDefaults.StandardFloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Timer, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    ) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.BarChart, contentDescription = null)
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
        }
    }
}
