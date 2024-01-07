package com.example.societyhelpapp.presentation.helpers

import android.content.Context
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.ResourcesCompat
import com.example.societyhelpapp.R
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.SubtitleUI
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.TopicUI
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SpannableHelper @Inject constructor(
    @ActivityContext private val context: Context
) {

    private val spanHighlight by lazy {
        ForegroundColorSpan(
            ResourcesCompat.getColor(context.resources, R.color.test, null)
        )
    }

    fun highlightMain(query: String, itemsList: List<TopicUI>): List<TopicUI> {
        itemsList.forEach { item ->
            item.title.getSpans(0, item.title.length, ForegroundColorSpan::class.java).forEach {
                item.title.removeSpan(it)
            }

            if(item.title.contains(query, true)) {
                val index = item.title.toString().indexOf(query, 0, true)
                item.title.setSpan(spanHighlight, index, index + query.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return itemsList
    }

    fun highlightConstitution(query: String, itemsList: MutableList<Any>): MutableList<Any> {
        itemsList.forEach { item ->
            if(item is SubtitleUI) {
                item.subtitle.getSpans(0, item.subtitle.length, ForegroundColorSpan::class.java)
                    .forEach {
                        item.subtitle.removeSpan(it)
                    }

                if (item.subtitle.contains(query, true)) {
                    val index = item.subtitle.toString().indexOf(query, 0, true)
                    item.subtitle.setSpan(
                        spanHighlight,
                        index,
                        index + query.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
        return itemsList
    }
}