package com.spring.boot.api.auth;

public class JwtConfig {
    public static final String SECRET_KEY = "testing.1234";

    public static final String RSA_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\r\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuFDhVKA3GzIzC7Xm4+R\r\n" +
            "IKU44u36FRelS/+9f7fFLTNDDspV6UPNtkaDYj7ZzII2+dZ+WUFMXqb8nrO0XzaX\r\n" +
            "pibl+3wqOg/782wcLBX3eFoMmBMYimXIJQU1VUyxDopDTf//BHNWOEDXquiFTC2f\r\n" +
            "Uj+27lLz81mBRUjTB5DJ/XjQ70QYdNCyhddk0OqQWqTD2h124VoXzTG5Q2wRGZyJ\r\n" +
            "KKqBzxT+z+VyacI2fbeFnopLgmyokgLq+bP20cz8tbWocxFj+rudUxWN8w4x5mG9\r\n" +
            "B7cPREneBwQMVb3X76ocJ5kZb4amrGRbL8RnrWr3Rdp509fIb6Jd+4kIVwkzxZvc\r\n" +
            "yQIDAQAB\r\n" +
            "-----END PUBLIC KEY-----";

    public static final String RSA_PRIVATE_KEY="-----BEGIN RSA PRIVATE KEY-----\r\n" +
            "MIIEowIBAAKCAQEAtuFDhVKA3GzIzC7Xm4+RIKU44u36FRelS/+9f7fFLTNDDspV\r\n" +
            "6UPNtkaDYj7ZzII2+dZ+WUFMXqb8nrO0XzaXpibl+3wqOg/782wcLBX3eFoMmBMY\r\n" +
            "imXIJQU1VUyxDopDTf//BHNWOEDXquiFTC2fUj+27lLz81mBRUjTB5DJ/XjQ70QY\r\n" +
            "dNCyhddk0OqQWqTD2h124VoXzTG5Q2wRGZyJKKqBzxT+z+VyacI2fbeFnopLgmyo\r\n" +
            "kgLq+bP20cz8tbWocxFj+rudUxWN8w4x5mG9B7cPREneBwQMVb3X76ocJ5kZb4am\r\n" +
            "rGRbL8RnrWr3Rdp509fIb6Jd+4kIVwkzxZvcyQIDAQABAoIBAQCTkEZcQIsr0Tdg\r\n" +
            "0fc1VW3ECq1RJrbg6Bh/r1anxI/8fd2BBLC3rPdDObhvwMkbJILZq0J+IJtP0KwM\r\n" +
            "HnLPVQyei8qM1s531Uf2TeiZhgwN95Mr7lSh76jEfmRfdKd1VQkj2gla/Pm4IFIl\r\n" +
            "XMOaCUICKtkfip2/YRxW3IEzHJhvxftwnGd8op4/Wazbptq3ifJMueRMI7Ns2Br7\r\n" +
            "W0dpajMB/POxT3wxfe0WXtGiWoXJWtJm1rPtw1331Tp1QEGwH8P81D5L5Vb5y/Cj\r\n" +
            "0xyYjJAvV6BNixB4gsujSUDkO4TSsQFhPF6wvBn4YHyd624ldlpzQ2sRNHLEz0J0\r\n" +
            "lC3QqfzRAoGBAOrCyQkrgiAf1kd3l3xaoEaCD65Hbsk6Hd8v1lr4Zgw9yoVFdW1j\r\n" +
            "u3z/x0mbm+MfQnshsNh2+f/I1o8wJjBLA0P4vxW46/A6Yj7+YAkDa3JZ4FgJMYbl\r\n" +
            "Ifzs+FEib/RmEuWiZAlCHXvkq5ywn4xTpCoeLPDHbraUYh4nZIa9kTjFAoGBAMds\r\n" +
            "4c5Mv5d/Ca/pDpNLNeI7sub9CPXktHRucMZ5gYW2eH7x2vNszWZPlRRBNbQDQmSm\r\n" +
            "xMtQx2G7n4ejYZYQe0WLoI5SreLdjsBgyYTzW6P7hiwEBXv/wxEoD8rZnkhayIUI\r\n" +
            "eSiq7ONZC+uq4PrBUKbonEYpLfm5UHbc+qfxuGw1AoGAfKHmquifT8C3OP/ju5A9\r\n" +
            "cNFR09t2lHw3Kasy6N7hBnSzlOdYPTfqbKU6PvjO3NhWqhor0e/wL3yHdENCsvbQ\r\n" +
            "CAECKV0gXUwRppHTmFVpcK8IbJSM56mzzpxcBG788spSE/mPlF62WnT/KtOWEKDD\r\n" +
            "Ed0tTLMEMwg5wvp04xe3BmUCgYA6e3jAugsZj1y05KCsm0rC/ITbbmr5SUeOOXED\r\n" +
            "E1DjeH5fo+d0qXyWu1HZrcHk92u5/poJkbfWtmHzMfuFOXIdE/RSqHZr6FNboUaX\r\n" +
            "oZJHRZS0Rb2h/oAjAN+YujbLuyvAr5pwTafIiC2Rv/vYLWbMe7yQarUb3bd2FBnB\r\n" +
            "tIZD3QKBgA9Kk77kwko7KFDz8aTMMWa4SNUb8sO16ldIsNacmrr7SsSQpHAQytn4\r\n" +
            "EugE/sTbj8joEKzo99eFi512xnlirmlNpYjh8m+SYD1uxZeEs5OqKKp9W/viYop6\r\n" +
            "lMTJR1phM1ZxQsVMJT1Kg+CCgyw6ZK/x2GBM2gWWMlfSRe3vrG4t\r\n" +
            "-----END RSA PRIVATE KEY-----";
}
