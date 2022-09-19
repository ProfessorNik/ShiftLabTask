package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.entity.Monitor;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.MonitorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("monitor")
@Log4j2
public class MonitorController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @Operation(summary = "Update monitor by id",
            description = "In request not all fields may be specified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Monitor modified",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Monitor.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PutMapping("monitor")
    public ResponseEntity<?> updateMonitor(@RequestParam(name = "id") Long id, @RequestBody MonitorDto dto) {
        var product = updateProductService.updateProduct(id, dto, ProductType.MONITOR);
        log.info("Product with id: " + id + "has been updated");
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Get monitors",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of monitors"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping("monitor")
    public ResponseEntity<List<?>> getMonitors() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.MONITOR));
    }


    @Operation(summary = "Post monitor",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Monitor posted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Monitor.class))),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PostMapping("monitor")
    public ResponseEntity<?> postMonitor(@RequestBody MonitorDto dto) {
        var product = addProductService.addProduct(dto, ProductType.MONITOR);
        log.info("The product with id" + product.getId() + "has been created");
        return ResponseEntity.ok(product);
    }

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<?> invalidDataException(InvalidDataException invalidDataException) {
        log.error("Invalid data was sent: " + invalidDataException.getMessage());
        return new ResponseEntity<>(invalidDataException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<?> notFoundException(ProductNotFoundException e) {
        log.error("Product not found: " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
