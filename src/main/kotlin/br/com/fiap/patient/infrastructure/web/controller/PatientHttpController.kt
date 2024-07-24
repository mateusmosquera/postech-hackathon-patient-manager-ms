package br.com.fiap.patient.infrastructure.web.controller

import br.com.fiap.patient.application.controller.PatientApplicationController
import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.application.dto.response.PatientResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/patient")
class PatientHttpController(private val patientService: PatientApplicationController) {

    @PostMapping
    fun create(@Valid @RequestBody patient: PatientRequest,
               uriBuilder: UriComponentsBuilder): ResponseEntity<PatientResponse> {
        val patientCreated = patientService.create(patient)
        val uri = uriBuilder.path("/api/v1/patient/{cpf}").buildAndExpand(patientCreated.cpf).toUri()
        return ResponseEntity.created(uri).body(patientCreated)
    }

    @GetMapping("/{cpf}")
    fun findByCpf(@PathVariable(required = true) cpf: String) =
        ResponseEntity(patientService.findByCpf(cpf = cpf), HttpStatus.OK)

}

