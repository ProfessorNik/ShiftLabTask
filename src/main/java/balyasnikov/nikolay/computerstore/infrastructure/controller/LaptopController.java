package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.entity.Laptop;
import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.LaptopDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get laptops",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of laptops"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping
    public ResponseEntity<List<?>> getLaptops() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.LAPTOP));
    }

    @Operation(summary = "Post laptop",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Laptop posted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Monitor.class))),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PostMapping
    public ResponseEntity<?> postLaptop(@RequestBody LaptopDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.LAPTOP));
    }

    @Operation(summary = "Update laptop id",
            description = "In request not all fields may be specified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Laptop modified",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Laptop.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PutMapping
    public ResponseEntity<?> updateLaptop(@RequestParam(name = "id") Long id, @RequestBody LaptopDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.LAPTOP));
    }

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<?> invalidDataException(InvalidDataException invalidDataException) {
        return new ResponseEntity<>(invalidDataException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
