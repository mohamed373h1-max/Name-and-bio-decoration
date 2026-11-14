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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.BioCategory
import com.example.domain.BioQuote
import com.example.domain.BioSectionTab
import com.example.domain.FavoriteCategoryKeys
import com.example.domain.LanguageFilter
import com.example.domain.ReadyAboutTemplate
import com.example.ui.components.CopyableSnippetCard
import com.example.ui.components.CustomBioDialog
import com.example.ui.components.HeroBannerHeader
import com.example.ui.components.StyledFilterChip
import com.example.ui.components.categoryAccentColor
import com.example.ui.components.categoryIcon
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.shareText
import com.example.ui.theme.CardBorderSubtle
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.DeepBronze
import com.example.ui.theme.ElevatedCharcoal
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.SilverMuted

@Composable
fun BioScreen(
    activeSectionTab: BioSectionTab,
    selectedCategory: BioCategory?,
    selectedLanguage: LanguageFilter,
    searchQuery: String,
    bios: List<BioQuote>,
    readyAbouts: List<ReadyAboutTemplate>,
    favoriteContents: Set<String>,
    onSectionTabChanged: (BioSectionTab) -> Unit,
    onCategoryChanged: (BioCategory?) -> Unit,
    onLanguageChanged: (LanguageFilter) -> Unit,
    onSearchChanged: (String) -> Unit,
    onToggleFavoriteBio: (BioQuote) -> Unit,
    onToggleFavoriteReadyAbout: (ReadyAboutTemplate) -> Unit,
    onSaveCustomBio: (String, BioCategory) -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showCustomDialog by remember { mutableStateOf(false) }

    if (showCustomDialog) {
        CustomBioDialog(
            onDismiss = { showCustomDialog = false },
            onSave = { text, cat ->
                onSaveCustomBio(text, cat)
                onShowMessage("تم حفظ البايو المخصص في المفضلة")
            }
        )
    }

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
                    title = if (activeSectionTab == BioSectionTab.BIO_QUOTES) {
                        stringResource(R.string.bio_title)
                    } else {
                        "قسم نبذة جاهزة للحسابات"
                    },
                    subtitle = if (activeSectionTab == BioSectionTab.BIO_QUOTES) {
                        stringResource(R.string.bio_subtitle)
                    } else {
                        "قوالب نبذة منسقة ومزخرفة جاهزة للنسخ في ملفك الشخصي"
                    },
                    badgeText = if (activeSectionTab == BioSectionTab.BIO_QUOTES) {
                        "ملكي • هادئ • تقني وسايبر"
                    } else {
                        "${readyAbouts.size} نبذة جاهزة"
                    }
                )
            }

            // Sub-section switcher: عبارات البايو | نبذة جاهزة
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BioSectionTab.entries.forEach { tab ->
                        val selected = activeSectionTab == tab
                        Surface(
                            color = if (selected) DeepBronze else MidnightSurface,
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                1.2.dp,
                                if (selected) ChampagneGold else CardBorderSubtle
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .clip(RoundedCornerShape(16.dp))
                                .testTag("bio_subtab_${tab.name.lowercase()}")
                                .clickable { onSectionTabChanged(tab) }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                            ) {
                                Icon(
                                    imageVector = if (tab == BioSectionTab.BIO_QUOTES) {
                                        Icons.Filled.FormatQuote
                                    } else {
                                        Icons.Filled.Badge
                                    },
                                    contentDescription = null,
                                    tint = if (selected) ChampagneGold else SilverMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = tab.label,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (selected) ChampagneGold else SilverMuted,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // Search + Language + Custom Bio row
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onSearchChanged,
                        placeholder = {
                            Text("ابحث في العبارات أو النبذات...", color = SilverMuted)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                tint = ChampagneGold
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { onSearchChanged("") }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "مسح البحث",
                                        tint = SilverMuted
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ChampagneGold,
                            unfocusedBorderColor = CardBorderSubtle,
                            focusedContainerColor = MidnightSurface,
                            unfocusedContainerColor = MidnightSurface,
                            focusedTextColor = PearlWhite,
                            unfocusedTextColor = PearlWhite,
                            cursorColor = ChampagneGold
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("bio_search_input")
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            LanguageFilter.entries.forEach { lang ->
                                StyledFilterChip(
                                    label = lang.label,
                                    selected = selectedLanguage == lang,
                                    onClick = { onLanguageChanged(lang) },
                                    accentColor = ChampagneGold,
                                    testTag = "bio_lang_${lang.name.lowercase()}"
                                )
                            }
                        }

                        Button(
                            onClick = { showCustomDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ElevatedCharcoal,
                                contentColor = ChampagneGold
                            ),
                            border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(14.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                            modifier = Modifier.testTag("add_custom_bio_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "بايو مخصص",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }

            // Category chips: All, Royal/Luxury, Calm/Unique, Tech/Cyber
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        StyledFilterChip(
                            label = "الكل",
                            selected = selectedCategory == null,
                            onClick = { onCategoryChanged(null) },
                            accentColor = ChampagneGold,
                            icon = Icons.Filled.AutoAwesome,
                            testTag = "bio_cat_all"
                        )
                    }
                    items(BioCategory.entries) { cat ->
                        StyledFilterChip(
                            label = cat.label,
                            selected = selectedCategory == cat,
                            onClick = { onCategoryChanged(cat) },
                            accentColor = categoryAccentColor(cat),
                            icon = categoryIcon(cat),
                            testTag = "bio_cat_${cat.name.lowercase()}"
                        )
                    }
                }
            }

            if (activeSectionTab == BioSectionTab.BIO_QUOTES) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "العبارات المتاحة (${bios.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = PearlWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = selectedCategory?.label ?: "جميع التصنيفات",
                            style = MaterialTheme.typography.labelMedium,
                            color = categoryAccentColor(selectedCategory)
                        )
                    }
                }

                if (bios.isEmpty()) {
                    item { EmptySearchCard() }
                } else {
                    items(
                        items = bios,
                        key = { it.id }
                    ) { bio ->
                        val accent = categoryAccentColor(bio.category)
                        val langBadge = if (bio.isArabic) "عربي" else "EN"
                        val isFav = favoriteContents.contains(bio.text)

                        CopyableSnippetCard(
                            text = bio.text,
                            badgeLabel = "${bio.category.label} • $langBadge",
                            accentColor = accent,
                            badgeIcon = categoryIcon(bio.category),
                            isFavorite = isFav,
                            onCopy = {
                                copyTextToClipboard(context, bio.text)
                                onShowMessage("تم نسخ العبارة بنجاح")
                            },
                            onShare = {
                                shareText(context, bio.text)
                            },
                            onToggleFavorite = {
                                onToggleFavoriteBio(bio)
                            },
                            cardTestTag = "bio_card_${bio.id}"
                        )
                    }
                }
            } else {
                // --- READY ABOUT TEMPLATES (نبذة جاهزة) ---
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "قوالب النبذة الجاهزة (${readyAbouts.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = PearlWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "مناسبة لجميع المنصات",
                            style = MaterialTheme.typography.labelMedium,
                            color = ChampagneGold
                        )
                    }
                }

                if (readyAbouts.isEmpty()) {
                    item { EmptySearchCard() }
                } else {
                    items(
                        items = readyAbouts,
                        key = { it.id }
                    ) { tpl ->
                        val accent = categoryAccentColor(tpl.category)
                        val isFav = favoriteContents.contains(tpl.content)

                        CopyableSnippetCard(
                            text = tpl.content,
                            badgeLabel = "${tpl.title} • ${tpl.platformHint}",
                            accentColor = accent,
                            badgeIcon = categoryIcon(tpl.category),
                            isFavorite = isFav,
                            onCopy = {
                                copyTextToClipboard(context, tpl.content)
                                onShowMessage("تم نسخ النبذة الجاهزة")
                            },
                            onShare = {
                                shareText(context, tpl.content)
                            },
                            onToggleFavorite = {
                                onToggleFavoriteReadyAbout(tpl)
                            },
                            cardTestTag = "ready_about_${tpl.id}"
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

@Composable
private fun EmptySearchCard() {
    Surface(
        color = MidnightSurface,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, CardBorderSubtle),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(28.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = SilverMuted,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "لا توجد نتائج مطابقة لبحثك",
                style = MaterialTheme.typography.titleMedium,
                color = PearlWhite
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "جرّب تغيير التصنيف أو كلمة البحث",
                style = MaterialTheme.typography.bodyMedium,
                color = SilverMuted
            )
        }
    }
}
