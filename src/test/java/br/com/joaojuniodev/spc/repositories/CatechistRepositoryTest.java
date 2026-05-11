package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.Step;
import br.com.joaojuniodev.spc.models.enums.NameOfTheCommunityOrParishEnum;
import br.com.joaojuniodev.spc.models.enums.StepNameEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CatechistRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private CatechistRepository repository;

    @Autowired
    private StepRepository stepRepository;

    private Catechist catechist0;

    private Step step0;

    @BeforeEach
    void setup() {
        NameOfTheCommunityOrParishEnum communityOrParish = NameOfTheCommunityOrParishEnum.SAO_SEBASTIAO;

        step0 = new Step(
            1L,
            StepNameEnum.CRISMA,
            communityOrParish,
            Collections.emptySet(),
            Collections.emptySet()
        );

        catechist0 = new Catechist(
            1L,
            "João Junio",
            "Trindade Castro",
            communityOrParish,
            step0,
            Collections.emptySet()
        );
    }

    @DisplayName("JUnit Test for Given Catechist Object when Save then Return Saved Catechist")
    @Test
    void testGivenCatechistObject_WhenSave_thenReturnSavedCatechist() {
        Step savedStep = stepRepository.save(step0);
        Catechist savedCatechist = repository.save(catechist0);

        assertNotNull(savedStep);
        assertNotNull(savedCatechist);
        assertTrue(savedStep.getId() > 0);
        assertTrue(savedCatechist.getId() > 0);
    }
}