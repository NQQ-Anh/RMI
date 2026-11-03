import java.rmi.Naming;

import RMI.ObjectService;
import RMI.TicketSla;

public class RMIObject {
    public static void main(String[] args) throws Exception {
        ObjectService service = (ObjectService) Naming.lookup("rmi://36.50.135.242/RMIObjectService");
        TicketSla ticket = (TicketSla) service.requestObject("B23DCCN036", "ow6JEpEE");

        // Phần xử lý dữ liệu
        String priority = ticket.getPriority();
            int hours = ticket.getOpenedHoursAgo();
            boolean breached = false;
            switch (priority.toUpperCase()) {
                case "CRITICAL":
                    breached = hours > 2;
                    break;

                case "HIGH":
                    breached = hours > 8;
                    break;

                case "MEDIUM":
                    breached = hours > 24;
                    break;

                case "LOW":
                    breached = hours > 72;
                    break;
            }

            ticket.setBreached(breached);
            String action;

            if (!breached) {
                action = "MONITOR";
            }
            else if (priority.equalsIgnoreCase("CRITICAL")
                    || hours > 96) {
                action = "ESCALATE_L2";
            }
            else {
                action = "ESCALATE_L1";
            }
            ticket.setAction(action);

            
        service.submitObject("B23DCCN036", "ow6JEpEE", ticket);
    }
}
