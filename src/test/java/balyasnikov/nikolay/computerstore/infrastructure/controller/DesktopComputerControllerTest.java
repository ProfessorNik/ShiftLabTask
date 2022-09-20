package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.db.repository.ProductRepository;
import balyasnikov.nikolay.computerstore.infrastructure.dto.DesktopComputerDto;
import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class DesktopComputerControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private Product expectedProduct;

    @BeforeEach
    public void initDB() {
        addProductToRepository();
    }

    @Test
    public void testGetDesktopComputers() throws Exception {
        mockMvc.perform(get("/desktop"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of((expectedProduct)))));
    }

    @Test
    public void testPostProduct() throws Exception {
        var desktopComputerDto = new DesktopComputerDto();
        desktopComputerDto.setCost(BigDecimal.valueOf(100));
        desktopComputerDto.setManufacturer("samsung");
        desktopComputerDto.setQuantity(4);
        desktopComputerDto.setSeriesNumber("1234abc");
        desktopComputerDto.setFormFactor("monoblock");

        var errDesktopComputerDto = new DesktopComputerDto();
        errDesktopComputerDto.setCost(BigDecimal.valueOf(100));
        errDesktopComputerDto.setManufacturer("samsung");
        errDesktopComputerDto.setQuantity(4);
        errDesktopComputerDto.setSeriesNumber("1234abc");
        errDesktopComputerDto.setFormFactor("floor");

        var desktopComputer = new DesktopComputer();
        desktopComputer.setId(expectedProduct.getId() + 1);
        desktopComputer.setCost(BigDecimal.valueOf(100));
        desktopComputer.setManufacturer("samsung");
        desktopComputer.setQuantity(4);
        desktopComputer.setSeriesNumber("1234abc");
        desktopComputer.setFormFactor("monoblock");

        mockMvc.perform(post("/desktop")
                        .content(mapper.writeValueAsString(desktopComputerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(desktopComputer)));

        mockMvc.perform(post("/desktop")
                        .content(mapper.writeValueAsString(errDesktopComputerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    public void updateProduct() throws Exception {
        var laptopDto = new HardDriveDto();
        laptopDto.setSeriesNumber("h2345");

        var errLaptopDto = new HardDriveDto();
        errLaptopDto.setQuantity(-1);

        expectedProduct.setSeriesNumber("h2345");

        mockMvc.perform(put("/desktop").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(expectedProduct)));
        mockMvc.perform(put("/desktop").param("id", expectedProduct.getId().toString() + 2)
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        mockMvc.perform(put("/desktop").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(errLaptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    public void addProductToRepository() {
        var laptop = new DesktopComputer();
        laptop.setCost(BigDecimal.valueOf(100.00));
        laptop.setManufacturer("samsung");
        laptop.setQuantity(4);
        laptop.setSeriesNumber("1234abc");
        laptop.setFormFactor("monoblock");

        expectedProduct = repository.save(laptop);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
}