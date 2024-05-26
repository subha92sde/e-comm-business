package com.e.comm.business.service;

import com.e.comm.business.dto.request.CreateOrderRequestBody;
import com.e.comm.business.model.Buyer;
import com.e.comm.business.model.OrderDetail;
import com.e.comm.business.model.Product;
import com.e.comm.business.model.ProductStockInfo;
import com.e.comm.business.repository.BuyerRepository;
import com.e.comm.business.repository.OrderDetailRepository;
import com.e.comm.business.repository.ProductRepository;
import com.e.comm.business.repository.ProductStockInfoRepository;
import com.e.comm.business.utility.CommonUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final BuyerRepository buyerRepository;
    private final ProductRepository productRepository;
    private final ProductStockInfoRepository productStockInfoRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CommonUtility commonUtility;

    public ResponseEntity<?> createOrder(CreateOrderRequestBody requestBody) {
        OrderDetail orderDetail = new OrderDetail();
        try {
            Optional<Buyer> buyer = buyerRepository.findById(requestBody.getBuyerId());
            Optional<Product> product = productRepository.findById(requestBody.getProductId());
            if (buyer.isPresent()) {
                orderDetail.setBuyer(buyer.get());
            } else
                return new ResponseEntity<>("buyerId: " + requestBody.getBuyerId() + " --> not present", HttpStatus.INTERNAL_SERVER_ERROR);
            if (product.isPresent()) {
                orderDetail.setProduct(product.get());
            } else
                return new ResponseEntity<>("productId: " + requestBody.getProductId() + " --> not present", HttpStatus.INTERNAL_SERVER_ERROR);

            Optional<ProductStockInfo> productStockInfo;
            productStockInfo = productStockInfoRepository.findById(product.get().getId());
            if (requestBody.getUnit() != productStockInfo.get().getMaximumOrderCapacityPerBuyer()) {
                return new ResponseEntity<>("you can not order more than " + productStockInfo.get().getMaximumOrderCapacityPerBuyer() + " unit per order", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (requestBody.getUnit() <= productStockInfo.get().getInStockQuantity()) {
                orderDetail.setUnit(requestBody.getUnit());
                BigDecimal totalPrice = productStockInfo.get().getPricePerUnit().multiply(BigDecimal.valueOf(requestBody.getUnit()));
                orderDetail.setTotalPrice(totalPrice);
                orderDetail.setOrderStatus(OrderDetail.OrderStatus.sold);
                orderDetail.setPlacedAt(commonUtility.getCurrentTime());

                orderDetailRepository.save(orderDetail);
            } else {
                return new ResponseEntity<>("stock not available", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("order create successfully with order id: " + orderDetail.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> getTotalSaleOfToday() {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails = orderDetailRepository.findAll();
        BigDecimal totalSaleAmount = BigDecimal.valueOf(0.00);
        for (OrderDetail orderDetail : orderDetails) {
            String dateToMatch = new SimpleDateFormat("yyyy-MM-dd").format(orderDetail.getPlacedAt());
            if (dateToMatch.equals(today)) {
                totalSaleAmount = totalSaleAmount.add(orderDetail.getTotalPrice());
            }
        }
        return new ResponseEntity<>("total sale amount of today: " + totalSaleAmount, HttpStatus.OK);
    }
}
