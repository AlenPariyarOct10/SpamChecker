import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class SpamChecker {

    public static final String SpamHaus = "sbl.spamhaus.org";
    public static void main(String [] args)
    {
        String redText = "\u001B[31m"; 
        String greenText = "\u001B[32m"; 
        String resetText = "\u001B[0m"; 

        String banner = """
+-------------------------------------------------------------+
|                        %s SpamChecker %s                        |
+-------------------------------------------------------------+
|   SpamChecker is a CLI tool designed to identify            |
|  and flag potential spam IP Addresses by cross-referencing  |
|  them with known spam database (Spamhaus Block List - SBL). |
+-------------------------------------------------------------+
| Developed at Ratna Rajyalaxmi Campus, Tribhuvan University, |
|                      Department of BCA                      |
+-------------------------------------------------------------+
|                  Developed by %s                  |
+-------------------------------------------------------------+
               
                """;

        String result = String.format(banner, greenText, resetText, greenText + "Alen Pariyar" + resetText);
        System.out.println(result);

        System.out.printf("+------------------------------------+%n");
        System.out.printf("| %-20s | %-11s | %n", "IP Address", "Status");
        System.out.printf("+------------------------------------+%n");

        String validateIp = 
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

        for (String string : args) {
            if(Pattern.matches(validateIp, string))
            {
                System.out.printf("| %-20s | %-20s | %n", string,(isSpammer(string)?redText+"Spammer"+resetText:greenText+"Not Spammer"+resetText));
            }else{
                System.out.printf("| %-20s | %-20s | %n", string,redText+"Invalid IP"+resetText);
            }
        }

        System.out.printf("+------------------------------------+%n");

    }

    public static boolean isSpammer(String ip)
    {
        String reverseDns = SpamHaus;

        try{
            InetAddress inetAddress = InetAddress.getByName(ip);

            byte [] byteIp = inetAddress.getAddress();

            for(byte oct : byteIp)
            {
                int octInt = (oct<0)?oct+256:oct;

                reverseDns = octInt+"."+reverseDns;
            }
            
            InetAddress.getByName(reverseDns);
            
            return true;
            
        }catch(UnknownHostException e)
        {
            return false;
        }

    }
}
