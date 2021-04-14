package com.armyenjoyers.hospital.domain.patients

import javax.persistence.*

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    var id: Int?,

    val region: String,
    val district: String,
    val locality: String,
    val streetAddress: String,
    val houseNumber: String,
    val placementNumber: String,
    val phoneNumber: String,
    val homePhoneNumber: String,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "street_types_id", nullable = false)
    val streetType: StreetType,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "locality_types_id", nullable = false)
    val localityType: LocalityType,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "placement_types_id", nullable = false)
    val placementType: PlacementType,
)
