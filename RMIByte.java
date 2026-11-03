import java.rmi.Naming;
import java.util.LinkedHashMap;
import java.util.Map;

import RMI.ByteService;

public class RMIByte {
    public static void main(String[] args) throws Exception {
        ByteService service = (ByteService) Naming.lookup("rmi://36.50.135.242/RMIByteService");
        byte[] data = service.requestData("B23DCCN036", "q7M9pLrD");
        
        //Phần xử lý dữ liệu
            Map<Byte, Integer> frequency = new LinkedHashMap<>();
            for (byte b : data) {
                frequency.put(b, frequency.getOrDefault(b, 0) + 1);
            }
            int minCount = Integer.MIN_VALUE;
            for (int count : frequency.values()) {
                if (count > minCount) {
                    minCount = count;
                }
            }
            byte minElement = 0;
            for (byte b : data) {
                if (frequency.get(b) == minCount) {
                    minElement = b;
                    break;
                }
            }
            byte[] result = {
                minElement,
                (byte) minCount
            };



        service.submitData("B23DCCN036", "q7M9pLrD", result);

    }
}
