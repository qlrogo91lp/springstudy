package org.zerock.domain;

// ������ ������ ���õ� Criteria Ŭ����
public class Criteria {
	private int page; // ���� ������ ��ȣ
	private int perPageNum; // ������ �� �������� �Խñ� ����
	// �ʱ� ������
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	// ���� ����Ǵ� page �� 
	public void setPage(int page) {
		System.out.println("Criteria.setPage() >>> : " + page);
		if(page <=0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	// ���� ����Ǵ� ������ �� �������� �Խñ� ����
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
	// method for MyBatis SQL Mapper : ���� �������� ù �Խñ� ��ȣ�� ����
	public int getPageStart() {
		// ���� �Խñ� ��ȣ  = (������ ��ȣ-1) * ������ �� �������� ����
		return (this.page - 1) * perPageNum;
	}
	// method for MyBatis SQL Mapper : ������ �� �������� ����
	public int getPerPageNum() {
		return this.perPageNum;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", " + "perPageNum=" + perPageNum + "]";
	}
}
