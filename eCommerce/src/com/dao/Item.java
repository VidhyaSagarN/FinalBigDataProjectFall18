package com.dao;

public class Item extends Thread {

	public String item_id;
	public String item_brand;
	public String item_category;
	public String item_description;
	public String item_dimensions;
	public String item_flavor;
	public String item_name;
	public String item_price;
	public String item_shipping;
	public String item_size;

	public Item(String item_id, String item_brand, String item_category, String item_description,
			String item_dimensions, String item_flavor, String item_name, String item_price, String item_shipping,
			String item_size) {
		this.item_id = item_id;
		this.item_brand = item_brand;
		this.item_category = item_category;
		this.item_description = item_description;
		this.item_dimensions = item_dimensions;
		this.item_flavor = item_flavor;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_shipping = item_shipping;
		this.item_size = item_size;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_brand() {
		return item_brand;
	}

	public void setItem_brand(String item_brand) {
		this.item_brand = item_brand;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_dimensions() {
		return item_dimensions;
	}

	public void setItem_dimensions(String item_dimensions) {
		this.item_dimensions = item_dimensions;
	}

	public String getItem_flavor() {
		return item_flavor;
	}

	public void setItem_flavor(String item_flavor) {
		this.item_flavor = item_flavor;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_price() {
		return item_price;
	}

	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	public String getItem_shipping() {
		return item_shipping;
	}

	public void setItem_shipping(String item_shipping) {
		this.item_shipping = item_shipping;
	}

	public String getItem_size() {
		return item_size;
	}

	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}

}
