package chatbot.client.global.util;

public class DateUtils {
    public enum Period {
        DAY(1), WEEK(7), MONTH(30), YEAR(365);
        private final int days;
        Period(int days) {
            this.days = days;
        }
        public int getDays() {
            return days;
        }
        public int getSeconds() {
            return days * 24 * 60 * 60;
        }
    }

}
