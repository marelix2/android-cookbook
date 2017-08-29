package cotam_kolego.cookbook.api;

/**
 * Created by Michał on 16.06.2017.
 * Wykorzystywane przy logowaniu i rejestracji
 */

public class UserResponse {



    private String objectId;
    private String sessionToken;
    private String username;
    private String email;
    private String firstName;
    private String lastName;


    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getSessionToken() {
        return sessionToken;
    }
    public String getObjectId() {
        return objectId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "objectId='" + objectId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }


    //{"objectId":"G4xu1eh8j5","username":"michal@wp.pl","email":"michal@wp.pl","createdAt":"2017-06-16T18:31:37.972Z","updatedAt":"2017-06-16T18:52:26.547Z","emailVerified":true,"ACL":{"*":{"read":true},"G4xu1eh8j5":{"read":true,"write":true}},"sessionToken":"r:094596e78fad2de1fb9d65979397173b"}

}


