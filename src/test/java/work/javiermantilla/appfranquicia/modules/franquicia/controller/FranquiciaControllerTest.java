package work.javiermantilla.appfranquicia.modules.franquicia.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basic.util.JSONUtil;
import work.javiermantilla.appfranquicia.config.TestSecurityConfig;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaUpdateDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;

@Log4j2
@WebMvcTest
@ContextConfiguration(classes = { FranquiciaController.class, TestSecurityConfig.class })
class FranquiciaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	private FranquiciaServices franquiciaServices;

	@BeforeEach
	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	void testCrearFranquicia() throws Exception {
		
		FranquiciaDTO franquiciaDTO =FranquiciaDTO.builder()
				.nombre("demo")				
				.build();
		String inputJson = JSONUtil.mapToJson(franquiciaDTO);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/franquicia")
						.content(inputJson).contentType(MediaType.APPLICATION_JSON))
				.andReturn();		
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	void testListaFranquicia() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/franquicia")				
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();		
		log.info(mvcResult.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	void testActualizarNombre() throws Exception {
		FranquiciaUpdateDTO franquiciaUpdateDTO= new FranquiciaUpdateDTO("nombre");
		
		String inputJson = JSONUtil.mapToJson(franquiciaUpdateDTO);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.patch("/api/v1/franquicia/{id}",1)
						.content(inputJson)
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn();		
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

}
