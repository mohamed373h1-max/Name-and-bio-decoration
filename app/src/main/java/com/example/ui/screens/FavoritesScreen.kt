package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.local.FavoriteEntity
import com.example.domain.BioCategory
import com.example.domain.FavoriteCategoryKeys
import com.example.domain.FavoriteFilter
import com.example.ui.components.CopyableSnippetCard
import com.example.ui.components.HeroBannerHeader
import com.example.ui.components.StyledFilterChip
import com.example.ui.components.categoryAccentColor
import com.example.ui.components.categoryIcon
import com.example.ui.components.copyTextToClipboard
import com.example.ui.components.shareText
import com.example.ui.theme.CalmLavender
import com.example.ui.theme.CardBorderSubtle
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.RoseError
import com.example.ui.theme.SilverMuted

@Composable
fun FavoritesScreen(
    favorites: List<FavoriteEntity>,
    totalCount: Int,
    selectedFilter: FavoriteFilter,
    searchQuery: String,
    onFilterChanged: (FavoriteFilter) -> Unit,
    onSearchChanged: (String) -> Unit,
    onRemoveFavorite: (FavoriteEntity) -> Unit,
    onClearAll: () -> Unit,
    onNavigateToDecorator: () -> Unit,
    onNavigateToBio: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showClearConfirm by remember { mutableStateOf(false) }

    if (showClearConfirm) {
        AlertDialog(
            onDismissRequest = { showClearConfirm = false },
            containerColor = MidnightSurface,
            titleContentColor = PearlWhite,
            textContentColor = SilverMuted,
            title = { Text("مسح جميع المفضلات؟") },
            text = { Text("سيتم حذف جميع الزخارف والعبارات المحفوظة نهائياً.") },
            confirmButton = {
                Button(
                    onClick = {
                        onClearAll()
                        showClearConfirm = false
                        onShowMessage("تم مسح المفضلة")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RoseError,
                        contentColor = ObsidianBg
                    )
                ) {
                    Text("مسح الكل")
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearConfirm = false }) {
                    Text("إلغاء", color = SilverMuted)
                }
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
                    title = stringResource(R.string.favorites_title),
                    subtitle = stringResource(R.string.favorites_subtitle),
                    badgeText = "$totalCount محفوظات سريعة"
                )
            }

            if (totalCount > 0) {
                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onSearchChanged,
                        placeholder = {
                            Text("ابحث في مفضلاتك...", color = SilverMuted)
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
                            .testTag("favorites_search_input")
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(FavoriteFilter.entries) { filter ->
                            val accent = when (filter) {
                                FavoriteFilter.ALL -> ChampagneGold
                                FavoriteFilter.DECORATED_NAMES -> ChampagneGold
                                FavoriteFilter.ROYAL_LUXURY -> ChampagneGold
                                FavoriteFilter.CALM_UNIQUE -> CalmLavender
                                FavoriteFilter.TECH_CYBER -> CyberCyan
                                FavoriteFilter.READY_ABOUT -> ChampagneGold
                            }
                            StyledFilterChip(
                                label = filter.label,
                                selected = selectedFilter == filter,
                                onClick = { onFilterChanged(filter) },
                                accentColor = accent,
                                testTag = "fav_filter_${filter.name.lowercase()}"
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
                            text = "العناصر المحفوظة (${favorites.size})",
                            style = MaterialTheme.typography.titleMedium,
                            color = PearlWhite,
                            fontWeight = FontWeight.Bold
                        )

                        TextButton(
                            onClick = { showClearConfirm = true },
                            modifier = Modifier.testTag("clear_all_favorites_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DeleteSweep,
                                contentDescription = null,
                                tint = RoseError,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("مسح الكل", color = RoseError)
                        }
                    }
                }
            }

            if (favorites.isEmpty()) {
                item {
                    Surface(
                        color = MidnightSurface,
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(1.dp, CardBorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_empty_favorites),
                                contentDescription = "لا توجد مفضلات محفوظة",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(RoundedCornerShape(20.dp))
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = if (totalCount == 0) "قائمة مفضلاتك فارغة حالياً" else "لا توجد نتائج مطابقة",
                                style = MaterialTheme.typography.titleLarge,
                                color = PearlWhite,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (totalCount == 0) {
                                    "اضغط على رمز القلب بجانب أي اسم مزخرف أو عبارة بايو أو لقب عشوائي لحفظه هنا."
                                } else {
                                    "جرّب اختيار تصنيف آخر أو مسح نص البحث."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = SilverMuted,
                                textAlign = TextAlign.Center
                            )
                            if (totalCount == 0) {
                                Spacer(modifier = Modifier.height(18.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(
                                        onClick = onNavigateToDecorator,
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = ChampagneGold,
                                            contentColor = ObsidianBg
                                        ),
                                        shape = RoundedCornerShape(14.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("empty_go_decorator")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.AutoFixHigh,
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("زخرفة الأسماء")
                                    }
                                    OutlinedButton(
                                        onClick = onNavigateToBio,
                                        border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.6f)),
                                        shape = RoundedCornerShape(14.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("empty_go_bio")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.AutoAwesome,
                                            contentDescription = null,
                                            tint = ChampagneGold,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("تصفح البايو", color = ChampagneGold)
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                items(
                    items = favorites,
                    key = { it.id }
                ) { entity ->
                    val bioCat = BioCategory.fromStorageKey(entity.category)
                    val accent = categoryAccentColor(bioCat)
                    val icon = when {
                        entity.category == FavoriteCategoryKeys.READY_ABOUT -> Icons.Filled.Badge
                        bioCat != null -> categoryIcon(bioCat)
                        else -> Icons.Filled.Favorite
                    }

                    CopyableSnippetCard(
                        text = entity.content,
                        badgeLabel = entity.styleLabel,
                        accentColor = accent,
                        badgeIcon = icon,
                        isFavorite = true,
                        onCopy = {
                            copyTextToClipboard(context, entity.content)
                            onShowMessage("تم النسخ بنجاح")
                        },
                        onShare = {
                            shareText(context, entity.content)
                        },
                        onToggleFavorite = {},
                        onDelete = {
                            onRemoveFavorite(entity)
                        },
                        cardTestTag = "favorite_card_${entity.id}"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
