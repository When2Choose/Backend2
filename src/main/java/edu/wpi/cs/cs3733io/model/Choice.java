package edu.wpi.cs.cs3733io.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

public class Choice {
    public final UUID uuid;
    public final String uuidString;
    public final int memberCount;
    public final String description;

    public final String[] alternativeNames;
    LinkedList<Alternative> alternatives;

    boolean isCompleted;
    String dateCompleted;

    //@formatter:off
    public String getUuidString() {
        return uuidString;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public String getDescription() {
        return description;
    }

    public LinkedList<Alternative> getAlternatives() {
        return alternatives;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean flag) {
        this.isCompleted = flag;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    //@formatter:on


    /**
     * A Choice that people can access.
     *
     * @param memberCount
     * @param description
     * @param alternativeNames
     */
    public Choice(int memberCount, String description, String[] alternativeNames) {
        uuid = UUID.randomUUID();
        uuidString = uuid.toString();
        this.memberCount = memberCount;
        this.description = description;
        this.alternativeNames = alternativeNames;
        this.dateCompleted = "Not Complete";
        this.isCompleted = false;
        alternatives = new LinkedList<>();
    }

    public Choice(String uuidString, int memberCount, String description, String dateCompleted, boolean isCompleted,
                  String[] alternativeNames) {
        this.uuidString = uuidString;
        uuid = UUID.fromString(uuidString); // this needs to be deleted
        // uuid = UUID.fromString(uuidString);
        this.memberCount = memberCount;
        this.description = description;
        this.alternativeNames = alternativeNames;
        this.dateCompleted = dateCompleted;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts a choice to a string including all fields.
     */
    public String toString() {

        String alts = "[";
        for (String s : alternativeNames) {
            if (alts.equals("[")) {
                alts = alts + "\"" + s + "\"";
            } else {
                alts = alts + ", " + "\"" + s + "\"";
            }
        }
        alts = alts + "]";

        return "{" + "\"ID\" : \"" + uuidString + "\"," + "\"Member Count\" :" + "\""

                + memberCount + "\"," + " \"Alternatives\" :" + alts + "," + "\"DateCompleted\" :"
                + "\"" + dateCompleted + "\"," + "\"Description\" :" + "\"" + description + "\"}";

    }

    /**
     * Creates a list of alternatives that correspond to
     */
    public void createAlternatives() {
        for (String name : alternativeNames) {
            alternatives.add(new Alternative(name, this.uuidString));
        }
    }

    /**
     * Complete the choice.
     *
     * @param date
     */
    public void competed(String date) {
        dateCompleted = date;
        isCompleted = true;
    }
}
