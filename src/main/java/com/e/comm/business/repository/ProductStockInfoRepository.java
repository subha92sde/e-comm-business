package com.e.comm.business.repository;

import com.e.comm.business.model.ProductStockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockInfoRepository extends JpaRepository<ProductStockInfo, Long> {

}
