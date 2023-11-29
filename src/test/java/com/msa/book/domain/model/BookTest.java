package com.msa.book.domain.model;

import com.msa.book.domain.model.vo.BookStatus;
import com.msa.book.domain.model.vo.Classfication;
import com.msa.book.domain.model.vo.Location;
import com.msa.book.domain.model.vo.Source;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class BookTest {
    @Test
    void 도서_입고(){
        String 오브젝트 = "오브젝트";
        String 조영호 = "조영호";
        String 강일 = "강일";
        String 코드로_이해하는_객체지향_설계 = "코드로 이해하는 객체지향 설계";
        LocalDate publicationDate = LocalDate.now();
        Source supply = Source.SUPPLY;
        Classfication computer = Classfication.COMPUTER;
        Location pangyo = Location.PANGYO;
        Book book = Book.enterBook(
                오브젝트,
                조영호,
                강일,
                코드로_이해하는_객체지향_설계,
                publicationDate,
                supply,
                computer,
                pangyo);

        assertThat(book.getTitle()).isEqualTo(오브젝트);
        assertThat(book.getDesc().getAuthor()).isEqualTo(조영호);
        assertThat(book.getDesc().getSource()).isEqualTo(supply);
        assertThat(book.getDesc().getPublicationDate()).isEqualTo(publicationDate);
        assertThat(book.getDesc().getIsbn()).isEqualTo(강일);
        assertThat(book.getLocation()).isEqualTo(pangyo);
        assertThat(book.getClassfication()).isEqualTo(computer);
        assertThat(book.getBookStatus()).isEqualTo(BookStatus.ENTERED);

    }

    @Test
    void 도서_이용_가능_처리(){
        String 오브젝트 = "오브젝트";
        String 조영호 = "조영호";
        String 강일 = "강일";
        String 코드로_이해하는_객체지향_설계 = "코드로 이해하는 객체지향 설계";
        LocalDate publicationDate = LocalDate.now();
        Source supply = Source.SUPPLY;
        Classfication computer = Classfication.COMPUTER;
        Location pangyo = Location.PANGYO;
        Book book = Book.enterBook(
                오브젝트,
                조영호,
                강일,
                코드로_이해하는_객체지향_설계,
                publicationDate,
                supply,
                computer,
                pangyo);


        book.makeAvailable();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.AVAILABLE);

    }

    @Test
    void 도서_이용_불가능_처리(){
        String 오브젝트 = "오브젝트";
        String 조영호 = "조영호";
        String 강일 = "강일";
        String 코드로_이해하는_객체지향_설계 = "코드로 이해하는 객체지향 설계";
        LocalDate publicationDate = LocalDate.now();
        Source supply = Source.SUPPLY;
        Classfication computer = Classfication.COMPUTER;
        Location pangyo = Location.PANGYO;
        Book book = Book.enterBook(
                오브젝트,
                조영호,
                강일,
                코드로_이해하는_객체지향_설계,
                publicationDate,
                supply,
                computer,
                pangyo);


        book.makeUnAvailable();
        assertThat(book.getBookStatus()).isEqualTo(BookStatus.UNAVAILABLE);

    }

}