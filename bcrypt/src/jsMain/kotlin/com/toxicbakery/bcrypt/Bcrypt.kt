package com.toxicbakery.bcrypt

@ExperimentalStdlibApi
actual object Bcrypt {

    actual fun hash(input: String, saltRounds: Int): ByteArray {
        val salt = genSaltSync(saltRounds)
        return hashSync(input, salt).encodeToByteArray()
    }

    actual fun verify(input: String, expected: ByteArray): Boolean =
        compareSync(input, expected.decodeToString())

}
