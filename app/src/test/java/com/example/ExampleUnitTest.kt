package com.example

import com.example.domain.AgeGroup
import com.example.domain.BioCatalog
import com.example.domain.BioCategory
import com.example.domain.DecoratorFilter
import com.example.domain.LanguageFilter
import com.example.domain.NameDecoratorEngine
import com.example.domain.SuggestedCatalog
import com.example.domain.SymbolCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun arabicTatweelDoesNotBreakNonConnectingLetters() {
        val results = NameDecoratorEngine.generateDecorations("أحمد")
        val royal = results.first { it.id == "ar_tatweel_royal" }
        assertEquals("أحـمـد", royal.decoratedText)
    }

    @Test
    fun englishUnicodeDecorationsGenerateDistinctOutputs() {
        val results = NameDecoratorEngine.generateDecorations("Sultan")
        assertTrue(results.size >= 25)
        assertTrue(results.any { it.filter == DecoratorFilter.ENGLISH && it.decoratedText != "Sultan" })
        assertTrue(results.any { it.filter == DecoratorFilter.CYBER })
        assertTrue(results.any { it.filter == DecoratorFilter.FRAMES })
    }

    @Test
    fun bioCatalogCoversAllThreeRequestedCategoriesAndBothLanguages() {
        val bios = BioCatalog.allBios
        assertTrue(bios.any { it.category == BioCategory.ROYAL_LUXURY && it.isArabic })
        assertTrue(bios.any { it.category == BioCategory.ROYAL_LUXURY && !it.isArabic })
        assertTrue(bios.any { it.category == BioCategory.CALM_UNIQUE && it.isArabic })
        assertTrue(bios.any { it.category == BioCategory.CALM_UNIQUE && !it.isArabic })
        assertTrue(bios.any { it.category == BioCategory.TECH_CYBER && it.isArabic })
        assertTrue(bios.any { it.category == BioCategory.TECH_CYBER && !it.isArabic })
        assertFalse(bios.any { it.text.isBlank() })
    }

    @Test
    fun suggestedCatalogCoversNamesAgesReadyAboutSymbolsAndRandomGenerator() {
        assertTrue(SuggestedCatalog.suggestedNames.any { it.category == BioCategory.ROYAL_LUXURY })
        assertTrue(SuggestedCatalog.suggestedNames.any { it.category == BioCategory.CALM_UNIQUE })
        assertTrue(SuggestedCatalog.suggestedNames.any { it.category == BioCategory.TECH_CYBER })

        assertTrue(SuggestedCatalog.readyAboutTemplates.size >= 6)
        AgeGroup.entries.forEach { ageGroup ->
            assertTrue(SuggestedCatalog.ageRecommendations.any { it.ageGroup == ageGroup })
        }

        val generated = SuggestedCatalog.generateRandomNicknames(null, LanguageFilter.ALL, count = 4)
        assertEquals(4, generated.size)
        assertTrue(generated.all { it.text.isNotBlank() })

        assertTrue(SuggestedCatalog.decorativeSymbols.any { it.category == SymbolCategory.ROYAL })
        assertTrue(SuggestedCatalog.decorativeSymbols.any { it.category == SymbolCategory.FRAMES && it.isPairFrame })
        assertTrue(SuggestedCatalog.decorativeSymbols.any { it.category == SymbolCategory.CYBER })
    }
}
