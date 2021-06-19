package org.meme.corp.database.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class ChatPK implements Serializable {

    protected Long id;

    protected String clientName;

}
