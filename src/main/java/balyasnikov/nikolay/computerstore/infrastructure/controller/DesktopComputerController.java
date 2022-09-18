package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.DesktopComputerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("desktop")
public class DesktopComputerController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @GetMapping
    public ResponseEntity<List<?>> getDesktopComputers() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.DESKTOP_COMPUTER));
    }

    @PostMapping
    public ResponseEntity<?> postDesktopComputer(@RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.DESKTOP_COMPUTER));
    }

    @PutMapping
    public ResponseEntity<?> updateDesktopComputer(@RequestParam(name = "id") Long id, @RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.DESKTOP_COMPUTER));
    }
}
