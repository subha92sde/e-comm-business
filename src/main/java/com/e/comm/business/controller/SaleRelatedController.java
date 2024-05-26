package com.e.comm.business.controller;

import com.e.comm.business.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaleRelatedController {
    private final OrderDetailService orderDetailService;

    @RequestMapping(value = "/total-sale-of-today", method = RequestMethod.GET)
    public ResponseEntity<?> getTotalSaleOfToday() {
        return orderDetailService.getTotalSaleOfToday();
    }
}
