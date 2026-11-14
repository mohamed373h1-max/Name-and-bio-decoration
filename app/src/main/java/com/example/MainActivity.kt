package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.FavoriteCategoryKeys
import com.example.ui.screens.BioScreen
import com.example.ui.screens.DecoratorScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.SuggestionsScreen
import com.example.ui.theme.ChampagneGold
import com.example.ui.theme.DeepBronze
import com.example.ui.theme.ElevatedCharcoal
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PearlWhite
import com.example.ui.theme.SilverMuted
import com.example.ui.theme.ZakhraflyTheme
import com.example.ui.viewmodel.ZakhraflyViewModel
import kotlinx.coroutines.launch

enum class AppTab(
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    DECORATOR(
        labelRes = R.string.nav_decorator,
        selectedIcon = Icons.Filled.AutoFixHigh,
        unselectedIcon = Icons.Outlined.AutoFixHigh,
        testTag = "nav_decorator_tab"
    ),
    SUGGESTIONS(
        labelRes = R.string.nav_suggestions,
        selectedIcon = Icons.Filled.Stars,
        unselectedIcon = Icons.Outlined.Stars,
        testTag = "nav_suggestions_tab"
    ),
    BIO(
        labelRes = R.string.nav_bio,
        selectedIcon = Icons.Filled.AutoAwesome,
        unselectedIcon = Icons.Outlined.AutoAwesome,
        testTag = "nav_bio_tab"
    ),
    FAVORITES(
        labelRes = R.string.nav_favorites,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        testTag = "nav_favorites_tab"
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(ObsidianBg.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(MidnightSurface.toArgb())
        )
        setContent {
            ZakhraflyTheme(darkTheme = true) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    ZakhraflyApp()
                }
            }
        }
    }
}

