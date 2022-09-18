package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.service.DeleteProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {
    private GetProductService getProductService;
    private DeleteProductService deleteProductService;

    @GetMapping("product")
    public ResponseEntity<?> getProduct(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(getProductService.getProductBy(id));
    }

    @DeleteMapping("product")
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "id") Long id) {
        deleteProductService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
