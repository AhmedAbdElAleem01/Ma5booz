package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.dtos.*;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserRepoImpl implements UserRepo{
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

    @Override
    public int createUser(UserDTO user) throws SQLException {
        if (user == null) {
            System.err.println("Error creating user: User is null");
            return -1;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "INSERT INTO User (name, email, password, phoneNumber, creditLimit, birthDate, job, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getPhoneNumber());
                statement.setDouble(5, user.getCreditLimit());
                statement.setDate(6, ((user.getBirthDate()) != null) ? (new java.sql.Date(user.getBirthDate().getTime())) : null);
                statement.setString(7, user.getJob());
                statement.setString(8, user.getUsername());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create user");
                    return -1;
                } else {
                    System.out.println("user is created successfully");
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            int newUserId = resultSet.getInt(1); // getGeneratedKeys() -> "id" (wrong), 1 (right)
                            System.out.println("User created successfully with ID: " + newUserId);
                            return newUserId;
                        }
                    }
                    System.err.println("Failed to retrieve user ID");
                    return -1;
                }
            }
        }
    }

    @Override
    public boolean isFoundUsername(String name) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "SELECT id FROM User WHERE username = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, name);
                try(ResultSet resultSet = statement.executeQuery();) {
                    return resultSet.next();
                }
            }
        }
    }

    @Override
    public boolean isFoundEmail(String email) throws SQLException{
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "SELECT id FROM User WHERE email = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, email);
                try(ResultSet resultSet = statement.executeQuery();) {
                    return resultSet.next();
                }
            }
        }
    }

   public Optional<UserDTO> findByEmailAndPassword(String email, String pass) { 
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class
            );
            query.setParameter("email", email);
            query.setParameter("password", pass);
            UserDTO user = new UserDTO();
            user.setId(query.getSingleResult().getId());
            user.setName(query.getSingleResult().getName());
            user.setEmail(query.getSingleResult().getEmail());
            user.setPassword(query.getSingleResult().getPassword());
            user.setPhoneNumber(query.getSingleResult().getPhoneNumber());
            user.setCreditLimit(query.getSingleResult().getCreditLimit());
            user.setBirthDate(query.getSingleResult().getBirthDate());
            user.setJob(query.getSingleResult().getJob());
            user.setCreatedAt(query.getSingleResult().getCreatedAt());
            user.setUsername(query.getSingleResult().getUsername());
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close(); 
        }
    }    
    public Optional<UserDTO> findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            User userEntity = em.find(User.class, id);
            if (userEntity != null) {
                UserDTO user = new UserDTO();
                user.setId(userEntity.getId());
                user.setName(userEntity.getName());
                user.setUsername(userEntity.getUsername());
                user.setEmail(userEntity.getEmail());
                user.setPassword(userEntity.getPassword());
                user.setBirthDate(userEntity.getBirthDate());
                user.setCreditLimit(userEntity.getCreditLimit());
                user.setPhoneNumber(userEntity.getPhoneNumber());
                user.setJob(userEntity.getJob());
                return Optional.of(user);
            }
        } catch (Exception e) {
            System.out.println("Failed to find user: " + e.getMessage());
        } finally {
            em.close();
        }
        return Optional.empty();
    }
    
    public List<User> getAllUsers() throws SQLException {
        List<User> customers = new ArrayList<>();
        
        String query = "SELECT * FROM user";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Date bd = rs.getDate("birthDate") != null ? rs.getDate("birthDate") : null;
                User customer = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phoneNumber"),
                    rs.getDouble("creditLimit"),
                    bd,
                    rs.getString("job"),
                    rs.getTimestamp("createdAt").toLocalDateTime()
                );
                customers.add(customer);
            }
        }
        return customers;
    }
    
}
