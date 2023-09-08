package com.epam.service.interfaces;

import java.util.List;

import com.epam.dto.BookDTO;
import com.epam.dto.BookResponseDTO;
import com.epam.exceptions.BookException;

public interface BookService {
	BookResponseDTO getById(String id) throws BookException;
	List<BookResponseDTO> getBooks();
	BookDTO addBook(BookDTO dto) throws BookException;
	BookDTO updateBook(BookDTO dto) throws BookException;
	void deleteBookById(String id) throws BookException;
}
