class UserDTO {
    constructor(id, firstName, lastName, username, age, email, password, image, roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
        this.image = image;
        this.roles = roles;
        this.createdActivities = [];
        this.invitedActivities = [];
        this.friends = [];
        this.notifications = [];
        this.comments = [];
    }
}