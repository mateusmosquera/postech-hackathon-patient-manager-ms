package br.com.fiap.patient.application.controller

import br.com.fiap.patient.application.dto.response.PatientResponse
import br.com.fiap.patient.domain.entities.extension.toDTO
import br.com.fiap.patient.domain.entities.extension.toEntity
import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.domain.usecases.PatientDomainUseCase
import org.springframework.stereotype.Service

@Service
class PatientApplicationController(private val patientDomainUseCase: PatientDomainUseCase) {

    fun create(patient: PatientRequest): PatientResponse =
        patientDomainUseCase.create(patient.toEntity()).toDTO()

    fun findByCpf(cpf: String): PatientResponse =
        patientDomainUseCase.findByCpf(cpf).toDTO()

    fun findById(id: Long): PatientResponse =
        patientDomainUseCase.findById(id).toDTO()

}