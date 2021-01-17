package com.todo.serviceImpl;

import com.todo.domain.TodoBucket;
import com.todo.modal.TodoBucketRequest;
import com.todo.repository.TodoBucketRepository;
import com.todo.security.services.UserDetailsImpl;
import com.todo.service.TodoBucketService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TodoBucketServiceImpl implements TodoBucketService {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    TodoBucketRepository todoBucketRepository;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    private Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public List<TodoBucket> getAllTodoBuckets(String username) {
        return getListByCriteria(username);
    }

    List<TodoBucket> getListByHbm() {
        Query<TodoBucket> query = getSession().createQuery("select distinct bucket from TodoBucket bucket INNER JOIN FETCH bucket.todoSet todo", TodoBucket.class);
        return query.getResultList();
    }

    List<TodoBucket> getListByCriteria(String username) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<TodoBucket> criteria = builder.createQuery(TodoBucket.class);
        Root<TodoBucket> root = criteria.from(TodoBucket.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("user"), username));

        Query<TodoBucket> query = getSession().createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Boolean addTodoBucket(TodoBucketRequest todoBucketRequest) {
        Authentication authentication = getUser();
        if (authentication.isAuthenticated()) {
            UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
            TodoBucket todoBucket = TodoBucket.builder()
                    .name(todoBucketRequest.getName())
                    .user(details.getUsername())
                    .build();
            todoBucketRepository.save(todoBucket);
            return Boolean.TRUE;
        } else {
            throw new AuthenticationServiceException("Invalid Access");
        }
    }

    @Override
    public Boolean removeTodoBucket(TodoBucketRequest todoBucketRequest) {
        Authentication authentication = getUser();
        if (authentication.isAuthenticated()) {
            todoBucketRepository.deleteById(todoBucketRequest.getId());
            return Boolean.TRUE;
        } else {
            throw new AuthenticationServiceException("Invalid Access");
        }
    }
}
