package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dao.Item;

import redis.clients.jedis.Jedis;

public class RedisSession {

	final static Logger log = Logger.getLogger(RedisSession.class);

	Jedis jedis = new Jedis("localhost");

	CassandraSession node = new CassandraSession();

	public List<Item> cachedItems(List<String> itemIds) {
		log.info("Entering: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Map<String, String> itemHashMap = new HashMap<String, String>();
		List<Item> itemslist = new ArrayList<Item>();
		List<String> cassandraItemIds = new ArrayList<String>();

		for (String member : itemIds) {
			if (jedis.exists("itemid:" + member)) {
				log.info("Fetching " + member + " from Redis In memory Cache" + jedis.get("item_id:" + member));

				Item item = new Item(null, null, null, null, null, null, null, null, null, null);
				itemHashMap = jedis.hgetAll("itemid:" + member);
				item.item_id = itemHashMap.get("itemid");
				item.item_brand = itemHashMap.get("itembrand");
				item.item_category = itemHashMap.get("itemcategory");
				item.item_description = itemHashMap.get("itemdescription");
				item.item_dimensions = itemHashMap.get("itemdimensions");
				item.item_flavor = itemHashMap.get("itemflavor");
				item.item_name = itemHashMap.get("itemname");
				item.item_price = itemHashMap.get("itemprice");
				item.item_shipping = itemHashMap.get("itemshipping");
				item.item_size = itemHashMap.get("itemsize");
				itemslist.add(item);
				log.info("Item in Redis Cache :\n" + "item_id=" + item.item_id + "\n" + "item_brand=" + item.item_brand
						+ "\n" + "item_category=" + item.item_category + "\n" + "item_description="
						+ item.item_description + "\n" + "item_dimensions=" + item.item_dimensions + "\n"
						+ "item_flavor=" + item.item_flavor + "\n" + "item_price=" + item.item_price + "\n"
						+ "item_shipping=" + item.item_shipping + "\n" + "item_size=" + item.item_size + "\n");

			} else {
				log.info("Item ID: " + member
						+ " not cached in Redis In Memory. Adding to item list to fetch from cassandra");
				cassandraItemIds.add(member);
			}

		}

		if (cassandraItemIds.size() > 0) {
			log.info("Items to fetch from cassandra due to redis cache expiration: " + cassandraItemIds.toString());

			List<Item> fullItemList = node.getAllItems(cassandraItemIds);

			itemslist.addAll(fullItemList);

			for (Item item : fullItemList) {
				itemHashMap.put("itemid", item.item_id);
				itemHashMap.put("itembrand", item.item_brand);
				itemHashMap.put("itemcategory", item.item_category);
				itemHashMap.put("itemdescription", item.item_description);
				itemHashMap.put("itemdimensions", item.item_dimensions);
				itemHashMap.put("itemflavor", item.item_flavor);
				itemHashMap.put("itemname", item.item_name);
				itemHashMap.put("itemprice", item.item_price);
				itemHashMap.put("itemshipping", item.item_shipping);
				itemHashMap.put("itemsize", item.item_size);

				jedis.hmset("itemid:" + item.item_id, itemHashMap);
				jedis.expire("itemid:" + item.item_id, 120);
				log.info("Items to fetched from cassandra and put to redis cache with expiration of 120 seconds: \n"
						+ "item_id=" + item.item_id + "\n" + "item_brand=" + item.item_brand + "\n" + "item_category="
						+ item.item_category + "\n" + "item_description=" + item.item_description + "\n"
						+ "item_dimensions=" + item.item_dimensions + "\n" + "item_flavor=" + item.item_flavor + "\n"
						+ "item_price=" + item.item_price + "\n" + "item_shipping=" + item.item_shipping + "\n"
						+ "item_size=" + item.item_size + "\n");

			}
			node.close();

		}
		log.info("Exiting: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		return itemslist;

	}

}
