package com.library.abc.titan.controller;

import com.library.abc.titan.exception.AppException;
import com.library.abc.titan.model.Library;
import com.library.abc.titan.payload.ApiResponse;
import com.library.abc.titan.payload.LibraryRequest;
import com.library.abc.titan.repository.LibraryRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@OpenAPIDefinition(info = @Info(
        title = "Library Gateway Service",
        description = "Library Gateway Service",
        version = "1.0.0"))
@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping("/allBooks")
    public List<Library> getAllBooks() {
        return libraryRepository.findAll();
    }

    @PostMapping("/addBook")
    public ResponseEntity createProduct(@RequestBody LibraryRequest request) {
        Library library = new Library();
        library.setBookName(request.getBookName());
        library.setBookDescription(request.getBookDescription());
        library.setPrice(request.getPrice());
        library.setBookGenre(request.getBookGenre());
        Library result = libraryRepository.save(library);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true,
                        "New Book successfully added into library."));
    }

    @GetMapping("/book/{id}")
    public Library getBook(@PathVariable Long id) {
        Optional<Library> library = libraryRepository.findById(id);
        if (library.isEmpty()) {
            throw new AppException("Book not found with ID: " + id);
        }
        return library.get();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity updateBookInfo(@RequestBody LibraryRequest request,
                                         @PathVariable Long id) {
        Optional<Library> productOptional = libraryRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new AppException("Book not found with ID: " + id);
        }
        Library library = productOptional.get();
        library.setBookName(request.getBookName());
        library.setBookDescription(request.getBookDescription());
        library.setPrice(request.getPrice());
        library.setBookGenre(request.getBookGenre());
        libraryRepository.save(library);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,
                        "Book details successfully updated."));
    }
}
