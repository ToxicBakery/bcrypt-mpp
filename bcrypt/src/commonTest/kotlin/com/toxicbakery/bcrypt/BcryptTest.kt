package com.toxicbakery.bcrypt

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalStdlibApi
class BcryptTest {

    @Test
    fun hash() {
        val hash = Bcrypt.hash(PASSWORD, SALT_ROUNDS)
        assertTrue(Bcrypt.verify(PASSWORD, hash))
        assertFalse(Bcrypt.verify("$PASSWORD$PASSWORD", hash))
    }

    companion object {
        private const val PASSWORD = "Hello bcrypt!"
        private const val SALT_ROUNDS = 6
    }

}
