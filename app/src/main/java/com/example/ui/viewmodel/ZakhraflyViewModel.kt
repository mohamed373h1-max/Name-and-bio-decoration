package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.FavoriteEntity
import com.example.data.repository.FavoritesRepository
import com.example.domain.AgeGroup
import com.example.domain.AgeRecommendationItem
import com.example.domain.BioCatalog
import com.example.domain.BioCategory
import com.example.domain.BioQuote
import com.example.domain.BioSectionTab
import com.example.domain.DecoratedResult
import com.example.domain.DecoratorFilter
import com.example.domain.DecoratorSectionTab
import com.example.domain.DecorativeSymbolItem
import com.example.domain.FavoriteCategoryKeys
import com.example.domain.FavoriteFilter
import com.example.domain.GeneratedNickname
import com.example.domain.LanguageFilter
import com.example.domain.NameDecoratorEngine
import com.example.domain.ReadyAboutTemplate
import com.example.domain.SuggestedCatalog
import com.example.domain.SuggestedDecoratedName
import com.example.domain.SuggestionsSectionTab
import com.example.domain.SymbolCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ZakhraflyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoritesRepository by lazy {
        val dao = AppDatabase.getInstance(application).favoriteDao()
        FavoritesRepository(dao)
    }

    // --- Name Decorator & Decorative Symbols State ---
    private val _decoratorSectionTab = MutableStateFlow(DecoratorSectionTab.NAME_DECORATOR)
    val decoratorSectionTab: StateFlow<DecoratorSectionTab> = _decoratorSectionTab

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> = _nameInput

    private val _decoratorFilter = MutableStateFlow(DecoratorFilter.ALL)
    val decoratorFilter: StateFlow<DecoratorFilter> = _decoratorFilter

    val decoratedResults: StateFlow<List<DecoratedResult>> = combine(
        _nameInput,
        _decoratorFilter
    ) { input, filter ->
        val generated = NameDecoratorEngine.generateDecorations(input)
        if (filter == DecoratorFilter.ALL) {
            generated
        } else {
            generated.filter { it.filter == filter }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NameDecoratorEngine.generateDecorations("")
    )

    // Decorative Symbols Studio State
    private val _selectedSymbolCategory = MutableStateFlow(SymbolCategory.ALL)
    val selectedSymbolCategory: StateFlow<SymbolCategory> = _selectedSymbolCategory

    private val _composedSymbolText = MutableStateFlow("")
    val composedSymbolText: StateFlow<String> = _composedSymbolText

    val filteredSymbols: StateFlow<List<DecorativeSymbolItem>> = _selectedSymbolCategory
        .map { category ->
            if (category == SymbolCategory.ALL) {
                SuggestedCatalog.decorativeSymbols
            } else {
                SuggestedCatalog.decorativeSymbols.filter { it.category == category }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SuggestedCatalog.decorativeSymbols
        )

    // --- Suggestions, Random Nickname & Age Group State ---
    private val _suggestionsTab = MutableStateFlow(SuggestionsSectionTab.SUGGESTED_NAMES)
    val suggestionsTab: StateFlow<SuggestionsSectionTab> = _suggestionsTab

    private val _suggestedCategoryFilter = MutableStateFlow<BioCategory?>(null)
    val suggestedCategoryFilter: StateFlow<BioCategory?> = _suggestedCategoryFilter

    private val _suggestedLanguageFilter = MutableStateFlow(LanguageFilter.ALL)
    val suggestedLanguageFilter: StateFlow<LanguageFilter> = _suggestedLanguageFilter

    val filteredSuggestedNames: StateFlow<List<SuggestedDecoratedName>> = combine(
        _suggestedCategoryFilter,
        _suggestedLanguageFilter
    ) { cat, lang ->
        SuggestedCatalog.suggestedNames.filter { item ->
            val matchCat = cat == null || item.category == cat
            val matchLang = when (lang) {
                LanguageFilter.ALL -> true
                LanguageFilter.ARABIC -> item.isArabic
                LanguageFilter.ENGLISH -> !item.isArabic
            }
            matchCat && matchLang
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SuggestedCatalog.suggestedNames
    )

    // Random Nickname Generator State
    private val _randomVibeFilter = MutableStateFlow<BioCategory?>(null)
    val randomVibeFilter: StateFlow<BioCategory?> = _randomVibeFilter

    private val _randomLanguageFilter = MutableStateFlow(LanguageFilter.ALL)
    val randomLanguageFilter: StateFlow<LanguageFilter> = _randomLanguageFilter

    private val _randomNicknames = MutableStateFlow(
        SuggestedCatalog.generateRandomNicknames(null, LanguageFilter.ALL, count = 4)
    )
    val randomNicknames: StateFlow<List<GeneratedNickname>> = _randomNicknames

    // Age Group State
    private val _selectedAgeGroup = MutableStateFlow(AgeGroup.YOUTH)
    val selectedAgeGroup: StateFlow<AgeGroup> = _selectedAgeGroup

    val ageRecommendations: StateFlow<List<AgeRecommendationItem>> = _selectedAgeGroup
        .map { group ->
            SuggestedCatalog.ageRecommendations.filter { it.ageGroup == group }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SuggestedCatalog.ageRecommendations.filter { it.ageGroup == AgeGroup.YOUTH }
        )

    // --- Bio & Ready About State ---
    private val _bioSectionTab = MutableStateFlow(BioSectionTab.BIO_QUOTES)
    val bioSectionTab: StateFlow<BioSectionTab> = _bioSectionTab

    private val _bioCategoryFilter = MutableStateFlow<BioCategory?>(null)
    val bioCategoryFilter: StateFlow<BioCategory?> = _bioCategoryFilter

    private val _bioLanguageFilter = MutableStateFlow(LanguageFilter.ALL)
    val bioLanguageFilter: StateFlow<LanguageFilter> = _bioLanguageFilter

    private val _bioSearchQuery = MutableStateFlow("")
    val bioSearchQuery: StateFlow<String> = _bioSearchQuery

    val filteredBios: StateFlow<List<BioQuote>> = combine(
        _bioCategoryFilter,
        _bioLanguageFilter,
        _bioSearchQuery
    ) { category, lang, query ->
        BioCatalog.allBios.filter { bio ->
            val matchCat = category == null || bio.category == category
            val matchLang = when (lang) {
                LanguageFilter.ALL -> true
                LanguageFilter.ARABIC -> bio.isArabic
                LanguageFilter.ENGLISH -> !bio.isArabic
            }
            val q = query.trim()
            val matchQuery = q.isEmpty() || bio.text.contains(q, ignoreCase = true)
            matchCat && matchLang && matchQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BioCatalog.allBios
    )

    val filteredReadyAbouts: StateFlow<List<ReadyAboutTemplate>> = combine(
        _bioCategoryFilter,
        _bioLanguageFilter,
        _bioSearchQuery
    ) { category, lang, query ->
        SuggestedCatalog.readyAboutTemplates.filter { tpl ->
            val matchCat = category == null || tpl.category == category
            val matchLang = when (lang) {
                LanguageFilter.ALL -> true
                LanguageFilter.ARABIC -> tpl.isArabic
                LanguageFilter.ENGLISH -> !tpl.isArabic
            }
            val q = query.trim()
            val matchQuery = q.isEmpty() ||
                tpl.content.contains(q, ignoreCase = true) ||
                tpl.title.contains(q, ignoreCase = true)
            matchCat && matchLang && matchQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SuggestedCatalog.readyAboutTemplates
    )

    // --- Favorites State (Backed by Room) ---
    val allFavorites: StateFlow<List<FavoriteEntity>> = repository.allFavorites
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val favoriteContentsSet: StateFlow<Set<String>> = allFavorites
        .map { list -> list.map { it.content }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    private val _favoriteFilter = MutableStateFlow(FavoriteFilter.ALL)
    val favoriteFilter: StateFlow<FavoriteFilter> = _favoriteFilter

    private val _favoriteSearchQuery = MutableStateFlow("")
    val favoriteSearchQuery: StateFlow<String> = _favoriteSearchQuery

    val filteredFavorites: StateFlow<List<FavoriteEntity>> = combine(
        allFavorites,
        _favoriteFilter,
        _favoriteSearchQuery
    ) { items, filter, query ->
        val q = query.trim()
        items.filter { item ->
            val matchFilter = when (filter) {
                FavoriteFilter.ALL -> true
                FavoriteFilter.DECORATED_NAMES -> item.category == FavoriteCategoryKeys.DECORATED_NAME
                FavoriteFilter.ROYAL_LUXURY -> item.category == BioCategory.ROYAL_LUXURY.storageKey
                FavoriteFilter.CALM_UNIQUE -> item.category == BioCategory.CALM_UNIQUE.storageKey
                FavoriteFilter.TECH_CYBER -> item.category == BioCategory.TECH_CYBER.storageKey
                FavoriteFilter.READY_ABOUT -> item.category == FavoriteCategoryKeys.READY_ABOUT
            }
            val matchQuery = q.isEmpty() ||
                item.content.contains(q, ignoreCase = true) ||
                item.styleLabel.contains(q, ignoreCase = true)
            matchFilter && matchQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // --- Decorator & Symbols Actions ---
    fun setDecoratorSectionTab(tab: DecoratorSectionTab) {
        _decoratorSectionTab.value = tab
    }

    fun onNameInputChanged(value: String) {
        _nameInput.value = value
    }

    fun appendSymbolToName(symbol: String) {
        val current = _nameInput.value
        _nameInput.value = if (current.isBlank()) symbol else "$current $symbol"
    }

    fun setDecoratorFilter(filter: DecoratorFilter) {
        _decoratorFilter.value = filter
    }

    fun setSymbolCategory(category: SymbolCategory) {
        _selectedSymbolCategory.value = category
    }

    fun onComposedSymbolChanged(value: String) {
        _composedSymbolText.value = value
    }

    fun applySymbolToComposer(item: DecorativeSymbolItem) {
        val current = _composedSymbolText.value.trim()
        _composedSymbolText.value = if (item.isPairFrame) {
            val inner = current.ifEmpty { _nameInput.value.trim().ifEmpty { "اسمك | Name" } }
            "${item.leftPart} $inner ${item.rightPart}"
        } else {
            if (current.isEmpty()) item.symbol else "$current ${item.symbol}"
        }
    }

    fun clearComposedSymbol() {
        _composedSymbolText.value = ""
    }

    fun sendComposedToDecorator() {
        val text = _composedSymbolText.value.trim()
        if (text.isNotEmpty()) {
            _nameInput.value = text
            _decoratorSectionTab.value = DecoratorSectionTab.NAME_DECORATOR
        }
    }

    // --- Suggestions, Random Nickname & Age Actions ---
    fun setSuggestionsTab(tab: SuggestionsSectionTab) {
        _suggestionsTab.value = tab
    }

    fun setSuggestedCategoryFilter(category: BioCategory?) {
        _suggestedCategoryFilter.value = category
    }

    fun setSuggestedLanguageFilter(lang: LanguageFilter) {
        _suggestedLanguageFilter.value = lang
    }

    fun setRandomVibeFilter(category: BioCategory?) {
        _randomVibeFilter.value = category
        regenerateRandomNicknames()
    }

    fun setRandomLanguageFilter(lang: LanguageFilter) {
        _randomLanguageFilter.value = lang
        regenerateRandomNicknames()
    }

    fun regenerateRandomNicknames() {
        _randomNicknames.value = SuggestedCatalog.generateRandomNicknames(
            categoryFilter = _randomVibeFilter.value,
            languageFilter = _randomLanguageFilter.value,
            count = 4
        )
    }

    fun setSelectedAgeGroup(group: AgeGroup) {
        _selectedAgeGroup.value = group
    }

    // --- Bio & Ready About Actions ---
    fun setBioSectionTab(tab: BioSectionTab) {
        _bioSectionTab.value = tab
    }

    fun setBioCategoryFilter(category: BioCategory?) {
        _bioCategoryFilter.value = category
    }

    fun setBioLanguageFilter(lang: LanguageFilter) {
        _bioLanguageFilter.value = lang
    }

    fun onBioSearchChanged(query: String) {
        _bioSearchQuery.value = query
    }

    // --- Favorites Actions ---
    fun setFavoriteFilter(filter: FavoriteFilter) {
        _favoriteFilter.value = filter
    }

    fun onFavoriteSearchChanged(query: String) {
        _favoriteSearchQuery.value = query
    }

    fun toggleFavorite(
        content: String,
        category: String,
        styleLabel: String,
        onResult: (Boolean) -> Unit = {}
    ) {
        viewModelScope.launch {
            val saved = repository.toggleFavorite(content, category, styleLabel)
            onResult(saved)
        }
    }

    fun saveCustomBioToFavorites(
        text: String,
        category: BioCategory
    ) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return
        viewModelScope.launch {
            repository.addFavorite(
                content = trimmed,
                category = category.storageKey,
                styleLabel = "بايو مخصص • ${category.label}"
            )
        }
    }

    fun removeFavorite(
        entity: FavoriteEntity,
        onRemoved: (FavoriteEntity) -> Unit = {}
    ) {
        viewModelScope.launch {
            repository.removeFavorite(entity.id)
            onRemoved(entity)
        }
    }

    fun restoreFavorite(entity: FavoriteEntity) {
        viewModelScope.launch {
            repository.addFavorite(
                content = entity.content,
                category = entity.category,
                styleLabel = entity.styleLabel
            )
        }
    }

    fun clearAllFavorites() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}
