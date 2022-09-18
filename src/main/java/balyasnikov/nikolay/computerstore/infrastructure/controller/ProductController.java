package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.DeleteProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
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
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
