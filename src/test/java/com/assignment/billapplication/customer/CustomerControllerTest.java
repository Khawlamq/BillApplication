package com.assignment.billapplication.controller;

        import com.assignment.billapplication.BillApplication;
        import com.assignment.billapplication.dto.CustomerRequestDto;
        import com.assignment.billapplication.entity.Customer;
        import com.assignment.billapplication.entity.CustomerType;
        import com.assignment.billapplication.service.CustomerService;
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
public class CustomerControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CustomerService customerService;
    private Customer customerOne;
    private Customer customerTwo;
    private Customer customerThree;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        customerOne = new Customer(1L, "Nawaf", CustomerType.EMPLOYEE, null,null);
        customerTwo = new Customer(2L, "Abdullah", CustomerType.AFFILIATE,null, null);
        customerThree = new Customer(3L, "Ahmed", CustomerType.NORMAL,null, null);
    }

    @Test
    public void shouldFindAllCustomers() throws Exception {
        List<Customer> list = new ArrayList<>();
        list.add(customerOne);
        list.add(customerTwo);
        list.add(customerThree);
        when(customerService.findAll()).thenReturn(list);
        this.mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        customerService.save(customerOne);
        when(customerService.findByID(1L)).thenReturn(customerOne);
        this.mockMvc.perform(get("/api/customers/{id}" , customerOne.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nawaf"))
                .andExpect(jsonPath("$.type").value(CustomerType.EMPLOYEE.name()));
    }

    @Test
    public void shouldSaveNewCustomer() throws Exception {
        CustomerRequestDto customerRequestDto =
                new CustomerRequestDto("Nawaf", CustomerType.EMPLOYEE.name());
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerRequestDto))
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

