package com.github.user.soilitouraplication.ui.detection

import androidx.lifecycle.ViewModel
import java.util.*

class DetailDetectionViewModel : ViewModel() {

    data class SoilDetail(val label: String, val description: String)
    data class PlantRecommendation(val label: String, val plants: List<String>)
    data class TvTextResult(val label: String, val data: String)

    private val soilDetails = mapOf(
        "gambut" to SoilDetail("gambut", "Deskripsi gambut"),
        "humus" to SoilDetail("humus", "Deskripsi humus"),
        "aluvial" to SoilDetail("aluvial", "Deskripsi aluvial"),
        "laterit" to SoilDetail("laterit", "Deskripsi laterit"),
        "litosol" to SoilDetail("litosol", "Deskripsi litosol"),
        "kapur" to SoilDetail("kapur", "Deskripsi kapur"),
        "liat" to SoilDetail("liat", "Deskripsi liat"),
        "organosol" to SoilDetail("organosol", "Deskripsi organosol"),
        "pasir" to SoilDetail("pasir", "Deskripsi pasir"),
        "regosol" to SoilDetail("regosol", "Deskripsi regosol"),
        "vulkanik" to SoilDetail("vulkanik", "Deskripsi vulkanik")
    )

    private val plantRecommendations = mapOf(
        "gambut" to PlantRecommendation("gambut", listOf("Tanaman gambut 1", "Tanaman gambut 2")),
        "humus" to PlantRecommendation("humus", listOf("Tanaman humus 1", "Tanaman humus 2")),
        "aluvial" to PlantRecommendation("aluvial", listOf("Tanaman aluvial 1", "Tanaman aluvial 2")),
        "laterit" to PlantRecommendation("laterit", listOf("Tanaman laterit 1", "Tanaman laterit 2")),
        "litosol" to PlantRecommendation("litosol", listOf("Tanaman litosol 1", "Tanaman litosol 2")),
        "kapur" to PlantRecommendation("kapur", listOf("Tanaman kapur 1", "Tanaman kapur 2")),
        "liat" to PlantRecommendation("liat", listOf("Tanaman liat 1", "Tanaman liat 2")),
        "organosol" to PlantRecommendation("organosol", listOf("Tanaman organosol 1", "Tanaman organosol 2")),
        "pasir" to PlantRecommendation("pasir", listOf("Tanaman pasir 1", "Tanaman pasir 2")),
        "regosol" to PlantRecommendation("regosol", listOf("Tanaman regosol 1", "Tanaman regosol 2")),
        "vulkanik" to PlantRecommendation("vulkanik", listOf("Tanaman vulkanik 1", "Tanaman vulkanik 2"))
    )

    fun getSoilDetail(label: String): SoilDetail? {
        val lowercaseLabel = label.lowercase(Locale.getDefault())
        return soilDetails.entries.firstOrNull { lowercaseLabel.contains(it.key) }?.value
    }

    fun getPlantRecommendation(label: String): PlantRecommendation? {
        val lowercaseLabel = label.lowercase(Locale.getDefault())
        return plantRecommendations.entries.firstOrNull { lowercaseLabel.contains(it.key) }?.value
    }
}
