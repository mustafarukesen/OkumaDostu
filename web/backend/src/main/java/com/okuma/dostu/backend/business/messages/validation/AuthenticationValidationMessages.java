package com.okuma.dostu.backend.business.messages.validation;

public class AuthenticationValidationMessages {
    public static final String notNullFirstName = "Kullanıcı ismi geçersiz olamaz";
    public static final String notBlankFirstName = "Kullanıcı ismi boş olamaz";


    public static final String notNullLastName = "Kullanıcı soy ismi geçersiz olamaz";
    public static final String notBlankLastName = "Kullanıcı soy ismi boş olamaz";

    public static final String notNullEmail = "Email geçersiz olamaz";
    public static final String notBlankEmail = "Email boş olamaz";

    public static final String notNullPassword = "Şifre geçersiz olamaz";
    public static final String notBlankPassword = "Şifre boş olamaz";

    public static final String notNullCurrentPassword = "Şu anki şifre geçersiz olamaz";
    public static final String notBlankCurrentPassword = "Şu anki boş olamaz";

    public static final String notNullNewPassword = "Yeni şifre geçersiz olamaz";
    public static final String notBlankNewPassword = "Yeni şifre boş olamaz";

    public static final String notNullConfirmationPassword = "Onaylama şifresi geçersiz olamaz";
    public static final String notBlankConfirmationPassword = "Onaylama şifresi boş olamaz";
}
