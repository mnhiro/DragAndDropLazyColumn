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
                    R.drawable.onepiece01_luffy,
                    "ルフィ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece02_zoro_bandana,
                    "ゾロ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece03_nami,
                    "ナミ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece04_usopp_sogeking,
                    "ウソップ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece05_sanji,
                    "サンジ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece06_chopper,
                    "チョッパー",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece07_robin,
                    "ロビン",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece08_franky,
                    "フランキー",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece09_brook,
                    "ブルック",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece10_jinbe,
                    "ジンベエ",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece11_arlong,
                    "アーロン",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece12_buggy,
                    "バギー",
                    "OnePiece"
                ),
                Music(
                    R.drawable.onepiece13_crocodile,
                    "クロコダイル",
                    "OnePiece"
                ),
            )
        }
    }
}
