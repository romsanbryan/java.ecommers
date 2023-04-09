package romsanbryan.ecommers.model.request;

import romsanbryan.ecommers.annotations.Generated;

@Generated
public class ProductRequest {

	private String id;
	private String description;
	private float amount;

	public ProductRequest() {

	}

	public ProductRequest(String id, String description, float amount) {
		this.id = id;
		this.description = description;
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
