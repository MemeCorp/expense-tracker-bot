package org.meme.corp.database.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class ChatPK implements Serializable {

    protected long id;

    @Column(name = "client_name")
    protected String clientName;

}
