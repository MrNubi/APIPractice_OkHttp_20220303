package com.nepplus.apipractice_okhttp_20220303.datas

import org.json.JSONObject
import java.io.Serializable

class TopicData : Serializable{

    var id = 0  // id는 Int라고 명시
    var title = ""  // title는  String이라고 명시
    var imageUrl = ""  // 서버: img_url , 앱: imageUrl  변수명 다른 경우
    var replyCount = 0 //  서버: reply_count , 앱: replyCount  변수명 다른 경우

    // 하나의 토론주제 : 여러개의 (목록) 선택진영
    val sideList = ArrayList<SideData>()

    // 내가 투표해둔 진영
    var mySelectedSide : SideData? =null

    companion object{
        // 주제 정보를 담고 있는 JSONObject가 들어오면 -> TopicData형태로 변환해주는 함수 => static메소드
        fun getTopicDataFromJson(jsonObj:JSONObject):TopicData{

            // 기본 내용의 TopicData 생성
            val topicData = TopicData()

            // jsonObj에서 데이터 추출 -> 멤버변수 대입
            topicData.id = jsonObj.getInt("id")
            topicData.title = jsonObj.getString("title")
            topicData.imageUrl = jsonObj.getString("img_url")
            topicData.replyCount = jsonObj.getInt("reply_count")

            // sides라는 JSONArray가 들어있음
            // => topicData의 하위정보로, 선택진영 목록으로 저장
            // => JSONArray -> ArrayList
            val sidesArr = jsonObj.getJSONArray("sides")

            for(i in 0 until sidesArr.length()){
                // 선택 진영 정보를 들고있는 JSONObject 추출
                val sideObj = sidesArr.getJSONObject(i)

                // sideObj도, sideData로 (선택진영) 변환
                val sideData = SideData.getSideDataFromJson(sideObj)

                // topicData변수의 하위목록으로 등록
                topicData.sideList.add(sideData)
            }

            // 투표해둔 진영이 있다면? 선택진영데이터도 파싱
            // 진영이 없다면? => mySide항목은, null일 경우도 있다
            if(!jsonObj.isNull("my_side")){
                // null 이 아닐때만 파싱
                val mySideObj =jsonObj.getJSONObject("my_side")

                // 선택 진영 JSON=> mySelectedSide 변수(SideData)
                topicData.mySelectedSide = SideData.getSideDataFromJson(mySideObj)

            }


            // 완성된 TopicData 리턴
            return  topicData
        }
    }
}