package com.example.jpawebapp.entity;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class BaseHistory {

    @CreatedDate
    @Column(name = "CREATE_AT", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATE_AT", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

}
