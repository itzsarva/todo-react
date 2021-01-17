package com.todo.audit;

import com.todo.util.Updatable;

public interface AuditService {
    void audit(Updatable updatable);
}
