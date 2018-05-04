package com.taotao.search.service;

import com.taotao.pojo.SearchResult;

/**
 * Created by admin on 2018/5/3.
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
