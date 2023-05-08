package com.assignment.billapplication.controller;

import com.assignment.billapplication.BillApplication;
import com.assignment.billapplication.dto.BillRequestDto;
import com.assignment.billapplication.dto.CustomerRequestDto;
import com.assignment.billapplication.dto.ProductRequestDto;
import com.assignment.billapplication.entity.*;
import com.assignment.billapplication.repository.BillRepository;
import com.assignment.billapplication.service.BillService;
import com.assignment.billapplication.service.CustomerService;
import com.assignment.billapplication.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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
public class BillControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private BillService billService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private ProductService productService;
    private Bill billOne , billTwo , billThree;
    private Customer customer = new Customer(1L, "Nawaf", CustomerType.EMPLOYEE, null,null);
    private Product product = new Product(1L , "Soap", 10.0 , ProductType.NON_GROCERY , null);
    @Before
    public void init(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        List<Product> products = new ArrayList<>();
        products.add(product);
        billOne = new Bill(1L,null,0,0,0,products,customer);
        billTwo = new Bill(2L,null,0,0,0,products,customer);
        billThree = new Bill(3L,null,0,0,0,products,customer);
    }
    @Test
    public void shouldFindAllBills() throws Exception {
        List<Bill> list = new ArrayList<>();
        list.add(billOne);
        list.add(billTwo);
        list.add(billThree);
        when(billService.findAll()).thenReturn(list);
        this.mockMvc.perform(get("/api/bills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }
    @Test
    public void shouldFindBillById() throws Exception {
        customerService.save(customer);
        productService.save(product);
        billService.save(billOne);
        when(billService.findById(1L)).thenReturn(billOne);
        this.mockMvc.perform(get("/api/bills/{id}" , billOne.getId()))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name").value("Nawaf"))
//                .andExpect(jsonPath("$.type").value(CustomerType.EMPLOYEE.name()));
    }
    @Test
    public void shouldSaveNewBill() throws Exception {
        List<Long> productIds = new ArrayList<>();
        productIds.add(product.getId());
        BillRequestDto billRequestDto =
                new BillRequestDto(productIds, customer.getId());
        mockMvc.perform(post("/api/bills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(billRequestDto))
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