package romsanbryan.ecommers.model.response;

import java.util.Date;
import java.util.List;

import romsanbryan.ecommers.annotations.Generated;
import romsanbryan.ecommers.model.ProductCart;

@Generated
public class CartResponse {

	private volatile List<ProductCart> product;
	private Date createTime;

	public CartResponse() {
	}

	public CartResponse(List<ProductCart> product, Date createTime) {
		this.product = product;
		this.createTime = createTime;
	}

	public List<ProductCart> getProduct() {
		return product;
	}

	public void setProduct(List<ProductCart> product) {
		this.product = product;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartResponse other = (CartResponse) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
