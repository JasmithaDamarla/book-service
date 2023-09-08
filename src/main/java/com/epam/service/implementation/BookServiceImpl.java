package com.epam.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDTO;
import com.epam.dto.BookResponseDTO;
import com.epam.entity.Book;
import com.epam.exceptions.BookException;
import com.epam.repository.BookRepository;
import com.epam.service.interfaces.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookResponseDTO getById(String id)  {
		
//		return Optional.ofNullable(bookRepository
//						.findById(id))
		return bookRepository
						.findById(Integer.valueOf(id))
						.map(book -> {
							log.info("retrieving book of id {}",id);
							return BookResponseDTO
								.builder()
								.bookId(book.getBookId())
								.author(book.getAuthor())
								.publisher(book.getPublisher())
								.name(book.getName())
								.build();
							
						}).orElseGet(() -> { 
							log.info("book of id {} not found",id);
							return BookResponseDTO.builder().developerMessage("No Book found with Id: "+ id).build();
						});
	}

	@Override
	public List<BookResponseDTO> getBooks() {
		log.info("retrieving all the Books list");
		return bookRepository.findAll().stream().map(book -> 
			 BookResponseDTO.builder()
					.bookId(book.getBookId())
					.author(book.getAuthor())
					.publisher(book.getPublisher())
					.name(book.getName())
					.build()
			).toList();
	}

	@Override
	public BookDTO addBook(BookDTO dto) throws BookException {
		log.info("book getting created : {}", dto);
		Book book = Optional.ofNullable(bookRepository.save(modelMapper.map(dto, Book.class)))
				.orElseThrow(() -> new BookException("Failed to add new book"));
		return modelMapper.map(book, BookDTO.class);
	}

	@Override
	public BookDTO updateBook(BookDTO bookDto) throws BookException {
		log.info("book getting updated : {}",bookDto);
		return Optional.ofNullable(bookRepository.save(modelMapper.map(bookDto, Book.class))).map(book -> bookDto)
				.orElseThrow(() -> new BookException("Failed to update book"));
	}

	@Override
	public void deleteBookById(String id) {// throws BookException {
//		if (bookRepository.existsById(id)) {
			log.info("book of id = {} getting deleted", id);
			bookRepository.deleteById(Integer.valueOf(id));
//		} else {
//			throw new BookException("Not able to find the book to delete");
//		}
	}

}
