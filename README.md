# EN
## Spring Boot Rest service for parity data
### Spring Boot REST service, which contains parity data where TL equivalents of currencies are kept together with the Parity code and the day it belongs, and enables certain operations on this parity data.

- A method was used to calculate the ratio of two currency units to each other, and this method was included in the Controller Class with the **Dependency Injection Pattern.**
- For yield change method calculation, 3 different functions were defined and these functions were implemented and used with the **Strategy Pattern.**
- The **Factory Pattern** was used to select this 3 yield change method calculation more effectively than the request parameter to the service.
- When unwanted situations occur in the requests sent by the user, the control of these situations was made with **Controller Advice Annotation** as **Global Exception Handling**. Each undesirable situation that occurred was handled separately.





# TR 
## Parity verileri için Spring Boot Rest servisi
### Para birimlerinin TL karşılıklarının, Parite kodu ve ait olduğu gün ile birlikte tutulduğu parite verilerini içeren Spring Boot REST hizmeti, bu parite verileri üzerinde belirli işlemlerin yapılmasını sağlar.

- İki para biriminin birbirine oranını hesaplamak için bir metod kullanıldı ve bu yöntem **Bağımlılık Enjeksiyoni Pattern**'i ile Controller Sınıfına dahil edildi. 
- Getiri değişim yöntemi hesaplaması için 3 farklı fonksiyon tanımlandı ve bu fonksiyonlar **Strateji Pattern**'i ile uygulandı ve kullanıldı.
- Bu 3 getiri değişim metodu hesaplamasını hizmet içerisinde request parametresinden daha etkin bir şekilde seçmek için **Fabrika Pattern**'i kullanıldı.
- Kullanıcı tarafından gönderilen request'lerde istenmeyen durumlar oluştuğunda bu durumların kontrolü **Global Exception Handler** ile ve **Global Advice Annotation** ile yapıldı. Ortaya çıkan her istenmeyen durum ayrı ayrı ele alındı.
