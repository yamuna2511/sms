package com.sa.socialcoding.sms.repository.impl;

import com.sa.socialcoding.sms.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserCredentialRepository {

    @Autowired
    private EntityManager entityManager;
    public UserCredentials findByUserName(String userName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserCredentials> cq = builder.createQuery(UserCredentials.class);
        Root<UserCredentials> userCred = cq.from(UserCredentials.class);
        cq.where(builder.equal(userCred.get("userName"), userName));
        TypedQuery<UserCredentials> query = entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
