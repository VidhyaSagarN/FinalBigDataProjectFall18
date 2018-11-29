package com.model;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.dao.Item;

public class SolrSearch {

	final static Logger log = Logger.getLogger(SolrSearch.class);

	public List<Item> solrSearchData(String str) {
		log.info("Entering: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		List<Item> itemList = new ArrayList<Item>();
		HttpSolrServer solrserver = new HttpSolrServer("http://localhost:8983/solr/items");

		SolrQuery query = new SolrQuery();
		// query.setQuery("item_description:*protein* AND
		// item_category:*Supplements Men*");
		//query.setQuery("Item_description:*protein*");
		
		if(str.equalsIgnoreCase("protein")){
		query.setQuery("Item_description:"+str.trim());
		//query.setQuery("Item_description:protein");
		}
		
		if(str.equalsIgnoreCase("RiteBite")){
			query.setQuery("Item_name:"+str);
		}
		
		if(str.equalsIgnoreCase("Prime AND only")){
			query.setQuery("Item_shipping:(Prime~0.75 AND only~0.75)");
			
		}
		if(str.equalsIgnoreCase("Pime")){
			query.setQuery("Item_shipping:(Pime~1 AND only~0.8)");
			
		}
		
		//query.setQuery("item_description:"+""+str+""+"~0.7");

		// query.setRequestHandler("/select");
		// query.addFilterQuery("item_category:Supplements Men");
		// query.setFields("item_id","item_name","item_category");
		// query.setStart(0);
		query.set("defType", "edismax");
		query.set("wt", "json");
		query.set("indent", true);
		log.info("Solr search query on index: " + query);

		try {

			RedisSession redissession = new RedisSession();
			QueryResponse response = solrserver.query(query);
			SolrDocumentList results = response.getResults();

			List<String> solrItemList = new ArrayList<String>();

			for (int i = 0; i < results.size(); ++i) {
				solrItemList.add((String) results.get(i).getFieldValue("Item_id"));

			}

			itemList = redissession.cachedItems(solrItemList);

		} 
		
		catch (Exception e) {
			
			log.info(e.toString());
			System.out.println(e.toString());
		}
		
		log.info("Exiting: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		return itemList;
	}

}
