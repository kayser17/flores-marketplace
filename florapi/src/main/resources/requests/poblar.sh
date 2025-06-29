#!/bin/bash
export LANG=en_US.UTF-8

BASE_URL="http://localhost:8080"

echo "Poblando la base de datos..."

### CLIENTES
curl -s -X POST "$BASE_URL/clientes" -H "Content-Type: application/json" -d '{"nombre": "Juan Perez", "email": "juan@example.com", "direccion": "Calle 123", "password": "hola"}'
curl -s -X POST "$BASE_URL/clientes" -H "Content-Type: application/json" -d '{"nombre": "Ana Gomez", "email": "ana@example.com", "direccion": "Calle 456", "password": "hola"}'
curl -s -X POST "$BASE_URL/clientes" -H "Content-Type: application/json" -d '{"nombre": "Laura Torres", "email": "laura@example.com", "direccion": "Calle 789", "password": "hola"}'
curl -s -X POST "$BASE_URL/clientes" -H "Content-Type: application/json" -d '{"nombre": "Pedro Nu\u00f1ez", "email": "pedro@example.com", "direccion": "Avenida 321", "password": "hola"}'

### FLORICULTORES
curl -s -X POST "$BASE_URL/floricultores" -H "Content-Type: application/json" -d '{
  "nombre": "Luis Flores",
  "email": "luis@example.com",
  "ubicacion": "Madrid",
  "latitud": 40.4168,
  "longitud": -3.7038,
  "disponibilidad": true,
  "password": "hola"
}'

curl -s -X POST "$BASE_URL/floricultores" -H "Content-Type: application/json" -d '{
  "nombre": "Marta Ramos",
  "email": "marta@example.com",
  "ubicacion": "Barcelona",
  "latitud": 41.3879,
  "longitud": 2.1699,
  "disponibilidad": true,
  "password": "hola"
}'

curl -s -X POST "$BASE_URL/floricultores" -H "Content-Type: application/json" -d '{
  "nombre": "Carlos Verde",
  "email": "carlos@example.com",
  "ubicacion": "Valencia",
  "latitud": 39.4699,
  "longitud": -0.3763,
  "disponibilidad": true,
  "password": "hola"
}'

curl -s -X POST "$BASE_URL/floricultores" -H "Content-Type: application/json" -d '{
  "nombre": "Sofia Azul",
  "email": "sofia@example.com",
  "ubicacion": "Sevilla",
  "latitud": 37.3886,
  "longitud": -5.9823,
  "disponibilidad": true,
  "password": "hola"
}'

### PRODUCTOS (40)
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Rosa Roja", "tipoFlor": "Rosa", "color": "Rojo", "precio": 14.76, "cantidad": 15, "imagen": "/images/rosa.png", "esRamo": false,"ocasion": "San Valentin", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Tulipan Amarillo", "tipoFlor": "Tulipan", "color": "Amarillo", "precio": 17.07, "cantidad": 5, "imagen": "/images/tulipan.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Margarita Blanca", "tipoFlor": "Margarita", "color": "Blanco", "precio": 24.94, "cantidad": 10, "imagen": "/images/margarita.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Girasol Luminoso", "tipoFlor": "Girasol", "color": "Amarillo", "precio": 37.68, "cantidad": 0, "imagen": "/images/girasol.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Lirio Blanco", "tipoFlor": "Lirio", "color": "Blanco", "precio": 19.27, "cantidad": 20, "imagen": "/images/lirio.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Peonia Rosa", "tipoFlor": "Peonia", "color": "Rosa", "precio": 14.92, "cantidad": 3, "imagen": "/images/peonia.png", "esRamo": false, "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Flor Silvestre", "tipoFlor": "Otro tipo", "color": "Multicolor", "precio": 17.66, "cantidad": 7, "imagen": "/images/floresvarias.webp", "esRamo": false,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Rosas", "tipoFlor": "Ramo", "color": "Rojo", "precio": 42.71, "cantidad": 0, "imagen": "/images/ramoRosas.jpg", "esRamo": true,"ocasion": "San Valentin", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Tulipanes", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 40.61, "cantidad": 12, "imagen": "/images/ramoTulipanes.jpg", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "marta@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo Silvestre", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 28.15, "cantidad": 6, "imagen": "/images/floresvarias.webp", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "marta@example.com"}}'

curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Rosa Roja", "tipoFlor": "Rosa", "color": "Rojo", "precio": 15.76, "cantidad": 15, "imagen": "/images/rosa.png", "esRamo": false,"ocasion": "San Valentin", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Tulipan Amarillo", "tipoFlor": "Tulipan", "color": "Amarillo", "precio": 18.07, "cantidad": 5, "imagen": "/images/tulipan.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Margarita Blanca", "tipoFlor": "Margarita", "color": "Blanco", "precio": 25.94, "cantidad": 10, "imagen": "/images/margarita.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Girasol Luminoso", "tipoFlor": "Girasol", "color": "Amarillo", "precio": 38.68, "cantidad": 0, "imagen": "/images/girasol.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Lirio Blanco", "tipoFlor": "Lirio", "color": "Blanco", "precio": 20.27, "cantidad": 20, "imagen": "/images/lirio.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Peonia Rosa", "tipoFlor": "Peonia", "color": "Rosa", "precio": 15.2, "cantidad": 3, "imagen": "/images/peonia.png", "esRamo": false, "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Flor Silvestre", "tipoFlor": "Otro tipo", "color": "Multicolor", "precio": 18.66, "cantidad": 7, "imagen": "/images/floresvarias.webp", "esRamo": false,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Rosas", "tipoFlor": "Ramo", "color": "Rojo", "precio": 44.71, "cantidad": 0, "imagen": "/images/ramoRosas.jpg", "esRamo": true,"ocasion": "San Valentin", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Tulipanes", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 44.61, "cantidad": 12, "imagen": "/images/ramoTulipanes.jpg", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "luis@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo Silvestre", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 29.15, "cantidad": 6, "imagen": "/images/floresvarias.webp", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "luis@example.com"}}'

curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Rosa Roja", "tipoFlor": "Rosa", "color": "Rojo", "precio": 14.76, "cantidad": 15, "imagen": "/images/rosa.png", "esRamo": false,"ocasion": "San Valentin", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Tulipan Amarillo", "tipoFlor": "Tulipan", "color": "Amarillo", "precio": 16.07, "cantidad": 5, "imagen": "/images/tulipan.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Margarita Blanca", "tipoFlor": "Margarita", "color": "Blanco", "precio": 23.94, "cantidad": 10, "imagen": "/images/margarita.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Girasol Luminoso", "tipoFlor": "Girasol", "color": "Amarillo", "precio": 32.68, "cantidad": 0, "imagen": "/images/girasol.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Lirio Blanco", "tipoFlor": "Lirio", "color": "Blanco", "precio": 18.27, "cantidad": 20, "imagen": "/images/lirio.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Peonia Rosa", "tipoFlor": "Peonia", "color": "Rosa", "precio": 12.2, "cantidad": 3, "imagen": "/images/peonia.png", "esRamo": false, "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Flor Silvestre", "tipoFlor": "Otro tipo", "color": "Multicolor", "precio": 16.66, "cantidad": 7, "imagen": "/images/floresvarias.webp", "esRamo": false,"ocasion": "Cumplea\u00f1os" "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Rosas", "tipoFlor": "Ramo", "color": "Rojo", "precio": 40.71, "cantidad": 0, "imagen": "/images/ramoRosas.jpg", "esRamo": true,"ocasion": "San Valentin", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Tulipanes", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 41.61, "cantidad": 12, "imagen": "/images/ramoTulipanes.jpg", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "carlos@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo Silvestre", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 27.15, "cantidad": 6, "imagen": "/images/floresvarias.webp", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "carlos@example.com"}}'

curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Rosa Roja", "tipoFlor": "Rosa", "color": "Rojo", "precio": 13.76, "cantidad": 15, "imagen": "/images/rosa.png", "esRamo": false,"ocasion": "San Valentin", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Tulipan Amarillo", "tipoFlor": "Tulipan", "color": "Amarillo", "precio": 14.07, "cantidad": 5, "imagen": "/images/tulipan.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Margarita Blanca", "tipoFlor": "Margarita", "color": "Blanco", "precio": 24.94, "cantidad": 10, "imagen": "/images/margarita.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Girasol Luminoso", "tipoFlor": "Girasol", "color": "Amarillo", "precio": 38.68, "cantidad": 0, "imagen": "/images/girasol.png", "esRamo": false,"ocasion": "Nacimiento", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Lirio Blanco", "tipoFlor": "Lirio", "color": "Blanco", "precio": 19.27, "cantidad": 20, "imagen": "/images/lirio.png", "esRamo": false,"ocasion": "Condolencias", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Peonia Rosa", "tipoFlor": "Peonia", "color": "Rosa", "precio": 14.2, "cantidad": 3, "imagen": "/images/peonia.png", "esRamo": false, "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Flor Silvestre", "tipoFlor": "Otro tipo", "color": "Multicolor", "precio": 17.66, "cantidad": 7, "imagen": "/images/floresvarias.webp", "esRamo": false,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Rosas", "tipoFlor": "Ramo", "color": "Rojo", "precio": 42.71, "cantidad": 0, "imagen": "/images/ramoRosas.jpg", "esRamo": true,"ocasion": "San Valentin", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo de Tulipanes", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 42.61, "cantidad": 12, "imagen": "/images/ramoTulipanes.jpg", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "sofia@example.com"}}'
curl -s -X POST "$BASE_URL/productos" -H "Content-Type: application/json" -d '{"nombre": "Ramo Silvestre", "tipoFlor": "Ramo", "color": "Multicolor", "precio": 28.15, "cantidad": 6, "imagen": "/images/floresvarias.webp", "esRamo": true,"ocasion": "Cumplea\u00f1os", "floricultor": {"email": "sofia@example.com"}}'


### PEDIDOS
curl -s -X POST "$BASE_URL/pedidos" -H "Content-Type: application/json" -d '{
  "cliente": {"email": "juan@example.com"},
  "floricultor": {"email": "luis@example.com"},
  "fecha": "2025-03-22T00:00:00",
  "estado": "COMPLETADO",
  "destino": "Calle 999",
  "lineasPedido": [
    {"producto": {"idProducto": 1}, "cantidad": 1, "precioUnitario": 20.0}
  ]
}'

curl -s -X POST "$BASE_URL/pedidos" -H "Content-Type: application/json" -d '{
  "cliente": {"email": "ana@example.com"},
  "floricultor": {"email": "marta@example.com"},
  "fecha": "2025-03-23T00:00:00",
  "estado": "COMPLETADO",
  "destino": "Calle 456",
  "lineasPedido": [
    {"producto": {"idProducto": 2}, "cantidad": 2, "precioUnitario": 35.0}
  ]
}'

curl -s -X POST "$BASE_URL/pedidos" -H "Content-Type: application/json" -d '{
  "cliente": {"email": "laura@example.com"},
  "floricultor": {"email": "carlos@example.com"},
  "fecha": "2025-03-25T00:00:00",
  "estado": "PENDIENTE",
  "destino": "Calle 111",
  "lineasPedido": [
    {"producto": {"idProducto": 3}, "cantidad": 1, "precioUnitario": 18.0}
  ]
}'

### VALORACIONES
curl -s -X POST "$BASE_URL/valoraciones" -H "Content-Type: application/json" -d '{
  "calificacionPedido": 5,
  "calificacionLogistica": 4,
  "comentario": "Todo perfecto. Repetire.",
  "fecha": "2025-03-30",
  "pedido": {"idPedido": 1}
}'

curl -s -X POST "$BASE_URL/valoraciones" -H "Content-Type: application/json" -d '{
  "calificacionPedido": 3,
  "calificacionLogistica": 3,
  "comentario": "Correcto pero mejorable.",
  "fecha": "2025-03-30",
  "pedido": {"idPedido": 2}
}'

echo "Base de datos completamente poblada con 40 productos, 4 floricultores, 4 clientes y distintos escenarios."
