package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COLOR")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@EntityListeners({UpdateListener.class, InsertListener.class})
public class ColorEntity /*extends  AbstractEntity  */ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;

    public ColorEntity(){

    }
    public ColorEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
