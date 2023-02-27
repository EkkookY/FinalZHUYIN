package com.example.testzhuyin.data

import com.example.testzhuyin.R
import com.example.testzhuyin.model.ClothContent
import com.example.testzhuyin.model.GroupContent
import com.example.testzhuyin.model.NoticeContent
import com.example.testzhuyin.model.UserContent

object DataProvide {
    val note =
        Note(
            id = 1,
            title = "【藏族】1111旅行笔记",
            time = "2023-1-28"
        )
    val notelist = listOf(
        note,
        Note(
            id = 2,
            title = "【汉族】2222旅行笔记",
            time = "2023-1-31"
        ),
        Note(
            id = 3,
            title = "【苗族】3333旅行笔记",
            time = "2023-2-2"
        ),
        Note(
            id = 4,
            title = "【白族】4444旅行笔记",
            time = "2023-1-8"
        ),
        Note(
            id = 5,
            title = "【壮族】5555旅行笔记",
            time = "2023-1-18"
        ),
        Note(
            id = 6,
            title = "【藏族】xxxx旅行笔记",
            time = "2022-12-28"
        ),
        Note(
            id = 7,
            title = "【藏族】xxxx旅行笔记",
            time = "2023-12-30"
        ),
        Note(
            id = 8,
            title = "【藏族】xxxx旅行笔记",
            time = "2022-12-23"
        ),
        Note(
            id = 9,
            title = "【藏族】xxxx旅行笔记",
            time = "2023-2-1"
        ),
        Note(
            id = 10,
            title = "【藏族】xxxx旅行笔记",
            time = "2023-1-8"
        ),
    )
    val comcontent1 =
        UserContent(
            id = 1,
            drawable=R.drawable.home_content_21,
            user = R.drawable.home_content_21,
            text="内容111111111111111哈哈哈哈",
            name = "useful11",
            time = "2022.11.11",
            Slocation = "湖南",
            Nationality = "侗族"
        )
    val comcontentlist1 = listOf(
        comcontent1,
        UserContent(
            id = 2,
            drawable=R.drawable.home_content_22,
            user = R.drawable.home_content_22,
            text="2BB2B2B2B2B2B2B22B2B2B2B2",
            name = "useful22",
            time = "2022.2.2",
            Slocation = "广西",
            Nationality = "壮族"
        ),
        UserContent(
            id = 3,
            drawable=R.drawable.home_content_23,
            user = R.drawable.home_content_23,
            text="2333",
            name = "useful33",
            time = "2022.3.3",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 4,
            drawable=R.drawable.home_content_24,
            user = R.drawable.home_content_24,
            text="4 nei内容内容",
            name = "useful44",
            time = "2022.4.4",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 5,
            drawable=R.drawable.home_content_25,
            user = R.drawable.home_content_25,
            text="5555555好想玩原神啊，但是安卓开发还没有做完55555555，速速搞定做波每日任务睡个爽觉",
            name = "useful55",
            time = "2022.5.5",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 6,
            drawable=R.drawable.home_content_26,
            user = R.drawable.home_content_26,
            text="内容66666666哈哈哈哈",
            name = "useful66",
            time = "2022.6.6",
            Slocation = "xx",
            Nationality = "xx"
        ),

    )
    val comcontent2 =
        UserContent(
            id = 1,
            drawable=R.drawable.home_content_11,
            user = R.drawable.home_content_11,
            text="想睡觉哈哈哈哈哈哈哈哈哈哈哈哈",
            name = "useless1",
            time = "2022.11.11",
            Slocation = "湖南",
            Nationality = "侗族"
        )
    val comcontentlist2 = listOf(
        comcontent2,
        UserContent(
            id = 2,
            drawable=R.drawable.home_content_12,
            user = R.drawable.home_content_12,
            text="woshi2BB2B2B2B2B2B2B22B2B2B2B2",
            name = "useless2",
            time = "2022.2.2",
            Slocation = "广西",
            Nationality = "壮族"
        ),
        UserContent(
            id = 3,
            drawable=R.drawable.home_content_13,
            user = R.drawable.home_content_13,
            text="23366399933",
            name = "useless3",
            time = "2022.3.3",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 4,
            drawable=R.drawable.home_content_14,
            user = R.drawable.home_content_14,
            text="4 nei内容内容",
            name = "user4",
            time = "2022.4.4",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 5,
            drawable=R.drawable.home_content_15,
            user = R.drawable.home_content_15,
            text="5555555好想玩原神啊，但是安卓开发还没有做完55555555，速速搞定做波每日任务睡个爽觉",
            name = "user5",
            time = "2022.5.5",
            Slocation = "xx",
            Nationality = "xx"
        ),
        UserContent(
            id = 6,
            drawable=R.drawable.home_content_16,
            user = R.drawable.home_content_16,
            text="内容66666666哈哈哈哈",
            name = "user6",
            time = "2022.6.6",
            Slocation = "xx",
            Nationality = "xx"
        ),
    )
    val Notice= NoticeContent(
        id = "嘻嘻哈哈嘿嘿",
        time = "15:46",
        text = "小姐姐衣服链接有吗",
        drawable = R.drawable.notice1,
    )
    val noticelist= listOf(
        Notice,
        NoticeContent(
            id = "小李有点甜",
            time = "13:20",
            text = "这是哪里呀好好看",
            drawable = R.drawable.notice8,
        ),
        NoticeContent(
            id = "未眠",
            time = "11:50",
            text = "周末有一个回族聚餐",
            drawable = R.drawable.notice2,
        ),
        NoticeContent(
            id = "半城烟雨",
            time = "9:34",
            text = "交个朋友吗，我也是侗族的",
            drawable = R.drawable.notice3,
        ),
        NoticeContent(
            id = "什么时候去大理",
            time = "7:20",
            text = "好的！",
            drawable = R.drawable.notice4,
        ),

        NoticeContent(
            id = "水色心情",
            time = "7:20",
            text = "那就这么说",
            drawable = R.drawable.notice5,
        ),
        NoticeContent(
            id = "去有风的地方",
            time = "7:20",
            text = "稍等",
            drawable = R.drawable.notice6,
        ),
        NoticeContent(
            id = "快乐再出发",
            time = "7:20",
            text = "那也太棒了吧",
            drawable = R.drawable.notice7,
        )
    )

