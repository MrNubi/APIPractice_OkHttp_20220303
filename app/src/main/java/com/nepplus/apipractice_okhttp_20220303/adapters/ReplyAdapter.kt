package com.nepplus.apipractice_okhttp_20220303.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nepplus.apipractice_okhttp_20220303.R
import com.nepplus.apipractice_okhttp_20220303.datas.ReplyData
import com.nepplus.apipractice_okhttp_20220303.datas.TopicData
import java.text.SimpleDateFormat
import java.util.*

class ReplyAdapter(
    val mContext:Context,
    resId: Int,
    val mList: List<ReplyData>
) : ArrayAdapter<ReplyData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.reply_list_item,null )
        }

        val row =tempRow!!

        val data = mList[position]

        val txtSelectedSide = row.findViewById<TextView>(R.id.txtSelecttedSide)
        val txtWriterNickname = row.findViewById<TextView>(R.id.txtWriterNickname)
        val txtReplyContent = row.findViewById<TextView>(R.id.txtReplyContent)
        val txtCreatedAt =  row.findViewById<TextView>(R.id.txtCreatedAt)

        txtReplyContent.text = data.content
        txtWriterNickname.text = data.writer.nickname
        txtSelectedSide.text =  "${data.selectedSide.title}"

        // 임시 - 작성일자만 "2022-03-10" 형태로 표시 => 연 / 월 / 일 데이터로 가공
        // 월은 1작게 나옴.  +1로 보정
        txtCreatedAt.text =  "${data.createdAt.get(Calendar.YEAR)}-${data.createdAt.get(Calendar.MONTH)+1}-${data.createdAt.get(Calendar.DAY_OF_MONTH)}"

        // 임시2 -  "2022-03-05" 형태로 표시 -> SimpleDataFormat 활용
//        val sdf =  SimpleDateFormat("yyyy-MM-dd")

        // 임시3 -  "22.03.05" 형태로 표시 -> SimpleDataFormat 활용
//        val sdf =  SimpleDateFormat("yy.MM.dd")

        // 연습
        // 양식 1) 2022년 3월 5일
//        val sdf =SimpleDateFormat("yyyy년 M월 d일")

        // 양식 2) 220305
//        val sdf =SimpleDateFormat("yyMMdd")

        // 양식 3) 3월 5일 오전 2시 5분
//        val sdf = SimpleDateFormat("M월 d일 a h시 m분")
        
        // 양식 4) 21년 3/5 (토) - 18:05
//        val sdf = SimpleDateFormat("yy년 M/d (E) - HH:mm")

        // 실제) 서버가 주는 created_at에 담긴 String을 => parse 해서, Calendar로 변경
        // createdAt 변수의 일시 값으로 => parse 결과물 사용

        // 서버가 주는 양식을 보고, 그대로 적자
        val sdf = SimpleDateFormat("")

        // sdf.format( Date객체 ) => 지정해둔 양식의 String으로 가공
        // createdAt : Calendar / format의 피라미터 : Date => Calendar의 내용물인 time 변수가 Date
        txtCreatedAt.text = sdf.format(data.createdAt.time)

        return row
    }
}