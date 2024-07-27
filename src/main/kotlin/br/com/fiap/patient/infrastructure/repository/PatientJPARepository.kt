package br.com.fiap.patient.infrastructure.repository

import br.com.fiap.patient.application.gateway.PatientRepositoryGateway
import br.com.fiap.patient.domain.entities.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientJPARepository : PatientRepositoryGateway, JpaRepository<Patient, Long>