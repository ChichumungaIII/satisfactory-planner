package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.collections.join
import util.collections.merge

data class ItemPool(val items: Map<Item, BigRational>) {
  operator fun get(item: Item) = items[item] ?: 0.br

  operator fun plus(other: ItemPool) = ItemPool(items.join(other.items, BigRational::plus))

  fun consume(item: Item, consumed: BigRational): ItemPool {
    val remaining = get(item) - consumed
    return when {
      remaining < 0.br -> throw Error("Cannot consume [$consumed] of [$item]: only [${get(item)}] available.")
      remaining == 0.br -> ItemPool(items - item)
      else -> ItemPool(items.merge(item, consumed, BigRational::minus))
    }
  }
}
