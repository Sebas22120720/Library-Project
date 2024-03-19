package com.example.libraryproject;

public class Libro {
    private String registrationDate;
    private String title;
    private String author;
    private String publicDate;
    private String isbn;
    private String edition;
    private String language;
    private String volumes;
    private String loanPeople;
    private String loanDate;
    private String returnedDate;
    private String status;

    public Libro(String registrationDate, String title, String author, String publicDate, String isbn, String edition, String language,
                 String volumes, String loanPeople, String loanDate, String returnedDate, String status) {
        this.registrationDate = registrationDate;
        this.title = title;
        this.author = author;
        this.publicDate = publicDate;
        this.isbn = isbn;
        this.edition = edition;
        this.language = language;
        this.volumes = volumes;
        this.loanPeople = loanPeople;
        this.loanDate = loanDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public String getLoanPeople() {
        return loanPeople;
    }

    public void setLoanPeople(String loanPeople) {
        this.loanPeople = loanPeople;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
