package com.aikitty.entity;

public class SongParams {

	private String s;//搜索关键词   必填
	private String type;//搜索类型  默认搜索音乐     必填
	private Integer limit;//搜索结果数量  默认100条     不必填
	private Integer offset;//搜索结果页数  默认一页        不必填
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	
}
