public class Events extends Task{
    private String startDate;
    private String endDate;

    public Events(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String formatDate(){
        return " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + formatDate();
    }
}
