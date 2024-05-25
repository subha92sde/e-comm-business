package com.e.comm.business.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBuyerWishlistRequestBody {
    private Long buyerId;
    private Long productId;
}
