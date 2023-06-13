package com.github.user.soilitouraplication.ui.faq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.user.soilitouraplication.R
import com.github.user.soilitouraplication.databinding.ActivityFaqBinding
import com.github.user.soilitouraplication.ui.faq.adapter.FaqAdapter
import com.github.user.soilitouraplication.ui.faq.adapter.FaqClass
import com.github.user.soilitouraplication.ui.faq.adapter.SectionItem

class FaqActivity : AppCompatActivity() {
    private val adapter = FaqAdapter()
    
    private val listFaq: ArrayList<FaqClass> = arrayListOf(
        FaqClass("answer0", "question0"),
        FaqClass("answer1", "question1"),
        FaqClass("answer2", "question2"),
        FaqClass("answer3", "question3"),
        FaqClass("answer4", "question4"),
        FaqClass("answer5", "question5"),
        FaqClass("answer6", "question6"),
        FaqClass("answer7", "question7"),
        FaqClass("answer8", "question8"),
        FaqClass("answer9", "question9"),
        FaqClass("answer10", "question10"),
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.recyclerView.adapter = adapter
        
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        
        for (i in listFaq.indices) {
            adapter.addSectionItem(
                SectionItem(listFaq[i].question, R.color.primary, listFaq[i].answer)
            )
        }
    }
}