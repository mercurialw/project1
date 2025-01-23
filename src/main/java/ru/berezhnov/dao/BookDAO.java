package ru.berezhnov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.berezhnov.models.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        List<Book> result = jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Person.class));
        result.sort(Comparator.comparingInt(Book::getId));
        return result;
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

//    public Optional<Book> show(String name) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE fullname=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=?, borrower_id=? WHERE id=?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYear(), updatedBook.getBorrowerId(), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year, borrower_id) VALUES (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getBorrowerId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
