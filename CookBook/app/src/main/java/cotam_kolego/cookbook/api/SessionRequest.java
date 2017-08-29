package cotam_kolego.cookbook.api;

/**
 * Created by Michał on 24.06.2017.
 * Zapytanie wykorzystywane do zapisania aktualnie wybranego przepisu dla danego użytkownika.
 * Wykorzystywane w SessionManagerze
 */

public class SessionRequest {

    private String recipeId;
    private String imageUrl;
    private String userId;
    private String dishName;
    private String Description;


    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
