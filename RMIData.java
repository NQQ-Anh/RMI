import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import RMI.DataService;

public class RMIData {
    public static void main(String[] args) throws Exception {
         DataService service = (DataService) Naming.lookup("rmi://36.50.135.242/RMIDataService");
        //  Object arr = service.requestData("B23DCCN036", "NH3mDIVc");
        int[] arr = (int[]) service.requestData(
                "B23DCCN036", "NH3mDIVc"
            );

            // Phần xử lý dữ liệu
            for (int x : arr) {
                System.out.print(x + " ");
            }

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {

                boolean isPeak = true;

                if (i > 0 && arr[i] <= arr[i - 1]) {
                    isPeak = false;
                }

                if (i < arr.length - 1 && arr[i] <= arr[i + 1]) {
                    isPeak = false;
                }
                if (isPeak) {
                    result.add(i + 1);
                }
            }


            service.submitData("B23DCCN036", "NH3mDIVc", result);
    }
}
