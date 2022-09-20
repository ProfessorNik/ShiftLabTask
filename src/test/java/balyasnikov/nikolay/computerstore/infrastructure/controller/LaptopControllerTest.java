package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.domain.entity.Laptop;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.db.repository.ProductRepository;
import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LaptopControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private Product expectedProduct;

    @BeforeEach
    public void initDB(){
        addProductToRepository();
    }

    @Test
    public void testGetLaptops() throws Exception {
        mockMvc.perform(get("/laptop"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of((expectedProduct)))));
    }

    @Test
    public void testPostProduct() throws Exception{
        var laptopDto = new LaptopDto();
        laptopDto.setCost(BigDecimal.valueOf(100));
        laptopDto.setManufacturer("samsung");
        laptopDto.setQuantity(4);
        laptopDto.setSeriesNumber("1234abc");
        laptopDto.setSize(13);

        var errLaptopDto = new LaptopDto();
        errLaptopDto.setCost(BigDecimal.valueOf(100));
        errLaptopDto.setManufacturer("samsung");
        errLaptopDto.setQuantity(4);
        errLaptopDto.setSeriesNumber("1234abc");
        errLaptopDto.setSize(8);

        var laptop = new Laptop();
        laptop.setId(expectedProduct.getId() + 1);
        laptop.setCost(BigDecimal.valueOf(100));
        laptop.setManufacturer("samsung");
        laptop.setQuantity(4);
        laptop.setSeriesNumber("1234abc");
        laptop.setSize(13);

        mockMvc.perform(post("/laptop")
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(laptop)));

        mockMvc.perform(post("/laptop")
                        .content(mapper.writeValueAsString(errLaptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    public void updateProduct() throws Exception {
        var laptopDto = new LaptopDto();
        laptopDto.setSeriesNumber("h234p");

        var errLaptopDto = new LaptopDto();
        errLaptopDto.setCost(BigDecimal.valueOf(-100));

        expectedProduct.setSeriesNumber("h234p");

        mockMvc.perform(put("/laptop").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(expectedProduct)));
        mockMvc.perform(put("/laptop").param("id", expectedProduct.getId().toString() + 2)
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        mockMvc.perform(put("/laptop").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(errLaptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    public void addProductToRepository() {
        var laptop = new Laptop();
        laptop.setCost(BigDecimal.valueOf(100.00));
        laptop.setManufacturer("samsung");
        laptop.setQuantity(4);
        laptop.setSeriesNumber("1234abc");
        laptop.setSize(13);

        expectedProduct = repository.save(laptop);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
}