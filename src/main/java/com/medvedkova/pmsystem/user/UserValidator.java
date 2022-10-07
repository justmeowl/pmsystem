package com.medvedkova.pmsystem.user;

import com.medvedkova.pmsystem.exception.user.InvalidPasswordException;
import com.medvedkova.pmsystem.exception.user.InvalidUsernameException;

import java.util.Arrays;

public class UserValidator {

    static void validateUsername(String username) {
        if (username == null)
            throw new InvalidUsernameException("Invalid Username!");
        if (!(username.length() >= 5 && username.length() <= 15))
            throw new InvalidUsernameException("Username length should be" +
                    "between 5 to 15 characters!");
        if (!username.matches("[A-Za-z][A-Z-a-z0-9._]+[A-Z-a-z-0-9]"))
            throw new InvalidUsernameException("Usernames can contain letters (a-z), " +
                    "numbers (0-9), and symbols (., _)!");
    }

    static void validatePassword(char[] password, char[] confirmPassword) {
        boolean arePasswordAndConfirmPasswordEmpty = password == null
                || confirmPassword == null;
        if (arePasswordAndConfirmPasswordEmpty)
            throw new InvalidPasswordException("Invalid Password!");

        boolean isLengthCorrect = password.length > 5 && password.length < 15;
        if (!isLengthCorrect)
            throw new InvalidPasswordException("Password length should be" +
                    "between 5 to 15 characters!");

        boolean havePasswordMatch = Arrays.equals(password,confirmPassword);
        if (!havePasswordMatch)
            throw new InvalidPasswordException("Passwords haven't match!");

        if (isPasswordCorrect(password))
            throw new InvalidPasswordException("Password should contains letters (a-z), uppercase letter (A-Z)," +
                    "numbers (0-9), and symbols(!@#$%^&*()_+./`~')!");
    }

    private static boolean isPasswordCorrect(char[] password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        boolean symbol = false;
        for (char c:
             password) {
            if (Character.isUpperCase(c))
                upperCase = true;
            if (Character.isLowerCase(c))
                lowerCase = true;
            if (Character.isDigit(c))
                digit = true;
            else
                symbol = true;
            if (upperCase && lowerCase && digit && symbol)
                return true;
        }
        return false;
    }
}
