package com.expensive_pig.carin.core;

import com.expensive_pig.carin.game_data.GameConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigParserTest {
    @Test
    public void TestGameConfigParser_FromText_ShouldPass() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String test = "10 10 " +
                "0.5 " +
                "5000 1000 " +
                "100 100 " +
                "10  4 " +
                "15 1 " +
                "20 " +
                "500";

        GameConfiguration expected = GameConfiguration.builder()
                .m(10).n(10)
                .virusSpawnRate(0.5f)
                .initialAntibodyCredits(5000).antibodyPlacementCost(1000)
                .initialVirusHp(100).initialAntibodyHp(100)
                .virusAttackDamage(10).virusAttackGain(4)
                .antibodyAttackDamage(15).antibodyKillHpGain(1)
                .antibodyMoveHpCost(20)
                .antibodyKillCreditGain(500)
                .build();

        GameConfiguration actual = GameConfigParser.parseConfigFromText(test);

        String expectedJson = objectMapper.writeValueAsString(expected);
        String actualJson = objectMapper.writeValueAsString(actual);
        assertEquals(expectedJson, actualJson);
    }
}