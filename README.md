# exportar la variable del host de Postgres
        export DATABASE_HOST=localhost
        export CLIENTES_BASEURL=http://customers:8080/
        export SECURITY_BASEURL=http://authenticator:8090/
        export ORDERS_BASEURL=http://orders:9000/ 


# Levantamos el Docker Compose
        ./up.sh

# Validanmos con
        docker-compose ps

#Asegurarnos que corren todos los contenedores (Up)

# Generamos los tags de la migración
        java -jar ./build/libs/service.jar db tag ./src/main/resources/config.yaml 2018_08_22_21_10

# Aplicamos la migración a la base de datos
        java -jar ./build/libs/service.jar db migrate ./src/main/resources/config.yaml


# ============== Comandos utilitarios =================

# Usar el cliente de Postgres, el password es c7
        psql -h localhost -U user_user -W users
        psql -h localhost -U customer_user -W customers

# Listar volumenes de datos que Docker gestiona
        docker volume ls

# Limpiar los volumenes (si deseo borrarlos, no se recuperan)
        docker volume rm id_del_volumen

# Carga del API (HTTPie)
        http :8090/v1/users username=domix password=secreto
        http :8090/v1/users username=admin password=admin123
        http :8090/v1/users username=clodoaldo password=clave

        http :8080/v1/customers firstName=clodoaldo lastName=sanchez businessName=empresa  email=clodoaldosanchez@gmail.com  taxId=10416659651 --auth clodoaldo:clave
        http :8080/v1/customers firstName=cesar  lastName=mendieta businessName=empresa2  email=cesarmendieta@gmail.com  taxId=10111111111 --auth admin:admin123

        http :8080/v1/customers/1/addresses street="las camelias" name="almacen 1" country="Peru" customerId=1 --auth admin:admin123
        http :8080/v1/customers/1/addresses street="andres reyes" name="almacen 2" country="Peru" customerId=1 --auth clodoaldo:clave
        http :8080/v1/customers/1/addresses street="nicolas arriola" name="almacen 3" country="Peru" customerId=1 --auth admin:admin123

        http :8080/v1/customers/2/addresses street="buenos aires" name="store 1" country="Peru" customerId=2 --auth admin:admin123
        http :8080/v1/customers/2/addresses street="los lirios" name="store 2" country="Peru" customerId=2 --auth admin:admin123
        http :8080/v1/customers/2/addresses street="avenida larco" name="store principal" country="Peru" customerId=2 --auth admin:admin123

        http :8080/v1/orders createdAt=2018-08-22 amount=1000 customerId=1 deliveryAddressId=2 --auth domix:secreto
        http :8080/v1/orders createdAt=2018-08-22 amount=1000 customerId=1 deliveryAddressId=100 --auth domix:secreto



