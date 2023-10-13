package util.function

fun <T, R> using(receiver: T, block: T.() -> R): () -> R =
    { with(receiver) { block() } }

fun <T, A, R> using(receiver: T, block: T.(A) -> R): (A) -> R =
    { a -> with(receiver) { block(a) } }

fun <T, A, B, R> using(receiver: T, block: T.(A, B) -> R): (A, B) -> R =
    { a, b -> with(receiver) { block(a, b) } }
