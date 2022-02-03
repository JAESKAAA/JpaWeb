package com.example.jpawebapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("B")
public class Book extends Item{

    private String author;
    private String isbn;

}
