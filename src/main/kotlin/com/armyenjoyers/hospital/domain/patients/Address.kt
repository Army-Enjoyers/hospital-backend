package com.armyenjoyers.hospital.domain.patients

import org.springframework.data.annotation.Id

data class Address(
    @Id
    var id: Int?,

    val region: String,
    val district: String,
    val locality: String,
    val streetAddress: String,
    val houseNumber: String,
    val placementNumber: String,
    val phoneNumber: String,
    val homePhoneNumber: String,

    val streetType: StreetType,

    val localityType: LocalityType,

    val placementType: PlacementType,
)
