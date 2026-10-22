package com.example.vkapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkapp.data.AppInfo
import com.example.vkapp.data.loadApps
import com.example.vkapp.items.AppListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCardScreen(
    appInfo: AppInfo,
    onBackClick: () -> Unit = {},
    onShowCaseClick: (AppInfo) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("О приложении") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "назад")
                    }
                }
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppCard(appInfo)

            AppListShowCase(onAppClick = { clicked -> onShowCaseClick(clicked) })
        }
    }
}

@Composable
fun AppListShowCase(onAppClick: (AppInfo) -> Unit = {}) {
    val appList = remember { loadApps() }

    val chunks = appList.chunked(3)

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { chunks.size })

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Витрина",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp
        )

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            ShowCaseItem(onAppClick, chunks[page])
        }
    }
}

@Composable
fun ShowCaseItem(onClick: (AppInfo) -> Unit, chunk: List<AppInfo>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        chunk.forEach { it ->
            AppListItem(onClick, it)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AppCard(app: AppInfo) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(app.image),
                    contentDescription = app.name,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        app.name,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        app.category,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                app.description,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}