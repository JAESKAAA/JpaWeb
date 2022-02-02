package com.example.jpawebapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;

}
