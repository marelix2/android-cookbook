package cotam_kolego.cookbook.api;

/**
 * Created by Michał on 16.06.2017.
 * Klasa pobiera kod błędu. Wykorzystywana przez Retrofita do komunikacji podczas logowania
 */

public class ErrorResponse {

    int code;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    String error;

}
