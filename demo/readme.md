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

Questions:

ORM (Object-Relational Mapping): ORM is a programming technique that allows developers to map objects from an object-oriented programming language (such as Java) to relational database tables. It simplifies the data manipulation process by eliminating the need to write complex SQL queries and directly interact with the database. ORM frameworks, like JPA (Java Persistence API), handle the mapping between objects and database tables transparently.

JPA (Java Persistence API): JPA is a Java specification for ORM that provides a standard way for Java developers to manage relational data in applications. It allows developers to define entity classes that represent database tables and use annotations to map these classes to the corresponding tables. JPA also provides APIs for performing CRUD (Create, Read, Update, Delete) operations on entities.

application.properties: The application.properties file is commonly used in Spring Boot applications to externalize configuration properties. It contains key-value pairs that configure various aspects of the application, such as database connection settings, logging levels, and server port. This file is typically stored in the src/main/resources directory of the Spring Boot project.

* Frequently Used Annotations for Entity Types:
        @Entity: Marks a class as an entity, which represents a table in the database.
        @Id: Specifies the primary key of the entity.
        @GeneratedValue: Configures the generation strategy for the primary key values.
        @Column: Maps an entity field to a specific column in the database table.
        @OneToMany / @ManyToOne: Defines relationships between entities (one-to-many, many-to-one).
        @JoinColumn: Specifies the column used for joining tables in a relationship.
        @Transient: Marks a field as not persistent, meaning it will not be mapped to the database.

* Key Points to Observe:
        Ensure each entity has a primary key annotated with @Id.
        Use appropriate fetch strategies (LAZY or EAGER) to optimize performance.
        Understand the cascading behavior (cascade attribute) for relationships to manage the persistence of related entities.

* CRUD Operations Methods:
        Create: save() method to persist a new entity.
        Read: findById(), findAll(), or custom query methods to retrieve entities.
        Update: Modify the entity object and use save() method to update it.
        Delete: deleteById() or delete() method to remove entities from the database.

These methods are typically provided by JPA repositories, which are interfaces extending JpaRepository or its subinterfaces.
