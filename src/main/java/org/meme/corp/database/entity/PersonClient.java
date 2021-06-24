package org.meme.corp.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "person_client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonClient {

    @EmbeddedId
    //TODO specify GeneratedValue for composite key
    private PersonClientPK personClientPK;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;
}
