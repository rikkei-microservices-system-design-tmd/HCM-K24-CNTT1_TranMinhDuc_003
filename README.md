HE THONG MICROSERVICES QUAN LY THUOC VA DANH MUC

1. THU TU KHOI CHAY HE THONG

De he thong hoat dong chinh xac va dong bo cau hinh, can khoi chay cac service theo dung thu tu sau:

Buoc 1: Chay config-server (Cong 8888)
- Thu muc: config-server
- File chay: ConfigServerApplication.java
- Vai tro: Cung cap cau hinh tap trung (port, datasource H2, Eureka client) cho category-service va medicine-service.

Buoc 2: Chay eureka-server (Cong 8761)
- Thu muc: eureka-server
- File chay: EurekaServerApplication.java
- Vai tro: Dang ky va kham pha dich vu (Service Discovery). Dashboard quan ly tai: http://localhost:8761

Buoc 3: Chay category-service (Cong 8081)
- Thu muc: category-service
- File chay: CategoryServiceApplication.java
- Vai tro: Quan ly danh muc thuoc (Category), lay cau hinh tu config-server va dang ky vao eureka-server.
- Du lieu mau khoi tao tu dong khi chay:
  + ID 1: Khang sinh
  + ID 2: Giam dau ha sot

Buoc 4: Chay medicine-service (Cong 8082)
- Thu muc: medicine-service
- File chay: MedicineServiceApplication.java
- Vai tro: Quan ly thuoc (Medicine), lay cau hinh tu config-server, dang ky vao eureka-server va su dung OpenFeign de goi xac thuc category-service theo categoryId.

Buoc 5: Chay api-gateway (Cong 8080)
- Thu muc: api-gateway
- File chay: ApiGatewayApplication.java
- Vai tro: Cong duy nhat tiep nhan moi request tu client, dieu huong qua Load Balancer (lb://category-service va lb://medicine-service).


2. DANH SACH API KIEM THU QUA CONG 8080

Moi yeu cau cua client phai duoc goi thong qua API Gateway tai cong 8080.

Kich ban 1: Them moi thuoc thanh cong (voi categoryId hop le)
- Endpoint: POST http://localhost:8080/api/medicines
- Headers: Content-Type: application/json
- Request Body:
```json
{
  "name": "Panadol Extra",
  "price": 45000.0,
  "quantity": 100,
  "categoryId": 1,
  "description": "Giam dau nhanh chong"
}
```
- Ket qua ky vong:
  + HTTP Status: 201 Created
  + Response Body chua thong tin thuoc da duoc luu kem ID tu tang.

Kich ban 2: Them moi thuoc that bai (voi categoryId khong ton tai)
- Endpoint: POST http://localhost:8080/api/medicines
- Headers: Content-Type: application/json
- Request Body:
```json
{
  "name": "Thuoc Khong Hop Le",
  "price": 100000.0,
  "quantity": 10,
  "categoryId": 999,
  "description": "Kiem thu danh muc khong ton tai"
}
```
- Ket qua ky vong:
  + HTTP Status: 400 Bad Request
  + Response Body thong bao loi ro rang (Category not found with id: 999), thuoc khong duoc luu vao database.

Kich ban 3: Lay danh sach thuoc thanh cong
- Endpoint: GET http://localhost:8080/api/medicines
- Ket qua ky vong:
  + HTTP Status: 200 OK
  + Response Body tra ve danh sach cac loai thuoc da duoc luu trong he thong.

API bo sung kiem tra danh muc theo ID:
- Endpoint: GET http://localhost:8080/api/categories/1
- Ket qua ky vong:
  + HTTP Status: 200 OK
  + Response Body: Thong tin danh muc ID 1 (Khang sinh).
