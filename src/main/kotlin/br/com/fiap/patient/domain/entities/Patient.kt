package br.com.fiap.patient.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.validation.constraints.Email
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_seq")
    @SequenceGenerator(name = "patient_id_seq", sequenceName = "patient_id_seq", allocationSize = 1)
    val id: Long?,
    @Column(name = "CPF", unique = true)
    val cpf: String?,
    @Column(name = "NOME")
    val nome: String?,
    @Email
    @Column(name = "EMAIL", unique = true)
    val email: String?,

    @Column(name = "PASSWORD", unique = false)
    val password: String?,

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createDate: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "update_date", nullable = false, updatable = false)
    var updateDate: LocalDateTime? = null) {

}