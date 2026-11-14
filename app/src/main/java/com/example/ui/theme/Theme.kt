package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val ColorBorderMuted = CardBorderSubtle.copy(alpha = 0.55f)

private val ZakhraflyDarkColorScheme = darkColorScheme(
    primary = ChampagneGold,
    onPrimary = OnGold,
    primaryContainer = DeepBronze,
    onPrimaryContainer = ChampagneGoldLight,
    secondary = CyberCyan,
    onSecondary = OnCyan,
    secondaryContainer = CyberCyanDim,
    onSecondaryContainer = CyberCyan,
    tertiary = CalmLavender,
    onTertiary = OnLavender,
    tertiaryContainer = CalmLavenderDim,
    onTertiaryContainer = CalmLavender,
    background = ObsidianBg,
    onBackground = PearlWhite,
    surface = MidnightSurface,
    onSurface = PearlWhite,
    surfaceVariant = ElevatedCharcoal,
    onSurfaceVariant = SilverMuted,
    outline = CardBorderSubtle,
    outlineVariant = ColorBorderMuted,
    error = RoseError,
    onError = ObsidianBg
)

private val ZakhraflyLightColorScheme = lightColorScheme(
    primary = RoyalGoldDark,
    onPrimary = IvorySurface,
    background = IvoryBg,
    onBackground = ObsidianBg,
    surface = IvorySurface,
    onSurface = ObsidianBg
)

val ZakhraflyShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun ZakhraflyTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) ZakhraflyDarkColorScheme else ZakhraflyLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = ZakhraflyShapes,
        content = content
    )
}
