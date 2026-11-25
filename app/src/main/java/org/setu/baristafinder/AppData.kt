package org.setu.baristafinder

import android.content.Context
import org.setu.baristafinder.models.BaristaStore
import java.io.File

object AppData {
    lateinit var baristaStore: BaristaStore
        private set

    fun initialize(context: Context) {
        val file = File(
            context.filesDir,
            "barista_list.json"
        )

        baristaStore = BaristaStore(file)
    }
}