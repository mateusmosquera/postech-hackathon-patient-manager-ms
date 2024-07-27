package br.com.fiap.patient.domain.usecases

import br.com.fiap.patient.application.gateway.PatientRepositoryGateway
import br.com.fiap.patient.domain.entities.Patient
import br.com.fiap.patient.domain.exception.PatientExceptionEnum
import br.com.fiap.patient.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PatientDomainUseCase(private val patientRepositoryGateway: PatientRepositoryGateway) {

    @Transactional
    fun create(patient: Patient): Patient {

        if(patientExists(patient) == true) {
            throw BusinessException(PatientExceptionEnum.PATIENT_ALREADY_EXISTS_CPF)
        }

        if(emailExists(patient) == true){
            throw BusinessException(PatientExceptionEnum.PATIENT_ALREADY_EXISTS_EMAIL)
        }

        return patientRepositoryGateway.save(patient)
    }

    fun findByCpf(cpf: String) = patientRepositoryGateway.findByCpf(cpf)
        ?: throw BusinessException(PatientExceptionEnum.PATIENT_NOT_FOUND)

    fun patientExists(patient: Patient?) = patient?.cpf?.let { patientRepositoryGateway.existsByCpf(it) }

    fun emailExists(patient: Patient) = patient.email?.let { patientRepositoryGateway.existsByEmail(it) }

    fun findById(id: Long): Patient = id.let {patientRepositoryGateway.findById(it) ?: throw BusinessException(PatientExceptionEnum.PATIENT_NOT_FOUND)}

}