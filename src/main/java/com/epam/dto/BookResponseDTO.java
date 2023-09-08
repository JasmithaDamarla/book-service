package com.epam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(value = Include.NON_NULL)
public class BookResponseDTO {
	private Integer bookId;
	@Size(min = 3, max = 40, message = "name of book must be of size min of 3 and max of 40")
	private String name;
	@Size(min = 3, max = 30, message = "publisher name of book must be of size min of 3 and max of 30")
	private String publisher;
	@Size(min = 3, max = 30, message = "author name of book must be of size min of 3 and max of 30")
	private String author;
	private String developerMessage;
}