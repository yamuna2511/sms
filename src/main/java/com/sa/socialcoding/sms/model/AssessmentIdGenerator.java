package com.sa.socialcoding.sms.model;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.security.SecureRandom;

@Slf4j
public class AssessmentIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        SecureRandom rand = new SecureRandom();

        // Generate random integers in range 0 to 999
        int rand_int = rand.nextInt(100000000);
        return "E"+rand_int;
    }
}
