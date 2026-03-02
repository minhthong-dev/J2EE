package com.example.baitap_tuan2.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.baitap_tuan2.Service.BookService;
import com.example.baitap_tuan2.models.Book;

import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/add-book")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book("", "", "", 0, ""));
        return "addbook";
    }

    @PostMapping("/update-book")
    public String processUpdate(@ModelAttribute Book book) {
        bookService.addBookupdateBook(book);
        return "redirect:/api/books/";
    }

    @GetMapping("/update-book/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {

        Book book = bookService.getBookById(id);

        if (book == null) {
            return "redirect:/api/books/";
        }
        model.addAttribute("book", book);
        return "addbook";
    }

    @GetMapping("/{id}")
    public String getBookByIdString(@PathVariable("id") String id) {
        return bookService.getBookById(id).toString();
    }

    @PostMapping("/addupdate")
    public String addOrUpdateBook(@Valid @ModelAttribute("book") Book book, BindingResult result,
            @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            return "addbook";
        }
        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();

                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, imageFile.getBytes());
                book.setUrl("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bookService.addBookupdateBook(book);
        return "redirect:/api/books/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return "redirect:/api/books/";
    }

}
