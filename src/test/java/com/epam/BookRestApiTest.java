//package com.epam;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.epam.dto.BookDTO;
//import com.epam.dto.BookResponseDTO;
//import com.epam.entity.Book;
//import com.epam.restapi.BookRestApi;
//import com.epam.service.interfaces.BookService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebMvcTest(BookRestApi.class)
//class BookRestApiTest {
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//	private BookService bookService;
//	@InjectMocks
//	private BookRestApi bookRestApi;
//
//	@Test
//    void viewAllBooks() throws Exception {
//        List<BookResponseDTO> userList = new ArrayList<>();
//        userList.add(BookResponseDTO.builder().bookId(1).author("author").publisher("pub").name("name").build());
//        userList.add(BookResponseDTO.builder().bookId(1).author("author").publisher("pub").name("name").build());
//
//        Mockito.when(bookService.getBooks()).thenReturn(userList);
//
//        mockMvc.perform(get("/books"))
//            .andExpect(status().isAccepted());
//    }
//
//    @Test
//    void viewUserById() throws Exception {
//        BookResponseDTO dto = BookResponseDTO.builder().bookId(1).author("author").publisher("pub").name("name").build();
//
//        Mockito.when(bookService.getById(anyInt())).thenReturn(dto);
//        mockMvc.perform(get("/books/{id}", 1))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    void testAddNewBook() throws Exception {
//    	Book book = new Book(1,"name","pub", "author");
//        BookDTO dto = new BookDTO(1,"name","pub", "author");
//        Mockito.when(bookService.addBook(dto)).thenReturn(dto);
//
//        mockMvc.perform(post("/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(book)))
//            	.andExpect(status().isCreated());
//    }
//
//    @Test
//    void testDeleteBookById() throws Exception {
//        Mockito.doNothing().when(bookService).deleteBookById(anyInt());
//        mockMvc.perform(delete("/books/{id}", 1))
//            	.andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdateBookByUserName() throws Exception {
//    	Book book = new Book(1,"name","pub", "author");
//        BookDTO dto = new BookDTO(1,"name","pub", "author");
//        Mockito.when(bookService.updateBook(dto)).thenReturn(dto);
//
//        mockMvc.perform(put("/books/{id}", 1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(book)))
//            	.andExpect(status().isOk());
//    }
//
//}
