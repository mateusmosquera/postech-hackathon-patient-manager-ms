package br.com.fiap.patient.domain.entities.extension

import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.application.dto.response.PatientResponse
import br.com.fiap.patient.domain.entities.Patient

fun Patient.toDTO() = PatientResponse(cpf = cpf, nome = nome, email = email)
fun PatientRequest.toEntity() = Patient(id = null, cpf = cpf, nome = nome, email = email, password = password)
