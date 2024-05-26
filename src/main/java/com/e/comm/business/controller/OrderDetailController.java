package com.e.comm.business.controller;

import com.e.comm.business.dto.request.CreateBuyerWishlistRequestBody;
import com.e.comm.business.dto.request.CreateOrderRequestBody;
import com.e.comm.business.model.Buyer;
import com.e.comm.business.model.Product;
import com.e.comm.business.service.BuyerService;
import com.e.comm.business.service.BuyerWishlistService;
import com.e.comm.business.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @RequestMapping(value = "/create-order", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestBody requestBody) {
        return orderDetailService.createOrder(requestBody);
    }
}
