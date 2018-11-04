package com.springBlog.repositories;

import java.util.List;

import com.springBlog.entities.Role;
import com.springBlog.entities.User;

public interface UserRepositoryCustom {

	User create(User user, List<Role> roleList);
}
