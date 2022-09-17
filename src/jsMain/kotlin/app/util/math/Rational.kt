package app.util.math

import util.math.Rational

fun Rational.toFixed(places: Int) = toDouble().asDynamic().toFixed(places)
