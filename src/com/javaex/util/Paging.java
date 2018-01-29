package com.javaex.util;

public class Paging {
	private int pageSize; // �� �������� ������ �Խñ� ��
	private int pageBlock; // ����¡ �׺�[����] ������
	private int pageNo; // ������ ��ȣ
	private int startRowNo; // ��ȸ ���� row��ȣ
	private int endRowNo; // ��ȸ ������ row��ȣ
	private int firstPageNo; // ù ��° ������ ��ȣ
	private int finalPageNo; // ������ ������ ��ȣ
	private int prevPageNo; // ���� ������ ��ȣ
	private int nextPageNo; // ���� ������ ��ȣ
	private int startPageNo; // ���� ������(����¡ �׺� ����)
	private int endPageNo; // �� ������(����¡ �׺� ����)
	private int totalCount; // �Խñ� ��ü ��

	
	public Paging() {
		super();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartRowNo() {
		return startRowNo;
	}

	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}

	public int getEndRowNo() {
		return endRowNo;
	}

	public void setEndRowNo(int endRowNo) {
		this.endRowNo = endRowNo;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	}

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
	}

	@Override
	public String toString() {
		return "Paging [pageSize=" + pageSize + ", pageBlock=" + pageBlock + ", pageNo=" + pageNo + ", startRowNo="
				+ startRowNo + ", endRowNo=" + endRowNo + ", firstPageNo=" + firstPageNo + ", finalPageNo="
				+ finalPageNo + ", prevPageNo=" + prevPageNo + ", nextPageNo=" + nextPageNo + ", startPageNo="
				+ startPageNo + ", endPageNo=" + endPageNo + ", totalCount=" + totalCount + "]";
	}

	public void makePaging() { // �⺻ �� ����
		if (this.totalCount == 0) {
			return;
		}
		if (this.pageNo == 0) { // �⺻ ������ ��ȣ
			this.setPageNo(1);
		}
		if (this.pageSize == 0) { // �⺻ ������ ����Ʈ ������
			this.setPageSize(10);
		}
		if (this.pageBlock == 0) { // �⺻ ������ �׺�[����] ������
			this.setPageBlock(5);
		}

		// ù ������, ������ ������ ����ϱ�
		int finalPage = (totalCount + (pageSize - 1)) / pageSize;
		this.setFirstPageNo(1);
		this.setFinalPageNo(finalPage);

		// ����, ���� ������ ���
		boolean prevPageNo = pageNo == 1 ? true : false;
		boolean nextPageNo = pageNo == finalPage ? true : false;
		if (prevPageNo) {
			this.setPrevPageNo(1);
		} else {
			this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo - 1)));
		}
		if (nextPageNo) {
			this.setNextPageNo(finalPage);
		} else {
			this.setNextPageNo(((pageNo + 1) < finalPage ? finalPage : (pageNo + 1)));
		}

		// ����¡ �׺�[����]���
		int startPage = ((pageNo - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > finalPage) {
			endPage = finalPage;
		}
		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);
		
		//��ȸ ���� row, ��ȸ ������ row ���
		int startRowNo = ((pageNo - 1) * pageSize) + 1;
		int endRowNo = pageNo * pageSize;
		setStartRowNo(startRowNo);
		setEndRowNo(endRowNo);
	}
}