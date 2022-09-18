package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("monitor")
public class MonitorController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @PutMapping("monitor")
    public ResponseEntity<?> updateMonitor(@RequestParam(name = "id") Long id, @RequestBody MonitorDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.MONITOR));
    }

    @GetMapping("monitor")
    public ResponseEntity<List<?>> getMonitors() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.MONITOR));
    }


    @PostMapping("monitor")
    public ResponseEntity<?> postMonitor(@RequestBody MonitorDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.MONITOR));
    }
}
