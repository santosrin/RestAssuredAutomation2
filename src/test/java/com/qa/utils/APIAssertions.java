package com.qa.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class APIAssertions {
    Response response;
    private static Logger logger = LogManager.getLogger(APIAssertions.class);
    public APIAssertions(Response response) {
        this.response = response;
    }

    public static void assertGetCall(Response response) {
        Assert.assertEquals(response.jsonPath().getString("page"),"2");
        Assert.assertEquals(response.jsonPath().getString("per_page"),"6");
        List<Map<String,Object>> data = response.jsonPath().get("data");
        Assert.assertEquals(data.get(0).get("id").toString(),"7");
        Assert.assertEquals(data.get(0).get("email").toString(),"michael.lawson@reqres.in");
        logger.info("GET call assertion completed successfully");
    }

    public static void assertPostCall(Response response) {
        Assert.assertEquals(response.jsonPath().getString("name"),"SanMan");
        Assert.assertEquals(response.jsonPath().getString("job"),"QA");
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
        logger.info("POST call assertion completed successfully");
    }

    public static void assertPutCall(Response response) {
        Assert.assertEquals(response.jsonPath().getString("name"),"morpheus");
        Assert.assertEquals(response.jsonPath().getString("job"),"zion resident");
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"));
        logger.info("PUT call assertion completed successfully");
    }

    public static void assertBigGetAPICall(Response response) {
        Map<String,Object> order_info = response.jsonPath().getMap("order_info");
        Assert.assertEquals(order_info.get("order_number").toString(),"10001112");

        List<Map<String,Object>> order_items = (List<Map<String, Object>>) order_info.get("order_items");
        Assert.assertEquals(order_items.get(0).get("sku").toString(),"32861");
        Assert.assertEquals(order_items.get(0).get("name").toString(),"Erika Running Short-32-Purple");
        Assert.assertEquals(order_items.get(0).get("discount_amount").toString(),"9.0");
        Assert.assertEquals(order_items.get(0).get("fulfillment_status").toString(),"SHIPPED");

        Map<String,Object> attributes = (Map<String, Object>) order_items.get(0).get("attributes");
        Assert.assertEquals(attributes.get("color"),"Green");
        Assert.assertEquals(attributes.get("size"),"XL");

        Map<String,Object> dimensions = (Map<String, Object>) order_items.get(0).get("dimensions");
        Assert.assertEquals(dimensions.get("uom").toString(),"in");

        Assert.assertEquals(order_items.get(1).get("item_id").toString(),"182");
        Assert.assertEquals(order_items.get(1).get("sku").toString(),"32862");
        Assert.assertEquals(order_items.get(1).get("discount_percent").toString(),"20.0");

        List<Map<String,Object>> shipments = (List<Map<String, Object>>) order_info.get("shipments");
        Assert.assertEquals(shipments.get(0).get("ship_method").toString(),"Best Way - Table Rate");
        Assert.assertEquals(shipments.get(0).get("carrier_service").toString(),"Fedex 24");
        List<Map<String ,Object>> itemsInfo = (List<Map<String, Object>>) shipments.get(0).get("items_info");
        Assert.assertEquals(itemsInfo.get(0).get("quantity").toString(),"3");
        Assert.assertEquals(itemsInfo.get(1).get("sku").toString(),"32862");

        Map<String,Object> shippedTo = (Map<String, Object>) shipments.get(0).get("shipped_to");
        Assert.assertEquals(shippedTo.get("first_name").toString(),"Veronica");
        Assert.assertEquals(shippedTo.get("email").toString(),"roni_cost@example.com");

        Map<String ,Object> address = (Map<String, Object>) shippedTo.get("address");
        Assert.assertEquals(address.get("street_1"),"6146 Honey, Bluff");
        Assert.assertEquals(address.get("country"),"US");

        Map<String,Object> shippedFrom = (Map<String, Object>) shipments.get(0).get("shipped_from");
        Assert.assertEquals(shippedFrom.get("phone").toString(),"09999999999");

        Map<String,Object> shippedFromAddress = (Map<String, Object>) shippedFrom.get("address");
        Assert.assertEquals(shippedFromAddress.get("city").toString(),"Bangalore");

        Map<String,Object> billing = (Map<String, Object>) order_info.get("billing");
        Assert.assertEquals(billing.get("amount").toString(),"311.76");

        Map<String,Object> billedTo = (Map<String, Object>) billing.get("billed_to");
        Assert.assertEquals(billedTo.get("last_name").toString(),"Costello");

        Map<String,Object> billAddress = (Map<String, Object>) billedTo.get("address");
        Assert.assertEquals(billAddress.get("state").toString(),"Michigan");

        Map<String,Object> customer = (Map<String, Object>) order_info.get("customer");
        Assert.assertEquals(customer.get("phone").toString(),"(555) 229-3326");

        Map<String,Object> custAddress = (Map<String, Object>) customer.get("address");
        Assert.assertEquals(custAddress.get("zip").toString(),"49628-7978");

        Assert.assertEquals(order_info.get("currency_code"),"NZD");

        Map<String,Object> oiAttr = (Map<String, Object>) order_info.get("attributes");
        Assert.assertEquals(oiAttr.get("checkout_country"),"US");

        List<Map<String,Object>> promotions = (List<Map<String, Object>>) order_info.get("promotions");
        Assert.assertEquals(promotions.get(0).get("value"),"NEWYEARSALE");

        Assert.assertEquals(response.jsonPath().getString("status"),"SUCCESS");

        logger.info("Assertions is complete");
    }
}
