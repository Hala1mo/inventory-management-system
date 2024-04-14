 # Inventory Management System
This project is an Inventory Management System that helps businesses track and manage their inventory, orders, suppliers, and customers.
## Resources
### Products
- Attributes: ID, name, description, quantity, price
- Relationships: 
  - Belongs to Supplier
  - Stored in Warehouse
  - Included in Orders

### Suppliers
- Attributes: ID, name, phone number
- Relationships:
  - Supplies Products
  - 
### Warehouse
- Attributes: ID, Name, Address, Capacity
- Relationships:
  - Stores Products

### Orders
- Attributes: ID, date, status, total cost
- Relationships:
  - Contains Products

## ERD Diagram
![image](https://github.com/Hala1mo/inventory-management-system/assets/121310040/6c34cdda-960e-459f-a0ac-ae7708a9cf30)
### /Product
![image](https://github.com/Hala1mo/inventory-management-system/assets/121310040/39a2cfb1-542a-442b-aa97-d7ce96c81191)
### /Supplier
![image](https://github.com/Hala1mo/inventory-management-system/assets/121310040/bc12a60f-7476-48c3-b678-8dd7037afb85)
### /WareHouse
![image](https://github.com/Hala1mo/inventory-management-system/assets/121310040/55bf4278-6131-4340-bee7-1aff1982f9c1)
### /Order
![image](https://github.com/Hala1mo/inventory-management-system/assets/121310040/e253933c-5126-4234-a9e9-f343dd3a9691)


## How to Run the Application
- Clone this repository to your local machine.
- Install the required dependencies (e.g., database, web server).
- Configure the application settings (e.g., database connection).
- Run the application.

## OpenAPI Specification
https://app.swaggerhub.com/apis/HALAMONTASER13_1/inventory_management_system/
