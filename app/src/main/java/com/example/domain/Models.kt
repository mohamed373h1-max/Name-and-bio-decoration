package com.example.domain

enum class DecoratorFilter(val label: String) {
    ALL("الكل"),
    ARABIC("زخرفة عربية"),
    ENGLISH("زخرفة إنجليزية"),
    FRAMES("إطارات ملكية"),
    CYBER("تقني وسايبر")
}

enum class DecoratorSectionTab(val label: String) {
    NAME_DECORATOR("زخرفة الأسماء"),
    SYMBOLS_STUDIO("رموز زخرفية")
}

enum class SymbolCategory(val label: String) {
    ALL("الكل"),
    ROYAL("ملكية وتيجان"),
    FRAMES("أقواس وإطارات"),
    CALM("هادئة ونجوم"),
    CYBER("تقنية وسايبر"),
    DIVIDERS("فواصل للبايو")
}

data class DecorativeSymbolItem(
    val id: String,
    val symbol: String,
    val name: String,
    val category: SymbolCategory,
    val isPairFrame: Boolean = false,
    val leftPart: String = "",
    val rightPart: String = ""
)

data class DecoratedResult(
    val id: String,
    val styleName: String,
    val decoratedText: String,
    val categoryTag: String,
    val filter: DecoratorFilter
)

enum class BioCategory(
    val storageKey: String,
    val label: String,
    val phraseTitle: String
) {
    ROYAL_LUXURY("ROYAL_LUXURY", "ملكي وفخم", "عبارات ملكي وفخمة"),
    CALM_UNIQUE("CALM_UNIQUE", "هادئ ومميز", "عبارات هادئة ومميزة"),
    TECH_CYBER("TECH_CYBER", "تقني وسايبر", "عبارات تقنية وسايبر");

    companion object {
        fun fromStorageKey(key: String): BioCategory? =
            entries.firstOrNull { it.storageKey == key }
    }
}

enum class LanguageFilter(val label: String) {
    ALL("الكل"),
    ARABIC("عربي"),
    ENGLISH("إنجليزي")
}

data class BioQuote(
    val id: String,
    val text: String,
    val category: BioCategory,
    val isArabic: Boolean
)

data class ReadyAboutTemplate(
    val id: String,
    val title: String,
    val content: String,
    val category: BioCategory,
    val platformHint: String,
    val isArabic: Boolean
)

enum class SuggestionsSectionTab(val label: String) {
    SUGGESTED_NAMES("اقتراحات الأسماء"),
    RANDOM_NICKNAME("لقب عشوائي"),
    BY_AGE("حسب العمر")
}

enum class BioSectionTab(val label: String) {
    BIO_QUOTES("عبارات البايو"),
    READY_ABOUT("نبذة جاهزة")
}

data class SuggestedDecoratedName(
    val id: String,
    val decoratedName: String,
    val styleSubtitle: String,
    val category: BioCategory,
    val isArabic: Boolean
)

enum class AgeGroup(
    val label: String,
    val rangeLabel: String,
    val description: String
) {
    TEENS(
        label = "ناشئين",
        rangeLabel = "١٣ - ١٧ سنة",
        description = "زخارف عصرية وألقاب ألعاب وحضور حيوي"
    ),
    YOUTH(
        label = "شباب وبنات",
        rangeLabel = "١٨ - ٢٥ سنة",
        description = "أسلوب أنيق يجمع بين الفخامة والبساطة والسايبر"
    ),
    ADULTS(
        label = "نخبة وطموح",
        rangeLabel = "٢٦ - ٣٥ سنة",
        description = "أسماء وعبارات تعكس الثقة والاحتراف والهدوء"
    ),
    MATURE(
        label = "وقور وأصيل",
        rangeLabel = "+٣٦ سنة",
        description = "خطوط عربية كلاسيكية وعبارات حكمة ووقار"
    )
}

data class AgeRecommendationItem(
    val id: String,
    val text: String,
    val label: String,
    val ageGroup: AgeGroup,
    val category: BioCategory,
    val isName: Boolean
)

data class GeneratedNickname(
    val id: String,
    val text: String,
    val category: BioCategory,
    val styleLabel: String
)

enum class FavoriteFilter(val label: String) {
    ALL("الكل"),
    DECORATED_NAMES("أسماء مزخرفة"),
    ROYAL_LUXURY("ملكي وفخم"),
    CALM_UNIQUE("هادئ ومميز"),
    TECH_CYBER("تقني وسايبر"),
    READY_ABOUT("نبذة جاهزة")
}

object FavoriteCategoryKeys {
    const val DECORATED_NAME = "DECORATED_NAME"
    const val READY_ABOUT = "READY_ABOUT"
}
