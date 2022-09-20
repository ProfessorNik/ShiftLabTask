package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import balyasnikov.nikolay.computerstore.infrastructure.db.repository.ProductRepository;
import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
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
public class MonitorControllerTest {
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
    public void testGetMonitors() throws Exception {
        mockMvc.perform(get("/monitor"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of((Monitor)expectedProduct))));

    }

    @Test
    public void testPostProduct() throws Exception{
        var monitorDto = new MonitorDto();
        monitorDto.setCost(BigDecimal.valueOf(100));
        monitorDto.setManufacturer("samsung");
        monitorDto.setQuantity(4);
        monitorDto.setSeriesNumber("1234abc");
        monitorDto.setDiagonal(13.0);

        var errMonitorDto = new MonitorDto();
        errMonitorDto.setCost(BigDecimal.valueOf(100));
        errMonitorDto.setManufacturer("samsung");
        errMonitorDto.setQuantity(4);
        errMonitorDto.setSeriesNumber("1234abc");
        errMonitorDto.setDiagonal(-13.0);

        var monitor = new Monitor();
        monitor.setId(expectedProduct.getId() + 1);
        monitor.setCost(BigDecimal.valueOf(100));
        monitor.setManufacturer("samsung");
        monitor.setQuantity(4);
        monitor.setSeriesNumber("1234abc");
        monitor.setDiagonal(13.0);

        mockMvc.perform(post("/monitor")
                        .content(mapper.writeValueAsString(monitorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(monitor)));

        mockMvc.perform(post("/monitor")
                        .content(mapper.writeValueAsString(errMonitorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    public void updateProduct() throws Exception {
        var monitorDto = new MonitorDto();
        monitorDto.setManufacturer("hp");

        var errMonitorDto = new MonitorDto();
        errMonitorDto.setQuantity(-1);

        expectedProduct.setManufacturer("hp");

        mockMvc.perform(put("/monitor").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(monitorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mapper.writeValueAsString(expectedProduct)));
        mockMvc.perform(put("/monitor").param("id", expectedProduct.getId().toString() + 2)
                        .content(mapper.writeValueAsString(monitorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
        mockMvc.perform(put("/monitor").param("id", expectedProduct.getId().toString())
                        .content(mapper.writeValueAsString(errMonitorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    public void addProductToRepository() {
        var monitor = new Monitor();
        monitor.setCost(BigDecimal.valueOf(100.00));
        monitor.setManufacturer("samsung");
        monitor.setQuantity(4);
        monitor.setSeriesNumber("1234abc");
        monitor.setDiagonal(13.0);

        expectedProduct = repository.save(monitor);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }
}
