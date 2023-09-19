package com.sa.socialcoding.sms.repository.impl;


import com.sa.socialcoding.sms.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<User> getUsers(Integer userId, String userType, String firstName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(userId)){
            predicates.add(builder.equal(user.get("userId"), userId));
        }
        if(Objects.nonNull(userType)){
            predicates.add(builder.equal(user.get("userType"), userType));
        }
        if(Objects.nonNull(firstName)){
            predicates.add(builder.equal(user.get("firstName"), firstName));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
