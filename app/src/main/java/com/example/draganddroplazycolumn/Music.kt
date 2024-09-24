package com.example.draganddroplazycolumn

data class Music(
    val imageId: Int,
    val title: String,
    val artist: String
) {
    companion object {
        fun samples(): List<Music> {
            return listOf(
                Music(
                    R.drawable.comet,
                    "comet",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.tenkyu,
                    "天球、彗星は夜を跨いで",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.next_color_planet,
                    "NEXT COLOR PLANET",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.ghost,
                    "GHOST",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.still_still_stellar,
                    "Still Still Stellar",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.template,
                    "TEMPLATE",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.wicked,
                    "Wicked",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.spector,
                    "Specter",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.soiree,
                    "ソワレ",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.housousitu,
                    "放送室",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.pioneer,
                    "先駆者",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.maintenance,
                    "スイちゃんのメンテナンスソング",
                    "星街すいせい"
                ),
                Music(
                    R.drawable.bibideba,
                    "ビビデバ",
                    "星街すいせい"
                ),
            )
        }
    }
}
