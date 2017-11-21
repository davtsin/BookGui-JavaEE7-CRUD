package com.bookgui.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author davtsin
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b")
})
public class Book {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 1, max = 500, message = "title must be in range {min}-{max} symbols")
    private String title;
    @NotNull
    @Digits(integer = 6, fraction = 2, message = "price must have the same format: '{integer}'.'{fraction}' digits")
    private Float price;
    @Size(min = 10, max = 2000, message = "description must be in range {min}-{max} symbols")
    private String description;
    private String isbn;
    @Max(value = 100000, message = "number of pages should not be over {value}")
    @NotNull
    private Integer nbOfPage;
    private Boolean illustrations;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Book() {
    }

    public Book(String title, String description, Float price,
        String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append('}');
        return sb.toString();
    }
}
