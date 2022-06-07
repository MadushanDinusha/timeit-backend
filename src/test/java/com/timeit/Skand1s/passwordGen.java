package com.timeit.Skand1s;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordGen {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "JithminI@1";
        String encoded = encoder.encode(rawPass);
        System.out.println(encoded);

    }
}
