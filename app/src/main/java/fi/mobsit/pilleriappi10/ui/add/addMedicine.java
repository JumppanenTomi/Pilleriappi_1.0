package fi.mobsit.pilleriappi10.ui.add;

public class addMedicine {

    private String medicineName;
    private Integer minute;
    private Integer hour;
    private Integer day;
    private Integer month;
    private Integer year;

    public addMedicine(String medicineName, Integer minute, Integer hour, Integer day, Integer month, Integer year){
        this.medicineName = medicineName;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
