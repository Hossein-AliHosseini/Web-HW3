# سرور نقاشی لوپی

این پروژه یک Web Server ساده بر پایه Spring Boot می‌باشد که قادر است داده‌های کاربر را به‌صورت RESTfull دخیره و بازیابی کند.

---

##  ویژگی‌های اصلی

- **ثبت کاربران پیش‌فرض:** کاربران توسط فایل داده‌ای اضافه می‌شوند و ورود کاربران آنلاین نیست.
- **امکان ذخیره و بازیابی نقاشی:** هر کاربر فقط یک نقاشی می‌تواند ثبت و دریافت کند.
- **API کاملاً RESTful:** برای استفاده ساده توسط کلاینت‌های وب، موبایل و حتی curl.
- **پایگاه داده H2:** اطلاعات موقتی در حافظه ذخیره می‌شود؛ قابل مشاهده با کنسول تحت وب H2.
- **ذخیره نقاشی به صورت JSON:** اطلاعات مربوط به خطوط نقاشی (یا مختصات و ...) به صورت رشته‌ی JSON ذخیره می‌شود.

---

##  ساختار پوشه‌ها

```
draw-backend/
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── loupiart
│   │   │           ├── controller
│   │   │           │   └── DrawingController.java
│   │   │           ├── DrawBackendApplication.java
│   │   │           ├── model
│   │   │           │   ├── Drawing.java
│   │   │           │   └── User.java
│   │   │           └── repository
│   │   │               ├── DrawingRepository.java
│   │   │               └── UserRepository.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── loupiart
```

---

##  نحوه اجرا

1. با دستور زیر پروژه را اجرا کنید:
```sh
mvn spring-boot:run
```
2. سرور روی `localhost:8080` اجرا خواهد شد.
43. برای تست کنسول H2 از آدرس:
```
http://localhost:8080/h2-console
```
استفاده کنید (jdbc url=jdbc:h2:mem:drawdb).

---

##  تعامل با دیتابیس

- جدول کاربران (`users`) و نقاشی‌ها (`drawings`) در هنگام اجرا خودکار با `schema.sql` ساخته می‌شوند.
- `data.sql` کاربران پیش‌فرض (مثلاً ali و zahra) و یک نمونه نقاشی برای ali را وارد می‌کند.
- هر نقاشی (فیلد content) شامل یک رشته JSON از داده‌ی ترسیم است و به کاربر مرتبط می‌شود.
- هر کاربر فقط یک نقاشی می‌تواند داشته باشد (در صورت ثبت مجدد نقاشی قبلی حذف یا ویرایش می‌شود).

---

##  مثال‌ از فراخوانی API

### ثبت یا به‌روزرسانی نقاشی برای کاربر
```bash
curl -X POST http://localhost:8080/api/drawing?ali   -H "Content-Type: application/json"   -d '{"content":"{"lines":[[0,0],[10,10]]}"}'
```

### دریافت نقاشی کاربر
```bash
curl http://localhost:8080/api/drawing?ali
```

---

##  توضیحات فنی

- کلاس `User` معرف کاربر (فیلد username بعنوان کلید اصلی).
- کلاس `Drawing` شامل id، رشته content (JSON نقاشی)، و ارتباط به کلاس User (کلید خارجی).
- ریپازیتوری‌ها (`UserRepository` و `DrawingRepository`) مدیریت دسترسی‌های دیتابیس را انجام می‌دهند.
- `DrawingController` دو endpoint اصلی دارد:
  - POST `/api/drawing?{username}`: ثبت یا به‌روزرسانی نقاشی
  - GET `/api/drawing?{username}`: دریافت نقاشی براساس نام کاربری
- همه داده‌های اولیه در `schema.sql` و `data.sql` موجود است.


## استفاده از هوش مصنوعی
برای Init. کردن اولیه پروژه از ChatGPT و سایت [Spring Initializr](https://start.spring.io/) استفاده شده و اسم `Loupi` و `LoupiArt` به همین دلیل در پروژه وجود دارند.
---
