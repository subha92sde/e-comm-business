package com.e.comm.business.repository;

import com.e.comm.business.model.Buyer;
import com.e.comm.business.model.BuyerWishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerWishlistRepository extends JpaRepository<BuyerWishlist, Long> {

    Page<BuyerWishlist> findAllByBuyer(Optional<Buyer> buyer, Pageable pageable);
}
