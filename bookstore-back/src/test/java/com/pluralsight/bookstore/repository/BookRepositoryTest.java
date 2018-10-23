package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    private static Long bookId;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(BookRepository.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

    @Inject
    private BookRepository bookRepository;


    @Test(expected = Exception.class)
    public void findWithInvalidId() {
        bookRepository.find(null);
    }

    @Test(expected = Exception.class)
    public void createInvalidBook() {
        Book book = new Book("isbn", null, 12F, 123, Language.ENGLISH, new Date(), "http://fwfwef", "Lorem ipsum lorem");
        book = bookRepository.create(book);
    }

    @Test
    public void create() {
        // Count all
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        Book book = new Book("isbn", "a  title", 12F, 123, Language.ENGLISH, new Date(), "http://fwfwef", "Lorem ipsum lorem");
        book = bookRepository.create(book);
        Long bookId = book.getId();

        // Check created book
        assertNotNull(bookId);

        // Find created book
        Book bookFound = bookRepository.find(bookId);

        // Check the found book
        assertEquals("a title", bookFound.getTitle());
        assertTrue(bookFound.getIsbn().startsWith("13"));

        // Count all
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        // Delete the book
        bookRepository.delete(bookId);

        // Count all
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

    }

}
