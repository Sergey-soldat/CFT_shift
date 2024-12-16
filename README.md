Описание проекта:
- Java 17
- Maven pom.xml v 3.9.9

Используемые сторонние библиотеки:
- org.projectlombok v 1.18.30
- apache commons-cli v 1.8.0
- slf4j v 1.7.36

Плагин для сборки jar архива:
- maven-assembly-plugin v 3.6.0

Руководство к запуску:
Запуск приложения осуществляется из директории CFT_shift/target.
Там находятся два jar архива:
1) CFT_shift-1.0-SNAPSHOT.jar
2) CFT_shift-1.0-SNAPSHOT-jar-with-dependencies.jar
Второй jar архив самодостаточный с зависимостями, которые необходимы для запуска приложения.
Также в папке CFT_shift/target находятся два тестовых файла:
1) In1.txt
2) In2.txt
Эти файлы содержат тесовые данные, которые можно обработать приложением.
Пример корректного ввода для запуска приложения:
   java -jar CFT_shift-1.0-SNAPSHOT-jar-with-dependencies.jar -s -f -p sample_ -o some/path in1.txt in2.txt

В приложение была расширина функциональность.
Добавлена распознавание опции -v, которая активирует нестрогую обработку опций.
При вводе набора добавленных и недобавленных опций, например:
   -s -f -p sample_ -o some/path -w -z -x -v
и вводе опции -v будет происходить корректная обработка переданных данных.
После обработки данных в консоль  будет возвращён список нераспознанных опций:
   -w -z -x
При вводе: 
-s -f -p sample_ -o some/path -w -z -x
приложение выбросит исключение и вернёт в консоль список нераспознаннх опций без обработки данных.

Для сборки jar архива введите команду:
 mvn clean package
находясь в корневой директории CFT_shift.
После сборки jar архива нужно поместить файлы .txt с данными,
которые хотим обработать в директорию CFT_SHft.
Всё готово для запуска приложения. 
