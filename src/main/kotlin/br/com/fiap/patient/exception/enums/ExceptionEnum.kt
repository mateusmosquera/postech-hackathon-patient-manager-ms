package br.com.fiap.patient.exception.enums

import br.com.fiap.patient.exception.dto.ResponseErrorDto

fun interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}