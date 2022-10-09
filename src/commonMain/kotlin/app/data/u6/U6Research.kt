package app.data.u6

enum class U6Research(
    val category: Category,
    val displayName: String,
) {
    HOG_RESEARCH(Category.ALIEN_ORGANISMS, "Hog Research"),
    HATCHER_RESEARCH(Category.ALIEN_ORGANISMS, "Hatcher Research"),
    STINGER_RESEARCH(Category.ALIEN_ORGANISMS, "Stinger Research"),
    SPITTER_RESEARCH(Category.ALIEN_ORGANISMS, "Spitter Research"),
    BIO_ORGANIC_PROPERTIES(Category.ALIEN_ORGANISMS, "Bio-Organic Properties"),
    CATERIUM(Category.CATERIUM, "Caterium"),
    BERYL_NUT(Category.NUTRIENTS, "Beryl Nut"),
    PALEBERRY(Category.NUTRIENTS, "Paleberry"),
    BACON_AGARIC(Category.NUTRIENTS, "Bacon Agaric"),
    BLUE_POWER_SLUGS(Category.POWER_SLUGS, "Blue Power Slugs"),
    SLUG_SCANNING(Category.POWER_SLUGS, "Slug Scanning"),
    YELLOW_POWER_SHARDS(Category.POWER_SLUGS, "Yellow Power Shards"),
    OVERCLOCK_PRODUCTION(Category.POWER_SLUGS, "Overclock Production"),
    QUARTZ(Category.QUARTZ, "Quartz"),
    SULFUR(Category.SULFUR, "Sulfur"),
    ;

    enum class Category(
        val displayName: String,
    ) {
        HARD_DRIVE("Hard Drive"),
        ALIEN_ORGANISMS("Alien Organisms"),
        CATERIUM("Caterium"),
        NUTRIENTS("Nutrients"),
        POWER_SLUGS("Power Slugs"),
        QUARTZ("Quartz"),
        SULFUR("Sulfur"),
    }
}