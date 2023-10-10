package com.questSpringRestful.questBook.controller;

import com.questSpringRestful.questBook.entity.Book;
import com.questSpringRestful.questBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository repo;
    //get all books
    //localhost:8080/books
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        List<Book> books = repo.findAll();
        return books;
    }

    // get a specific book with an ID
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id){
        Book book = repo.findById(id).get();
        return book;
    }

    // create a new book
    @PostMapping("/book/add")
    public void createBook(@RequestBody Book book){
        repo.save(book);
    }

    //to delete a book in the list
    @DeleteMapping("books/{id}")
    public boolean deleteBook(@PathVariable int id){
        repo.deleteById(id);
        return true;
    }

    // to update a book with id
    @PutMapping("/book/update/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book){
        //getting books
        Book bookToUpdate = repo.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repo.save(bookToUpdate);
    }

    //to search a specific books with a text contained in title or description
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return repo.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
}
