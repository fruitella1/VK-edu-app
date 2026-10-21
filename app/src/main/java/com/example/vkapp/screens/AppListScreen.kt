package com.example.vkapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vkapp.data.AppInfo
import com.example.vkapp.data.loadApps
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.TopAppBarDefaults
import com.example.vkapp.R
import com.example.vkapp.items.AppListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(onAppClick: (Int) -> Unit) {
    val appList: List<AppInfo> = loadApps()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text("Vk app catalog")
            }, navigationIcon = {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    painter = painterResource(R.drawable.vk_icon),
                    contentDescription = "Apps catalog"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(appList) { index, item ->
                AppListItem(onClick = { onAppClick(index) }, item)
            }
        }
    }
}