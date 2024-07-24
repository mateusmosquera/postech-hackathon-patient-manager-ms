package br.com.fiap.patient.client

import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.application.dto.response.PatientResponse
import jakarta.validation.Valid
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "PatientCucumber",
    url = "\${server.host}:\${server.port}\${server.servlet.context-path}/patient"
)
@Service
interface PatientCucumberClient {

    @PostMapping
    fun create(@Valid @RequestBody patientResponse: PatientRequest
    ): ResponseEntity<PatientResponse>


    @GetMapping("/{cpf}")
    fun findByCpf(@PathVariable(required = true) cpf: String): ResponseEntity<PatientResponse>

}