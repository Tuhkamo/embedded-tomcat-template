package model;

public class ShoppingListItem {

    private long id;
    private String title;

    /**
     * Empty constructor only used by Gson when converting JSON Strings to Java
     * objects. Set to private to prevent creating uninitialized objects.
     */
    @SuppressWarnings("unused")
    private ShoppingListItem() {
    }

    public ShoppingListItem(long id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public ShoppingListItem(String title) {
    	this.title = title;
    }
    
    public ShoppingListItem(long id) {
    	this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ShoppingListItem && ((ShoppingListItem) other).id == this.id;
    }
}