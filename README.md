# Discount Shopping

Requirements
------------

For building and running the application you need:

- [JDK 1.8](https://www.oracle.com/tr/java/technologies/javase/javase8-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [H2](https://www.h2database.com/html/main.html)

Building the project
------------

Clone the project and paste the code below into the terminal.
```sh
mvn spring-boot:run
```
<br>

### Post Request Example

```
http://localhost:8080/cart/add

{
    "deliveryAddress": "Istanbul/TURKEY",
    "gain": null,
    "paymentType": "CARD",
    "entries": [
        {
            "productCode": "QN90E",
            "name": "Samsung QLED",
            "brand": "SAMSUNG",
            "price": 13500.00,
            "productType": "TV",
            "stock": 11
        },
        {
            "productCode": "D3310N",
            "name": "Nikon D3310",
            "brand": "NIKON",
            "price": 5700.00,
            "productType": "CAMERA",
            "stock": 2
        }
    ],
    "customer": {
        "name": "Ümit",
        "lastName": "Çelebi",
        "userName": "ucelebi",
        "phoneNumber": "05555555555",
        "affiliate": true,
        "card": {
            "cardType": "SILVER",
            "cartNumber": "453453455"
        }
    }
}
```

### Get Request Example

```
http://localhost:8080/cart/00310031-0035-0030-0035-003600790039
```

UML DIAGRAM
------------
<img width="893" alt="uml_diagram" src="https://user-images.githubusercontent.com/53602829/162438346-04b830d5-e690-43cc-b877-e2ec04507923.png">