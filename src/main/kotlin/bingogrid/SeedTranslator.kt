package bingogrid

import java.util.*

class SeedTranslator{
    companion object {
        fun alphaNumericSeedToRandomLong(seed: String): Long {
            val rand = Random()
            rand.setSeed(seed.hashCode().toLong())
            return rand.nextLong()
        }
    }
}