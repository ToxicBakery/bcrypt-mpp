package com.toxicbakery.bcrypt

import at.favre.lib.crypto.bcrypt.LongPasswordStrategies

private typealias BcryptLib = at.favre.lib.crypto.bcrypt.BCrypt
private typealias BcryptVersion = at.favre.lib.crypto.bcrypt.BCrypt.Version

actual object Bcrypt {

    actual fun hash(
        input: String,
        saltRounds: Int
    ): ByteArray =
        BcryptLib.withDefaults()
            .hash(saltRounds, input.toByteArray())

    actual fun verify(
        input: String,
        expected: ByteArray
    ): Boolean =
        BcryptLib.verifyer(null, LongPasswordStrategies.truncate(BcryptVersion.VERSION_2A))
            .verify(input.toByteArray(), expected)
            .verified

}
