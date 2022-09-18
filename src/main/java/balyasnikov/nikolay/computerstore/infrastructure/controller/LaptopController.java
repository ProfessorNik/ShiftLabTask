package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("laptop")
public class LaptopController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @GetMapping
    public ResponseEntity<List<?>> getLaptops() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.LAPTOP));
    }

    @PostMapping
    public ResponseEntity<?> postLaptop(@RequestBody LaptopDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.LAPTOP));
    }

    @PutMapping
    public ResponseEntity<?> updateLaptop(@RequestParam(name = "id") Long id, @RequestBody LaptopDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.LAPTOP));
    }

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<?> invalidDataException(InvalidDataException invalidDataException){
        return new ResponseEntity<>(invalidDataException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
