package com.msa.book.domain.model.vo;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class BookDesc {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private Source source;

    public static BookDesc createBookDesc(String description, String author, String isbn, LocalDate publicationDate, Source source) {
        return new BookDesc(description, author, isbn, publicationDate, source);
    }

    public static BookDesc sample(){
        return BookDesc.createBookDesc("코드로 이해하는 객체지향 설계","조영호","강일",LocalDate.now(),Source.SUPPLY);
    }

}
