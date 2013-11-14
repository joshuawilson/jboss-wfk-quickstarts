/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.spring.matrixvariables.data;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.jboss.as.quickstarts.kitchensink.spring.matrixvariables.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {
    
    private static final Logger log = Logger.getLogger(MemberDaoImpl.class.getName());
    
    @Autowired
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByNameAndEmail(String name, String email) {
        log.fine("findByNameAndEmail name = " + name + ", email = " + email);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        EntityType<Member> type = em.getMetamodel().entity(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Fix for JDF-558; joshuawilson 11/13/2013
        // The original design had the first WHERE clause overwriting the second one.  Since it needs to handle both
        //   a Name and Email as well the single version, it made sense to externalize the Predicates.
        Predicate isLikeName = null;
        Boolean nameExists = false;
        Predicate isLikeEmail = null;
        Boolean emailExists = false;
        // If the name exist create the Predicate for a LIKE comparison of the name. 
        if (name != null && !name.isEmpty() && name != "") {
            isLikeName = cb.like(cb.lower(member.get(type.getDeclaredSingularAttribute("name", String.class))), "%" + name.toLowerCase() + "%");
            nameExists = true;
        }
        // If the email exist create the Predicate for a LIKE comparison of the email. 
        if (email != null && !email.isEmpty() && email != "") {
            isLikeEmail = cb.like(cb.lower(member.get(type.getDeclaredSingularAttribute("email", String.class))), "%" + email.toLowerCase() + "%");
            emailExists = true;
        }
        if (nameExists && emailExists) {
            // If both name and email exist then use both in the WHERE clause.
            criteria.where(cb.and(isLikeName,isLikeEmail));
        } else if (nameExists) {
            // If only name exists then use only name in the WHERE clause.
            criteria.where(isLikeName);
        } else if (emailExists) {
            // If only email exists then use only email in the WHERE clause.
            criteria.where(isLikeEmail);
        }
        return em.createQuery(criteria).getResultList();
    }

    public Member findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(member).where(cb.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public void register(Member member) {
        em.persist(member);
        return;
    }
}
