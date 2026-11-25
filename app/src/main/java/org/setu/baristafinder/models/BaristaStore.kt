package org.setu.baristafinder.models

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.concurrent.atomic.AtomicLong

class BaristaStore (private val file: File) {
    private val baristas = ArrayList<BaristaModel>()
    private val lastId = AtomicLong(0L)
    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    init { // on class create
        load()
    }
    private fun load() {
        if (!file.exists()) return

        val type = object : TypeToken<ArrayList<BaristaModel>>() {}.type

        val loaded: ArrayList<BaristaModel> =
            gson.fromJson(file.readText(), type) ?: arrayListOf()

        baristas.clear()
        baristas.addAll(loaded)

        lastId.set(baristas.maxOfOrNull { it.id } ?: 0L)
    }
    private fun save() {
        file.parentFile?.mkdirs()
        file.writeText(gson.toJson(baristas))
    }

    fun findAll(): List<BaristaModel> {
        return baristas
    }
    fun findOne(id: Long): BaristaModel? {
        return baristas.find { p -> p.id == id }
    }

    fun create(barista: BaristaModel) {
        barista.validate()
        barista.id = lastId.incrementAndGet()
        baristas.add(barista)
        save()
    }

    fun update(barista: BaristaModel): Boolean {
        barista.validate()
        val foundBarista = findOne(barista.id)
        return if (foundBarista != null) {
            foundBarista.name = barista.name
            foundBarista.address = barista.address
            foundBarista.x = barista.x
            foundBarista.y = barista.y
            foundBarista.imageUri = barista.imageUri
            save()
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val foundBarista = findOne(id)
        return if (foundBarista != null) {
            baristas.remove(foundBarista)
            save()
            true
        } else {
            false
        }
    }
}

