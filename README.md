updated branch "main"

# Running the Application with Docker

- docker compose build
- docker compose up

# Gateway api route

- orders : http://localhost:3000/api/order/orders
  - POST "/" persist multiple productsIds as list of ids [1,2,3...]
  - GET "/" get all orders
  - GET "/${id}" get one order
- products : http://localhost:3000/api/product/products
  - GET "/" get all products
  - POST "/" persist one product
  - GET "/${id}" fetch one product based on id
  - DELETE "/${id}" delete one product
