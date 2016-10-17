package atpl.cc.tinkerlust.classes;

/**
 * Created by user9 on 7/10/16.
 */

public class Products {

    String product_id="";
    String sku="";
    String name="";
    String set="";
    String type="";
    String category_ids[];
    String website_ids[];


    public Products(String product_id, String sku, String name, String set, String type, String[] category_ids, String[] website_ids) {
        this.product_id = product_id;
        this.sku = sku;
        this.name = name;
        this.set = set;
        this.type = type;
        this.category_ids = category_ids;
        this.website_ids = website_ids;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(String[] category_ids) {
        this.category_ids = category_ids;
    }

    public String[] getWebsite_ids() {
        return website_ids;
    }

    public void setWebsite_ids(String[] website_ids) {
        this.website_ids = website_ids;
    }
}
