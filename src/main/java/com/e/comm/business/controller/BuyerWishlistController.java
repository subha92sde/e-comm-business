package com.e.comm.business.controller;

import com.e.comm.business.dto.request.CreateBuyerWishlistRequestBody;
import com.e.comm.business.dto.request.GetIndividualBuyerWishlistRequestBody;
import com.e.comm.business.service.BuyerWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> addToWishlist(@RequestBody CreateBuyerWishlistRequestBody requestBody) {
        return buyerWishlistService.addToWishlist(requestBody);
    }

    @RequestMapping(value = "/get-wishlist-of-buyer", method = RequestMethod.POST)
    public ResponseEntity<?> getWishlistOfBuyerByBuyerId(@RequestBody GetIndividualBuyerWishlistRequestBody requestBody, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return buyerWishlistService.getWishlistOfBuyerByBuyerId(requestBody, pageNumber, pageSize);
    }
}
