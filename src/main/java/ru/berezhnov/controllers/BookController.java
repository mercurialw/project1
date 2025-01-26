package ru.berezhnov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.berezhnov.dao.BookDAO;
import ru.berezhnov.dao.PersonDAO;
import ru.berezhnov.models.Book;
import ru.berezhnov.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String booksPage(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> owner =  bookDAO.getPersonByBookId(id);
        if(owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
        return "books/show";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(@PathVariable("id") int bookId, @ModelAttribute("person") Person person) {
        bookDAO.giveBookToPerson(bookId, person);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@PathVariable("id") int bookId) {
        bookDAO.takeBookFromPerson(bookId);
        return "redirect:/books/" + bookId;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
