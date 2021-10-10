package fi.mobsit.pilleriappi10.ui.add;

public class addMedicine {

    private String medicineName;
    private String date;
    private String time;

    public addMedicine(String medicineName, Integer minute, Integer hour, Integer day, Integer month, Integer year){
        this.medicineName = medicineName;
        this.time = hour + "." + minute;
        this.date = day + "." + month + "." + year;
    }
}
