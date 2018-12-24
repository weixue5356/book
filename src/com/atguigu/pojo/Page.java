package com.atguigu.pojo;

import java.util.List;

public class Page<T> {
	public static final int PAGE_SIZE = 4;
	// 当前页
	private int pageNo;
	// 总页码
	private int pageTotal;
	// 总记录数
	private int pageTotalCount;
	// 当前页数据
	private List<T> items;
	// 每页显示的数量 默认4
	private int pageSize = PAGE_SIZE;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		// 数据有效性边界检查
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageNo > pageTotal) {
			pageNo = pageTotal;
		}
		this.pageNo = pageNo;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageTotal=" + pageTotal
				+ ", pageTotalCount=" + pageTotalCount + ", items=" + items
				+ ", pageSize=" + pageSize + ", url=" + url + "]";
	}

}
