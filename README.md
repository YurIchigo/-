Для реализации приложения выбрала MySQL;
REST API реализовано с помощью Spring Boot;
Тестирование запросов было проведено через Postman;

API по работе с клиентами: 

1. GET /api/v0/pool/clients
<img width="1257" height="751" alt="image" src="https://github.com/user-attachments/assets/d16655a7-044d-437f-9306-698dfeb010ad" />

2. GET /api/v0/pool/clients/{id}
<img width="1278" height="543" alt="image" src="https://github.com/user-attachments/assets/96e1c281-2bd6-4471-9f75-2911a5109cf2" />

3. POST /api/v0/pool/clients
<img width="1285" height="491" alt="image" src="https://github.com/user-attachments/assets/2e385ec3-d347-4ea3-8cd7-ad31ef5d9e2b" />

4. PATCH /api/v0/pool/clients/{id}
<img width="1285" height="642" alt="image" src="https://github.com/user-attachments/assets/00be0876-c326-4393-a237-688bff4d2b49" />

API по работе с записями:

1. GET /api/v0/pool/time-slots/available
<img width="1280" height="973" alt="image" src="https://github.com/user-attachments/assets/5f6615c3-31ce-426b-ad78-52d31fcf1f9b" />

2. GET /api/v0/pool/reservations
   
3. POST /api/v0/pool/reservations
<img width="1257" height="545" alt="image" src="https://github.com/user-attachments/assets/82b44559-1569-4d59-a404-d06a90220198" />

4. POST /api/v0/pool/reservations/{id}/cancel
<img width="1277" height="669" alt="image" src="https://github.com/user-attachments/assets/14fe1355-6900-4f8f-819e-247d95a2cd72" />
