package com.armyenjoyers.hospital.domain.requests

data class Disease(
    var mkb10Code: String,
    var mainDiagnosis: String,
    var coDiagnosis: String?,
    var complications: String?,
    var anamnesis: String?,
    var complaints: String?,
    var labExaminationInterpretation: String?,
    var instrumentalExaminationInterpretation: String?,
    var extra: String?,
    var recomendations: String?,
    var manipulations: MutableList<String>,
    var completedProcedures: MutableList<String>
)