package com.msa.book.domain.model;

import com.msa.book.domain.model.vo.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    private String title;
    @Embedded
    private BookDesc desc;
    @Enumerated(EnumType.STRING)
    private Classfication classfication;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    @Enumerated(EnumType.STRING)
    private Location location;

    /** 입고 처리 **/
    public static Book enterBook(String title,
                                 String author,
                                 String isbn,
                                 String description,
                                 LocalDate publicationDate,
                                 Source source,
                                 Classfication classfication,
                                 Location location){
        BookDesc bookDesc = BookDesc.createBookDesc(description, author, isbn, publicationDate, source);
        return new Book(1L,title,bookDesc,classfication, BookStatus.ENTERED,location);
    }


    public static Book sample(){
        return enterBook("오브젝트","조영호","강일","코드로 이해하는 객체지향 설계",LocalDate.now(),Source.SUPPLY,Classfication.COMPUTER,Location.PANGYO);
    }

    public Book makeAvailable(){
        this.bookStatus = BookStatus.AVAILABLE;
        return this;
    }

    public Book makeUnAvailable(){
        this.bookStatus = BookStatus.UNAVAILABLE;
        return this;
    }
}
