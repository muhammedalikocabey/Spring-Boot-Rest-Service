# EN

## Spring Boot Rest service for parity data
### Spring Boot REST service, which contains parity data where TL equivalents of currencies are kept together with the Parity code and the day it belongs, and enables certain operations on this parity data.

------------------

- A method was used to calculate the ratio of two currency units to each other, and this method was included in the Controller Class with the **Dependency Injection Pattern.**
- For yield change method calculation, 3 different functions were defined and these functions were implemented and used with the **Strategy Pattern.**
- The **Factory Pattern** was used to select this 3 yield change method calculation more effectively than the request parameter to the service.
- When unwanted situations occur in the requests sent by the user, the control of these situations was made with **Controller Advice Annotation** as **Global Exception Handling**. Each undesirable situation that occurred was handled separately.
- Factory Pattern changed to be applied with Enum data type.


&nbsp;


##### Requests within the service:

```
localhost : 127.0.0.1
```

- GET:
  - ```localhost:8080/``` :arrow_right: List All Data
  - ```localhost:8080/2020-11-21/``` :arrow_right: List data on the requested date
  - ```localhost:8080/2020-11-21/USDTRY``` :arrow_right: List the data in the requested date and parity code
  - ```localhost:8080/2020-11-21/2020-11-25/USDTRY``` :arrow_right: List data between two requested dates and in the parity code
  - ```localhost:8080/2020-11-21/2020-11-25/USDTRY/absolute``` :arrow_right: Calculate and list the data between the two requested dates and in the parity code according to the desired return exchange method.
- POST:
  - ```localhost:8080/``` :arrow_right: Check the data sent without distinguishing the day and save all to the database
  - ```localhost:8080/2020-11-21``` :arrow_right: Check the data of the day sent according to its parameter and save all to the database

------------------



&nbsp;
&nbsp;



# TR 

## Parity verileri için Spring Boot Rest servisi
### Para birimlerinin TL karşılıklarının, Parite kodu ve ait olduğu gün ile birlikte tutulduğu parite verilerini içeren Spring Boot REST hizmeti, bu parite verileri üzerinde belirli işlemlerin yapılmasını sağlar.

------------------

- İki para biriminin birbirine oranını hesaplamak için bir metod kullanıldı ve bu yöntem **Bağımlılık Enjeksiyoni Pattern**'i ile Controller Sınıfına dahil edildi. 
- Getiri değişim yöntemi hesaplaması için 3 farklı fonksiyon tanımlandı ve bu fonksiyonlar **Strateji Pattern**'i ile uygulandı ve kullanıldı.
- Bu 3 getiri değişim metodu hesaplamasını hizmet içerisinde request parametresinden daha etkin bir şekilde seçmek için **Fabrika Pattern**'i kullanıldı.
- Kullanıcı tarafından gönderilen request'lerde istenmeyen durumlar oluştuğunda bu durumların kontrolü **Global Exception Handler** ile ve **Global Advice Annotation** ile yapıldı. Ortaya çıkan her istenmeyen durum ayrı ayrı ele alındı.
- Factory Pattern'i Enum veri tipi ile uygulanacak şekilde değiştirildi.  


&nbsp;


##### Servis içerisindeki istekler:

```
localhost : 127.0.0.1
```

- GET:
  - ```localhost:8080/``` :arrow_right: Tüm verileri Listele
  - ```localhost:8080/2020-11-21/``` :arrow_right: İstenen tarihteki verileri listele
  - ```localhost:8080/2020-11-21/USDTRY``` :arrow_right: İstenen tarihteki ve parite kodundaki verileri listele
  - ```localhost:8080/2020-11-21/2020-11-25/USDTRY``` :arrow_right: İstenen iki tarih arasındaki ve parite kodundaki verileri listele
  - ```localhost:8080/2020-11-21/2020-11-25/USDTRY/absolute``` :arrow_right: İstenen iki tarih arasındaki ve parite kodundaki verileri istenen getiri değişim metoduna göre hesapla ve listele
- POST:
  - ```localhost:8080/``` :arrow_right: Gönderilen verileri gün ayırt etmeden kontrol edip veri tabanına kaydet
  - ```localhost:8080/2020-11-21``` :arrow_right: Gönderilen güne ait verileri parametresine göre kontrol edip veri tabanına kaydet

------------------
