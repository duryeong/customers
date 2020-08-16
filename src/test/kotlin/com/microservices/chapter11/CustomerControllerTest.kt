package com.microservices.chapter11

import org.junit.Assert.*
import com.microservices.chapter11.CustomerController
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `we should get the customer list`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].name").value("Kotlin"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[1].name").value("Spring"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[2].name").value("Microservice"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[3].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("\$[3].name").value("OpenShift"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `we should get a customer by id`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value("Kotlin"))
                .andDo(MockMvcResultHandlers.print())
    }
}
