/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import fpw.model.User;

/**
 *
 * @author fran
 */
public class Utility {

    public static void main(String[] args) {
        User usr = new User();
        System.out.println("url valido = " + isUrlValid("22-10-1993")); // no
        System.out.println("data valida = " + isDateValid("ww")); //no
        System.out.println("stringa = " + splitIntoWords("ciao come stai"));
        
        AuthorTokenizer test = new AuthorTokenizer("Francesco, Soru (5)");
        System.out.println("AuthorTokenizer name = " + test.getName());
        System.out.println("AuthorTokenizer surname = " + test.getSurname());
        System.out.println("AuthorTokenizer id = " + test.getId());
        
        //System.out.println(UserFactory.getInstance().getUserByName("").getSurname());
    }

    /* Controllo che la data sia in formato valido in modo molto rustico */
    public static boolean isDateValid(String date) {
        String[] numbers = date.split("-");
        /* Se ci sono lettere in mezzo rendo false */
        if (!hasOnlyDigits(numbers[0]) || !hasOnlyDigits(numbers[1]) || !hasOnlyDigits(numbers[2])) {
            return false;
        }
        /* Impongo la forma YYYY-MM-DD come segnato nel campo date del database */
        return (numbers[0].length() == 4) && (numbers[1].length() == 2 && Integer.parseInt(numbers[1]) < 13) && (numbers[2].length() == 2 && Integer.parseInt(numbers[2]) < 32);
    }

    /* Controllo che nella stringa in ingresso ci siano solo numeri */
    private static boolean hasOnlyDigits(String input) {
        if (input == null || input.length() < 1) {
            return false;
        } else {
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* Controllo che url inserito sia valido */
    public static boolean isUrlValid(String url) {
        if (url.length() < 4) {
            return false;
        }
        return (url.substring(0, 3).equals("www") || url.substring(0, 4).equals("http"));
    }

    /* Controllo che la stringa in input non sia null o vuota */
    public static boolean isStringNullOrEmpty(String str) {
        if(str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    /* Divido la stringa in parole separate da spazio e rendo la prima */
    public static String splitIntoWords(String words) {
        String[] word= words.split("\\s");
        return word[0];
    }

}
