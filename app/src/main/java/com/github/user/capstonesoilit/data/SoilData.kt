package com.github.user.capstonesoilit.data

import java.util.*

data class SoilDetail(val label: String, val description: String)

data class PlantRecommendation(val label: String, val plants: List<String>)

fun getSoilDetail(label: String): SoilDetail {
    return when (label.lowercase(Locale.getDefault())) {
        "gambut" -> SoilDetail("gambut", "Deskripsi gambut")
        "humus" -> SoilDetail("humus", "Deskripsi humus")
        "aluvial" -> SoilDetail("aluvial", "Deskripsi aluvial")
        "laterit" -> SoilDetail("laterit", "Deskripsi laterit")
        "litosol" -> SoilDetail("litosol", "Deskripsi litosol")
        "kapur" -> SoilDetail("kapur", "Deskripsi kapur")
        "liat" -> SoilDetail("liat", "Deskripsi liat")
        "organosol" -> SoilDetail("organosol", "Deskripsi organosol")
        "pasir" -> SoilDetail("pasir", "Deskripsi pasir")
        "regosol" -> SoilDetail("regosol", "Deskripsi regosol")
        "vulkanik" -> SoilDetail("vulkanik", "Deskripsi vulkanik")
        else -> SoilDetail("", "")
    }
}

fun getPlantRecommendation(label: String): PlantRecommendation {
    return when (label.lowercase(Locale.getDefault())) {
        "gambut" -> PlantRecommendation("gambut", listOf("Tanaman gambut 1", "Tanaman gambut 2"))
        "humus" -> PlantRecommendation("humus", listOf("Tanaman humus 1", "Tanaman humus 2"))
        "aluvial" -> PlantRecommendation("aluvial", listOf("Tanaman aluvial 1", "Tanaman aluvial 2"))
        "laterit" -> PlantRecommendation("laterit", listOf("Tanaman laterit 1", "Tanaman laterit 2"))
        "litosol" -> PlantRecommendation("litosol", listOf("Tanaman litosol 1", "Tanaman litosol 2"))
        "kapur" -> PlantRecommendation("kapur", listOf("Tanaman kapur 1", "Tanaman kapur 2"))
        "liat" -> PlantRecommendation("liat", listOf("Tanaman liat 1", "Tanaman liat 2"))
        "organosol" -> PlantRecommendation("organosol", listOf("Tanaman organosol 1", "Tanaman organosol 2"))
        "pasir" -> PlantRecommendation("pasir", listOf("Tanaman pasir 1", "Tanaman pasir 2"))
        "regosol" -> PlantRecommendation("regosol", listOf("Tanaman regosol 1", "Tanaman regosol 2"))
        "vulkanik" -> PlantRecommendation("vulkanik", listOf("Tanaman vulkanik 1", "Tanaman vulkanik 2"))
        else -> PlantRecommendation("", emptyList())
    }
}

