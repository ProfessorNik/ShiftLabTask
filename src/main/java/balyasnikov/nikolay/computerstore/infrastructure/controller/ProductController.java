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
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("product")
@Log4j2
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
    @GetMapping
    public ResponseEntity<?> getProduct(@RequestParam(name = "id") Long id) {
        var response = getProductService.getProductBy(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been removed"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
            })
    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "id") Long id) {
        deleteProductService.delete(id);
        log.info("Product with id: " + id + "has been deleted");
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e) {
        log.error("Product not found: " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
