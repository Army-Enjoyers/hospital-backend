package com.armyenjoyers.hospital.domain.personnel

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @Column(name="id")
    val id: Int,

    @Column(name="name")
    val name: String,

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    val personnel: List<HospitalPersonnel>,

    @ManyToMany
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "roles_id")],
        inverseJoinColumns = [JoinColumn(name = "permissions_id")]
    )
    val permissions: MutableList<Permission>

)
