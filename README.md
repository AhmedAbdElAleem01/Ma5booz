# 🎂 Ma5booz

**Ma5booz** is a playful, vibrant, and mobile-first bakery & pastry eCommerce platform designed for sweet lovers and powered by Java technologies. With a pink & purple aesthetic and deliciously interactive UI, it brings the bakery to your fingertips!

---

## 🍪 Tech Stack

- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Backend**: Java Servlets & JSP (No Spring)
- **Database**: MySQL on Railway
- **Asynchronous**: Vanilla JS for dynamic updates
- **ORM**: Custom/native ORM implementation
- **Build**: Single JAR package (no Maven)
- **Hosting**: (You can add this later if deployed)

---

## 🧁 Features

### 🌸 Customer Side
- Browse delightful products with a stylish card layout
- Add to cart, update quantity, or remove with ease
- Subscribe for monthly sweet deliveries
- Reorder past favorites from history
- Leave reviews for products
- Secure login/registration before checkout

### 🛠 Admin Side
- Add/Edit/Delete bakery & pastry items
- Manage customer reviews
- Track subscriptions & reorder history

---

## 🧁 Design Highlights
- **Color palette**: Pink & Purple candy-shop vibes
- **Typography**: Playful and clean fonts
- **UX**: Focused on joy & smooth browsing
- **Responsiveness**: Mobile-first layout for a sweet experience anywhere

---

## 🚀 Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/yourusername/ma5booz.git
   cd ma5booz
   ```

2. **Set up the database**
   - Import the SQL dump in `db/ma5booz.sql` to your MySQL instance (Railway or local)
   - Update DB credentials in `DBConnection.java`

3. **Build the JAR**
   ```bash
   javac -d build src/**/*.java
   jar -cvf ma5booz.jar -C build .
   ```

4. **Run**
   ```bash
   java -jar ma5booz.jar
   ```

---

## 💡 Future Work

- Payment gateway integration
- Email notifications for orders/subscriptions
- Admin analytics dashboard
- More themes (seasonal, minimalist, etc.)

---

## 🍰 Made with Love

Created as a graduation project by a passionate Java backend dev with a sweet tooth and a flair for playful UX. 💜✨

---

Wanna add your name, logo, or anything specific to it?
