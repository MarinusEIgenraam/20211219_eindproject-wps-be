## Models

- **elements**
    - [Category](./src/main/java/com/willpowered/eindprojectwpsbe/model/elements/Category.java)
    - [Project](./src/main/java/com/willpowered/eindprojectwpsbe/model/elements/Project.java)
    - [Task](./src/main/java/com/willpowered/eindprojectwpsbe/model/elements/Task.java)

## The process

**Lombok**
Ik ben de annotaties van Lombok gaan gebruiken om het bouw process wat te versnellen. De verschillende annotaties zorgen
ervoor dat je herhalende code niet hoeft uit te schrijven. Zo heeft elke entity getters, setters en constructors nodig
die er structureel steeds hetzelfde uit zien.

**Mapstruct**
Ik heb Mapstruct toegevoegd voor om bijvoorbeeld een gebruikersnaam aan een comment of project mee te geven zonder het
hele project mee te hoeven geven. Hiervoor had ik op de normale weg een extra DTO moeten maken. Ook maak ik gebruik van
de “Abstract Class” functionaliteit van Mapstruct om aantallen te berekenen.

- @Mapper = initialiseert mapstruct
- @Mapping = geeft aan wat er met een value gebeurt als deze van dto naar entiteit (of andersom) wordt omgezet
- @InheritInverseConfiguration: geeft aan dat het mappen van entiteit attributen op dezelfde manier, maar andersom moet
  gebeuren als de data in de tegenovergestelde richting getransporteerd moet worden
