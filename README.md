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
- **Deployment**: Dockerized app for easy setup and scaling

---

## 🧁 Features

- Browse delightful products with a stylish card layout
- Add to cart, update quantity, or remove with ease
- Secure login/registration before checkout
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

3.🐳 Docker Deployment
```bash
   docker build -t ma5booz-app .
   docker run -p 8080:8080 --env-file .env ma5booz-app
```
---

## 👨‍🍳 Development Team

- [Ahmed AbdElAleem](https://github.com/AhmedAbdElAleem01)
- [Alaa Hathout](https://github.com/AlaaHathout)
- [Salma ElKhatib](https://github.com/Salmaelkatib)

---

## 🍰 Made with Love

Created as a graduation project by a group of passionate ITI trainees with a sweet tooth and a flair for playful UX. 💜✨
