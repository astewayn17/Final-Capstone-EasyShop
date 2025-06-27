<h1 align="center">🛍️ Welcome to the EZ Shop Ecommerce API Project!</h1>

EasyShop (EZ Shop) demonstrates enterprise-level backend development with secure JWT authentication, role-based access control, and full e-commerce functionality.
The API features a complete product catalog with advanced search and filtering capabilities, persistent shopping cart management that maintains state across sessions, user profile management for shipping details, and separate admin/customer roles. It follows industry best practices including the DAO pattern for data access, prepared statements for SQL injection prevention, and stateless REST architecture. EZ Shop provides JSON responses, integrates seamlessly with any frontend framework, and showcases proficiency in Spring Security, database design, and RESTful API development.

---

## 🚀 Features
- **User Authentication & Authorization**: JWT-based authentication with role-based access control
- **Category Management**: Browse and manage product categories
- **Product Search & Filtering**: Advanced search with multiple filter options
- **Shopping Cart**: Persistent shopping cart with full CRUD operations
- **User Profiles**: Manage user information and shipping details
- **Security**: Spring Security integration with JWT tokens

## 🛠️ Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Security with JWT**
- **MySQL Database**
- **JDBC for data access**
- **BCrypt for password encryption**

## 🐛 Bug Fixes Implemented
1. **`CategoriesController`**: Class to manage API requests for the categories
2. **Product Search Fix**: Corrected the price range filtering logic
3. **Product Update Fix**: Fixed duplicate product creation on update

## 🧪 Unit Tests
Unit Tests have been implemented in the following classes
- `MySqlCategoryDaoTest`
- `MySqlProductDaoTest`
- `MySqlProfileDaoTest`
- `MySqlShoppingCartDaoTest`

## 🧑‍💻 Default Users
- **Admin**: username: `admin`, password: `password`
- **User**: username: `user`, password: `password`

---

## 📚 API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/register` | Register new user | Public |
| POST | `/login` | Login user | Public |

### Categories
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/categories` | Get all categories | Public |
| GET | `/categories/{id}` | Get category by ID | Public |
| GET | `/categories/{id}/products` | Get products in category | Public |
| POST | `/categories` | Create category | Admin |
| PUT | `/categories/{id}` | Update category | Admin |
| DELETE | `/categories/{id}` | Delete category | Admin |

### Products
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/products` | Search products with filters | Public |
| GET | `/products/{id}` | Get product by ID | Public |
| POST | `/products` | Create product | Admin |
| PUT | `/products/{id}` | Update product | Admin |
| DELETE | `/products/{id}` | Delete product | Admin |

### Shopping Cart
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/cart` | Get user's cart | Authenticated |
| POST | `/cart/products/{id}` | Add product to cart | Authenticated |
| PUT | `/cart/products/{id}` | Update item quantity | Authenticated |
| DELETE | `/cart` | Clear cart | Authenticated |

### User Profile
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/profile` | Get user profile | Authenticated |
| PUT | `/profile` | Update user profile | Authenticated |

---

## 📸 Example Screenshots

<table>
  <tr>
    <td align="center" width="500">
      <img src="https://github.com/astewayn17/Bread-n-Bytes/blob/main/Screenshots/add_sandwich.png" width="500"/><br/>
      <sub><i>Home Screen</i></sub>
    </td>
    <td align="center" width="500">
      <img src="https://github.com/astewayn17/Bread-n-Bytes/blob/main/Screenshots/bread_type.png" width="500"/><br/>
      <sub><i>Logging In</i></sub>
    </td>
  </tr>
</table>

<table>
  <tr>
    <td align="center" width="500">
      <img src="https://github.com/astewayn17/Bread-n-Bytes/blob/main/Screenshots/add_sandwich.png" width="500"/><br/>
      <sub><i>Shopping Cart</i></sub>
    </td>
    <td align="center" width="500">
      <img src="https://github.com/astewayn17/Bread-n-Bytes/blob/main/Screenshots/bread_type.png" width="500"/><br/>
      <sub><i>User Profile</i></sub>
    </td>
  </tr>
</table>

---

## 💡 Interesting Code Highlights

The `mapRow` method in the `MySqlProfileDao` class demonstrates an elegant implementation of method mapping to save repetitive use of the same code. It is used when resultset mapping is needed in the other methods in the class:

```java
private Profile mapRow(ResultSet row) throws SQLException {
        Profile profile = new Profile();
        profile.setUserId(row.getInt("user_id"));
        profile.setFirstName(row.getString("first_name"));
        profile.setLastName(row.getString("last_name"));
        profile.setPhone(row.getString("phone"));
        profile.setEmail(row.getString("email"));
        profile.setAddress(row.getString("address"));
        profile.setCity(row.getString("city"));
        profile.setState(row.getString("state"));
        profile.setZip(row.getString("zip"));
        return profile;
    }
```

The `search` method in the `products-services.js` javascript file shows a clever approach to mapping images to specific products. It uses the product's name to map it to the corresponding image in the if statement. It also uses the axios as the API call with the const url above it:

```javascript
search()
    {
        const url = `${config.baseUrl}/products${this.filter.queryString()}`;
        axios.get(url)
             .then(response => {
                 let data = {};
                 data.products = response.data;
                 data.products.forEach(product => {
                     if (!this.hasPhoto(product.imageUrl)) {
                         if (product.name && product.name.includes("Smart Home Hub")) { product.imageUrl = "smart-home.png"; }
                         if (product.name && product.name.includes("Portable Charger")) { product.imageUrl = "portable-charger.png"; }
                         if (product.name && product.name.includes("Men's Swim Trunks")) { product.imageUrl = "mens-swim-trunks.png"; }
                         if (product.name && product.name.includes("Men's Casual Shirt")) { product.imageUrl = "mens-casual-shirt.png"; }
                         if (product.name && product.name.includes("Women's Jumpsuit")) { product.imageUrl = "womens-jumpsuit.png"; }
                         if (product.name && product.name.includes("Women's Swimwear")) { product.imageUrl = "womens-swimwear.png"; }
                         if (product.name && product.name.includes("Air Fryer")) { product.imageUrl = "air-fryer.png"; }
                         if (product.name && product.name.includes("Cookies")) { product.imageUrl = "cookies.png"; }
                         if (product.name && product.name.includes("Grandma Cookies")) { product.imageUrl = "grandma-cookies.png"; }
                     }
                 });
                 templateBuilder.build('product', data, 'content', this.enableButtons);
             })
            .catch(error => {
                const data = {
                    error: "Searching products failed."
                };
                templateBuilder.append("error", data, "errors")
            });
    }
```

---

## 📄 Credits
Claude model Opus 4 for assistance with front end customizations relating to the dark blue color theme.
