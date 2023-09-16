package com.tcs.library.librarymanagement.service;

import com.tcs.library.librarymanagement.Projections.ProjectUser;
import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.model.BorrowingRecord;
import com.tcs.library.librarymanagement.repository.AuthorRepository;
import com.tcs.library.librarymanagement.repository.BookRepository;
import com.tcs.library.librarymanagement.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordService
{

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BorrowingRecord addBorrowedRecord(BorrowingRecord borrowedRecord)
    {
        try
        {
            this.authorRepository.save(borrowedRecord.getBook().getAuthor());
            this.bookRepository.save(borrowedRecord.getBook());
            borrowedRecord.setBorrowingDate(LocalDate.now());
            return this.borrowingRecordRepository.save(borrowedRecord);
        }
        catch (Exception e)
        {
            System.out.println("Unable to add borrowed record : "+e.getMessage());
            return null;
        }
    }

    public BorrowingRecord updatedReturnedRecord(Long id)
    {
        try
        {
            if(!this.borrowingRecordRepository.existsById(id))
            {
                return null;
            }
            BorrowingRecord borrowedRecord=this.borrowingRecordRepository.findById(id).get();
            borrowedRecord.setReturnDate(LocalDate.now());
            return this.borrowingRecordRepository.save(borrowedRecord);
        }
        catch (Exception e)
        {
            System.out.println("Unable to updated returned record : "+e.getMessage());
            return null;
        }
    }

    public List<ProjectUser> getBorrowedUsers(String user)
    {
        try
        {
            return this.borrowingRecordRepository.findBookByUser(user);
        }
        catch (Exception e)
        {
            System.out.println("Unable to updated returned record : "+e.getMessage());
            return null;
        }
    }


}
