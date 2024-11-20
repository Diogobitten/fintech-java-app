package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.util.CriptografiaUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class TesteCripto {
    public static void main(String[] args) {
        try {
            System.out.println(CriptografiaUtils.criptografar("123456"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
