package com.e.comm.business.service;

import com.e.comm.business.dto.request.BuyerWishlistRequestBody;
import com.e.comm.business.model.Buyer;
import com.e.comm.business.model.BuyerWishlist;
import com.e.comm.business.model.Product;
import com.e.comm.business.repository.BuyerRepository;
import com.e.comm.business.repository.BuyerWishlistRepository;
import com.e.comm.business.repository.ProductRepository;
import com.e.comm.business.utility.CommonUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerWishlistService {
    private final BuyerWishlistRepository buyerWishlistRepository;
    private final BuyerRepository buyerRepository;
    private final ProductRepository productRepository;
    private final CommonUtility commonUtility;

    public ResponseEntity<?> addToWishlist(BuyerWishlistRequestBody requestBody) {
        Optional<Buyer> buyer;
        Optional<Product> product;
        BuyerWishlist buyerWishlist;
        try {
            buyer = buyerRepository.findById(requestBody.getBuyerId());
            product = productRepository.findById(requestBody.getProductId());
            if (buyer.isPresent()) {
                if (product.isPresent()) {
                    buyerWishlist = new BuyerWishlist();
                    buyerWishlist.setBuyer(buyer.get());
                    buyerWishlist.setProduct(product.get());
                    buyerWishlist.setCreatedAt(commonUtility.getCurrentTime());
                    buyerWishlistRepository.save(buyerWishlist);
                } else throw new Exception("productId: " + product.get().getId() + " --> not present");
            } else throw new Exception("buyerId: " + buyer.get().getId() + " --> not present");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("wish list created for buyerId: " + buyer.get().getId() + " with wishlist id: " + buyerWishlist.getId(), HttpStatus.OK);
    }
}
