package com.armyenjoyers.hospital.domain.requests

data class PaidService(
    var contractNumber: String,
    var insuranceNumber: String?
)