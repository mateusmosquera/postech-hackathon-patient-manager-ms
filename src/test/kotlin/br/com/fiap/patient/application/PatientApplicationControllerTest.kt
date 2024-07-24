package br.com.fiap.patient.application


import br.com.fiap.patient.application.controller.PatientApplicationController
import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.application.dto.response.PatientResponse
import br.com.fiap.patient.domain.entities.Patient
import br.com.fiap.patient.domain.entities.extension.toEntity
import br.com.fiap.patient.domain.usecases.PatientDomainUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PatientApplicationControllerTest {

    private val patientDomainUseCase = mock(PatientDomainUseCase::class.java)
    private val patientApplicationController = PatientApplicationController(patientDomainUseCase)

    @Test
    fun `teste criacao do patient na layer application`() {
        val patienteRequestDto = PatientRequest(
            cpf = "123.456.789-09",
            email = "email@email.com",
            nome = "Jonh Doe",
            password = "password123")
        val patient = patienteRequestDto.toEntity()
        val patientResponse = PatientResponse(
            cpf = "123.456.789-09",
            email = "email@email.com",
            nome = "Jonh Doe")

        `when`(patientDomainUseCase.create(patient)).thenReturn(patient)

        val result = patientApplicationController.create(patienteRequestDto)

        assertEquals(patientResponse, result)
    }

    @Test
    fun `teste busca por cpf do patient na layer application`() {
        val cpf = "123.456.789-09"
        val patient = Patient(
            id = 1,
            cpf = "123.456.789-09",
            email = "email@email.com",
            nome = "Jonh Doe",
            password = "password123")
        val patientResponse = PatientResponse(cpf = "123.456.789-09",
            email = "email@email.com",
            nome = "Jonh Doe")

        `when`(patientDomainUseCase.findByCpf(cpf)).thenReturn(patient)

        val result = patientApplicationController.findByCpf(cpf)

        assertEquals(patientResponse, result)
    }
}