package com.assignment.billapplication.controller;

import com.assignment.billapplication.BillApplication;
import com.assignment.billapplication.dto.CustomerRequestDto;
import com.assignment.billapplication.dto.ProductRequestDto;
import com.assignment.billapplication.entity.Customer;
import com.assignment.billapplication.entity.CustomerType;
import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.entity.ProductType;
import com.assignment.billapplication.service.CustomerService;
import com.assignment.billapplication.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ BillApplication.class })
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ProductService productService;
    private Product productOne;
    private Product productTwo;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        productOne = new Product(1L , "Soap",10.0 , ProductType.NON_GROCERY , null);
        productTwo= new Product(2L , "Juice",10.0 , ProductType.GROCERY , null);
    }
    @Test
    public void shouldFindAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(productOne);
        list.add(productTwo);
        when(productService.findAll()).thenReturn(list);
        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    public void shouldFindProductById() throws Exception {
        productService.save(productOne);
        when(productService.findByID(1L)).thenReturn(productOne);
        this.mockMvc.perform(get("/api/products/{id}" , productOne.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Soap"))
                .andExpect(jsonPath("$.type").value(ProductType.NON_GROCERY.name()));
    }

    @Test
    public void shouldSaveNewProduct() throws Exception {
        ProductRequestDto productRequestDto =
                new ProductRequestDto("Soap", 10.0, "Non_grocery");
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productRequestDto))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}