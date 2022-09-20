package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.db.repository.ProductRepository;
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
public class HardDriveControllerTest {
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
    public void testGetHardDrives() throws Exception {
        mockMvc.perform(get("/hdd"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of((expectedProduct)))));
    }

    @Test
    public void testPostProduct() throws Exception{
        var hardDriveDto = new HardDriveDto();
        hardDriveDto.setCost(BigDecimal.valueOf(100));
        hardDriveDto.setManufacturer("samsung");
        hardDriveDto.setQuantity(4);
        hardDriveDto.setSeriesNumber("1234abc");
        hardDriveDto.setCapacity(256);

        var errHardDriveDto = new HardDriveDto();
        errHardDriveDto.setCost(BigDecimal.valueOf(100));
        errHardDriveDto.setManufacturer("samsung");
        errHardDriveDto.setQuantity(4);
        errHardDriveDto.setSeriesNumber("1234abc");
        errHardDriveDto.setCapacity(0);

        var hardDrive = new HardDrive();
        hardDrive.setId(expectedProduct.getId() + 1);
        hardDrive.setCost(BigDecimal.valueOf(100));
        hardDrive.setManufacturer("samsung");
        hardDrive.setQuantity(4);
        hardDrive.setSeriesNumber("1234abc");
        hardDrive.setCapacity(256);

        mockMvc.perform(post("/hdd")
                        .content(mapper.writeValueAsString(hardDriveDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(hardDrive)));

        mockMvc.perform(post("/hdd")
                        .content(mapper.writeValueAsString(errHardDriveDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    public void updateProduct() throws Exception {
        var laptopDto = new HardDriveDto();
        laptopDto.setSeriesNumber("h234p");

        var errLaptopDto = new HardDriveDto();
        errLaptopDto.setCost(BigDecimal.valueOf(-1.25));

        expectedProduct.setSeriesNumber("h234p");

        mockMvc.perform(put("/hdd").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(expectedProduct)));
        mockMvc.perform(put("/hdd").param("id", expectedProduct.getId().toString() + 2)
                        .content(mapper.writeValueAsString(laptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        mockMvc.perform(put("/hdd").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(errLaptopDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    public void addProductToRepository() {
        var laptop = new HardDrive();
        laptop.setCost(BigDecimal.valueOf(100.00));
        laptop.setManufacturer("samsung");
        laptop.setQuantity(4);
        laptop.setSeriesNumber("1234abc");
        laptop.setCapacity(256);

        expectedProduct = repository.save(laptop);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
}