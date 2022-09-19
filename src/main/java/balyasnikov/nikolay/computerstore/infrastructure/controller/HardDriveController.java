package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.entity.HardDrive;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.HardDriveDto;
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
@RequestMapping("hdd")
@Log4j2
public class HardDriveController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @Operation(summary = "Get hard drives",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of hard drives"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping
    public ResponseEntity<List<?>> getHardDrives() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.HDD));
    }

    @Operation(summary = "Post hard drive",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Hard drive posted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HardDrive.class))),
                    @ApiResponse(responseCode = "422", description = "Not all data for product creation was transferred")
            })
    @PostMapping
    public ResponseEntity<?> postHardDrive(@RequestBody HardDriveDto dto) {
        var product = addProductService.addProduct(dto, ProductType.HDD);
        log.info("The product with id" + product.getId() + "has been created");
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Update hard drive by id",
            description = "In request not all fields may be specified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Hard drive modified",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = HardDrive.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PutMapping
    public ResponseEntity<?> updateHardDrive(@RequestParam(name = "id") Long id, @RequestBody HardDriveDto dto) {
        var product = updateProductService.updateProduct(id, dto, ProductType.HDD);
        log.info("Product with id: " + id + "has been updated");
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
