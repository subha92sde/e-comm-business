package com.e.comm.business.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BuyerWishlistRequestBody {
    private Long buyerId;
    private Long productId;
}
