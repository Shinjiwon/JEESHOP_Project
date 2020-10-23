package com.jeeshop.util;

public class Criteria {

	// 페이징 → 페이지 번호 출력
	private int page; // 현재 페이지
	private int perPageNum; // 한 페이지 당 게시물 개수
	
	// sql문에서 사용
	private int rowStart;
	private int rowEnd;
	
	// 페이지 기본 설정
	public Criteria() {
		
		this.page = 1; // 현재 페이지 번호
		this.perPageNum = 5; // 한 페이지당 출력 게시물 개수
	}
	
	public void setPage(int page) {
		
		if(page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 5;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		
		return page;
	}
	
	public  int getPageStart() {
		
		return (this.page - 1) * perPageNum;
	}
	
	public int getPerPageNum() {
		
		return this.perPageNum;
	}
	
	public int getRowStart() {
		
		return ((page - 1) * perPageNum) + 1;
	}
	
	public int getRowEnd() {
		
		return getRowStart() + perPageNum - 1;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}
}