package com.example.assignmnet1_webservices.dto;


import com.example.assignmnet1_webservices.entity.enums.orderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class orderDTO {
    private int id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private double price;
    @NotNull
    private orderStatus status;
}
