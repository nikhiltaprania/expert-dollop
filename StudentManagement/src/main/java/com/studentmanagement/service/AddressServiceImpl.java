package com.studentmanagement.service;

import com.studentmanagement.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final Connection connection;

    public AddressServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAddress(Address address) {
        String query = "INSERT INTO Address (address_id, city, state, pin_code) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, address.getAddress_id());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getState());
            statement.setInt(4, address.getPinCode());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateAddress(Address address) {
        String query = "UPDATE Address SET city=?, state=?, pin_code=? WHERE address_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setString(1, address.getCity());
            statement.setString(2, address.getState());
            statement.setInt(3, address.getPinCode());
            statement.setInt(4, address.getAddress_id());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deleteAddress(int addressId) {
        String query = "DELETE FROM Address WHERE address_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, addressId);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Address getAddressById(int addressId) {
        String query = "SELECT * FROM Address WHERE address_id=?";
        Address address = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, addressId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                address = mapResultSetToAddress(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public List<Address> getAllAddresses() {
        String query = "SELECT * FROM Address";
        List<Address> addresses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Address address = mapResultSetToAddress(resultSet);
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    private Address mapResultSetToAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setAddress_id(resultSet.getInt("address_id"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        address.setPinCode(resultSet.getInt("pinCode"));
        return address;
    }
}
