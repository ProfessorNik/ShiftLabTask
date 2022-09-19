package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.DeleteProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.domain.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductController {
    private GetProductService getProductService;
    private DeleteProductService deleteProductService;

    @Operation(summary = "Get product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product schema, there may be additional fields",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping("product")
    public ResponseEntity<?> getProduct(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(getProductService.getProductBy(id));
    }

    @Operation(summary = "Delete product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been removed"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
            })
    @DeleteMapping("product")
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "id") Long id) {
        deleteProductService.delete(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
