package kr.or.ddit.common.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.calendar.service.CalendarService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CalendarController {
	@Inject
	private CalendarService service;
	
	@ResponseBody
	@RequestMapping(value="calendar.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Map<String, Object>> monthPlan() {
		List<Map<String, Object>> list = service.calendarList();
		
		log.info(list.toString());
		
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		
		for(int i=0; i < list.size(); i++) {			
			hash.put("no", list.get(i).get("ID")); //제목
			hash.put("title", list.get(i).get("TITLE")); //제목
			hash.put("start", list.get(i).get("START1")); //시작일자
			hash.put("end", list.get(i).get("END1")); //종료일자
			
			jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
			jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
			
			log.info("jsonArrCheck: {}", jsonArr);
		}
		return jsonArr;
	}
	
	@RequestMapping("/calendar/calendarList.do")
	public String calendarList() {
		return "/common/calendar/Calendar";
	}
}
