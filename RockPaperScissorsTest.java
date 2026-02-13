import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for RockPaperScissors class
 * Tests cover all game outcomes, edge cases, and boundary conditions
 */
public class RockPaperScissorsTest {

    // Tests for checkWinner method - All possible tie scenarios
    @Test
    @DisplayName("Test checkWinner returns tie when both choose rock")
    public void testCheckWinnerTieRock() {
        String result = RockPaperScissors.checkWinner("r", "r");
        assertEquals("It's a tie!", result, "Both choosing rock should result in a tie");
    }

    @Test
    @DisplayName("Test checkWinner returns tie when both choose paper")
    public void testCheckWinnerTiePaper() {
        String result = RockPaperScissors.checkWinner("p", "p");
        assertEquals("It's a tie!", result, "Both choosing paper should result in a tie");
    }

    @Test
    @DisplayName("Test checkWinner returns tie when both choose scissors")
    public void testCheckWinnerTieScissors() {
        String result = RockPaperScissors.checkWinner("s", "s");
        assertEquals("It's a tie!", result, "Both choosing scissors should result in a tie");
    }

    // Tests for checkWinner method - Player wins scenarios
    @Test
    @DisplayName("Test checkWinner player wins with rock against scissors")
    public void testCheckWinnerPlayerWinsRockVsScissors() {
        String result = RockPaperScissors.checkWinner("r", "s");
        assertEquals("You win!", result, "Rock should beat scissors");
    }

    @Test
    @DisplayName("Test checkWinner player wins with paper against rock")
    public void testCheckWinnerPlayerWinsPaperVsRock() {
        String result = RockPaperScissors.checkWinner("p", "r");
        assertEquals("You win!", result, "Paper should beat rock");
    }

    @Test
    @DisplayName("Test checkWinner player wins with scissors against paper")
    public void testCheckWinnerPlayerWinsScissorsVsPaper() {
        String result = RockPaperScissors.checkWinner("s", "p");
        assertEquals("You win!", result, "Scissors should beat paper");
    }

    // Tests for checkWinner method - Computer wins scenarios
    @Test
    @DisplayName("Test checkWinner computer wins with rock against scissors")
    public void testCheckWinnerComputerWinsRockVsScissors() {
        String result = RockPaperScissors.checkWinner("s", "r");
        assertEquals("Computer wins!", result, "Computer's rock should beat player's scissors");
    }

    @Test
    @DisplayName("Test checkWinner computer wins with paper against rock")
    public void testCheckWinnerComputerWinsPaperVsRock() {
        String result = RockPaperScissors.checkWinner("r", "p");
        assertEquals("Computer wins!", result, "Computer's paper should beat player's rock");
    }

    @Test
    @DisplayName("Test checkWinner computer wins with scissors against paper")
    public void testCheckWinnerComputerWinsScissorsVsPaper() {
        String result = RockPaperScissors.checkWinner("p", "s");
        assertEquals("Computer wins!", result, "Computer's scissors should beat player's paper");
    }

    // Test all 9 possible combinations comprehensively
    @Test
    @DisplayName("Comprehensive test: All 9 possible game combinations")
    public void testAllNineCombinations() {
        // Ties
        assertEquals("It's a tie!", RockPaperScissors.checkWinner("r", "r"));
        assertEquals("It's a tie!", RockPaperScissors.checkWinner("p", "p"));
        assertEquals("It's a tie!", RockPaperScissors.checkWinner("s", "s"));

        // Player wins
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"));
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"));
        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"));

        // Computer wins
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("s", "r"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("r", "p"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("p", "s"));
    }

    // Edge case tests with different input variations (though the code expects only r, p, s)
    @Test
    @DisplayName("Test game logic is consistent across multiple calls")
    public void testConsistencyAcrossMultipleCalls() {
        // Call the same scenario multiple times to ensure consistency
        for (int i = 0; i < 10; i++) {
            assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"),
                "Rock should consistently beat scissors");
            assertEquals("Computer wins!", RockPaperScissors.checkWinner("s", "r"),
                "Computer's rock should consistently beat player's scissors");
            assertEquals("It's a tie!", RockPaperScissors.checkWinner("p", "p"),
                "Same choice should consistently result in tie");
        }
    }

