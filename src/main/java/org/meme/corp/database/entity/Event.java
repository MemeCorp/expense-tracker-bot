package org.meme.corp.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Event {

    @Id
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name="event_id", nullable=false)
    private Set<Transaction> transactions = new HashSet<>();
}
