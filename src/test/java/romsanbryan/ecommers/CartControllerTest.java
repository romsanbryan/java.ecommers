package romsanbryan.ecommers;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import romsanbryan.ecommers.controllers.CartController;
import romsanbryan.ecommers.exceptions.ServiceException;
import romsanbryan.ecommers.model.ProductCart;
import romsanbryan.ecommers.model.response.CartIdResponse;
import romsanbryan.ecommers.model.response.CartResponse;
import romsanbryan.ecommers.services.CartService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CartController.class)
class CartControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	CartService cartService;

	@MockBean
	CartIdResponse cartIdResponseManual;

	@DisplayName("Give Cart null")
	@Test
	public void givenCartProduct_whenCartNull_thenReturnNoContent()
			throws Exception {

		mvc.perform(
				MockMvcRequestBuilders.get("/cart/{{id}}", Mockito.anyString())
						.accept(MediaType.APPLICATION_JSON).content(""))
				.andDo(print()).andExpect(status().isNotFound());

	}
	@DisplayName("Give Cart Exception")
	@Test
	public void givenCartProduct_whenCartNull_thenReturnException()
			throws Exception {
		willThrow(Exception.class).given(cartService);

		mvc.perform(
				MockMvcRequestBuilders.get("/cart/{{id}}", Mockito.anyString())
						.accept(MediaType.APPLICATION_JSON).content(""))
				.andDo(print()).andExpect(status().isInternalServerError());

	}

	@DisplayName("Give Cart empty")
	@Test
	public void givenCartProduct_whenCartEmpty_thenReturnNoContent()
			throws Exception {

		CartResponse car = new CartResponse();

		given(cartService.getCart(Mockito.anyString())).willReturn(car);

		mvc.perform(
				MockMvcRequestBuilders.get("/cart/{{id}}", Mockito.anyString())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

	}

	@DisplayName("Give Cart Product null")
	@Test
	public void givenCartProduct_whenCartProductNull_thenReturnNoContent()
			throws Exception {

		CartResponse car = new CartResponse();
		List<ProductCart> products = new ArrayList<ProductCart>();
		car.setProduct(products);

		given(cartService.getCart(Mockito.anyString())).willReturn(car);

		mvc.perform(
				MockMvcRequestBuilders.get("/cart/{{id}}", Mockito.anyString())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

	}

	@DisplayName("Give Cart OK")
	@Test
	public void givenCartProduct_whenCartProductNotNull_thenReturnOK()
			throws Exception {

		CartResponse car = new CartResponse();
		List<ProductCart> products = new ArrayList<ProductCart>();
		products.add(new ProductCart());
		car.setProduct(products);

		given(cartService.getCart(Mockito.anyString())).willReturn(car);

		mvc.perform(
				MockMvcRequestBuilders.get("/cart/{{id}}", Mockito.anyString())
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@DisplayName("Delete Cart")
	@Test
	public void deleteCartProduct_thenReturnOK() throws Exception {

		mvc.perform(MockMvcRequestBuilders
				.delete("/cart/{{id}}", Mockito.anyString())
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNoContent());

	}

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@DisplayName("Delete Cart Exception")
	@Test
	public void deleteCartProduct_thenReturnException() throws Exception {

		willThrow(Exception.class).given(cartService);;

		mvc.perform(MockMvcRequestBuilders
				.delete("/cart/{{id}}", Mockito.anyString())
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isInternalServerError());

	}

	@DisplayName("Create Cart")
	@Test
	public void createCart_thenReturnOK() throws Exception {

		CartIdResponse car = new CartIdResponse();

		given(cartService.newCart()).willReturn(car);

		mvc.perform(MockMvcRequestBuilders.post("/cart/")
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}
	@DisplayName("Create Cart Exception")
	@Test
	public void createCart_thenReturnException() throws Exception {

		willThrow(Exception.class).given(cartService);

		mvc.perform(MockMvcRequestBuilders.post("/cart/")
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isInternalServerError());

	}
	@DisplayName("PUT product Cart")
	@Test
	public void addProduct_thenReturnOK() throws Exception {
		String jsonEmpty = "{}";

		mvc.perform(MockMvcRequestBuilders.put("/cart/a").content(jsonEmpty)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isAccepted());

	}
	@DisplayName("PUT product Cart Exception")
	@Test
	public void addProduct_thenReturnException() throws Exception {
		String jsonEmpty = "{}";
		willThrow(Exception.class).given(cartService);

		mvc.perform(MockMvcRequestBuilders.put("/cart/a").content(jsonEmpty)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isInternalServerError());

	}
	@DisplayName("PUT product Cart ServiceException")
	@Test
	public void addProduct_thenReturnServiceException() throws Exception {
		String jsonEmpty = "{}";
		willThrow(ServiceException.class).given(cartService)
				.addProduct(Mockito.anyString(), Mockito.any());

		mvc.perform(MockMvcRequestBuilders.put("/cart/a").content(jsonEmpty)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());

	}

}
