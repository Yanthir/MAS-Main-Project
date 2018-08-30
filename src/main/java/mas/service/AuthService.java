package mas.service;

import mas.mapper.AuthMapper;
import mas.model.business.Client;
import mas.model.business.Employee;
import mas.model.dto.AuthDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {

    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getClientIdForAuth(String login, String password) {
        if(login == null || password == null) {
            return null;
        }
        AuthDTO authDTO = AuthMapper.selectAuthByLogin(login);
        if(authDTO == null || !digestHash(password).equals(authDTO.getHash())) {
            return null;
        }
        if(Client.getById(Client.class, authDTO.getId()) != null) {
            return authDTO.getId();
        }
        return null;
    }

    public static String getEmployeeIdForAuth(String login, String password) {
        if(login == null || password == null) {
            return null;
        }
        AuthDTO authDTO = AuthMapper.selectAuthByLogin(login);
        if(authDTO == null || !digestHash(password).equals(authDTO.getHash())) {
            return null;
        }
        if(Employee.getById(Employee.class, authDTO.getId()) != null) {
            return authDTO.getId();
        }
        return null;
    }

    private static String digestHash(String plaintext) {
        byte[] bytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] digestedBytes = digest.digest(bytes);
        return bytesToHex(digestedBytes);
    }

    private static String bytesToHex(byte[] digestedBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte digestedByte : digestedBytes) {
            String hex = Integer.toHexString(0xff & digestedByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
