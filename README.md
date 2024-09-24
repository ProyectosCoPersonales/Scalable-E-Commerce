# SCALABLE-E-COMMERCE

![image](https://github.com/user-attachments/assets/8d5e3021-29f5-400a-bfb8-a15f636ffc19)

## TEST

### REGISTER CUSTOMER AND ADMIN 

![image](https://github.com/user-attachments/assets/1f077f92-1042-41d3-941b-1742a70f6746)

### LOGIN (IT IS VERIFIED IF YOU ARE A USER OR ADMIN THROUGH THE TOKEN)

![image](https://github.com/user-attachments/assets/afe7c915-4d19-41f6-bff7-58adaf3312a3)

### ADMIN: ADD PRODUCTS TO THE DATABASE

![image](https://github.com/user-attachments/assets/9aa81dee-383d-4f9c-a0bf-3d8631b71ddf)

### CLIENT: BUYING
### START OF PURCHASE

![image](https://github.com/user-attachments/assets/1f20b1bb-9dd8-44d1-b1ae-b35bf867714b)

### END OF PURCHASE PLEASE NOTE THAT IF THE STOCK IS EXCEEDED, YOUR REQUEST WILL BE REJECTED
### CAN ALSO BE REMOVED

![image](https://github.com/user-attachments/assets/c774754a-7d4c-46b1-accf-c89eecad47bc)

```json
{
    "id": 1,
    "idUser": 1,
    "email": "jandresllg2001@gmail.com",
    "total": 409.44,
    "cartItems": [
        {
            "id": 1,
            "idProduct": 5,
            "nameProduct": "Lápiz Mecánico",
            "quantity": 21,
            "unitPrice": 2.5
        },
        {
            "id": 2,
            "idProduct": 2,
            "nameProduct": "Cuaderno A5",
            "quantity": 1,
            "unitPrice": 3.0
        },
        {
            "id": 3,
            "idProduct": 4,
            "nameProduct": "Taza de Cerámica",
            "quantity": 2,
            "unitPrice": 7.99
        },
        {
            "id": 4,
            "idProduct": 6,
            "nameProduct": "Regla de Plástico",
            "quantity": 10,
            "unitPrice": 1.0
        },
        {
            "id": 5,
            "idProduct": 7,
            "nameProduct": "Set de Rotuladores",
            "quantity": 2,
            "unitPrice": 8.99
        },
        {
            "id": 6,
            "idProduct": 8,
            "nameProduct": "Cargador USB",
            "quantity": 2,
            "unitPrice": 10.0
        },
        {
            "id": 7,
            "idProduct": 10,
            "nameProduct": "Almohadilla Térmica",
            "quantity": 12,
            "unitPrice": 20.0
        },
        {
            "id": 8,
            "idProduct": 15,
            "nameProduct": "Juego de Cuchillos de Cocina",
            "quantity": 2,
            "unitPrice": 24.99
        }
    ]
}
```
### CUSTOMER: IT SHOULD BE GENERATED AUTOMATICALLY BUT HERE I DO IT MANUALLY OK

![image](https://github.com/user-attachments/assets/0b14ccdb-7e72-4393-b5a8-cd01b2b7f272)

```json
{
    "orderId": 1,
    "name": "Juan Andrés",
    "lastName": "López García",
    "email": "jandresllg2001@gmail.com",
    "role": "CLIENT",
    "address": "Av. 12345, Edificio XYZ, Apt. 101",
    "phone": "+241432534",
    "items": [
        {
            "id": 1,
            "idProduct": 5,
            "nameProduct": "Lápiz Mecánico",
            "quantity": 21,
            "unitPrice": 2.5,
            "totalPrice": 52.5
        },
        {
            "id": 2,
            "idProduct": 2,
            "nameProduct": "Cuaderno A5",
            "quantity": 1,
            "unitPrice": 3.0,
            "totalPrice": 3.0
        },
        {
            "id": 3,
            "idProduct": 4,
            "nameProduct": "Taza de Cerámica",
            "quantity": 2,
            "unitPrice": 7.99,
            "totalPrice": 15.98
        },
        {
            "id": 4,
            "idProduct": 6,
            "nameProduct": "Regla de Plástico",
            "quantity": 10,
            "unitPrice": 1.0,
            "totalPrice": 10.0
        },
        {
            "id": 5,
            "idProduct": 7,
            "nameProduct": "Set de Rotuladores",
            "quantity": 2,
            "unitPrice": 8.99,
            "totalPrice": 17.98
        },
        {
            "id": 6,
            "idProduct": 8,
            "nameProduct": "Cargador USB",
            "quantity": 2,
            "unitPrice": 10.0,
            "totalPrice": 20.0
        },
        {
            "id": 7,
            "idProduct": 10,
            "nameProduct": "Almohadilla Térmica",
            "quantity": 12,
            "unitPrice": 20.0,
            "totalPrice": 240.0
        },
        {
            "id": 8,
            "idProduct": 15,
            "nameProduct": "Juego de Cuchillos de Cocina",
            "quantity": 2,
            "unitPrice": 24.99,
            "totalPrice": 49.98
        }
    ],
    "totalAmount": 409.44,
    "orderStatus": "PENDING",
    "orderDate": "2024-09-24T00:38:03.912451144"
}
```

![image](https://github.com/user-attachments/assets/49000bac-3bed-4196-af07-55bb09dcb0d9)

### 

![image](https://github.com/user-attachments/assets/6ee6a615-dd61-47aa-a9e8-135a6f48640e)

