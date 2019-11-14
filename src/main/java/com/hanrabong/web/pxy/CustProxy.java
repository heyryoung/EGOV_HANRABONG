package com.hanrabong.web.pxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hanrabong.web.hcust.HCust;
import com.hanrabong.web.hcust.HCustMapper;

@Lazy
@Component("manager")
public class CustProxy extends Proxy{
	@Autowired HCustMapper mapper;
	
	public ArrayList<HCust> makeUsers() {
        ArrayList<HCust> tempUserList = new ArrayList<>();
        
        for (int cnt = 0; cnt < 700; cnt++) {
            String cname = "", cid="", cpw="", gen="", skinType = "";
            HCust tempUser = new HCust();
            List<String> LN = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                    "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                    "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                    "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                    "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
            List<String> FN = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                    "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                    "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                    "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                    "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                    "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                    "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                    "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                    "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                    "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
            List<String> gender = Arrays.asList("남", "여");
            List<String> en1 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                    "p", "q", "r", "s", "t", "u", "v", "x", "y", "z");
            List<String> type = Arrays.asList("건성", "중성", "지성", "트러블성", "민감성");
            Collections.shuffle(FN);
            Collections.shuffle(gender);
            Collections.shuffle(type);
            for (int i = 0; i < 5; i++) {
                Collections.shuffle(en1);
                cid += en1.get(0);
            }
            for (int i = 0; i < 5; i++) {
                Collections.shuffle(en1);
                cpw += en1.get(0);
            }
            cname = LN.get(0)+FN.get(0)+FN.get(1);
            tempUser.setCid(cid);
            tempUser.setCname(cname);
            tempUser.setCpw(cpw);
            tempUser.setGen(gender.get(0));
            tempUser.setSkinType(type.get(0));
            tempUserList.add(tempUser);
            System.out.println(tempUser.toString()+"-------------------------");
        }
        return tempUserList;
    }
	public List<String> setBirth(){
	      Random r = new Random();
	      List<String> birth = new ArrayList<>();
	      String y,m,d= "";
	      for(int i=0; i<500; i++) {
	          y=String.valueOf(r.nextInt(80)+1929);
	          m=String.valueOf(r.nextInt(11)+1);
	          if(m=="2") {d=String.valueOf(r.nextInt(28)+1);
	          }else if(m =="4"||m=="6"||m=="9"||m=="11") {
	              d=String.valueOf(r.nextInt(30)+1);
	          }else {d=String.valueOf(r.nextInt(31)+1);}
	          birth.add(y+"-"+m+"-"+d);
	      }
	      return birth;
	  }
	/*public User makeUser(){
		 // uid,pwd,uname,birth,gender,tel,pettype;
		return new User(makeUserid(),"1",makeUsername(),makeBirthday(),makeGender(),makeTelephone(),makePetType());
*/
	/*@Transactional
	public void insertHCust() {
		for(int i=0; i<500;i++) {
			mapper.insertHCust(makeUser());
		}
	}*/
}
