package com.example.demo;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {
     
	
	private int orderId;
	@NotBlank(message = "Customer name is required")
	private String customerName;
	@NotBlank(message = "Food items cannot be empty")
	private String foodItems;
	@Min(value =1,message = "Quantity must be atleast 1")
	private int quantity;
	@NotNull(message =" Price is Mandatory")
	private Double price;
}
