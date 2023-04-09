package romsanbryan.ecommers.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import romsanbryan.ecommers.exceptions.ServiceException;
import romsanbryan.ecommers.model.request.ProductRequest;
import romsanbryan.ecommers.model.response.CartIdResponse;
import romsanbryan.ecommers.model.response.CartResponse;
import romsanbryan.ecommers.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired(required = true)
	CartService cartService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getCart(@PathVariable String id) {
		Long ini = new java.util.Date().getTime();
		HttpStatus status = HttpStatus.OK;
		CartResponse cart = null;
		try {
			cart = cartService.getCart(id);
			if (cart == null || cart.getProduct() == null
					|| cart.getProduct().isEmpty()) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} finally {
			log.info("Tiempo de ejecucion: ".concat("getCart : ") + Float
					.valueOf(String
							.valueOf((new java.util.Date().getTime() - ini)))
					/ 1000 + "s");
		}

		return new ResponseEntity<>(cart, status);
	}

	@PostMapping("/")
	public ResponseEntity<?> newCart() {
		HttpStatus status = HttpStatus.CREATED;
		CartIdResponse cart = null;
		Long ini = new java.util.Date().getTime();

		try {
			cart = cartService.newCart();
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} finally {
			log.info("Tiempo de ejecucion: ".concat("newCart: ") + Float
					.valueOf(String
							.valueOf((new java.util.Date().getTime() - ini)))
					/ 1000 + "s");
		}

		return new ResponseEntity<>(cart, status);

	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<?> addProduct(@PathVariable String id,
			@RequestBody ProductRequest pm) {
		HttpStatus status = HttpStatus.ACCEPTED;
		Long ini = new java.util.Date().getTime();

		try {
			cartService.addProduct(id, pm);
		} catch (ServiceException e) {
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} finally {
			log.info("Tiempo de ejecucion: ".concat("addProduct: ") + Float
					.valueOf(String
							.valueOf((new java.util.Date().getTime() - ini)))
					/ 1000 + "s");
		}

		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteProduct(@PathVariable String id) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		Long ini = new java.util.Date().getTime();

		try {
			cartService.delete(id);

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} finally {
			log.info("Tiempo de ejecucion: ".concat("deleteProduct: ") + Float
					.valueOf(String
							.valueOf((new java.util.Date().getTime() - ini)))
					/ 1000 + "s");
		}

		return new ResponseEntity<>(status);

	}

}
