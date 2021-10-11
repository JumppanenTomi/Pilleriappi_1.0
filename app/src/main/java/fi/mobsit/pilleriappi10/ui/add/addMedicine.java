package fi.mobsit.pilleriappi10.ui.add;

/**
 * @author Tomi Jumppanen
 */

public class addMedicine {

    private final String medicineName;
    private final String date;
    private final String time;

    public addMedicine(String medicineName, Integer minute, Integer hour, Integer day, Integer month, Integer year) {
        this.medicineName = medicineName;
        this.time = hour + "." + minute;
        this.date = day + "." + month + "." + year;
    }
}
