package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/5/3.
 */
public class SearchResult implements Serializable {

    //总页数
    private long totalPages;
    //总数量
    private long totalNumber;
    //查询出的商品列表
    private List<SearchItem> itemList;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
