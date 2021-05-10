package com.github.andtho.spring.springdataexample.entity

import javax.persistence.*

@Entity
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val username: String,

    @Column
    val password: String,

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(targetEntity = ContactInfoEntity::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val contactInfoEntity: ContactInfoEntity?
) {
    constructor(username: String, password: String) : this(null, username, password, null)
    constructor(id : Long?, username: String, password: String) : this(id, username, password, null)
    constructor(username: String, password: String, contactInfoEntity: ContactInfoEntity) : this(
        null,
        username,
        password,
        contactInfoEntity
    )
}
