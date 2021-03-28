package com.example.heart.Model

import java.util.*

class DataModel(var SYS: Int, var DYS: Int, var PUL: Int) {
    var SAVE: Date = Date()
    var STATUS: String? = null

    companion object {
        var IDESTR = arrayOf("Bajo", "Normal", "Normal Alto", "Muy Alto")
    }

    /**
     * Se genera el nuevo objeto que contendra los datos del nuevo registro
     * @param _sys sistolica
     * @param _dys diastolica
     * @param _pul pulsaciones
     */
    init {
        SAVE = Date()
        val cal = (SYS + 2 * DYS) / 3.0f

        when{
            cal > 0  && cal < 69 -> STATUS = IDESTR[0]
            cal >= 70  && cal < 105 -> STATUS = IDESTR[1]
            cal >= 105  && cal < 250 -> STATUS = IDESTR[2]
            cal >= 250 -> STATUS = IDESTR[3]
        }

    }

}