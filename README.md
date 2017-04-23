CREATE SCHEMA `schedule` ;

mvn flyway:clean -Dflyway.url=jdbc:mysql://localhost:3306/schedule -Dflyway.user=root -Dflyway.password=