package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("hdd")
public class HardDriveController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @GetMapping
    public ResponseEntity<List<?>> getHardDrives() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.HDD));
    }

    @PostMapping
    public ResponseEntity<?> postHardDrive(@RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.HDD));
    }

    @PutMapping
    public ResponseEntity<?> updateHardDrive(@RequestParam(name = "id") Long id, @RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.HDD));
    }
}
