package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.BioCategory
import com.example.ui.theme.CalmLavender
import com.example.ui.theme.CalmLavenderDim
import com.example.ui.theme.CardBorderSubtle
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.CyberCyanDim
import com.example.ui.theme.DeepBronze
import com.example.ui.theme.ElevatedCharcoal
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.RoseError
import com.example.ui.theme.SilverMuted
import kotlinx.coroutines.delay

fun copyTextToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    val clip = ClipData.newPlainText("Zakhrafly", text)
    clipboard?.setPrimaryClip(clip)
}

fun shareText(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "مشاركة النص"))
}

fun categoryAccentColor(category: BioCategory?): Color = when (category) {
    BioCategory.ROYAL_LUXURY -> ChampagneGold
    BioCategory.CALM_UNIQUE -> CalmLavender
    BioCategory.TECH_CYBER -> CyberCyan
    null -> ChampagneGold
}

fun categoryContainerColor(category: BioCategory?): Color = when (category) {
    BioCategory.ROYAL_LUXURY -> DeepBronze
    BioCategory.CALM_UNIQUE -> CalmLavenderDim
    BioCategory.TECH_CYBER -> CyberCyanDim
    null -> DeepBronze
}

fun categoryIcon(category: BioCategory?): ImageVector = when (category) {
    BioCategory.ROYAL_LUXURY -> Icons.Filled.WorkspacePremium
    BioCategory.CALM_UNIQUE -> Icons.Filled.NightsStay
    BioCategory.TECH_CYBER -> Icons.Filled.Terminal
    null -> Icons.Filled.AutoAwesome
}

@Composable
fun HeroBannerHeader(
    title: String,
    subtitle: String,
    badgeText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(148.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MidnightSurface)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_hero_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.52f,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ObsidianBg.copy(alpha = 0.25f),
                            ObsidianBg.copy(alpha = 0.88f)
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            Surface(
                color = DeepBronze.copy(alpha = 0.85f),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, ChampagneGold.copy(alpha = 0.45f)),
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        tint = ChampagneGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.labelMedium,
                        color = ChampagneGold
                    )
                }
            }

            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = PearlWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SilverMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun StyledFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    accentColor: Color = ChampagneGold,
    icon: ImageVector? = null,
    testTag: String = ""
) {
    val bgColor by animateColorAsState(
        targetValue = if (selected) accentColor.copy(alpha = 0.18f) else ElevatedCharcoal,
        label = "chipBg"
    )
    val borderColor by animateColorAsState(
        targetValue = if (selected) accentColor else CardBorderSubtle,
        label = "chipBorder"
    )
    val contentColor by animateColorAsState(
        targetValue = if (selected) accentColor else SilverMuted,
        label = "chipContent"
    )

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .clip(RoundedCornerShape(14.dp))
            .then(if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) PearlWhite else SilverMuted,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}

@Composable
fun CopyableSnippetCard(
    text: String,
    badgeLabel: String,
    accentColor: Color,
    badgeIcon: ImageVector?,
    isFavorite: Boolean,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onToggleFavorite: () -> Unit,
    onDelete: (() -> Unit)? = null,
    cardTestTag: String = "",
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    var justCopied by remember { mutableStateOf(false) }

    LaunchedEffect(justCopied) {
        if (justCopied) {
            delay(1500)
            justCopied = false
        }
    }

    val heartScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.15f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "heartScale"
    )

    val borderColor by animateColorAsState(
        targetValue = if (justCopied) EmeraldSuccess else CardBorderSubtle,
        label = "cardBorder"
    )

    Surface(
        color = MidnightSurface,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, borderColor),
        tonalElevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .then(if (cardTestTag.isNotEmpty()) Modifier.testTag(cardTestTag) else Modifier)
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                justCopied = true
                onCopy()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = accentColor.copy(alpha = 0.14f),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(0.8.dp, accentColor.copy(alpha = 0.35f))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        if (badgeIcon != null) {
                            Icon(
                                imageVector = badgeIcon,
                                contentDescription = null,
                                tint = accentColor,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                        Text(
                            text = badgeLabel,
                            style = MaterialTheme.typography.labelMedium,
                            color = accentColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (onDelete != null) {
                        IconButton(
                            onClick = onDelete,
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("delete_favorite_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DeleteOutline,
                                contentDescription = "حذف من المفضلة",
                                tint = RoseError
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                onToggleFavorite()
                            },
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .scale(heartScale)
                                .testTag("favorite_toggle_button")
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = if (isFavorite) "إزالة من المفضلة" else "حفظ في المفضلة",
                                tint = if (isFavorite) ChampagneGold else SilverMuted
                            )
                        }
                    }

                    IconButton(
                        onClick = onShare,
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("share_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "مشاركة",
                            tint = SilverMuted
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = PearlWhite,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "اضغط على البطاقة للنسخ الفوري",
                    style = MaterialTheme.typography.labelMedium,
                    color = SilverMuted
                )

                Surface(
                    color = if (justCopied) EmeraldSuccess.copy(alpha = 0.2f) else ElevatedCharcoal,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        1.dp,
                        if (justCopied) EmeraldSuccess else accentColor.copy(alpha = 0.45f)
                    ),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .testTag("copy_button")
                        .clickable {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            justCopied = true
                            onCopy()
                        }
                ) {
                    AnimatedContent(targetState = justCopied, label = "copyBtnState") { copied ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (copied) Icons.Filled.Check else Icons.Filled.ContentCopy,
                                contentDescription = "نسخ",
                                tint = if (copied) EmeraldSuccess else accentColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (copied) "تم النسخ" else "نسخ",
                                style = MaterialTheme.typography.labelLarge,
                                color = if (copied) EmeraldSuccess else PearlWhite
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomBioDialog(
    onDismiss: () -> Unit,
    onSave: (String, BioCategory) -> Unit
) {
    var bioText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(BioCategory.ROYAL_LUXURY) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MidnightSurface,
        titleContentColor = PearlWhite,
        textContentColor = SilverMuted,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(DeepBronze)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        tint = ChampagneGold,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "إضافة بايو مخصص",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = bioText,
                    onValueChange = { bioText = it },
                    placeholder = {
                        Text(
                            text = "اكتب عبارتك الخاصة لحفظها في المفضلة...",
                            color = SilverMuted
                        )
                    },
                    minLines = 3,
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ChampagneGold,
                        unfocusedBorderColor = CardBorderSubtle,
                        focusedTextColor = PearlWhite,
                        unfocusedTextColor = PearlWhite,
                        cursorColor = ChampagneGold
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("custom_bio_input")
                )

                Text(
                    text = "اختر التصنيف:",
                    style = MaterialTheme.typography.labelLarge,
                    color = PearlWhite
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    BioCategory.entries.forEach { cat ->
                        val accent = categoryAccentColor(cat)
                        val isSelected = selectedCategory == cat
                        Surface(
                            color = if (isSelected) accent.copy(alpha = 0.16f) else ElevatedCharcoal,
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, if (isSelected) accent else CardBorderSubtle),
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { selectedCategory = cat }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                            ) {
                                Icon(
                                    imageVector = categoryIcon(cat),
                                    contentDescription = null,
                                    tint = accent,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = cat.label,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = PearlWhite
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (bioText.isNotBlank()) {
                        onSave(bioText.trim(), selectedCategory)
                        onDismiss()
                    }
                },
                enabled = bioText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ChampagneGold,
                    contentColor = ObsidianBg
                ),
                modifier = Modifier.testTag("save_custom_bio_button")
            ) {
                Text("حفظ في المفضلة")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء", color = SilverMuted)
            }
        }
    )
}
