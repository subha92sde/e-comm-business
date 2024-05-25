package com.e.comm.business.service;

import com.e.comm.business.dto.request.CreateBuyerWishlistRequestBody;
import com.e.comm.business.dto.request.GetIndividualBuyerWishlistRequestBody;
import com.e.comm.business.model.Buyer;
import com.e.comm.business.model.BuyerWishlist;
import com.e.comm.business.model.Product;
import com.e.comm.business.repository.BuyerRepository;
import com.e.comm.business.repository.BuyerWishlistRepository;
import com.e.comm.business.repository.ProductRepository;
import com.e.comm.business.utility.CommonUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ResponseEntity<?> addToWishlist(CreateBuyerWishlistRequestBody requestBody) {
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
                } else
                    return new ResponseEntity<>("productId: " + requestBody.getProductId() + " --> not present", HttpStatus.INTERNAL_SERVER_ERROR);
            } else
                return new ResponseEntity<>("buyerId: " + requestBody.getBuyerId() + " --> not present", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("wishlist created for buyerId: " + buyer.get().getId() + " with wishlist id: " + buyerWishlist.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> getWishlistOfBuyerByBuyerId(GetIndividualBuyerWishlistRequestBody requestBody, int pageNumber, int pageSize) {
        Optional<Buyer> buyer;
        Page<BuyerWishlist> buyerWishlists = null;
        try {
            buyer = buyerRepository.findById(requestBody.getBuyerId());
            if (buyer.isPresent()) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                buyerWishlists = buyerWishlistRepository.findAllByBuyer(buyer, pageable);
            } else if (buyer.isEmpty())
                return new ResponseEntity<>("buyerId: " + requestBody.getBuyerId() + " --> not present", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(buyerWishlists.getContent(), HttpStatus.OK);
    }
}
