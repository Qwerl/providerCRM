package ru.cg.providerCRM.web.response;

import lombok.Data;
import ru.cg.providerCRM.entity.Product;

@Data
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

}