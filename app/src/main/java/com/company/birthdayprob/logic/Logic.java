package com.company.birthdayprob.logic;

import java.util.Random;

import com.company.birthdayprob.ui.OutputInterface;


public class Logic implements LogicInterface {

    public static final String TAG = Logic.class.getName();


    OutputInterface mOut;


    public Logic(OutputInterface out) {
        mOut = out;
    }


    public void process() {
        int groupSize = mOut.getSize();
        int simulationCount = mOut.getCount();

        if (groupSize < 2 || groupSize > 365) {
            mOut.makeAlertToast("Group Size must be in the range 2-365.");
            return;
        }
        if (simulationCount <= 0) {
            mOut.makeAlertToast("Simulation Count must be positive.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        mOut.println("For a group of " + groupSize + " people, the percentage");
        mOut.println("of times that two people share the same birthday is");
        mOut.println(String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int size, int count) {
        // TODO -- add your code here
        Random random = new Random();
        int countOfDuplicate = 0;

        // Perform the simulation 'count' number of times
        for (int i = 0; i < count; i++) {
            int birthdayCount[] = new int[365];
            random.setSeed((i));

            // Generate 'size' number of random birthdays and count how many fall on each day of the year
            for (int j = 0; j < size; j++) {
                int currentBirthday = random.nextInt(365);
                birthdayCount[currentBirthday]++;

                // If a duplicate birthday is found, increment the count of groups with duplicate birthdays and break
                // out of the loop
                boolean isGreaterThanOne = birthdayCount[currentBirthday] > 1;
                if (isGreaterThanOne) {
                    countOfDuplicate++;
                    break;
                }
            }
        }
}


