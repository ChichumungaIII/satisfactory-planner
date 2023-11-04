package com.chichumunga.satisfactory.scripts.conversion

import app.game.data.Item
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.item.MajorItemGroup
import com.chichumunga.satisfactory.scripts.item.MinorItemGroup
import com.chichumunga.satisfactory.scripts.json.ItemSchema

fun convertToItemEnum(json: ItemSchema): ItemEnum {
  return ItemEnum(
    majorGroup = MajorItemGroup.NATURE, // TODO: Manual Override
    minorGroup = MinorItemGroup.MYCELIA, // TODO: Manual Override
    enumName = uppercaseUnderscore(json.name), json.name,
    category = Item.Category.PARTS, // TODO: Manual Override
    stack = json.stackSize.takeIf { it > 0 && json.form == "solid" },
    sink = json.sinkPoints.takeIf { it > 0 && json.form == "solid" },
    energy = json.energy.takeIf { it > 0.0 }?.downcast(),
    radiation = json.radioactive.takeIf { it > 0.0 }?.rationalize(),
    stable = json.stable,
    experimental = json.experimental,
  )
}


