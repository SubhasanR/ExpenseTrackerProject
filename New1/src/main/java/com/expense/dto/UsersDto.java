package com.expense.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data

public class UsersDto {

	private BigDecimal amount;
	private String date;
	private String title;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public UsersDto(BigDecimal amount, String date, String title) {
		super();
		this.amount = amount;
		this.date = date;
		this.title = title;
	}
	public UsersDto() {
		super();
	}

	
}
