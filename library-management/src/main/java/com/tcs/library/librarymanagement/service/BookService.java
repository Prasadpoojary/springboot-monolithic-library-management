package com.tcs.library.librarymanagement.service;

import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.enums.BookStatus;
import com.tcs.library.librarymanagement.repository.AuthorRepository;
import com.tcs.library.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addBook(Book book)
    {
        try
        {
            this.authorRepository.save(book.getAuthor());
            return this.bookRepository.save(book);
        }
        catch (Exception e)
        {
            System.out.println("Unable to add Book : "+e.getMessage());
            return null;
        }
    }


    public Book getBook(Long id)
    {
        try
        {
            return this.bookRepository.findById(id).get();
        }
        catch (Exception e)
        {
            System.out.println("Unable to fetch Book : "+e.getMessage());
            return null;
        }
    }

    public List<Book> getAllBooks()
    {
            return this.bookRepository.findAll();
    }

    public Boolean deleteBook(Long id)
    {
        try
        {
             this.bookRepository.deleteById(id);
             return true;
        }
        catch (Exception e)
        {
            System.out.println("Unable to Delete book : "+e.getMessage());
            return false;
        }
    }

    public Book updateBook(Long id,Book book)
    {
        try
        {
            if(!this.bookRepository.existsById(id))
            {
                return null;
            }
            return this.bookRepository.save(book);
        }
        catch (Exception e)
        {
            System.out.println("Unable to update Book : "+e.getMessage());
            return null;
        }
    }


    public Book updateStatus(Long id)
    {
        try
        {
            if(!this.bookRepository.existsById(id))
            {
                return null;
            }

            Book book=this.bookRepository.findById(id).get();
            BookStatus newStatus=book.getStatus()== BookStatus.AVAILABLE ? BookStatus.BORROWED : BookStatus.AVAILABLE;

            book.setStatus(newStatus);
            return this.bookRepository.save(book);
        }
        catch (Exception e)
        {
            System.out.println("Unable to update the status of the Book : "+e.getMessage());
            return null;
        }
    }


}
