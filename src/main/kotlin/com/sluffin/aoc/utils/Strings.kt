package com.sluffin.aoc.utils

import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

fun String.md5(): String =
    "%032x".format(BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray(StandardCharsets.UTF_8))))

fun String?.parseInts(
    vararg delimiters: String,
    radix: Int = 10,
): List<Int> = this?.split(*delimiters)?.mapNotNull { it.trim().toIntOrNull(radix) }.orEmpty()