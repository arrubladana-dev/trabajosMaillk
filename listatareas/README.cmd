11.	¿Cuál es la diferencia entre @RestController y @Controller en Spring Boot?

R/ La direfencia principal es que el RestController esta diseñada para apis y el controller esta diseñada 
para cuando el html esta dentro del proyecto 

12.	¿Por qué se usan DTOs en lugar de exponer directamente la entidad JPA?

T/ Los DTO se usann para proteger datos sencibles de la entidad 

13.	¿Qué ventaja tiene @PrePersist sobre asignar la fecha en el constructor de la entidad?

R/ La ventaja que tiene es que el @PrePersist le asigna la fecha cada que se crea 
la enteidad sin necesidad de uno ponerla manualmente 

14.	¿Qué diferencia hay entre spring.jpa.hibernate.ddl-auto=update y ddl-auto=create? ¿Cuál usarías en producción y por qué?

R/ La diferencia esta en que el auto create borra los datos ya existentes cada vez que se reinicia el programa
y el update actualiza los campos cada que la entidad se actualiza

15.	Si esta API fuera a producción con usuarios reales, menciona al menos 3 cambios que harías en la configuración o arquitectura.

R/ Los cambios que le haria a la api serian que añadiria una extencion llamada sprint Segurity para añadirli
mas seguridad a la api, tambien la base de dados la pondria como no relacional para optimizar la base de datos 
ya que considero que que para una lista de tareas no secitaria ser relacional