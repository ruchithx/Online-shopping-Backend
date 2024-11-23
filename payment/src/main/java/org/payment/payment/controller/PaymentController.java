package org.payment.payment.controller;
//import org.payment.payment.HashRequest;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @PostMapping("/generate-hash")
    public String generateHash(@RequestBody  HashRequest hashRequest) {
        try {
            String dataToHash = hashRequest.getMerchantId() +
                    hashRequest.getOrderId() +
                    hashRequest.getAmount() +
                    hashRequest.getCurrency() +
                    hashRequest.getMerchantSecret();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dataToHash.getBytes());
            byte[] digest = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b).toUpperCase();
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash: " + e.getMessage());
        }
    }
}
