package com.example.covenant.journey.api.dto.general;

import org.springframework.data.domain.Page;

import java.util.List;

public class ResponsePage<T> {
	private List<T> items;
	private long pageNumber;
	private long pageSize;
	private long totalCount;

	public ResponsePage() {

	}

	public ResponsePage(Page<?> page, List<T> items) {
		this.pageNumber = page.getNumber();
		this.pageSize = page.getSize();
		this.totalCount = page.getTotalElements();
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}