package com.armyenjoyers.hospital.domain.patients

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "military_affiliations")
data class MilitaryAffiliation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    var id: Int?,

//    @Enumerated(EnumType.STRING)
//    var militaryRank: MilitaryRank,
//    @Enumerated(EnumType.STRING)
//    var servicemanCategory: ServicemanCategory,

    @Column(name = "military_commissariat")//TODO comMissariat
    var militaryCommissariat: String,

    @Column(name = "conscription_date")
    var conscriptionDate: LocalDateTime

)