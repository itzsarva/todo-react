package com.todo.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoBucketRequest implements Serializable {
    private static final long serialVersionUID = 1l;

    private String name;

    private Long id;
}
