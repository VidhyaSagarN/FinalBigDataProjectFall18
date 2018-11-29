package com.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.dao.Item;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraSession {

	final static Logger log = Logger.getLogger(CassandraSession.class);

	/* Cassandra Cluster Instance */
	Cluster cluster;
	/* Cassandra Session instance */
	Session session;

	/* Cassandra Session instance instantiation using builder */
	public Session getSession() {

		if (log.isDebugEnabled()) {
			log.info("Entering: " + this.getClass().getName() + "."
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		}
		
		cluster = Cluster.builder().addContactPoint("localhost").build();
		session = cluster.connect("ecommerce");
		log.info("Exiting: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		return session;
	}

	/*
	 * Get all items from Cassandra ecommerce keyspace items table(column
	 * family)
	 */
	public Item getItem(String item_id) {
		log.info("Entering: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		log.info("Item ID is : " + item_id);
		ResultSet queryResults = getSession().execute("SELECT * FROM items where item_id= '" + item_id + "'");
		Item item = new Item(null, null, null, null, null, null, null, null, null, null);

		for (Row row : queryResults) {
			item.item_id = row.getString("item_id");
			item.item_brand = row.getString("item_brand");
			item.item_category = row.getString("item_category");
			item.item_description = row.getString("item_description");
			item.item_dimensions = row.getString("item_dimensions");
			item.item_flavor = row.getString("item_flavor");
			item.item_name = row.getString("item_name");
			Float price = row.getFloat("item_price");
			item.item_price = Float.toString(price);
			item.item_shipping = row.getString("item_shipping");
			item.item_size = row.getString("item_size");

		}
		log.info("Exiting: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		log.info("Item fetched from Casandra:\n" + "item_id=" + item.item_id + "\n" + "item_brand=" + item.item_brand
				+ "\n" + "item_category=" + item.item_category + "\n" + "item_description=" + item.item_description
				+ "\n" + "item_dimensions=" + item.item_dimensions + "\n" + "item_flavor=" + item.item_flavor + "\n"
				+ "item_price=" + item.item_price + "\n" + "item_shipping=" + item.item_shipping + "\n" + "item_size="
				+ item.item_size + "\n");
		return item;

	}

	public List<Item> getAllItems(List<String> itemIds) {

		log.info("Entering: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		List<Item> itemslist = new ArrayList<Item>();

		log.info("Item IDs: " + itemIds.toString());

		for (String itemid : itemIds) {

			log.info("Item ID: " + itemid);
			ResultSet queryResults = getSession().execute("SELECT * FROM items where item_id= '" + itemid + "'");
			
			log.info("Query Results: " + queryResults.toString());
			Item item = new Item(null, null, null, null, null, null, null, null, null, null);

			for (Row row : queryResults) {

				item.item_id = row.getString("item_id");
				item.item_brand = row.getString("item_brand");
				item.item_category = row.getString("item_category");
				item.item_description = row.getString("item_description");
				item.item_dimensions = row.getString("item_dimensions");
				item.item_flavor = row.getString("item_flavour");
				item.item_name = row.getString("item_name");
				Float price = row.getFloat("item_price");
				item.item_price = Float.toString(price);
				item.item_shipping = row.getString("item_shipping");
				item.item_size = row.getString("item_size");

				log.info("Item fetched from Casandra:\n" + "item_id=" + item.item_id + "\n" + "item_brand="
						+ item.item_brand + "\n" + "item_category=" + item.item_category + "\n" + "item_description="
						+ item.item_description + "\n" + "item_dimensions=" + item.item_dimensions + "\n"
						+ "item_flavor=" + item.item_flavor + "\n" + "item_price=" + item.item_price + "\n"
						+ "item_shipping=" + item.item_shipping + "\n" + "item_size=" + item.item_size + "\n");

				itemslist.add(item);
			}

		}
		
		log.info("Exiting: " + this.getClass().getName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		return itemslist;

	}

	public void close() {

		cluster.close();
	}
}
