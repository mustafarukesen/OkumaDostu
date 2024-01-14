package com.okuma.dostu.backend.business.messages.validation;

public class BookValidationMessages {
    public static final String notNullBookId = "Book id geçersiz olamaz";
    public static final String positiveBookId = "Kitap id'si positif bir değer olmalıdır";

    public static final String notNullDescription = "Açıklama geçersiz olamaz";
    public static final String notBlankDescription = "Açıklama boş olamaz";

    public static final String notNullIsbn13 = "Isbn13 alanı geçersiz olamaz";
    public static final String notBlankIsbn13 = "Isbn13 boş olamaz";

    public static final String notNullPageCount = "Sayfa sayısı geçersiz olamaz";
    public static final String positivePageCount = "Sayfa sayısı pozitif bir değer olmalıdır";

    public static final String notNullPublishedDate = "Yayınlanma tarihi boş olamaz";

    public static final String notNullTitle = "Kitap ismi geçersiz olamaz";
    public static final String notBlankTitle = "Kitap ismi boş olamaz";

    public static final String notNullThumbnail = "Kitap resmi geçersiz olamaz";
    public static final String notBlankThumbnail = "Kitap resmi boş olamaz";

    public static final String notNullAuthorId = "Yazar id'si geçersiz olamaz";
    public static final String positiveAuthorId = "Yazar pozitif bir değer olmalıdır";

    public static final String notNullCategoryId = "Kategori id'si geçersiz olamaz";
    public static final String positiveCategoryId = "Kategori pozitif bir değer olmalıdır";

    public static final String notNullPublisherId = "Yayınevi id'si geçersiz olamaz";
    public static final String positivePublisherId = "Yayınevi pozitif bir değer olmalıdır";
}
