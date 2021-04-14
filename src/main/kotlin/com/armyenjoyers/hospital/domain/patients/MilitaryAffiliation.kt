package com.armyenjoyers.hospital.domain.patients

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class MilitaryAffiliation(
    @Id
    var id: Int?,

    var militaryRank: MilitaryRank,
    var servicemanCategory: ServicemanCategory,

    var militaryCommissariat: String,

    var conscriptionDate: LocalDate

)