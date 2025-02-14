package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(
        provider = ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD,
        type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
@ActiveProfiles("test")
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание автора: успешный сценарий (Given/When/Then)")
    void createAuthorSuccess() throws Exception {
        // given
        authorRepository.deleteAll();
        assertThat(authorRepository.count()).isZero();

        Author authorRequest = new Author();
        authorRequest.setName("John Doe");
        authorRequest.setBiography("Short biography");

        // when
        mockMvc.perform(post("/api/authors")  // предполагаем, что Endpoints.AUTHOR = "/api/author"
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authorRequest)))
                .andExpect(status().isOk()) // проверяем HTTP 200
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.biography").value("Short biography"));

        // then
        assertThat(authorRepository.count()).isEqualTo(1);

        Author createdAuthor = authorRepository.findAll().get(0);
        assertThat(createdAuthor.getName()).isEqualTo("John Doe");
        assertThat(createdAuthor.getBiography()).isEqualTo("Short biography");
    }
}