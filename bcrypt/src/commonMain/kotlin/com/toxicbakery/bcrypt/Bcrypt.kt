package com.toxicbakery.bcrypt

/**
 * Platform dependent Bcrypt wrapper.
 */
expect object Bcrypt {

    /**
     * Given a secret and a number of rounds, hash the input using the platform Bcrypt implementation.
     *
     * @param input The secret to be hashed
     * @param saltRounds The computation iterations to be performed hashing the secret
     */
    fun hash(
        input: String,
        saltRounds: Int
    ): ByteArray

    /**
     * Given a secret, determine if it matches a previously hashed secret. This method will perform hashing of the
     * input secret to determine if it is the same value as a previously hashed secret.
     *
     * @param input The secret to be validated
     * @param expected The previously hashed secret to check against
     */
    fun verify(
        input: String,
        expected: ByteArray
    ): Boolean

}
