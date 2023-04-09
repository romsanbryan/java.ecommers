package romsanbryan.ecommers.model.response;

import romsanbryan.ecommers.annotations.Generated;

@Generated
public class CartIdResponse {

	private String idCart;

	public CartIdResponse() {
	}

	public CartIdResponse(String idCart) {
		this.idCart = idCart;
	}

	public String getIdCart() {
		return idCart;
	}

	public void setIdCart(String idCart) {
		this.idCart = idCart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCart == null) ? 0 : idCart.hashCode());
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
		CartIdResponse other = (CartIdResponse) obj;
		if (idCart == null) {
			if (other.idCart != null)
				return false;
		} else if (!idCart.equals(other.idCart))
			return false;
		return true;
	}

}
