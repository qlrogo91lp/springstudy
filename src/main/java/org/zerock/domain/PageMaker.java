package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;	// 전체 게시글 갯수
	private int startPage;	// 현재 페이지 그룹의 시작 페이지 번호
	private int endPage;	// 현재 페이지 그룹의 끝 페이지 번호
	private boolean prev;	// 이전 페이지 링크 : 맨 앞의 페이지 번호가 1이 아니라면 누를 수 있음
	private boolean next;	// 다음 페이지 링크 
	
	private int displayPageNum = 10; // 페이지 번호의 갯수(페이지 그룹의 페이지 갯수)
	private Criteria cri;
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	// 페이지 계산
	public void calcData() {
		// endPage = (현재 페이지 번호 / 페이지 번호의 갯수) * 페이지번호의 갯수
		// cri.getPage() : 시작 게시글 번호 
		// Math.ceil() : 소수점 이하를 올림
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		System.out.println("PageMaker/cri.getPage >>> : " + cri.getPage());
		System.out.println("PageMaker/startPage >>> : " + startPage);
		
		// cri.getPerPageNum() : 한 페이지에 출력되는 게시글의 갯수
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		// 최종 endPage 보정
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = (startPage == 1) ? false : true; // 1 페이지만 아니면 존재
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true; // 다음 페이지가 존재해야 하면 true
	}
	// uri에 쿼리스트링을 보냄
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
										.queryParam("page", page)
										.queryParam("perPageNum", cri.getPerPageNum())
										.build();
		return uriComponents.toUriString();
	}
	// 검색 기능 추가
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
										.queryParam("page", page)
										.queryParam("perPageNum", cri.getPerPageNum())
										.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
										.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
										.build();
		return uriComponents.toUriString();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
