Pojekt: Lagerverwaltungssystem

Besprochene Probleme:

1. Verwendung von Listen als Attributtypen: Ursprünglich wurden `ArrayList<String>` und `ArrayList<Integer>` in der Klasse `Warehouse` verwendet, um Artikel und ihre Preise zu speichern. Dies führte zu Fehlern beim Einsatz von JPA, da JPA einfache Attributtypen erwartet, die keine Container sind.

2. Mangelnde Strukturierung der Daten: Das ursprüngliche Design des `Warehouse`-Objekts ließ keine klare Trennung zwischen verschiedenen Entitäten wie Lager, Artikel und Preisen zu. Dies führte zu einer weniger flexiblen und schwer wartbaren Datenstruktur.

3. Fehlende Verwendung von JPA-Annotationen: Einige Attribute in der `Warehouse`-Klasse wurden nicht mit JPA-Annotationen versehen, was zu Fehlern oder unerwünschtem Verhalten führen kann, insbesondere wenn das Objekt mit einer Datenbank verbunden ist.


Mögliche Probleme:

1. Datenkonsistenz: Ohne eine klare Strukturierung der Daten und eine angemessene Verwendung von JPA-Annotationen besteht das Risiko inkonsistenter Daten, insbesondere wenn das System in einer Datenbank persistiert wird.

2. Performanzprobleme: Wenn große Datenmengen verarbeitet werden müssen, könnten ineffiziente Datenstrukturen oder Datenbankabfragen zu Leistungsproblemen führen.

3. Anforderungsänderungen: Da Anforderungen an das Lagerverwaltungssystem möglicherweise nicht vollständig spezifiziert wurden, könnten während der Entwicklung neue Anforderungen auftreten, die das Design und die Implementierung beeinflussen.


Um diese Probleme anzugehen, wurde das Projekt umstrukturiert, um eine klarere Entitätsstruktur zu ermöglichen und die Verwendung von JPA-Annotationen zu optimieren. Durch die Verwendung von Entitätsklassen für Lager, Artikel und Preise sowie die Definition von Beziehungen zwischen diesen Klassen kann das Lagerverwaltungssystem flexibler, wartbarer und besser an zukünftige Anforderungen anpassbar sein.

EKV:
Dokumentation: Umstellung von MySQL auf PostgreSQL
Änderungen in Gradle

Um von MySQL zu PostgreSQL zu wechseln, mussten wir unsere Gradle-Konfiguration aktualisieren. Wir haben die MySQL-Bibliothek entfernt und stattdessen die PostgreSQL-Bibliothek hinzugefügt. Hier ist der Code, den wir in unserer build.gradle Datei geändert haben:

Von:

```groovy

dependencies {
runtimeOnly 'mysql:mysql-connector-java'
}
```
Zu:

```groovy

dependencies {
runtimeOnly 'org.postgresql:postgresql'
}
```
Änderungen in der application.properties

Auch unsere application.properties Datei mussten wir anpassen, um die Verbindungseinstellungen auf PostgreSQL umzustellen.

Von:

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/deineDatenbank
spring.datasource.username=root
spring.datasource.password=deinPasswort
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
Zu:

```properties

spring.datasource.url=jdbc:postgresql://localhost:5432/deineDatenbank
spring.datasource.username=deinBenutzer
spring.datasource.password=deinPasswort
spring.datasource.driver-class-name=org.postgresql.Driver
```
Vorteile der Umstellung

Die Umstellung auf Java-Funktionen anstelle von direkten Datenbankoperationen hat das Schreiben von Code einfacher gemacht. Java-Methoden sind übersichtlicher und einfacher zu verwalten als komplexe SQL-Queries. Dies erleichtert das Verständnis und die Wartung des Codes, besonders für diejenigen, die noch lernen.
Zusammenfassung

Diese Änderungen haben nicht nur die Leistung unserer Anwendung verbessert, sondern auch den Entwicklungsprozess vereinfacht. Dadurch, dass wir Java-Methoden verwenden, können wir effizienter arbeiten und Fehler schneller beheben.