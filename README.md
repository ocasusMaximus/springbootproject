# springbootproject
## Návod na spuštění
1. Vyklonovat repozitář
2. Po vyklonování stačí v adresáři v terminálu zadat příkaz gradlew bootRun.
3. Aplikace poté běží lokálně na URl localhost:8080
## Změna ukládání HSQLDB( např. při použití jiného operačního systému, než Microsoft Windows)
 1. V souboru application.properties je nutné na řádku spring.datasource.url=jdbc:hsqldb:file:C:/db/ je potřeba za file: dosadit vlastní cestu k ukládání do souboru
 2. Cestu je nejlepší zadat  na konci se složkou, která nemusí do té doby existovat, např. C:/db/ vytvoří na disku C: složku db do které se HSQLDB uloží.
 3. Poté je nutné tuto samou cestu zadat u všech aplikací.
 4. HSQLDB si automaticky zkontroluje jestli už náhodou složka s db neexistuje a pokud ano pouze se na ní napojí. V opačném případě vytvoří novou složku s novou databázi.
