package bingogrid

import java.util.*

fun main(args: Array<String>) {
    val randNum = Random()
    randNum.setSeed("ROCKETRACCOON".hashCode().toLong())
    print(
            GridTasker.fillGrid(GridGenerator.generate(randNum.nextDouble()))
    )
}