package org.setu.baristafinder.models

import java.util.concurrent.atomic.AtomicLong

class BaristaStore { // TODO currently mem. convert to JSON storage after lab
    private val baristas = ArrayList<BaristaModel>()
    private val lastId = AtomicLong(0L)

    fun findAll(): List<BaristaModel> {
        return baristas
    }
    fun findOne(id: Long): BaristaModel? {
        return baristas.find { p -> p.id == id }
    }

    fun create(barista: BaristaModel) {
        barista.id = lastId.incrementAndGet()
        baristas.add(barista)
    }

    fun update(barista: BaristaModel): Boolean {
        val foundBarista = findOne(barista.id)
        return if (foundBarista != null) {
            foundBarista.name = barista.name
            foundBarista.address = barista.address
            foundBarista.x = barista.x
            foundBarista.y = barista.y
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val foundBarista = findOne(id)
        return if (foundBarista != null) {
            baristas.remove(foundBarista)
            true
        } else {
            false
        }
    }
}