    // Test symmetry: Player choice A beats Computer choice B should be opposite of Computer choice B beats Player choice A
    @Test
    @DisplayName("Test game logic symmetry")
    public void testGameLogicSymmetry() {
        // When player wins with rock vs scissors
        String playerWins = RockPaperScissors.checkWinner("r", "s");
        // Computer should win when choices are reversed
        String computerWins = RockPaperScissors.checkWinner("s", "r");

        assertEquals("You win!", playerWins);
        assertEquals("Computer wins!", computerWins);

        // Verify the pattern for all winning combinations
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("r", "p"));

        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("p", "s"));
    }

    // Test the transitive property: if A beats B and B beats C, then C beats A (rock-paper-scissors cycle)
    @Test
    @DisplayName("Test rock-paper-scissors circular property")
    public void testCircularProperty() {
        // Rock beats Scissors
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"));

        // Scissors beats Paper
        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"));

        // Paper beats Rock (completing the circle)
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"));
    }

    // Negative test: Verify that no unexpected outcomes occur
    @Test
    @DisplayName("Negative test: Verify only three possible outcomes")
    public void testOnlyThreePossibleOutcomes() {
        String[] choices = {"r", "p", "s"};
        String[] validOutcomes = {"You win!", "Computer wins!", "It's a tie!"};

        for (String playerChoice : choices) {
            for (String compChoice : choices) {
                String result = RockPaperScissors.checkWinner(playerChoice, compChoice);

                // Verify result is one of the three valid outcomes
                boolean isValidOutcome = false;
                for (String validOutcome : validOutcomes) {
                    if (result.equals(validOutcome)) {
                        isValidOutcome = true;
                        break;
                    }
                }

                assertTrue(isValidOutcome,
                    "Result '" + result + "' for (" + playerChoice + ", " + compChoice + ") should be one of the valid outcomes");
            }
        }
    }

    // Test case insensitivity is NOT handled (the main game converts to lowercase before calling)
    @Test
    @DisplayName("Test that method expects lowercase input")
    public void testExpectsLowercaseInput() {
        // The checkWinner method itself doesn't handle case conversion
        // This is done in the RockPaperScissorsGame method before calling checkWinner
        String result = RockPaperScissors.checkWinner("r", "s");
        assertEquals("You win!", result);
    }

    // Regression test: Ensure the exact victory conditions
    @Test
    @DisplayName("Regression test: Verify exact victory conditions")
    public void testExactVictoryConditions() {
        // Rock beats Scissors (not paper)
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("r", "p"));

        // Paper beats Rock (not scissors)
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("p", "s"));

        // Scissors beats Paper (not rock)
        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("s", "r"));
    }

    // Test abstract method checkWinner() throws exception
    @Test
    @DisplayName("Test abstract checkWinner method throws UnsupportedOperationException")
    public void testAbstractCheckWinnerMethod() {
        RockPaperScissors rps = new RockPaperScissors();
        assertThrows(UnsupportedOperationException.class, () -> {
            rps.checkWinner();
        }, "Abstract checkWinner() method should throw UnsupportedOperationException");
    }

    // Boundary test: Test deterministic behavior
    @Test
    @DisplayName("Boundary test: Verify deterministic outcomes")
    public void testDeterministicOutcomes() {
        // The checkWinner method should always return the same result for the same input
        String result1 = RockPaperScissors.checkWinner("r", "p");
        String result2 = RockPaperScissors.checkWinner("r", "p");
        String result3 = RockPaperScissors.checkWinner("r", "p");

        assertEquals(result1, result2, "Same input should produce same output");
        assertEquals(result2, result3, "Same input should produce same output");
        assertEquals("Computer wins!", result1, "Result should be 'Computer wins!'");
    }

    // Additional test: Verify no side effects
    @Test
    @DisplayName("Test method has no side effects")
    public void testNoSideEffects() {
        // Call the method multiple times with different inputs
        RockPaperScissors.checkWinner("r", "s");
        RockPaperScissors.checkWinner("p", "r");
        RockPaperScissors.checkWinner("s", "p");

        // Verify that subsequent calls still work correctly
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"));
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("s", "r"));
        assertEquals("It's a tie!", RockPaperScissors.checkWinner("p", "p"));
    }

    // Test to ensure code matches game rules (rock crushes scissors, paper covers rock, scissors cut paper)
    @Test
    @DisplayName("Test game rules are correctly implemented")
    public void testGameRulesImplementation() {
        // Rock crushes Scissors
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"),
            "Rock should crush scissors");

        // Paper covers Rock
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"),
            "Paper should cover rock");

        // Scissors cut Paper
        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"),
            "Scissors should cut paper");
    }

    // Stress test: Verify performance with many calls
    @Test
    @DisplayName("Stress test: Performance with many calls")
    public void testPerformanceWithManyCalls() {
        long startTime = System.currentTimeMillis();

        // Make 10000 calls to the method
        for (int i = 0; i < 10000; i++) {
            String[] choices = {"r", "p", "s"};
            String playerChoice = choices[i % 3];
            String compChoice = choices[(i + 1) % 3];
            RockPaperScissors.checkWinner(playerChoice, compChoice);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Verify it completes in reasonable time (should be very fast)
        assertTrue(duration < 1000, "10000 calls should complete in less than 1 second");
    }
}