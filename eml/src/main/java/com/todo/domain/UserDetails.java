package com.todo.domain;


import com.todo.util.Updatable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserDetails extends Updatable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(notes = "User Name")
    @Column(unique = true)
    private String username;

    @ApiModelProperty(notes = "Database generated password")
    private String password;

    private String role;

}
