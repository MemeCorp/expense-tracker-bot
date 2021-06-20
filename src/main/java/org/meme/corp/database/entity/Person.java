package org.meme.corp.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany()
    @JoinTable(
            name = "person_transaction_jt",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "transaction_id") }
    )
    Set<Transaction> transactions = new HashSet<>();
}
