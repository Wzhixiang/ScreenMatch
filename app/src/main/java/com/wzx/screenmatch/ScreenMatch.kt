package com.wzx.screenmatch

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.math.BigDecimal

/**
 * 描述：
 * @author wzx
 * @date 2019/10/22
 *
 * @param baseDPI 设计图dpi
 */
class ScreenMatch(var baseDPI: Int) {

    var textSize: List<Int> = listOf()

    /**
     * dimens.xml文件根目录
     */
    private val rootPath: String by lazy {
        ".\\app\\src\\main\\res\\"
    }

    /**
     * 常见屏幕dp
     */
    private val dimens: List<Int> by lazy {
        listOf(
            320,
            360,
            384,
            392,
            400,
            411,
            432,
            480,
            533,
            592,
            600,
            640,
            662,
            720,
            768,
            800,
            811,
            820,
            960,
            1024,
            1280,
            1365
        )
    }

    /**
     * 创建文件夹
     */
    fun createDimenFiles() {
        for (i in dimens) {
            val filePath = rootPath + "values-sw" + i + "dp"

            val file = File(filePath)

            //创建文件夹values-swXXXdp
            if (!file.exists()) {
                file.mkdir()
            }

            writeContent(getDimensFilePath(i), i)
        }

        writeContent(rootPath + "values\\dimens.xml", baseDPI)
    }

    fun getDimensFilePath(dimens: Int) = rootPath + "values-sw" + dimens + "dp\\dimens.xml"

    /**
     * 创建文件，往dimens.xml写入内容
     */
    fun writeContent(filePath: String, dimens: Int) {
        try {
            val file = File(filePath)
            //如果文件存在则删除
            if (file.exists()) {
                file.delete()
                file.createNewFile()
            }

            var fw = BufferedWriter(FileWriter(file))

            fw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n")
            fw.write("<resources>" + "\n")
            fw.write("    <!-- view size -->\n")

            var sp = StringBuffer()
            for (i in 1..baseDPI) {
                if (i % 2 == 0 || i == 1) {
                    sp.setLength(0)
                    sp.append("    <dimen name=\"dp_" + i + "\">")
                    val dp = BigDecimal(dimens * i).divide(BigDecimal(baseDPI), 4, BigDecimal.ROUND_DOWN).toDouble()
                    sp.append(dp).append("dp</dimen>" + "\n")
                    fw.write(sp.toString())
                }
            }

            fw.write("    <!-- text size -->\n")

            textSize.let {
                for (i in it) {
                    sp.setLength(0)
                    sp.append("    <dimen name=\"sp_" + i + "\">")
                    val dp = BigDecimal(dimens * i).divide(BigDecimal(baseDPI), 4, BigDecimal.ROUND_DOWN).toDouble()
                    sp.append(dp).append("sp</dimen>" + "\n")
                    fw.write(sp.toString())
                }
            }

            fw.write("</resources>")
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {

        }
    }
}
