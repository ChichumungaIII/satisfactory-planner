package com.chichumunga.satisfactory.util.math

import java.math.BigInteger

inline val Long.bi get() = BigInteger.valueOf(this)
inline val Int.bi get() = toLong().bi