@Composable
fun ZakhraflyApp(
    viewModel: ZakhraflyViewModel = viewModel()
) {
    var currentTab by rememberSaveable { mutableStateOf(AppTab.DECORATOR) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val decoratorSectionTab by viewModel.decoratorSectionTab.collectAsStateWithLifecycle()
    val nameInput by viewModel.nameInput.collectAsStateWithLifecycle()
    val decoratorFilter by viewModel.decoratorFilter.collectAsStateWithLifecycle()
    val decoratedResults by viewModel.decoratedResults.collectAsStateWithLifecycle()
    val selectedSymbolCategory by viewModel.selectedSymbolCategory.collectAsStateWithLifecycle()
    val filteredSymbols by viewModel.filteredSymbols.collectAsStateWithLifecycle()
    val composedSymbolText by viewModel.composedSymbolText.collectAsStateWithLifecycle()

    val suggestionsTab by viewModel.suggestionsTab.collectAsStateWithLifecycle()
    val suggestedCategoryFilter by viewModel.suggestedCategoryFilter.collectAsStateWithLifecycle()
    val suggestedLanguageFilter by viewModel.suggestedLanguageFilter.collectAsStateWithLifecycle()
    val filteredSuggestedNames by viewModel.filteredSuggestedNames.collectAsStateWithLifecycle()
    val randomVibeFilter by viewModel.randomVibeFilter.collectAsStateWithLifecycle()
    val randomLanguageFilter by viewModel.randomLanguageFilter.collectAsStateWithLifecycle()
    val randomNicknames by viewModel.randomNicknames.collectAsStateWithLifecycle()
    val selectedAgeGroup by viewModel.selectedAgeGroup.collectAsStateWithLifecycle()
    val ageRecommendations by viewModel.ageRecommendations.collectAsStateWithLifecycle()

    val bioSectionTab by viewModel.bioSectionTab.collectAsStateWithLifecycle()
    val bioCategory by viewModel.bioCategoryFilter.collectAsStateWithLifecycle()
    val bioLanguage by viewModel.bioLanguageFilter.collectAsStateWithLifecycle()
    val bioSearch by viewModel.bioSearchQuery.collectAsStateWithLifecycle()
    val filteredBios by viewModel.filteredBios.collectAsStateWithLifecycle()
    val filteredReadyAbouts by viewModel.filteredReadyAbouts.collectAsStateWithLifecycle()

    val allFavorites by viewModel.allFavorites.collectAsStateWithLifecycle()
    val favoriteContents by viewModel.favoriteContentsSet.collectAsStateWithLifecycle()
    val filteredFavorites by viewModel.filteredFavorites.collectAsStateWithLifecycle()
    val favoriteFilter by viewModel.favoriteFilter.collectAsStateWithLifecycle()
    val favoriteSearch by viewModel.favoriteSearchQuery.collectAsStateWithLifecycle()

    fun showMessage(msg: String) {
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
        }
    }

    if (currentTab != AppTab.DECORATOR) {
        BackHandler {
            currentTab = AppTab.DECORATOR
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
    ) {
        val useNavigationRail = maxWidth >= 600.dp

        Scaffold(
            containerColor = ObsidianBg,
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = ElevatedCharcoal,
                        contentColor = PearlWhite,
                        actionColor = ChampagneGold
                    )
                }
            },
            bottomBar = {
                if (!useNavigationRail) {
                    NavigationBar(
                        containerColor = MidnightSurface,
                        contentColor = PearlWhite,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        AppTab.entries.forEach { tab ->
                            val selected = currentTab == tab
                            NavigationBarItem(
                                selected = selected,
                                onClick = { currentTab = tab },
                                icon = {
                                    TabNavIcon(
                                        tab = tab,
                                        selected = selected,
                                        favoritesCount = allFavorites.size
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(tab.labelRes),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = ChampagneGold,
                                    selectedTextColor = ChampagneGold,
                                    indicatorColor = DeepBronze,
                                    unselectedIconColor = SilverMuted,
                                    unselectedTextColor = SilverMuted
                                ),
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (useNavigationRail) {
                    NavigationRail(
                        containerColor = MidnightSurface,
                        contentColor = PearlWhite,
                        modifier = Modifier
                            .fillMaxHeight()
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    ) {
                        AppTab.entries.forEach { tab ->
                            val selected = currentTab == tab
                            NavigationRailItem(
                                selected = selected,
                                onClick = { currentTab = tab },
                                icon = {
                                    TabNavIcon(
                                        tab = tab,
                                        selected = selected,
                                        favoritesCount = allFavorites.size
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(tab.labelRes),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = ChampagneGold,
                                    selectedTextColor = ChampagneGold,
                                    indicatorColor = DeepBronze,
                                    unselectedIconColor = SilverMuted,
                                    unselectedTextColor = SilverMuted
                                ),
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    when (currentTab) {
                        AppTab.DECORATOR -> DecoratorScreen(
                            activeSectionTab = decoratorSectionTab,
                            nameInput = nameInput,
                            selectedFilter = decoratorFilter,
                            results = decoratedResults,
                            selectedSymbolCategory = selectedSymbolCategory,
                            symbols = filteredSymbols,
                            composedSymbolText = composedSymbolText,
                            favoriteContents = favoriteContents,
                            onSectionTabChanged = viewModel::setDecoratorSectionTab,
                            onNameInputChanged = viewModel::onNameInputChanged,
                            onFilterChanged = viewModel::setDecoratorFilter,
                            onAppendSymbol = viewModel::appendSymbolToName,
                            onSymbolCategoryChanged = viewModel::setSymbolCategory,
                            onComposedSymbolChanged = viewModel::onComposedSymbolChanged,
                            onApplySymbolToComposer = viewModel::applySymbolToComposer,
                            onClearComposedSymbol = viewModel::clearComposedSymbol,
                            onSendComposedToDecorator = viewModel::sendComposedToDecorator,
                            onSaveComposedToFavorites = { composed ->
                                viewModel.toggleFavorite(
                                    content = composed,
                                    category = FavoriteCategoryKeys.DECORATED_NAME,
                                    styleLabel = "تركيبة رموز مخصصة"
                                )
                            },
                            onToggleFavorite = { result ->
                                viewModel.toggleFavorite(
                                    content = result.decoratedText,
                                    category = FavoriteCategoryKeys.DECORATED_NAME,
                                    styleLabel = result.styleName
                                ) { saved ->
                                    showMessage(if (saved) "تم الحفظ في المفضلة" else "تمت الإزالة من المفضلة")
                                }
                            },
                            onShowMessage = ::showMessage
                        )

                        AppTab.SUGGESTIONS -> SuggestionsScreen(
                            activeTab = suggestionsTab,
                            suggestedCategoryFilter = suggestedCategoryFilter,
                            suggestedLanguageFilter = suggestedLanguageFilter,
                            suggestedNames = filteredSuggestedNames,
                            randomVibeFilter = randomVibeFilter,
                            randomLanguageFilter = randomLanguageFilter,
                            randomNicknames = randomNicknames,
                            selectedAgeGroup = selectedAgeGroup,
                            ageRecommendations = ageRecommendations,
                            favoriteContents = favoriteContents,
                            onTabChanged = viewModel::setSuggestionsTab,
                            onSuggestedCategoryChanged = viewModel::setSuggestedCategoryFilter,
                            onSuggestedLanguageChanged = viewModel::setSuggestedLanguageFilter,
                            onRandomVibeChanged = viewModel::setRandomVibeFilter,
                            onRandomLanguageChanged = viewModel::setRandomLanguageFilter,
                            onRegenerateRandom = viewModel::regenerateRandomNicknames,
                            onAgeGroupChanged = viewModel::setSelectedAgeGroup,
                            onToggleFavorite = { content, categoryKey, styleLabel ->
                                viewModel.toggleFavorite(
                                    content = content,
                                    category = categoryKey,
                                    styleLabel = styleLabel
                                ) { saved ->
                                    showMessage(if (saved) "تم الحفظ في المفضلة" else "تمت الإزالة من المفضلة")
                                }
                            },
                            onShowMessage = ::showMessage
                        )

                        AppTab.BIO -> BioScreen(
                            activeSectionTab = bioSectionTab,
                            selectedCategory = bioCategory,
                            selectedLanguage = bioLanguage,
                            searchQuery = bioSearch,
                            bios = filteredBios,
                            readyAbouts = filteredReadyAbouts,
                            favoriteContents = favoriteContents,
                            onSectionTabChanged = viewModel::setBioSectionTab,
                            onCategoryChanged = viewModel::setBioCategoryFilter,
                            onLanguageChanged = viewModel::setBioLanguageFilter,
                            onSearchChanged = viewModel::onBioSearchChanged,
                            onToggleFavoriteBio = { bio ->
                                viewModel.toggleFavorite(
                                    content = bio.text,
                                    category = bio.category.storageKey,
                                    styleLabel = bio.category.label
                                ) { saved ->
                                    showMessage(if (saved) "تم الحفظ في المفضلة" else "تمت الإزالة من المفضلة")
                                }
                            },
                            onToggleFavoriteReadyAbout = { tpl ->
                                viewModel.toggleFavorite(
                                    content = tpl.content,
                                    category = FavoriteCategoryKeys.READY_ABOUT,
                                    styleLabel = "${tpl.title} • ${tpl.category.label}"
                                ) { saved ->
                                    showMessage(if (saved) "تم الحفظ في المفضلة" else "تمت الإزالة من المفضلة")
                                }
                            },
                            onSaveCustomBio = viewModel::saveCustomBioToFavorites,
                            onShowMessage = ::showMessage
                        )

                        AppTab.FAVORITES -> FavoritesScreen(
                            favorites = filteredFavorites,
                            totalCount = allFavorites.size,
                            selectedFilter = favoriteFilter,
                            searchQuery = favoriteSearch,
                            onFilterChanged = viewModel::setFavoriteFilter,
                            onSearchChanged = viewModel::onFavoriteSearchChanged,
                            onRemoveFavorite = { entity ->
                                viewModel.removeFavorite(entity) { removed ->
                                    scope.launch {
                                        snackbarHostState.currentSnackbarData?.dismiss()
                                        val res = snackbarHostState.showSnackbar(
                                            message = "تمت الإزالة من المفضلة",
                                            actionLabel = "تراجع",
                                            duration = SnackbarDuration.Short
                                        )
                                        if (res == SnackbarResult.ActionPerformed) {
                                            viewModel.restoreFavorite(removed)
                                        }
                                    }
                                }
                            },
                            onClearAll = viewModel::clearAllFavorites,
                            onNavigateToDecorator = { currentTab = AppTab.DECORATOR },
                            onNavigateToBio = { currentTab = AppTab.BIO },
                            onShowMessage = ::showMessage
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TabNavIcon(
    tab: AppTab,
    selected: Boolean,
    favoritesCount: Int
) {
    val icon = if (selected) tab.selectedIcon else tab.unselectedIcon
    if (tab == AppTab.FAVORITES && favoritesCount > 0) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = ChampagneGold,
                    contentColor = Color(0xFF1A1405)
                ) {
                    Text(text = if (favoritesCount > 99) "99+" else favoritesCount.toString())
                }
            }
        ) {
            Icon(imageVector = icon, contentDescription = stringResource(tab.labelRes))
        }
    } else {
        Icon(imageVector = icon, contentDescription = stringResource(tab.labelRes))
    }
}
