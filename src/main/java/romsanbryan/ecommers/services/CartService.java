package romsanbryan.ecommers.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import romsanbryan.ecommers.constants.ExceptionConstants;
import romsanbryan.ecommers.exceptions.ServiceException;
import romsanbryan.ecommers.model.ProductCart;
import romsanbryan.ecommers.model.request.ProductRequest;
import romsanbryan.ecommers.model.response.CartIdResponse;
import romsanbryan.ecommers.model.response.CartResponse;

@Service
public class CartService {

	Logger logger = LoggerFactory.getLogger(CartService.class);
	public static HashMap<String, CartResponse> hashMap = new HashMap<>();

	@Autowired
	private Environment environment;

	public CartResponse getCart(String id) {
		logger.debug(String.format("getCart start: %s", id));
		CartResponse cartResponseManual = null;
		CartResponse a = hashMap.get(id);
		cartResponseManual = a;

		logger.debug(String.format("getCart finish: size %d",
				a != null && a.getProduct() != null && !a.getProduct().isEmpty()
						? cartResponseManual.getProduct().size()
						: 0));
		return cartResponseManual;
	}

	public CartIdResponse newCart() {
		logger.debug("new Cart start");
		CartResponse CartResponseManual = new CartResponse(null, new Date());
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		hashMap.put(uuidAsString, CartResponseManual);
		logger.debug(String.format("new cart finish: %s", uuid));

		return new CartIdResponse(uuidAsString);

	}

	public void addProduct(String id, ProductRequest pm)
			throws ServiceException {
		logger.debug(String.format("add product start to %s", id));

		if (!hashMap.containsKey(id)) {
			logger.warn("Cart, no exist");

			throw new ServiceException(ExceptionConstants.NO_CART,
					ExceptionConstants.NO_CART_CODE);
		}

		CartResponse a = hashMap.get(id);

		List<ProductCart> ProductManualos = a.getProduct() == null
				|| a.getProduct().isEmpty()
						? new ArrayList<ProductCart>()
						: a.getProduct();

		ProductManualos.add(new ProductCart(pm.getId(), pm.getDescription(),
				pm.getAmount()));

		a.setProduct(ProductManualos);
		hashMap.put(id, a);
		logger.debug("product added");

		logger.debug(String.format("add product finish to %s", id));

	}

	public void delete(String id) {
		if (hashMap.containsKey(id))
			hashMap.remove(id);
		else
			logger.warn("Can't delete cart, no exist");

	}

}
