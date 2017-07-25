package app.user;

import app.util.*;
import spark.*;
import static app.Application.userDao;
import static app.util.JsonUtil.*;

public class UserController {

    public static Route fetchAllUsers = (Request request, Response response) -> {
        return dataToJson(userDao.getAllUsers());
    };
}
