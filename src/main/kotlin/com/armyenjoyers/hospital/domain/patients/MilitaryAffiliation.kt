package com.armyenjoyers.hospital.domain.patients

import java.time.LocalDate

data class MilitaryAffiliation(

    var militaryRank: MilitaryRank,
    var servicemanCategory: ServicemanCategory,
    var militaryCommissariat: String,
    var conscriptionDate: LocalDate

)