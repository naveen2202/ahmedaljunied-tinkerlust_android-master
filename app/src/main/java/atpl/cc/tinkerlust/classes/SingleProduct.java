package atpl.cc.tinkerlust.classes;

/**
 * Created by user9 on 17/10/16.
 */

public class SingleProduct {
    String price="";
    String status="";
    String visibility="";
    String url_key="";
    String url_path="";
    String weight="";
    String options_container;
    String hasOptions="";
    String tax_class="";
    String created_at="";
    String updated_at="";
    String description="";
    String short_description="";

    public SingleProduct(String price, String status, String visibility, String url_key, String url_path, String weight, String options_container, String hasOptions, String tax_class, String created_at, String updated_at, String description,String short_description)
    {
        this.price = price;
        this.status = status;
        this.visibility = visibility;
        this.url_key = url_key;
        this.url_path = url_path;
        this.weight = weight;
        this.options_container = options_container;
        this.hasOptions = hasOptions;
        this.tax_class = tax_class;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.description = description;
        this.short_description = short_description;
    }

    public String getPrice() {
        return price;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getUrl_key() {
        return url_key;
    }

    public void setUrl_key(String url_key) {
        this.url_key = url_key;
    }

    public String getUrl_path() {
        return url_path;
    }

    public void setUrl_path(String url_path) {
        this.url_path = url_path;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOptions_container() {
        return options_container;
    }

    public void setOptions_container(String options_container) {
        this.options_container = options_container;
    }

    public String getHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(String hasOptions) {
        this.hasOptions = hasOptions;
    }

    public String getTax_class() {
        return tax_class;
    }

    public void setTax_class(String tax_class) {
        this.tax_class = tax_class;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
