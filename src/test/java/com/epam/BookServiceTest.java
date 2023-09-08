package com.epam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.BookDTO;
import com.epam.dto.BookResponseDTO;
import com.epam.entity.Book;
import com.epam.exceptions.BookException;
import com.epam.repository.BookRepository;
import com.epam.service.implementation.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	@Mock(lenient = true)
	private BookRepository bookRepository;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private BookServiceImpl serviceImpl;
	

	@Test
	void createBookSuccess() throws BookException {
		Book book = new Book(1,"name","pub", "author");
        BookDTO dto = new BookDTO(1,"name","pub", "author");
		Mockito.when(modelMapper.map(dto, Book.class)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		Mockito.when(modelMapper.map(book, BookDTO.class)).thenReturn(dto);
		BookDTO act = serviceImpl.addBook(dto);
	    assertNotNull(act);
	}
	
	@Test
	void createUserFail() throws BookException {
		Book book = new Book(1,"name","pub", "author");
        BookDTO dto = new BookDTO(1,"name","pub", "author");
		Mockito.when(modelMapper.map(dto, Book.class)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(null);
		assertThrows(BookException.class, () -> {
			serviceImpl.addBook(dto);
		});
	}
	
	@Test
	void viewBooks() {
		List<Book> expected = new ArrayList<>();
		expected.add(new Book());
		expected.add(new Book());
		Mockito.when(bookRepository.findAll()).thenReturn(expected);
		List<BookResponseDTO> actual = serviceImpl.getBooks();
		assertEquals(expected.size(), actual.size());
	}
	
	@Test
	void viewBookByIdSuccess() throws BookException {
		Optional<Book> book = Optional.of(new Book(1,"bookname","publisher", "author"));
		BookDTO dto = new BookDTO(1,"name","pub", "author");
		Mockito.when(bookRepository.findById(anyInt())).thenReturn(book);
		BookResponseDTO act = serviceImpl.getById(anyInt());
		assertEquals(dto.getAuthor(), act.getAuthor());
	}

	@Test
	void viewBookByIdFail() throws BookException {
		Mockito.when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
		BookResponseDTO act = serviceImpl.getById(anyInt());
		assertNotNull(act.getDeveloperMessage());
	}

	
	@Test
	void updateBookSuccess() throws BookException {
		Book book = new Book("name","pub", "author");
		BookDTO dto = new BookDTO(1,"name","pub", "author");
        
		Mockito.when(modelMapper.map(dto, Book.class)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		BookDTO act = serviceImpl.updateBook(dto);
	    assertNotNull(act);
	}
	
	@Test
	void updateUserFail() throws BookException {
		Book book = new Book("name","pub", "author");
		BookDTO dto = new BookDTO(1,"name","pub", "author");
        
		Mockito.when(modelMapper.map(dto, Book.class)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(null);
		assertThrows(BookException.class, () -> {
			serviceImpl.updateBook(dto);
		});
	}

	@Test
	void deleteBookSuccess() throws BookException {
		Mockito.when(bookRepository.existsById(anyInt())).thenReturn(true);
		Mockito.doNothing().when(bookRepository).deleteById(anyInt());
		serviceImpl.deleteBookById(anyInt());
		Mockito.verify(bookRepository, times(1)).deleteById(anyInt());
	}
}
