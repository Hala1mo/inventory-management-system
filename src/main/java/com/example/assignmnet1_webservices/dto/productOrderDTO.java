package com.example.assignmnet1_webservices.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class productOrderDTO {

    private int order_id;
    private int product_id;
    private int quantity_ordered;
}
