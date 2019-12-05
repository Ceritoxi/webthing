package happy.model.record;

import happy.domain.record.State;

public class RecordBackFill {

    private String dateTime;
    private State state;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
