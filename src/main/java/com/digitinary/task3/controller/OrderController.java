package com.digitinary.task3.controller;


import com.digitinary.task3.dto.OrderRequestDto;
import com.digitinary.task3.dto.ResponseDto;
import com.digitinary.task3.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("ecommerce/orders/v1")
public class OrderController {

    private final OrderService orderService;


    /**
     * @param userId
     * @param paymentMethod
     * @param orderRequestDto
     * @return ResponseEntity<ResponseDto>
     * calls the createOrder method in the orderService
     * @author Majd Alkhawaja
     */
    @PostMapping("/{userId}/{paymentMethod}")
    public ResponseEntity<ResponseDto> createOrder(@PathVariable("userId") Long userId, @PathVariable("paymentMethod") String paymentMethod, @RequestBody OrderRequestDto orderRequestDto) {
        orderService.createOrder(userId, orderRequestDto, paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                        .statusCode("CREATED")
                        .statusMsg("created")
                        .build());
    }


    /**
     * @param orderId
     * @return ResponseEntity<ResponseDto>
     * calls the shipOrder method in the orderService
     * @author Majd Alkhawaja
     */
    @PostMapping("/shippedOrders/{orderId}")
    public ResponseEntity<ResponseDto> shipOrder(@PathVariable("orderId") Long orderId) {
        orderService.shipOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder()
                .statusCode("OK")
                .statusMsg("created")
                .build());
    }

    /**
     * @param isGift
     * @param hasWarranty
     * @return ResponseEntity<ResponseDto>
     * calls the getProductDescription method in the orderService
     * @author Majd Alkhawaja
     */
    @GetMapping("/products")
    public ResponseEntity<ResponseDto> getProductDescription(@RequestParam(defaultValue = "false") boolean isGift, @RequestParam(defaultValue = "false") boolean hasWarranty) {
        String description = orderService.getProductDescription(isGift, hasWarranty);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder()
                .statusCode("OK")
                .statusMsg(description)
                .build());
    }


}
