package com.wcwac.jksb

data class UsersType(
//    var index: Int,
        var users: ArrayList<User>
) {
    data class User(
            var username: String = "202000000001",
            var password: String = "123456",
            var myvs_1: String = "否",
            var myvs_2: String = "否",
            var myvs_3: String = "否",
            var myvs_4: String = "否",
            var myvs_13a: String = "41",
            var myvs_13b: String = "4101",
            var myvs_13c: String = "河南省郑州市中原区科学大道100号郑州大学",
            var myvs_14: String = "否"
    )
}
