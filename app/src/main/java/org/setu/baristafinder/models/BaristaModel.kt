package org.setu.baristafinder.models

data class BaristaModel(
    var id:  Long = 0L,
    var name: String = "",
    var address: String = "", // todo possibly replace with android.address
    var x: Double = 0.0,
    var y: Double = 0.0
) // () means declared as a constructor