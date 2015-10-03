package com.example;

/**
 * Created by liran on 2015-10-03.
 */
public class Parcel {

    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {

            return i;
        }
    }

    protected class PDestination implements Destination {

        private String Label;

        public PDestination(String label) {
            this.Label = label;
        }

        @Override
        public String readLabel() {
            return Label;
        }
    }


    public Destination destination(String label) {
        return new PDestination(label);
    }

    public Contents contents() {
        return new PContents();
    }





}
