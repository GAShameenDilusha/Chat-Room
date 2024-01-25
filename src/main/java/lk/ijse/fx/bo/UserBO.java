package lk.ijse.fx.bo;

import lk.ijse.fx.dto.UserDTO;
import lk.ijse.fx.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO {
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean isExistUser(String user_name) throws SQLException, ClassNotFoundException;
    boolean isValidUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
}
