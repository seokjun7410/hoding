package com.msa.book.domain.model;

import com.msa.book.domain.model.vo.BookDesc;
import com.msa.book.domain.model.vo.BootStatus;
import com.msa.book.domain.model.vo.Classfication;
import com.msa.book.domain.model.vo.Location;
import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Book {
    private long no;
    private String title;
    private BookDesc desc;
    private Classfication classfication;
    private BootStatus bootStatus;
    private Location location;


}
