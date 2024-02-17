package com.library.abc.titan.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryRequest {
    @JsonProperty("bookName")
    private String bookName;

    @JsonProperty("bookDescription")
    private String bookDescription;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("bookGenre")
    private String bookGenre;

}
