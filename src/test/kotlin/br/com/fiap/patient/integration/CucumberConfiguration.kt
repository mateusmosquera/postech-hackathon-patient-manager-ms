package br.com.fiap.patient.integration

import br.com.fiap.patient.HackathonPatientManagerMsApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@CucumberContextConfiguration
@SpringBootTest(classes = [HackathonPatientManagerMsApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
class CucumberConfiguration {
}