package org.meme.corp.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Event {

    @Id
    private Long id;

    private String name;
}
