package org.meme.corp.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Chat {

    @EmbeddedId
    //TODO specify GeneratedValue for composite key
    private ChatPK chatPK;

    @ManyToMany()
    @JoinTable(
            name = "chat_event_jt",
            joinColumns = { @JoinColumn(name = "chat_client_name"), @JoinColumn(name = "chat_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    Set<Event> events = new HashSet<>();
}
