package lk.ijse.fx.bo;


import lk.ijse.fx.dao.custom.UserDAO;
import lk.ijse.fx.dao.custom.impl.UserDAOImpl;
import lk.ijse.fx.dto.UserDTO;
import lk.ijse.fx.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements lk.ijse.fx.bo.UserBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.add(new User(user.getUser_name(),user.getPassword()));
    }

    @Override
    public boolean isExistUser(String user_name) throws SQLException, ClassNotFoundException {
        return userDAO.isExist(user_name);
    }

    @Override
    public boolean isValidUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.isValid(new User(userDTO.getUser_name(),userDTO.getPassword()));
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {

        List<User>  allUsers = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : allUsers){
            userDTOList.add(new UserDTO(user.getUser_name(),user.getPassword()));
        }
        return userDTOList;
    }
}
