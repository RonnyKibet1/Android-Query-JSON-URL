package teamappcreative.com.androidqueryjson.model;

/**
 * Created by ronnykibet on 11/11/17.
 */

public class Product {

    private String productImage;
    private String productTitle;
    private String productDescription;

    public Product() {
    }

    public Product(String productImage, String productTitle, String productDescription) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
