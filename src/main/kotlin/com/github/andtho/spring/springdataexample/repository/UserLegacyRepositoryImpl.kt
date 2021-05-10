package com.github.andtho.spring.springdataexample.repository

import com.github.andtho.spring.springdataexample.entity.UserEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.NoResultException

@Component
class UserLegacyRepositoryImpl(val entityManager: EntityManager) : UserLegacyRepository {

    @Transactional
    override fun getUserById(id: Long): UserEntity? {
        with(entityManager) {
            return find(UserEntity::class.java, id)
        }
    }

    @Transactional
    override fun createUser(user: UserEntity): UserEntity {
        return with(entityManager) {
            persist(user)
            user
        }
    }

    @Transactional(readOnly = true)
    override fun getUserByName(name: String): UserEntity? {
        return try {
            with(entityManager) {
                createQuery(
                    "select u from UserEntity u where u.username like :username",
                    UserEntity::class.java
                ).apply {
                    setParameter("username", name)
                }.singleResult
            }
        } catch (e : NoResultException) {
            return null
        }
    }
}