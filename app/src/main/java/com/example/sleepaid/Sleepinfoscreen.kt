package com.example.sleepaid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.articles
import com.example.sleepaid.ui.theme.*

// List of all articles
@Composable
fun SleepInfoScreen(
    onBack: () -> Unit,
    onHome: () -> Unit,
    onNavigate: (String) -> Unit
) {
    SleepAidScaffold(title = "Sleep Information", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Learn how sleep works and how to improve it.", color = Navy, fontSize = 16.sp)

            articles.forEach { article ->
                Surface(
                    onClick = { onNavigate(Routes.article(article.id)) },   // e.g. "article/3"
                    shape = RoundedCornerShape(16.dp),
                    color = CreamLight,
                    shadowElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(article.title, color = Navy, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        Text(article.summary, color = Navy.copy(alpha = 0.8f))
                        Text(
                            "${article.readMinutes} min read",
                            color = PeriwinkleDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleScreen(
    articleId: Int,
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    val article = articles.firstOrNull { it.id == articleId } ?: articles.first()

    SleepAidScaffold(title = "Sleep Information", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(article.title, color = Navy, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("${article.readMinutes} min read", color = PeriwinkleDark, fontSize = 13.sp)

            article.sections.forEach { section ->
                section.heading?.let {
                    Text(it, color = Navy, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                if (section.body.isNotBlank()) {
                    Text(section.body, color = Navy, fontSize = 16.sp, lineHeight = 24.sp)
                }
                section.bullets.forEach { bullet ->
                    Row {
                        Text("•  ", color = Navy, fontSize = 16.sp)
                        Text(bullet, color = Navy, fontSize = 16.sp, lineHeight = 24.sp)
                    }
                }
            }

            // Sources
            HorizontalDivider(Modifier.padding(vertical = 8.dp))
            Text("Sources", color = Navy, fontWeight = FontWeight.Bold)
            article.sources.forEach { source ->
                Text(
                    source,
                    color = Navy.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}