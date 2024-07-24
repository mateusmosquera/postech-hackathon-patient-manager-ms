package br.com.fiap.patient.application.gateway

import br.com.fiap.patient.domain.entities.Patient
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface PatientRepositoryGateway {
    fun save(patient: Patient): Patient
    fun findByCpf(cpf: String): Patient?
    fun existsByCpf(cpf: String): Boolean
    fun existsByEmail(email: String): Boolean
}