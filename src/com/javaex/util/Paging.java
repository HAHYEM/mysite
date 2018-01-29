package com.javaex.util;

public class Paging {
	private int pageSize; // 한 페이지에 보여줄 게시글 수
	private int pageBlock; // 페이징 네비[블록] 사이즈
	private int pageNo; // 페이지 번호
	private int startRowNo; // 조회 시작 row번호
	private int endRowNo; // 조회 마지막 row번호
	private int firstPageNo; // 첫 번째 페이지 번호
	private int finalPageNo; // 마지막 페이지 번호
	private int prevPageNo; // 이전 페이지 번호
	private int nextPageNo; // 다음 페이지 번호
	private int startPageNo; // 시작 페이지(페이징 네비 기준)
	private int endPageNo; // 끝 페이지(페이징 네비 기준)
	private int totalCount; // 게시글 전체 수

	
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

	public void makePaging() { // 기본 값 설정
		if (this.totalCount == 0) {
			return;
		}
		if (this.pageNo == 0) { // 기본 페이지 번호
			this.setPageNo(1);
		}
		if (this.pageSize == 0) { // 기본 페이지 리스트 사이즈
			this.setPageSize(10);
		}
		if (this.pageBlock == 0) { // 기본 페이지 네비[블록] 사이즈
			this.setPageBlock(5);
		}

		// 첫 페이지, 마지막 페이지 계산하기
		int finalPage = (totalCount + (pageSize - 1)) / pageSize;
		this.setFirstPageNo(1);
		this.setFinalPageNo(finalPage);

		// 이전, 다음 페이지 계산
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

		// 페이징 네비[블록]계산
		int startPage = ((pageNo - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > finalPage) {
			endPage = finalPage;
		}
		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);
		
		//조회 시작 row, 조회 마지막 row 계산
		int startRowNo = ((pageNo - 1) * pageSize) + 1;
		int endRowNo = pageNo * pageSize;
		setStartRowNo(startRowNo);
		setEndRowNo(endRowNo);
	}
}