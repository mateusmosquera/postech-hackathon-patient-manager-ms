package br.com.fiap.patient.domain.exception

import br.com.fiap.patient.exception.dto.ResponseErrorDto
import br.com.fiap.patient.exception.enums.ExceptionEnum
import org.springframework.http.HttpStatus

enum class PatientExceptionEnum(private val error: String,
                                private val httpStatusCode: HttpStatus) : ExceptionEnum {

    PATIENT_NOT_FOUND("Patient not found.", HttpStatus.NOT_FOUND),
    PATIENT_ALREADY_EXISTS_CPF("CPF already registered for another patient.", HttpStatus.BAD_REQUEST),
    PATIENT_ALREADY_EXISTS_EMAIL("Email already registered for another patient.", HttpStatus.BAD_REQUEST);

    override fun getResponseError(): ResponseErrorDto {
        return ResponseErrorDto(error = error, status = httpStatusCode.value())
    }

}