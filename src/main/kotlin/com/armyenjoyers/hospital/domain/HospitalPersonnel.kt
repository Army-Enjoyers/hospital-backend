package com.armyenjoyers.hospital.domain

import javax.persistence.*

@Entity
@Table(name = "hospital_personnel")
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

    @Column(name = "position")
    val position: String,

    @ManyToMany
    @JoinTable(
        name = "hospital_personnel_roles",
        joinColumns = [JoinColumn(name = "hospital_personnel_id")],
        inverseJoinColumns = [JoinColumn(name = "roles_id")]
    )
    var roles: MutableList<Role>
)