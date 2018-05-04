package com.taotao.search.dao;

import com.taotao.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by admin on 2018/5/3.
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;

}
