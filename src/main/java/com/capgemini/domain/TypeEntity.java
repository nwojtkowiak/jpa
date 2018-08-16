package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TYPE")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@EntityListeners({UpdateListener.class, InsertListener.class})
public class TypeEntity /*extends  AbstractEntity */ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;

    public TypeEntity(String name){
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
