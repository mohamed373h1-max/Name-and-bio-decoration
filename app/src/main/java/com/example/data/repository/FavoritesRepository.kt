package com.example.data.repository

import com.example.data.local.FavoriteDao
import com.example.data.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val favoriteDao: FavoriteDao) {

    val allFavorites: Flow<List<FavoriteEntity>> = favoriteDao.observeAllFavorites()

    suspend fun toggleFavorite(
        content: String,
        category: String,
        styleLabel: String
    ): Boolean {
        val exists = favoriteDao.existsByContent(content)
        return if (exists) {
            favoriteDao.deleteByContent(content)
            false
        } else {
            favoriteDao.insertFavorite(
                FavoriteEntity(
                    content = content,
                    category = category,
                    styleLabel = styleLabel
                )
            )
            true
        }
    }

    suspend fun addFavorite(
        content: String,
        category: String,
        styleLabel: String
    ) {
        favoriteDao.insertFavorite(
            FavoriteEntity(
                content = content,
                category = category,
                styleLabel = styleLabel
            )
        )
    }

    suspend fun removeFavorite(id: Long) {
        favoriteDao.deleteById(id)
    }

    suspend fun clearAll() {
        favoriteDao.clearAll()
    }
}
