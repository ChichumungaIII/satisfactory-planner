package app.util.math

import util.math.Rational

fun Double.toFixed(places: Int) = asDynamic().toFixed(places) as String
fun Rational.toFixed(places: Int) = toDouble().toFixed(places)
