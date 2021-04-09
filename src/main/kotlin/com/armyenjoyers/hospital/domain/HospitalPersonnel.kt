package com.armyenjoyers.hospital.domain

import javax.persistence.*

@Entity
@Table(name = "hospital_personel")
data class HospitalPersonnel(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "patronymic")
    val patronymic: String,

    @Column(name = "login")
    val login: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "possition")
    val position: String,

    @ManyToMany
    @JoinTable(
        name = "hospital_personel_roles",
        joinColumns = [JoinColumn(name = "hospital_personel_id")],
        inverseJoinColumns = [JoinColumn(name = "roles_id")]
    )
    var roles: MutableList<Role>
)