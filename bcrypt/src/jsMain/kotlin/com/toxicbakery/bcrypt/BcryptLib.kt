@file:JsModule("bcrypt")
@file:JsNonModule
package com.toxicbakery.bcrypt

internal external fun genSaltSync(saltRounds: Int): String

internal external fun hashSync(input: String, salt: String): String

internal external fun compareSync(input: String, expected: String): Boolean