    val Group= GroupContent(
        id = "维吾尔族在安徽",
        text ="安徽六安有无小伙伴",
        drawable =R.drawable.pic_group_4,
        num = 2345
    )
    val grouplist= listOf(
        Group,
        GroupContent(
            id = "回族",
            text ="找在广州的穆斯林",
            drawable =R.drawable.pic_group_5,
            num = 2843
        ),
        GroupContent(
            id = "广州藏族",
            text ="找一个一起逛街的藏族伙伴",
            drawable =R.drawable.pic_group_1,
            num = 3820
        ),
        GroupContent(
            id = "哈萨克族在江苏",
            text ="南京哪里有哈萨克族的正宗美食鸭",
            drawable =R.drawable.pic_group_2,
            num = 3492
        ),
        GroupContent(
            id = "苗族在大理",
            text ="去有风的地方真好看啊",
            drawable =R.drawable.pic_group_3,
            num = 768
        ),

        )

//    val cloth =
//        ClothContent(
//            drawable =R.drawable.cloth_1
//        )
//    val clothlist = listOf(
//        cloth,
//        ClothContent(
//            id = 2,
//            drawable = R.drawable.cloth2
//        ),
//        ClothContent(
//            id = 3,
//            drawable = R.drawable.cloth3
//        ),
//        ClothContent(
//            id = 4,
//            drawable = R.drawable.cloth4
//        ),
//        ClothContent(
//            id = 5,
//            drawable = R.drawable.cloth5
//        ),
//        ClothContent(
//            id = 6,
//            drawable = R.drawable.cloth6
//        ),
//        ClothContent(
//            id = 7,
//            drawable = R.drawable.cloth7
//        ),
//        ClothContent(
//            id = 8,
//            drawable = R.drawable.cloth8
//        ),
//        ClothContent(
//            id = 9,
//            drawable = R.drawable.cloth9
//        ),
//        ClothContent(
//            id = 10,
//            drawable = R.drawable.cloth10
//        ),
//    )


    val ContentData = listOf(
        R.drawable.home_content_11 to R.string.home_content_11,
        R.drawable.home_content_12 to R.string.home_content_12,
        R.drawable.home_content_13 to R.string.home_content_13,
        R.drawable.home_content_14 to R.string.home_content_14,
        R.drawable.home_content_15 to R.string.home_content_15,
        R.drawable.home_content_16 to R.string.home_content_16
    ).map { HomeContent(it.first, it.second) }

    val ClothData = listOf(
        R.drawable.cloth_1 ,
        R.drawable.cloth2 ,
        R.drawable.cloth3 ,
        R.drawable.cloth4 ,
        R.drawable.cloth5 ,
        R.drawable.cloth6 ,
        R.drawable.cloth7 ,
        R.drawable.cloth8 ,
        R.drawable.cloth9 ,
        R.drawable.cloth10
    ).map { ClothContent(it) }
}




//    val CommunityContentData = listOf(
//        R.drawable.home_content_21 to R.string.home_content_11 to R.drawable.home_content_11 to R.string.home_content_11,
//        R.drawable.home_content_22 to R.string.home_content_12,
//        R.drawable.home_content_23 to R.string.home_content_13,
//        R.drawable.home_content_24 to R.string.home_content_14,
//        R.drawable.home_content_25 to R.string.home_content_15,
//        R.drawable.home_content_26 to R.string.home_content_16
//    ).map { UserContent(it.first) }
//}


