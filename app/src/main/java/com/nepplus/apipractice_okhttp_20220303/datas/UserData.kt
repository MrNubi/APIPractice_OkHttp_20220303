package com.nepplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject

class UserData {

    var id = 0,
    var email ="",
    var nickname =""

    companion object{

        fun getUserDataFromJson(jsonObject: JSONObject):UserData{

            val userdata = UserData()
            userdata.id = jsonObject.getInt("id")
            userdata.email = jsonObject.getString("email")
            userdata.nickname = jsonObject.getString("nick_name")
        }
    }
}