 # Inventory Management System
This project is an Inventory Management System that helps businesses track and manage their inventory, orders, suppliers, and customers. It provides endpoints for adding, updating, and deleting items, as well as querying inventory levels,managing supplier information, and processing orders.
## Resources

### Products
- Description: Represents a product available in the inventory.
- Attributes: ID, name, description, quantity, price
- Relationships: 
  - Belongs to Supplier
  - Stored in Warehouse
  - Included in Orders

### Suppliers
- Description: Represents a supplier of products
- Attributes: ID, name, phone number, list of products supplied
- Relationships:
  - Supplies Products
    
### Warehouse
- Description: Represents a location where products are stored
- Attributes: ID, name, address, capacity, list of products stored
- Relationships:
  - Stores Products

### Orders
- Description:  Represents a transaction for purchasing or selling products
- Attributes: ID, date, status (e.g., pending, shipped, delivered), total cost, list of products ordered
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
- Clone this repository in the local machine.
- Ensure Java and Maven are installed.
- Configure the application by updating the application.properties or application.yml file with your database connection details and other settings..
- Run the application in a web browser at http://localhost:8080 or using postman.

## OpenAPI Specification
https://app.swaggerhub.com/apis/HALAMONTASER13_1/inventory_management_system/
