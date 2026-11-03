import java.net.URI;
import java.rmi.Naming;

import RMI.CharacterService;

public class RMICharacter {
    public static void main(String[] args) throws Exception {
        CharacterService service = (CharacterService) Naming.lookup("rmi://36.50.135.242/RMICharacterService");
        String input = service.requestCharacter("B23DCCN036", "NtpnDy1E");

        // Phần xử lý dữ liệu
        String encoded = new URI(
                null, null, input, null
            ).toASCIIString();

            service.submitCharacter(
                "B23DCCN036", "NtpnDy1E", encoded
            );
    }
}
