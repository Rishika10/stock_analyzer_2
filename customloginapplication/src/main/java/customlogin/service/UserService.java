package customlogin.service;

import customlogin.dto.UserDto;
import customlogin.model.User;

public interface UserService {
	
	User findByUsername(String username);
	User save (UserDto userDto);

}