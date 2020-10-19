package com.jeeshop.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

/*
 * 이미지 파일의 확장자를 변수로 가지고 있어
 * 해당 파일이 이미지 파일인지 확인하기 위한 유틸
 */
public class MediaUtils {

	// 이미지 파일의 확장자를 가지고 있는 Map
	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<String, MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("JPG", MediaType.IMAGE_GIF);
		mediaMap.put("JPG", MediaType.IMAGE_PNG);
	}
	
	/*
	 * 이미지 파일인지 확인하는 메소드
	 * map에 해당하는 확장자가 key로 존재하는지 확인
	 * 
	 * @Params
	 * String type : 확인할 확장자 명
	 * 
	 * @return 
	 * 이미지 파일은 value,
	 * 이미지 파일이 아니면 null 
	 */
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
}
