package com.wzx.screenmatch

import java.util.ArrayList

/**
 * 描述：
 *
 * @author wzx
 * @date 2019/10/22
 */
object CreateDimensFile {

    @JvmStatic
    fun main(args: Array<String>) {
        val adapter = ScreenMatch(360)
        val textSize = ArrayList<Int>()
        textSize.add(8)
        textSize.add(10)
        textSize.add(12)
        textSize.add(14)
        textSize.add(16)
        textSize.add(18)
        textSize.add(20)
        adapter.textSize = textSize
        adapter.createDimenFiles()
    }
}
