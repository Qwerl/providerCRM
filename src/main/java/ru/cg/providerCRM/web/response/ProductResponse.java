package ru.cg.providerCRM.web.response;

import ru.cg.providerCRM.entity.Product;

public class ProductResponse extends Response {

	private String id;
	private String name;
	private String note;

	public ProductResponse() {}

	public ProductResponse(Product product) {
		id = product.getId().toString();
		name = product.getName();
		note = product.getNote();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
