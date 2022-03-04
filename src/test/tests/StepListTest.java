package tests;

import model.Step;
import model.StepList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StepListTest {

    StepList testList;
    Step stepA;
    Step stepB;
    Step stepC;

   @BeforeEach
    void runBefore() {
    testList = new StepList();
    stepA = new Step(1, "Add water to pot");
    stepB = new Step(2, "Turn heat to high");
    stepC = new Step(3, "Wait to boil");
   }

   @Test
    void addStepTest(){
       testList.addStep(stepA);
       assertTrue(testList.hasStep(stepA));
       assertFalse(testList.hasStep(stepB));
       testList.addStep(stepB);
       testList.addStep(stepC);
       assertTrue(testList.hasStep(stepB));
       assertTrue(testList.hasStep(stepC));

   }

   @Test
    void removeStepTest() {
       testList.addStep(stepA);
       testList.addStep(stepB);
       testList.addStep(stepC);
       assertTrue(testList.hasStep(stepA));
       assertTrue(testList.hasStep(stepB));
       assertTrue(testList.hasStep(stepC));
       testList.removeStep(stepB);
       assertFalse(testList.hasStep(stepB));
       testList.removeStep(stepC);
       assertFalse(testList.hasStep(stepC));
   }

   @Test
    void displayStepTest() {
       testList.addStep(stepA);
       testList.addStep(stepB);
       testList.addStep(stepC);
       assertEquals("Add water to pot" + "\n" + "Turn heat to high"
               + "\n" + "Wait to boil" + "\n", testList.getSteps());
   }
}
