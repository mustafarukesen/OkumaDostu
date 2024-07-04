import 'package:books_app/models/category.dart';
import 'package:flutter/cupertino.dart';

class Categories with ChangeNotifier {
  List<Category> categoriesList = [
    Category(
        categoryTitle: 'Kod',
        categoryLink: 'Code',
        iconLink: 'images/code.png'),
    Category(
        categoryTitle: 'Sanat',
        categoryLink: 'Art and Literature',
        iconLink: 'images/literature.png'),
    Category(
        categoryTitle: 'Biyografi /\n Otobiyografi',
        categoryLink: 'Biography Autobiography',
        iconLink: 'images/biography.png'),
    Category(
        categoryTitle: 'Aşçılık',
        categoryLink: 'cooking',
        iconLink: 'images/cooking.png'),
    Category(
        categoryTitle: 'Drama',
        categoryLink: 'drama',
        iconLink: 'images/drama.png'),
    Category(
        categoryTitle: 'Eğitim',
        categoryLink: 'Education',
        iconLink: 'images/education.png'),
    Category(
        categoryTitle: 'Fantezi',
        categoryLink: 'fantasy',
        iconLink: 'images/fantasy.png'),
    Category(
        categoryTitle: 'Kurgusal',
        categoryLink: 'fiction',
        iconLink: 'images/fiction.png'),
    Category(
        categoryTitle: 'Tarih',
        categoryLink: 'historical',
        iconLink: 'images/history.png'),
    Category(
        categoryTitle: 'Korku',
        categoryLink: 'horror',
        iconLink: 'images/horror.png'),
    Category(
        categoryTitle: 'Komedi',
        categoryLink: 'humor',
        iconLink: 'images/humor.png'),
    Category(
        categoryTitle: 'Dini',
        categoryLink: 'religious',
        iconLink: 'images/religious.png'),
    Category(
        categoryTitle: 'Spor',
        categoryLink: 'sports',
        iconLink: 'images/sports.png'),
    Category(
        categoryTitle: 'Gerilim',
        categoryLink: 'suspense',
        iconLink: 'images/suspense.png'),
    Category(
        categoryTitle: 'Heyecanlı',
        categoryLink: 'thriller',
        iconLink: 'images/thriller.png'),
    Category(
        categoryTitle: 'Seyehat /\n Fotoğraf',
        categoryLink: 'Travel and Photography',
        iconLink: 'images/travel.png'),
  ];
}
