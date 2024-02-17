package com.example.raspunsurimultiple.Domain;

public class IntrebareConvertor {
        public String toString(Intrebare intrebare) {
            return intrebare.getID() + "," +
                    intrebare.getText() + "," +
                    intrebare.getRaspunsA() + "," +
                    intrebare.getRaspunsB() + "," +
                    intrebare.getRaspunsC() + "," +
                    intrebare.getDificultate();
        }

        public Intrebare fromString(String line) {
            String[] tokens = line.split(",");
            return new Intrebare(
                    Integer.parseInt(tokens[0]),
                    tokens[1],
                    tokens[2],
                    tokens[3],
                    tokens[4],
                    tokens[5],
                    tokens[6]
            );
        }

}
