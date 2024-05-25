package com.e.comm.business.controller;

import com.e.comm.business.dto.request.BuyerWishlistRequestBody;
import com.e.comm.business.service.BuyerWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BuyerWishlistController {
    private final BuyerWishlistService buyerWishlistService;

    @RequestMapping(value = "/buyer/add-to-wishlist", method = RequestMethod.POST)
    public ResponseEntity<?> addToWishlist(@RequestBody BuyerWishlistRequestBody requestBody) {
        return buyerWishlistService.addToWishlist(requestBody);
    }
}
