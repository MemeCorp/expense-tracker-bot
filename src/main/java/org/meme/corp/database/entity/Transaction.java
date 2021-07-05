package org.meme.corp.database.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {

  //TODO ability to add a photo
  //TODO add Expenses entity

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @Column(name = "event_id", insertable = false, updatable = false)
  private String eventId;

  @OneToOne
  private Person owner;

  @ManyToMany()
  @JoinTable(
      name = "person_transaction_jt",
      joinColumns = {@JoinColumn(name = "transaction_id")},
      inverseJoinColumns = {@JoinColumn(name = "person_id")}
  )
  private Set<Person> persons = new HashSet<>();

}
