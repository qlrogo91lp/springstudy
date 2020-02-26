package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;	// ��ü �Խñ� ����
	private int startPage;	// ���� ������ �׷��� ���� ������ ��ȣ
	private int endPage;	// ���� ������ �׷��� �� ������ ��ȣ
	private boolean prev;	// ���� ������ ��ũ : �� ���� ������ ��ȣ�� 1�� �ƴ϶�� ���� �� ����
	private boolean next;	// ���� ������ ��ũ 
	
	private int displayPageNum = 10; // ������ ��ȣ�� ����(������ �׷��� ������ ����)
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
	// ������ ���
	public void calcData() {
		// endPage = (���� ������ ��ȣ / ������ ��ȣ�� ����) * ��������ȣ�� ����
		// cri.getPage() : ���� �Խñ� ��ȣ 
		// Math.ceil() : �Ҽ��� ���ϸ� �ø�
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		System.out.println("PageMaker/cri.getPage >>> : " + cri.getPage());
		System.out.println("PageMaker/startPage >>> : " + startPage);
		
		// cri.getPerPageNum() : �� �������� ��µǴ� �Խñ��� ����
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		// ���� endPage ����
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = (startPage == 1) ? false : true; // 1 �������� �ƴϸ� ����
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true; // ���� �������� �����ؾ� �ϸ� true
	}
	// uri�� ������Ʈ���� ����
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
										.queryParam("page", page)
										.queryParam("perPageNum", cri.getPerPageNum())
										.build();
		return uriComponents.toUriString();
	}
	// �˻� ��� �߰�
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
