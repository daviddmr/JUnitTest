package builders;

import entities.User;

public class UserBuilder {

    private User user;

    private UserBuilder() {
    }

    public static UserBuilder getUser() {
        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        builder.user.setName("User 1");

        return builder;
    }

    public User build() {
        return user;
    }

}
