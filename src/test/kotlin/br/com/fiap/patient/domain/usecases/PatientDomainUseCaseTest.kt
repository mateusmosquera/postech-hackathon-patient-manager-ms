package br.com.fiap.patient.domain.usecases

import br.com.fiap.patient.application.gateway.PatientRepositoryGateway
import br.com.fiap.patient.domain.entities.Patient
import br.com.fiap.patient.domain.exception.PatientExceptionEnum
import br.com.fiap.patient.exception.BusinessException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PatientDomainUseCaseTest {

    @Mock
    lateinit var patientRepositoryGateway: PatientRepositoryGateway

    @InjectMocks
    lateinit var patientDomainUseCase: PatientDomainUseCase

    private val patient = Patient(1L, "123.456.789-09", "Patient Teste", "patient@teste.com", "password123")

    @Test
    fun `Deve criar patient quando CPF e e-mail nao estao em uso`() {
        `when`(patient.cpf?.let { patientRepositoryGateway.existsByCpf(it) }).thenReturn(false)
        `when`(patient.email?.let { patientRepositoryGateway.existsByEmail(it) }).thenReturn(false)
        `when`(patientRepositoryGateway.save(patient)).thenReturn(patient)

        val result = patientDomainUseCase.create(patient)

        assertEquals(patient, result)
        verify(patientRepositoryGateway).save(patient)
    }

    @Test
    fun `Deve lancar BusinessException quando cpf ja esta em uso`() {
        `when`(patient.cpf?.let { patientRepositoryGateway.existsByCpf(it) }).thenReturn(true)

        val exception = assertThrows(BusinessException::class.java) {
            patientDomainUseCase.create(patient)
        }

        assertEquals(PatientExceptionEnum.PATIENT_ALREADY_EXISTS_CPF, exception.exceptionEnum)
    }

    @Test
    fun `Deve lancar BusinessException quando email ja estao`() {
        `when`(patient.cpf?.let { patientRepositoryGateway.existsByCpf(it) }).thenReturn(false)
        `when`(patient.email?.let { patientRepositoryGateway.existsByEmail(it) }).thenReturn(true)

        val exception = assertThrows(BusinessException::class.java) {
            patientDomainUseCase.create(patient)
        }

        assertEquals(PatientExceptionEnum.PATIENT_ALREADY_EXISTS_EMAIL, exception.exceptionEnum)
    }

    @Test
    fun `Deve buscar um patient por cpf`() {
        val cpf = "123.456.789-09"
        `when`(patientRepositoryGateway.findByCpf(cpf)).thenReturn(patient)

        val result = patientDomainUseCase.findByCpf(cpf)

        assertEquals(patient, result)
    }

    @Test
    fun `Deve lancar BusinessException quando patient nao for encontrado por cpf`() {
        val cpf = "987.654.321-09"
        `when`(patientRepositoryGateway.findByCpf(cpf)).thenReturn(null)

        val exception = assertThrows(BusinessException::class.java) {
            patientDomainUseCase.findByCpf(cpf)
        }

        assertEquals(PatientExceptionEnum.PATIENT_NOT_FOUND, exception.exceptionEnum)
    }
}