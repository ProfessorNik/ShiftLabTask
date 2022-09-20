package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.db.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private Product expectedProduct;


    @BeforeEach
    public void initDb(){
        addProductToRepository();
    }

    @Test
    public void testGetProduct() throws Exception {
        mockMvc.perform(get("/product").param("id", expectedProduct.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedProduct)));
        mockMvc.perform(get("/product").param("id", expectedProduct.getId().toString() + 1))
                .andExpect(status().is(404));
    }

    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(delete("/product").param("id", expectedProduct.getId().toString() + 1))
                .andExpect(status().is(404));
        mockMvc.perform(delete("/product").param("id", expectedProduct.getId().toString()))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/product").param("id", expectedProduct.getId().toString()))
                .andExpect(status().is(404));
    }

    public void addProductToRepository() {
        var computer = new DesktopComputer();
        computer.setCost(BigDecimal.valueOf(100));
        computer.setManufacturer("samsung");
        computer.setQuantity(4);
        computer.setSeriesNumber("1234abc");
        computer.setFormFactor("desktop");

        expectedProduct = repository.save(computer);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
}