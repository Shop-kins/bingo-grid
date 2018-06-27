package bingogrid

import java.util.*

class SeedTranslator{
    companion object {
        fun alphaNumericSeedToRandomDouble(seed: String): Double {
            val rand = Random()
            rand.setSeed(seed.hashCode().toLong())
            return rand.nextDouble()
        }
    }
}