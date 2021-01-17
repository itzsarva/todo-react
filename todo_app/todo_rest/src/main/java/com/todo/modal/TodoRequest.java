package com.todo.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest implements Serializable {

    private static final long serialVersionUID = 1l;

    private Long parentId;

    private Long childId;

    private String name;
}
