package br.com.fiap.patient.integration

import br.com.fiap.patient.application.dto.request.PatientRequest
import br.com.fiap.patient.application.dto.response.PatientResponse
import br.com.fiap.patient.client.PatientCucumberClient
import feign.FeignException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PatientHttpControllerSteps(var patientCucumberClient: PatientCucumberClient){

    private lateinit var patientRequest: PatientRequest
    private lateinit var patientResponse: ResponseEntity<PatientResponse>

    @Given("^que o patient envia uma solicitacao para criar um novo patient, com cpf \"([^\"]*)\", com nome ([^\"]*) e email ([^\"]*)")
    fun givenPatientRequestIsSentToCreateANewClient(cpf:String, nome:String, email:String) {

        patientRequest = PatientRequest(cpf, nome, email,"password123")
        try {
            patientResponse = patientCucumberClient.create(patientRequest)
        } catch (e:FeignException){
            patientResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }


    }

    @When("^patient recebe a solicitacao com code status (\\d+)")
    fun whenPatientRecebeSolicitacao(status:Int) {
        assertEquals(patientResponse.statusCode.value(), status)
    }


    @Then("^o patient recebe a resposta, com cpf \"([^\"]*)\", com nome ([^\"]*) e email ([^\"]*)")
    fun thenPatientRecebeResposta(cpf:String, nome:String, email:String) {

        if(patientResponse.statusCode.value() == 201) {
            assertEquals( PatientResponse(cpf, nome, email), patientResponse.body)
        }
    }


    @Given("^que o patient envia uma solicitacao para recuperar informacoes de um patient pelo CPF")
    fun givenPatientEnviaSolicitacaoRecuperarInformacoes() {


        patientResponse = patientCucumberClient.findByCpf("123.456.789-09")

    }

    @When("^patient recebe as informacoes com code status (\\d+)")
    fun whenPatientRecebeInformacao(status:Int) {
        assertEquals(patientResponse.statusCode.value(), status)
    }


    @Then("recebe as informacoes com a resposta")
    fun thenRecebeInformacoesResposta() {

        assertEquals( PatientResponse(cpf="123.456.789-09", nome="Patient Teste", email="patient@teste.com"), patientResponse.body)
    }

}