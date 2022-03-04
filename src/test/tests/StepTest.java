package tests;

import model.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepTest {

    private Step stepA;
    private Step stepB;
    private Step stepC;

    @BeforeEach
    void runBefore() {
        stepA = new Step(1, "Wash vegetables");
        stepB = new Step(2, "Peel vegetables");
        stepC = new Step( 23, "Cut vegetables");
    }

    @Test
    void getStepNumberTest() {
        assertEquals(1, stepA.getStepNumber());
        assertEquals(2, stepB.getStepNumber());
        assertEquals(23, stepC.getStepNumber());

    }

    @Test
    void getInstructionsTest() {
        assertEquals("Wash vegetables", stepA.getInstructions());
        assertEquals("Peel vegetables", stepB.getInstructions());
        assertEquals("Cut vegetables", stepC.getInstructions());
    }


}

