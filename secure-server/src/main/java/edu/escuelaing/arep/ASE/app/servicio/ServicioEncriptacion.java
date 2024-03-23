package edu.escuelaing.arep.ASE.app.servicio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ServicioEncriptacion {
    
    public String hashString(String originalString) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply hash function to the input string
            byte[] hashBytes = digest.digest(originalString.getBytes());

            // Convert byte array to a string representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                // Convert byte to hex string representation
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the hashed string
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            e.printStackTrace();
            return null; // or throw an exception
        }
    }

}
