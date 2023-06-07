package com.soilit.app.soilit.ui.faq.adapter

data class SectionItem(
    val title: String,
    val color: Int,
    val child: String,
    var expanded: Boolean = false
)