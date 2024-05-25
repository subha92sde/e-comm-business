package com.e.comm.business.repository;

import com.e.comm.business.model.BuyerWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerWishlistRepository extends JpaRepository<BuyerWishlist, Long> {

}
