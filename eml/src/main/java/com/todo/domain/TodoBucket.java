package com.todo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todo.util.Updatable;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todo_bucket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class TodoBucket extends Updatable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "todoBucket", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Todo> todoSet = new HashSet<>();

    @CreatedBy
    public String user;

    @CreatedDate
    public Instant createdDate;

    @LastModifiedBy
    public String lastModifiedUser;

    @LastModifiedDate
    public Instant lastModifiedDate;

}
