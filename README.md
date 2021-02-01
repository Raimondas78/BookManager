# BookManager
Program to store, access, modify books with data storage in file


This book manager application (back end part only). It is build as maven project, using Java 11.
You should run the BookApplication.class to run the program. Data is saved in txt file, that is 
stored in project directory. File will be created automatically. 
View the Controller class to understand what REST points are created. Basically, there is REST point to add
book, access book from the storage, update book fields and claculate total value based on a given formula 
(I am not providing it here in description).
Program can be tested using i.e. Postman, as front end part isn't built yet.

API end points description:

root "/api"

@GetMapping("books/price/{barcode}") - to get a book price by barcode that corresponds to a unique identifier.

@GetMapping("books/{barcode}") - to get a book by barcode.

@PostMapping("books") - to add a book. In case of testing with Postman, need to provide JSON with 5 or 6 fields under tab Body.

example:
1)
{
    "name": "Book",
    "author": "James",
    "barcode": "55abc555",
    "quantity": 3,
    "price": 35.37,
    "scienceIndex": 8
}
2)
{
    "name": "Book",
    "author": "James",
    "barcode": "55abc555",
    "quantity": 3,
    "price": 35.37,
}

@PatchMapping("books/{barcode}") - to update existing book by providing barcode and any field or fields to be updated.

example:
this JSON should provided in Postman tab Body. String values, that are not updated should typed as null and integer or double values as 0.
{
    "name": null,
    "author": "My version",
    "barcode": "bbbbb",
    "quantity": 0,
    "price": 0
}




