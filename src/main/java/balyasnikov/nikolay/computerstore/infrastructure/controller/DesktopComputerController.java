package balyasnikov.nikolay.computerstore.infrastructure.controller;

import balyasnikov.nikolay.computerstore.application.exception.InvalidDataException;
import balyasnikov.nikolay.computerstore.application.exception.ProductNotFoundException;
import balyasnikov.nikolay.computerstore.application.service.AddProductService;
import balyasnikov.nikolay.computerstore.application.service.GetProductService;
import balyasnikov.nikolay.computerstore.application.service.UpdateProductService;
import balyasnikov.nikolay.computerstore.domain.entity.DesktopComputer;
import balyasnikov.nikolay.computerstore.domain.value.ProductType;
import balyasnikov.nikolay.computerstore.infrastructure.dto.DesktopComputerDto;
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
@RequestMapping("desktop")
public class DesktopComputerController {
    private AddProductService addProductService;
    private GetProductService getProductService;
    private UpdateProductService updateProductService;

    @Operation(summary = "Get desktop computers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of desktop computers"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping
    public ResponseEntity<List<?>> getDesktopComputers() {
        return ResponseEntity.ok(getProductService.getProducts(ProductType.DESKTOP_COMPUTER));
    }

    @Operation(summary = "Post desktop computer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Desktop computer posted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DesktopComputer.class))),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PostMapping
    public ResponseEntity<?> postDesktopComputer(@RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(addProductService.addProduct(dto, ProductType.DESKTOP_COMPUTER));
    }

    @Operation(summary = "Update desktop computer by id",
            description = "In request not all fields may be specified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Desktop computer modified",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DesktopComputer.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "422", description = "Incorrect data")
            })
    @PutMapping
    public ResponseEntity<?> updateDesktopComputer(@RequestParam(name = "id") Long id, @RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(updateProductService.updateProduct(id, dto, ProductType.DESKTOP_COMPUTER));
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
