package com.todo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.todo.util.Updatable;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Builder
@Table(name = "todo")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Todo extends Updatable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Boolean completed;

    private Date sysTimestamp;

    @ManyToOne
    @JsonBackReference
    private TodoBucket todoBucket;

    @CreatedBy
    public String user;

    @CreatedDate
    public Instant createdDate;

    @LastModifiedBy
    public String lastModifiedUser;

    @LastModifiedDate
    public Instant lastModifiedDate;

}
