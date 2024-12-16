Описание проекта:
- Java 17
- Maven pom.xml version 1.0
Используемые сторонние библиотеки:
- org.projectlombok v 1.18.30
- apache commons-cli v 1.5.0

Руководство к запуску:
После сборки jar архива запускать необхоидмо этот файйл: CFT_shift-1.0-SNAPSHOT-jar-with-dependencies.jar 
Для того чтобы создать jar файл необходимо из директории проекта выполнить команду:
mvn clean package
После этого необходимо вставить в папку /target/ исходные файлы .txt для обработки их содержимого.
Для примера файлы с тестовыми данными располагаются по данному пути: /src/main/resources/InputData/.
Пример запуска из командной строки:
java -jar CFT_shift-1.0-SNAPSHOT-jar-with-dependencies.jar -s -f -p sample_ -o some/path in1.txt in2.txt
