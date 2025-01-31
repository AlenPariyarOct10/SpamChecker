# SpamChecker 

>SpamChecker is a Java based CLI tool designed to identify and flag potential spam IP addresses by cross-referencing them with known spam database (Spamhaus Block List - SBL). 

>Developed at Ratna Rajyalaxmi Campus, Tribhuvan University for BCA, 6th Semester - Network Programming. 

`Developed by Alen Pariyar`
![image](https://github.com/user-attachments/assets/1bccc28e-fb80-4d4e-9b9e-6526e60bc3b3)



## How it works ?
* Takes IP addresses as command line arguments
`java SpamChecker 192.168.1.1 127.0.0.2 2222.4444.6666.8882 192.10.0.1`

* Checks for invalid IP Addresses
```java
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
```

* Checks each IP Address in `SpamHaus Blocklist - SBL`
[Know more about SpamHaus Blocklist](https://www.spamhaus.org/blocklists/spamhaus-blocklist/)

```java
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
```

* Gives result as
  
  ![image](https://github.com/user-attachments/assets/51e96861-2bb8-4396-85af-d75569ba54ca)


