package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.AgeGroup
import com.example.domain.AgeRecommendationItem
import com.example.domain.BioCategory
import com.example.domain.FavoriteCategoryKeys
import com.example.domain.GeneratedNickname
import com.example.domain.LanguageFilter
import com.example.domain.SuggestedDecoratedName
import com.example.domain.SuggestionsSectionTab
import com.example.ui.components.CopyableSnippetCard
import com.example.ui.components.HeroBannerHeader
import com.example.ui.components.StyledFilterChip
import com.example.ui.components.categoryAccentColor
import com.example.ui.components.categoryIcon
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.shareText
import com.example.ui.theme.CardBorderSubtle
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.DeepBronze
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.SilverMuted

@Composable
fun SuggestionsScreen(
    activeTab: SuggestionsSectionTab,
    suggestedCategoryFilter: BioCategory?,
    suggestedLanguageFilter: LanguageFilter,
    suggestedNames: List<SuggestedDecoratedName>,
    randomVibeFilter: BioCategory?,
    randomLanguageFilter: LanguageFilter,
    randomNicknames: List<GeneratedNickname>,
    selectedAgeGroup: AgeGroup,
    ageRecommendations: List<AgeRecommendationItem>,
    favoriteContents: Set<String>,
    onTabChanged: (SuggestionsSectionTab) -> Unit,
    onSuggestedCategoryChanged: (BioCategory?) -> Unit,
    onSuggestedLanguageChanged: (LanguageFilter) -> Unit,
    onRandomVibeChanged: (BioCategory?) -> Unit,
    onRandomLanguageChanged: (LanguageFilter) -> Unit,
    onRegenerateRandom: () -> Unit,
    onAgeGroupChanged: (AgeGroup) -> Unit,
    onToggleFavorite: (content: String, categoryKey: String, styleLabel: String) -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp)
        ) {
            item {
                HeroBannerHeader(
                    title = stringResource(R.string.suggestions_title),
                    subtitle = stringResource(R.string.suggestions_subtitle),
                    badgeText = when (activeTab) {
                        SuggestionsSectionTab.SUGGESTED_NAMES -> "أسماء مزخرفة جاهزة"
                        SuggestionsSectionTab.RANDOM_NICKNAME -> "مولّد الألقاب العشوائي"
                        SuggestionsSectionTab.BY_AGE -> "مقترحات حسب العمر"
                    }
                )
            }

            // Top 3 Section Tabs: اقتراحات الأسماء | لقب عشوائي | حسب العمر
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SuggestionsSectionTab.entries.forEach { tab ->
                        val selected = activeTab == tab
                        val icon = when (tab) {
                            SuggestionsSectionTab.SUGGESTED_NAMES -> Icons.Filled.Stars
                            SuggestionsSectionTab.RANDOM_NICKNAME -> Icons.Filled.Casino
                            SuggestionsSectionTab.BY_AGE -> Icons.Filled.Group
                        }
                        Surface(
                            color = if (selected) DeepBronze else MidnightSurface,
                            shape = RoundedCornerShape(14.dp),
                            border = BorderStroke(
                                1.2.dp,
                                if (selected) ChampagneGold else CardBorderSubtle
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .clip(RoundedCornerShape(14.dp))
                                .testTag("suggestions_subtab_${tab.name.lowercase()}")
                                .clickable { onTabChanged(tab) }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = if (selected) ChampagneGold else SilverMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = tab.label,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (selected) ChampagneGold else SilverMuted,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            when (activeTab) {
                // ======================================================
                // TAB 1: اقتراحات الأسماء المزخرفة
                // ======================================================
                SuggestionsSectionTab.SUGGESTED_NAMES -> {
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                StyledFilterChip(
                                    label = "الكل",
                                    selected = suggestedCategoryFilter == null,
                                    onClick = { onSuggestedCategoryChanged(null) },
                                    accentColor = ChampagneGold,
                                    icon = Icons.Filled.AutoAwesome,
                                    testTag = "sugg_cat_all"
                                )
                            }
                            items(BioCategory.entries) { cat ->
                                StyledFilterChip(
                                    label = cat.phraseTitle,
                                    selected = suggestedCategoryFilter == cat,
                                    onClick = { onSuggestedCategoryChanged(cat) },
                                    accentColor = categoryAccentColor(cat),
                                    icon = categoryIcon(cat),
                                    testTag = "sugg_cat_${cat.name.lowercase()}"
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                LanguageFilter.entries.forEach { lang ->
                                    StyledFilterChip(
                                        label = lang.label,
                                        selected = suggestedLanguageFilter == lang,
                                        onClick = { onSuggestedLanguageChanged(lang) },
                                        accentColor = ChampagneGold
                                    )
                                }
                            }
                            Text(
                                text = "${suggestedNames.size} اسم",
                                style = MaterialTheme.typography.labelLarge,
                                color = ChampagneGold
                            )
                        }
                    }

                    items(
                        items = suggestedNames,
                        key = { it.id }
                    ) { item ->
                        val accent = categoryAccentColor(item.category)
                        val isFav = favoriteContents.contains(item.decoratedName)

                        CopyableSnippetCard(
                            text = item.decoratedName,
                            badgeLabel = "${item.category.phraseTitle} • ${item.styleSubtitle}",
                            accentColor = accent,
                            badgeIcon = categoryIcon(item.category),
                            isFavorite = isFav,
                            onCopy = {
                                copyTextToClipboard(context, item.decoratedName)
                                onShowMessage("تم نسخ الاسم المقترح")
                            },
                            onShare = { shareText(context, item.decoratedName) },
                            onToggleFavorite = {
                                onToggleFavorite(
                                    item.decoratedName,
                                    item.category.storageKey,
                                    item.category.phraseTitle
                                )
                            },
                            cardTestTag = "suggested_name_${item.id}"
                        )
                    }
                }

                // ======================================================
                // TAB 2: قسم لقب عشوائي
                // ======================================================
                SuggestionsSectionTab.RANDOM_NICKNAME -> {
                    item {
                        Surface(
                            color = MidnightSurface,
                            shape = RoundedCornerShape(22.dp),
                            border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "اختر طابع اللقب ولغته ثم اضغط للتوليد:",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = PearlWhite,
                                    fontWeight = FontWeight.Bold
                                )

                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    item {
                                        StyledFilterChip(
                                            label = "تشكيلة متنوعة",
                                            selected = randomVibeFilter == null,
                                            onClick = { onRandomVibeChanged(null) },
                                            accentColor = ChampagneGold,
                                            icon = Icons.Filled.AutoAwesome
                                        )
                                    }
                                    items(BioCategory.entries) { cat ->
                                        StyledFilterChip(
                                            label = cat.label,
                                            selected = randomVibeFilter == cat,
                                            onClick = { onRandomVibeChanged(cat) },
                                            accentColor = categoryAccentColor(cat),
                                            icon = categoryIcon(cat)
                                        )
                                    }
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    LanguageFilter.entries.forEach { lang ->
                                        StyledFilterChip(
                                            label = lang.label,
                                            selected = randomLanguageFilter == lang,
                                            onClick = { onRandomLanguageChanged(lang) },
                                            accentColor = ChampagneGold
                                        )
                                    }
                                }

                                Button(
                                    onClick = {
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        onRegenerateRandom()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ChampagneGold,
                                        contentColor = ObsidianBg
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp)
                                        .testTag("generate_random_nickname_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Refresh,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "توليد ألقاب عشوائية جديدة",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    items(
                        items = randomNicknames,
                        key = { it.id }
                    ) { nick ->
                        val accent = categoryAccentColor(nick.category)
                        val isFav = favoriteContents.contains(nick.text)

                        CopyableSnippetCard(
                            text = nick.text,
                            badgeLabel = nick.styleLabel,
                            accentColor = accent,
                            badgeIcon = Icons.Filled.Casino,
                            isFavorite = isFav,
                            onCopy = {
                                copyTextToClipboard(context, nick.text)
                                onShowMessage("تم نسخ اللقب العشوائي")
                            },
                            onShare = { shareText(context, nick.text) },
                            onToggleFavorite = {
                                onToggleFavorite(
                                    nick.text,
                                    FavoriteCategoryKeys.DECORATED_NAME,
                                    "لقب عشوائي • ${nick.category.label}"
                                )
                            },
                            cardTestTag = "random_nick_${nick.id}"
                        )
                    }
                }

                // ======================================================
                // TAB 3: قسم حسب العمر
                // ======================================================
                SuggestionsSectionTab.BY_AGE -> {
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(AgeGroup.entries) { group ->
                                StyledFilterChip(
                                    label = "${group.label} (${group.rangeLabel})",
                                    selected = selectedAgeGroup == group,
                                    onClick = { onAgeGroupChanged(group) },
                                    accentColor = ChampagneGold,
                                    icon = Icons.Filled.Group,
                                    testTag = "age_group_${group.name.lowercase()}"
                                )
                            }
                        }
                    }

                    item {
                        Surface(
                            color = MidnightSurface,
                            shape = RoundedCornerShape(18.dp),
                            border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "${selectedAgeGroup.label} • ${selectedAgeGroup.rangeLabel}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = ChampagneGold,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = selectedAgeGroup.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SilverMuted
                                )
                            }
                        }
                    }

                    items(
                        items = ageRecommendations,
                        key = { it.id }
                    ) { rec ->
                        val accent = categoryAccentColor(rec.category)
                        val isFav = favoriteContents.contains(rec.text)

                        CopyableSnippetCard(
                            text = rec.text,
                            badgeLabel = "${rec.label} • ${rec.ageGroup.rangeLabel}",
                            accentColor = accent,
                            badgeIcon = categoryIcon(rec.category),
                            isFavorite = isFav,
                            onCopy = {
                                copyTextToClipboard(context, rec.text)
                                onShowMessage("تم النسخ بنجاح")
                            },
                            onShare = { shareText(context, rec.text) },
                            onToggleFavorite = {
                                onToggleFavorite(
                                    rec.text,
                                    if (rec.isName) FavoriteCategoryKeys.DECORATED_NAME else rec.category.storageKey,
                                    "${rec.ageGroup.label} • ${rec.category.label}"
                                )
                            },
                            cardTestTag = "age_item_${rec.id}"
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
