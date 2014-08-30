package com.bluenightz.karaoke3;

import java.util.ArrayList;
import java.util.List;

public class Lang {
	public static String lang = "TH";
	private static List<String> strgroup = new ArrayList<String>();
	
	public static void buildlang(){
		if(lang == "TH"){
			strgroup.add(0,"ค้นหา");
			strgroup.add(1,"ชื่อเพลง");
			strgroup.add(2,"ชื่อศิลปิน");
			strgroup.add(3,"ดูคิวเพลง");
			strgroup.add(4,"บันเทิง");
			

			strgroup.add(5,"เพลงมาใหม่");
			strgroup.add(6,"20 อันดับฮิต");
			strgroup.add(7,"สตริง");
			strgroup.add(8,"ลูกกรุง");
			strgroup.add(9,"ลูกทุ่ง");
			strgroup.add(10,"เพลงทั้งหมด");
			
			
			// remote
			strgroup.add(11,"เปลี่ยนภาษา");
			strgroup.add(12,"ปุ่มควบคุม คิวเพลง");
		}
		if(lang == "CH"){
			strgroup.add(0,"搜索");
			strgroup.add(1,"按產品名稱");
			strgroup.add(2,"通過藝術家");
			strgroup.add(3,"查看播放列表");
			strgroup.add(4,"娛樂");
			

			strgroup.add(5,"新生入學");
			strgroup.add(6,"前20名");
			strgroup.add(7,"現代");
			strgroup.add(8,"民間");
			strgroup.add(9,"國家");
			strgroup.add(10,"所有歌曲");
			
			
			
			// remote
			strgroup.add(11,"語文");
			strgroup.add(12,"遙控器和播放列表");
		}
		if(lang == "JP"){
			strgroup.add(0,"検索");
			strgroup.add(1,"名前で");
			strgroup.add(2,"アーティスト");
			strgroup.add(3,"ビュープレイリスト");
			strgroup.add(4,"エンターテインメント");
			

			strgroup.add(5,"新規参入");
			strgroup.add(6,"トップ20");
			strgroup.add(7,"現代の");
			strgroup.add(8,"フォーク");
			strgroup.add(9,"カントリー");
			strgroup.add(10,"全ての曲");
			
			
			
			// remote
			strgroup.add(11,"ランゲージ");
			strgroup.add(12,"リモートおよびプレイリスト");
		}

		if(lang == "EN"){
			strgroup.add(0,"Search");
			strgroup.add(1,"By Name");
			strgroup.add(2,"By Artist");
			strgroup.add(3,"View Playlist");
			strgroup.add(4,"Entertainment");
			

			strgroup.add(5,"New Entry");
			strgroup.add(6,"Top 20");
			strgroup.add(7,"Modern");
			strgroup.add(8,"Folk");
			strgroup.add(9,"Country");
			strgroup.add(10,"All Songs");
			
			
			
			// remote
			strgroup.add(11,"LANGUAGE");
			strgroup.add(12,"REMOTE & PLAYLIST");
		}
		
		if(lang == "KR"){
			strgroup.add(0,"검색");
			strgroup.add(1,"이름으로");
			strgroup.add(2,"아티스트");
			strgroup.add(3,"재생 목록보기");
			strgroup.add(4,"환대");
			

			strgroup.add(5,"새 항목");
			strgroup.add(6,"상위 20");
			strgroup.add(7,"현대");
			strgroup.add(8,"사람들");
			strgroup.add(9,"국가");
			strgroup.add(10,"모든 노래");
			
			
			
			// remote
			strgroup.add(11,"언어");
			strgroup.add(12,"원격 및 재생");
		}
	}
	
	public static void setlang(String l){
		lang = l;
	}
	
	public static String getMenu(int i){
		return strgroup.get(i); 
	}
	
	public static String getLang(){
		return lang;
	}
}
