# 🌸 flor.es — Plataforma de venta y entrega de flores a domicilio

flor.es es una aplicación web que conecta a floricultores locales con clientes particulares para ofrecer flores frescas y ramos personalizados con entrega rápida y sostenible.

---

## 🚀 Funcionalidades principales

- 🔍 Catálogo con filtros por tipo de flor, color, precio y disponibilidad
- 💐 Compra de ramos predefinidos o creación personalizada
- 🛒 Carrito y gestión de pedidos en tiempo real
- 🌍 Entregas urgentes o programadas según localización
- 🧑‍🌾 Gestión de inventario por parte de los floricultores
- ⭐ Valoraciones de pedidos y logística
- 🧾 Vista de floricultores con productos y puntuación media

---

## ⚙️ Tecnologías utilizadas

- **Frontend:** Spring MVC + Thymeleaf
- **Backend:** Java + Spring Boot
- **Base de datos:** H2 (en memoria)
- **Consumo REST:** RestTemplate
- **Testing:** cURL scripts para pruebas de poblamiento
- **Otros:** Bootstrap, HTML5, CSS3

---

## 🧪 Cómo probar la aplicación

### 1. Clonar el repositorio

```bash
git clone https://github.com/aaleexxlex/flor.es
cd flor.es
```

### 2. Iniciar el backend

```bash
cd florapi
./mvnw spring-boot:run
```

### 3. Iniciar el frontend

```bash
cd ../florcliente
./mvnw spring-boot:run
```

### 4. Poblar la base de datos en florapi/src/main/resources/requests (opcional)

```bash

bash
chmod +x poblar.sh
./poblar.sh
```
### 5. Configurar la API de Google Maps

La aplicación utiliza la API de Google Maps para mostrar mapas, autocompletar direcciones y convertir direcciones en coordenadas geográficas. Es necesario configurar una clave API de Google para que estas funcionalidades funcionen correctamente.

#### APIs necesarias

Debes activar las siguientes APIs en tu cuenta de Google Cloud:

- Maps JavaScript API
- Places API
- Geocoding API

#### Pasos para obtener la clave API

1. Accede a https://console.cloud.google.com/
2. Crea un nuevo proyecto o selecciona uno existente.
3. Ve a "API y servicios" > "Biblioteca" y activa las siguientes:
   - Maps JavaScript API
   - Places API
   - Geocoding API
4. Ve a "API y servicios" > "Credenciales".
5. Haz clic en "Crear credencial" > "Clave de API".
6. Copia la clave generada.

Opcionalmente puedes restringir la clave para que solo funcione desde `localhost` o desde un dominio específico.

#### Dónde colocar la clave API

Reemplaza el texto `API_KEY` por tu clave real en los siguientes archivos:

- `florcliente/src/main/resources/templates/fragments/script.html`
- `florcliente/src/main/resources/templates/registro.html`

Busca la siguiente línea:

```bash
<script src="https://maps.googleapis.com/maps/api/js?key=API_KEY&libraries=places"></script>
```
### 6. Abrir en el navegador

```bash
http://localhost:8083/home
```

---

## 🔑 Usuarios de prueba

- Clientes: `juan@example.com`, `ana@example.com`, `laura@example.com`, `pedro@example.com`
- Floricultores: `marta@example.com`, `luis@example.com`, `carlos@example.com`, `sofia@example.com`

---

## 📦 Estructura del proyecto

```
/florapi
/florliente
README.md
```
---

## 📄 Licencia

Este proyecto es de uso académico y no se distribuye comercialmente.
