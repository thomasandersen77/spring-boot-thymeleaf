package com.github.andtho.spring.springdataexample.entity

import javax.persistence.*

@Entity
data class ContactInfoEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?,
    @Column
    val email : String,
    @Column
    val mobile : String?
) {
    constructor(email: String) : this(null, email, null)
    constructor(email: String, mobile: String) : this(null, email, mobile)
}