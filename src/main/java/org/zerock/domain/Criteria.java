package org.zerock.domain;

// 페이지 설정에 관련된 Criteria 클래스
public class Criteria {
	private int page; // 현재 페이지 번호
	private int perPageNum; // 페이지 당 보여지는 게시글 갯수
	// 초기 설정값
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	// 이후 변경되는 page 값 
	public void setPage(int page) {
		System.out.println("Criteria.setPage() >>> : " + page);
		if(page <=0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	// 이후 변경되는 페이지 당 보여지는 게시글 갯수
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	public int getPage() {
		return page;
	}
	// method for MyBatis SQL Mapper : 시작 페이지의 첫 게시글 번호를 구함
	public int getPageStart() {
		// 시작 게시글 번호  = (페이지 번호-1) * 페이자 당 보여지는 갯수
		return (this.page - 1) * perPageNum;
	}
	// method for MyBatis SQL Mapper : 페이지 당 보여지는 갯수
	public int getPerPageNum() {
		return this.perPageNum;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", " + "perPageNum=" + perPageNum + "]";
	}
}
