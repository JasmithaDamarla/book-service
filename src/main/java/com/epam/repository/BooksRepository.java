//package com.epam.repository;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
//import com.epam.entity.Book;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class BooksRepository {
//
//    @Autowired
//    private DynamoDBMapper dynamoDBMapper;
//
//    public Book findById(String id){
//        return dynamoDBMapper.load(Book.class, id);
//    }
//
//    public List<Book> findAll(){
//        return dynamoDBMapper.scan(Book.class, new DynamoDBScanExpression());
//    }
//
//
//    public Book save(Book book) {
//        dynamoDBMapper.save(book);
//        return book;
//    }
//
//    public void deleteById(String id) {
//        Book book = dynamoDBMapper.load(Book.class, id);
//        dynamoDBMapper.delete(book);
//    }
//}
