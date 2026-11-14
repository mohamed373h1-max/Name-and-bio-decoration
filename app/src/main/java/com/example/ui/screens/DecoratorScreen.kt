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
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.DecoratedResult
import com.example.domain.DecoratorFilter
import com.example.domain.DecoratorSectionTab
import com.example.domain.DecorativeSymbolItem
import com.example.domain.NameDecoratorEngine
import com.example.domain.SymbolCategory
import com.example.ui.components.CopyableSnippetCard
import com.example.ui.components.HeroBannerHeader
import com.example.ui.components.StyledFilterChip
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.shareText
import com.example.ui.theme.CalmLavender
import com.example.ui.theme.CardBorderSubtle
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.DeepBronze
import com.example.ui.theme.ElevatedCharcoal
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.SilverMuted

@Composable
fun DecoratorScreen(
    activeSectionTab: DecoratorSectionTab,
    nameInput: String,
    selectedFilter: DecoratorFilter,
    results: List<DecoratedResult>,
    selectedSymbolCategory: SymbolCategory,
    symbols: List<DecorativeSymbolItem>,
    composedSymbolText: String,
    favoriteContents: Set<String>,
    onSectionTabChanged: (DecoratorSectionTab) -> Unit,
    onNameInputChanged: (String) -> Unit,
    onFilterChanged: (DecoratorFilter) -> Unit,
    onAppendSymbol: (String) -> Unit,
    onSymbolCategoryChanged: (SymbolCategory) -> Unit,
    onComposedSymbolChanged: (String) -> Unit,
    onApplySymbolToComposer: (DecorativeSymbolItem) -> Unit,
    onClearComposedSymbol: () -> Unit,
    onSendComposedToDecorator: () -> Unit,
    onSaveComposedToFavorites: (String) -> Unit,
    onToggleFavorite: (DecoratedResult) -> Unit,
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
                    title = if (activeSectionTab == DecoratorSectionTab.NAME_DECORATOR) {
                        stringResource(R.string.decorator_title)
                    } else {
                        "استوديو الرموز الزخرفية"
                    },
                    subtitle = if (activeSectionTab == DecoratorSectionTab.NAME_DECORATOR) {
                        stringResource(R.string.decorator_subtitle)
                    } else {
                        "رموز وإطارات فريدة متوافقة مع الأسماء العربية والإنجليزية"
                    },
                    badgeText = if (activeSectionTab == DecoratorSectionTab.NAME_DECORATOR) {
                        "عربي + English • ${results.size} شكل"
                    } else {
                        "${symbols.size} رمز وإطار جاهز"
                    }
                )
            }

            // Sub-section Switcher: Name Decorator vs Decorative Symbols Studio
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DecoratorSectionTab.entries.forEach { tab ->
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
                                .testTag("decorator_subtab_${tab.name.lowercase()}")
                                .clickable { onSectionTabChanged(tab) }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                            ) {
                                Icon(
                                    imageVector = if (tab == DecoratorSectionTab.NAME_DECORATOR) {
                                        Icons.Filled.AutoFixHigh
                                    } else {
                                        Icons.Filled.Palette
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

            if (activeSectionTab == DecoratorSectionTab.NAME_DECORATOR) {
                item {
                    Surface(
                        color = MidnightSurface,
                        shape = RoundedCornerShape(22.dp),
                        border = BorderStroke(1.dp, CardBorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            OutlinedTextField(
                                value = nameInput,
                                onValueChange = onNameInputChanged,
                                placeholder = {
                                    Text(
                                        text = stringResource(R.string.decorator_input_hint),
                                        color = SilverMuted
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.AutoFixHigh,
                                        contentDescription = null,
                                        tint = ChampagneGold
                                    )
                                },
                                trailingIcon = {
                                    if (nameInput.isNotEmpty()) {
                                        IconButton(
                                            onClick = { onNameInputChanged("") },
                                            modifier = Modifier.testTag("clear_name_button")
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "مسح النص",
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
                                    focusedContainerColor = ElevatedCharcoal.copy(alpha = 0.6f),
                                    unfocusedContainerColor = ElevatedCharcoal.copy(alpha = 0.4f),
                                    focusedTextColor = PearlWhite,
                                    unfocusedTextColor = PearlWhite,
                                    cursorColor = ChampagneGold
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("name_input_field")
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "تجربة سريعة:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = SilverMuted
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(NameDecoratorEngine.sampleNames) { sample ->
                                        Surface(
                                            color = ElevatedCharcoal,
                                            shape = RoundedCornerShape(50),
                                            border = BorderStroke(1.dp, CardBorderSubtle),
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(50))
                                                .clickable { onNameInputChanged(sample) }
                                        ) {
                                            Text(
                                                text = sample,
                                                style = MaterialTheme.typography.labelMedium,
                                                color = ChampagneGold,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "إضافة رموز فخمة لاسمك:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = SilverMuted
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(NameDecoratorEngine.quickSymbols) { symbol ->
                                        Surface(
                                            color = ElevatedCharcoal,
                                            shape = RoundedCornerShape(12.dp),
                                            border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.3f)),
                                            modifier = Modifier
                                                .minimumInteractiveComponentSize()
                                                .clip(RoundedCornerShape(12.dp))
                                                .clickable { onAppendSymbol(symbol) }
                                        ) {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            ) {
                                                Text(
                                                    text = symbol,
                                                    style = MaterialTheme.typography.titleMedium,
                                                    color = PearlWhite
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(DecoratorFilter.entries) { filter ->
                            val accent: Color = when (filter) {
                                DecoratorFilter.ALL -> ChampagneGold
                                DecoratorFilter.ARABIC -> ChampagneGold
                                DecoratorFilter.ENGLISH -> CalmLavender
                                DecoratorFilter.FRAMES -> ChampagneGold
                                DecoratorFilter.CYBER -> CyberCyan
                            }
                            val icon = when (filter) {
                                DecoratorFilter.ALL -> Icons.Filled.AutoFixHigh
                                DecoratorFilter.ARABIC -> Icons.Filled.Translate
                                DecoratorFilter.ENGLISH -> Icons.Filled.Translate
                                DecoratorFilter.FRAMES -> Icons.Filled.WorkspacePremium
                                DecoratorFilter.CYBER -> Icons.Filled.Bolt
                            }
                            StyledFilterChip(
                                label = filter.label,
                                selected = selectedFilter == filter,
                                onClick = { onFilterChanged(filter) },
                                accentColor = accent,
                                icon = icon,
                                testTag = "decorator_filter_${filter.name.lowercase()}"
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
                        Text(
                            text = "الزخارف الجاهزة (${results.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = PearlWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (nameInput.isBlank()) "معاينة افتراضية" else "نسخ مباشر بضغطة",
                            style = MaterialTheme.typography.labelMedium,
                            color = ChampagneGold
                        )
                    }
                }

                items(
                    items = results,
                    key = { it.id }
                ) { item ->
                    val accent = when (item.filter) {
                        DecoratorFilter.CYBER -> CyberCyan
                        DecoratorFilter.ENGLISH -> CalmLavender
                        else -> ChampagneGold
                    }
                    val isFav = favoriteContents.contains(item.decoratedText)

                    CopyableSnippetCard(
                        text = item.decoratedText,
                        badgeLabel = "${item.styleName} • ${item.categoryTag}",
                        accentColor = accent,
                        badgeIcon = when (item.filter) {
                            DecoratorFilter.CYBER -> Icons.Filled.Bolt
                            DecoratorFilter.FRAMES -> Icons.Filled.WorkspacePremium
                            else -> Icons.Filled.AutoFixHigh
                        },
                        isFavorite = isFav,
                        onCopy = {
                            copyTextToClipboard(context, item.decoratedText)
                            onShowMessage("تم نسخ الزخرفة بنجاح")
                        },
                        onShare = {
                            shareText(context, item.decoratedText)
                        },
                        onToggleFavorite = {
                            onToggleFavorite(item)
                        },
                        cardTestTag = "decorated_card_${item.id}"
                    )
                }
            } else {
                // --- DECORATIVE SYMBOLS STUDIO ---
                item {
                    Surface(
                        color = MidnightSurface,
                        shape = RoundedCornerShape(22.dp),
                        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.35f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "صانع التركيبة الزخرفية (متوافق عربي وإنجليزي)",
                                style = MaterialTheme.typography.titleMedium,
                                color = ChampagneGold,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(
                                value = composedSymbolText,
                                onValueChange = onComposedSymbolChanged,
                                placeholder = {
                                    Text(
                                        text = "اضغط على (+) في أي رمز لتجميعه هنا أو اكتب اسمك...",
                                        color = SilverMuted
                                    )
                                },
                                trailingIcon = {
                                    if (composedSymbolText.isNotEmpty()) {
                                        IconButton(onClick = onClearComposedSymbol) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "مسح",
                                                tint = SilverMuted
                                            )
                                        }
                                    }
                                },
                                singleLine = false,
                                maxLines = 3,
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = ChampagneGold,
                                    unfocusedBorderColor = CardBorderSubtle,
                                    focusedContainerColor = ElevatedCharcoal.copy(alpha = 0.6f),
                                    unfocusedContainerColor = ElevatedCharcoal.copy(alpha = 0.4f),
                                    focusedTextColor = PearlWhite,
                                    unfocusedTextColor = PearlWhite,
                                    cursorColor = ChampagneGold
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("symbol_composer_field")
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        val txt = composedSymbolText.trim()
                                        if (txt.isNotEmpty()) {
                                            copyTextToClipboard(context, txt)
                                            onShowMessage("تم نسخ الرمز الزخرفي")
                                        }
                                    },
                                    enabled = composedSymbolText.isNotBlank(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = ChampagneGold,
                                        contentColor = ObsidianBg
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("copy_composed_symbol_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ContentCopy,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("نسخ")
                                }

                                OutlinedButton(
                                    onClick = {
                                        val txt = composedSymbolText.trim()
                                        if (txt.isNotEmpty()) {
                                            onSaveComposedToFavorites(txt)
                                            onShowMessage("تم الحفظ في المفضلة")
                                        }
                                    },
                                    enabled = composedSymbolText.isNotBlank(),
                                    border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.5f)),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = null,
                                        tint = ChampagneGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("حفظ", color = ChampagneGold)
                                }

                                OutlinedButton(
                                    onClick = onSendComposedToDecorator,
                                    enabled = composedSymbolText.isNotBlank(),
                                    border = BorderStroke(1.dp, CyberCyan.copy(alpha = 0.5f)),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Send,
                                        contentDescription = null,
                                        tint = CyberCyan,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("زخرفها", color = CyberCyan)
                                }
                            }
                        }
                    }
                }

                // Symbol Category Chips
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(SymbolCategory.entries) { cat ->
                            val accent = when (cat) {
                                SymbolCategory.CYBER -> CyberCyan
                                SymbolCategory.CALM -> CalmLavender
                                else -> ChampagneGold
                            }
                            StyledFilterChip(
                                label = cat.label,
                                selected = selectedSymbolCategory == cat,
                                onClick = { onSymbolCategoryChanged(cat) },
                                accentColor = accent,
                                testTag = "symbol_cat_${cat.name.lowercase()}"
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "اضغط على أي رمز للنسخ المباشر، أو (+) لإضافته للصانع:",
                        style = MaterialTheme.typography.labelLarge,
                        color = SilverMuted
                    )
                }

                // Render symbols in balanced 2-column rows inside LazyColumn
                val chunked = symbols.chunked(2)
                items(
                    items = chunked,
                    key = { pair -> pair.joinToString("_") { it.id } }
                ) { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { sym ->
                            val accent = when (sym.category) {
                                SymbolCategory.CYBER -> CyberCyan
                                SymbolCategory.CALM -> CalmLavender
                                else -> ChampagneGold
                            }
                            SymbolTileCard(
                                item = sym,
                                accentColor = accent,
                                onCopy = {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    val copyValue = if (sym.isPairFrame) {
                                        "${sym.leftPart}  ${sym.rightPart}"
                                    } else {
                                        sym.symbol
                                    }
                                    copyTextToClipboard(context, copyValue)
                                    onShowMessage("تم نسخ الرمز: $copyValue")
                                },
                                onAddToComposer = {
                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    onApplySymbolToComposer(sym)
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
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
private fun SymbolTileCard(
    item: DecorativeSymbolItem,
    accentColor: Color,
    onCopy: () -> Unit,
    onAddToComposer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MidnightSurface,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, CardBorderSubtle),
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .testTag("symbol_card_${item.id}")
            .clickable(onClick = onCopy)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = SilverMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    color = accentColor.copy(alpha = 0.16f),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(onClick = onAddToComposer)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "إضافة للصانع",
                        tint = accentColor,
                        modifier = Modifier.padding(6.dp).size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.symbol,
                style = MaterialTheme.typography.titleLarge,
                color = PearlWhite,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (item.isPairFrame) "إطار عربي / EN" else "نسخ سريع",
                style = MaterialTheme.typography.labelSmall,
                color = accentColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
