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
                    R.drawable.food_fruit_sandwich_ichigo,
                    "いちごサンド",
                    "パン系"
                ),
                Music(
                    R.drawable.food_kakuni_manju,
                    "角煮まんじゅう",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_coupe_kuppe,
                    "クッペ",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_india_dosa,
                    "ドサ",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_india_idli,
                    "イドリ",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_india_papad,
                    "パパド",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_india_wada,
                    "ワダ",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_melonpan_chocochip,
                    "チョコチップメロンパン",
                    "パン系"
                ),
                Music(
                    R.drawable.pan_pizza_toast,
                    "ピザトースト",
                    "パン系"
                ),
                Music(
                    R.drawable.sandwich_pj,
                    "サンドイッチ",
                    "パン系"
                ),
                Music(
                    R.drawable.sandwich_tamago,
                    "たまごサンド",
                    "パン系"
                ),
                Music(
                    R.drawable.sweets_maritozzo_ichigo,
                    "いちごマリトッツォ",
                    "パン系"
                ),
                Music(
                    R.drawable.sweets_taiwan_kasutera,
                    "台湾カステラ",
                    "パン系"
                ),
            )
        }
    }
}
