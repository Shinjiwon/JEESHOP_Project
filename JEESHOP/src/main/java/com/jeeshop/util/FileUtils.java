package com.jeeshop.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

// 웹프로젝트 외부영역에 존재하는 파일작업을 위한 클래스
public class FileUtils {
	
	// ▶ 파일 업로드
	/*
	 * @Params
	 * String uploadPath: 기본파일 업로드 경로
	 * String priginName: 원본 파일명
	 * byte[] fileData: 파일 데이터
	 
	 * @return
	 * String uploadFileName: 날짜경로 + 파일이름 → 예> \\2020\\10\\17\\uuid+fileName 
	 */
	public static String uploadFile(String uploadPath, String originName, byte[] fileData) throws Exception {
		
		System.out.println("=====uploadFile execute()...");
		
		// 파일명 설정 ex> uuid_파일명
		UUID uuid = UUID.randomUUID(); // 파일명 중복 방지
		String saveName = uuid.toString() + "_" + originName;
		// 파일 경로 설정 ex>날짜 경로 → \\2020\\10\\17
		String savePath = calcPath(uploadPath);
		System.out.println(savePath);
		// 설정 된 정보로 빈 파일 생성
		File target = new File(uploadPath + savePath, saveName);
		// 생성한 파일에 데이터 씀
		FileCopyUtils.copy(fileData, target);
		
		// 확자자명만 가져오기
		String formatName = originName.substring(originName.lastIndexOf(".") + 1);
		String uploadFileName = null;
		
		// 이미지 파일인지 일반파일인지 확인
		// 이미지 파일이면 썸네일 생성
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savePath, saveName);
			
		} else { // 일반 파일이면 아이콘 생성
			uploadFileName = makeIcon(uploadPath, savePath, saveName);
			
		}
		
		return uploadFileName;
	}

	// ▶ 날짜 폴더 경로 설정
	/*
	 * @params
	 * uploadPath: 기본 파일 업로드 경로
	 
	 * @return
	 * String: 생성된 날짜 폴더 경로. ex> \\2020\\10\\17 
	 */
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		/* 년/월/일 형태 날짜경로 */
		// 년 → \\2020
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		// 년 + 월 → \\2020\\10
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		// 년 + 월 + 일 → \\2020\\10\\17
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		System.out.println("=====calcPath result:" + datePath);
		
		// 경로별 모든 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	// ▶ 폴더 생성 메서드
	/*
	 * @Params
	 * String uploadPath: 기본 파일 업로드 경로
	 * String... paths: 생성할 폴더 경로
	 */
	private static void makeDir(String uploadPath, String... paths) {
		
		// 가장 마지막 매개변수의 폴더가 존재하는지 확인
		// 존재 할 경우 return
		if(new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		// 매개변수로 들어온 경로의 모든 폴더를 생성
		for(String path: paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	// ▶ 이미지 파일 썸네일 생성 메서드
	/*
	 * @Params
	 * String uploadPath: 기본 파일 업로드 경로
	 * String path: 날짜 경로
	 * String fileName: UUID_originName
	 
	 * @return
	 * String: 날짜경로 + s_ + fileName → ex> \\2020\\10\\17\\uuid + s_ + + fileName
	 */
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		// 썸네일 높이 80px, 너비 맞춤
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 120);
		
		// 썸네일 생성 준비
		String thumbNailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbNailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		// 썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 생성한 썸네일 경로의 subString을 반환
		System.out.println("=====makeThumbNail() thumbNail: " + thumbNailName);
		
		return thumbNailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ▶ 일반 파일의 아이콘 생성 메서드
	/*
	 * @Params
	 * String uploadPath: 기본 파일 업로드 경로
	 * String path: 날짜 경로
	 * String fileName: UUID_originName
	 
	 * @return
	 * String: 날짜경로 + s_ + fileName → ex> \\2020\\10\\17\\uuid + s_ + fileName
	 */
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ▶ 파일 가져오기
	/*
	 * 웹프로젝트 외부 영역의 파일을 가져와 
	   ResponseEntity로 반환
	 
	 * @Params
	 * String uploadPath: 외부 폴더 업로드 경로
	 * String fileName: 가져 올 파일명
	 
	 * @return
	 * ResponseEntity<byte[]>: 가져온 파일 정보와 Http상태코드 반환 
	 */
	public static ResponseEntity<byte[]> getFiles(String uploadPath, String fileName) throws Exception {
		
		InputStream in = null;
		byte[] fileData = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 파일의 확장자로 파일종류 확인
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType type = MediaUtils.getMediaType(formatName);
			
			// 파일헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			// 파일 가져오기
			in = new FileInputStream(uploadPath + fileName);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	// ▶ 이미지 파일 삭제
	/*
	 * @Params
	 * String uploadPath: 파일 경로
	 * String fileName: 삭제 할 파일
	 */
	public static void deleteFile(String uploadPath, String fileName) {
		// 날짜경로 + UUID_fileName
		String front = fileName.substring(0, 12);
		String end = fileName.substring(14);
		String origin = front + end;
		
		new File(uploadPath + origin.replace('/', File.separatorChar)).delete(); // 원본 파일 삭제
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete(); // 썸네일 파일 삭제 
	}
	
	// ▶ 썸네일 파일명 → 원래 파일명
	/*
	 * ex> /2020/10/17/s_UUID파일명 → /2020/10/17/UUID파일명
	 */
	public static String thumbToOriginName(String thumbNailName) {
		
		String front = thumbNailName.substring(0, 12);
		String end = thumbNailName.substring(14);
		
		return front + end;
	}
}
