package com.e.comm.business.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestBody {
    private Long buyerId;
    private Long productId;
    private int unit;
}
