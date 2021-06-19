package org.meme.corp.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person_client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonClient {

    @Id
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;
}
