package db_interface;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class AmzDBUploader {
	static DynamoDB dynamoDB = new DynamoDB(Regions.US_WEST_2);
	
	public static void main(String args[]){
		loadSampleData("FIRST_ORDER_DATA");
		getSampleData("101", "FIRST_ORDER_DATA");
	}
	
    private static void loadSampleData(String tableName) {
        Table table = dynamoDB.getTable(tableName);

        try {

            System.out.println("Adding data to " + tableName);

            Item item = new Item()
                .withPrimaryKey("replay_id", "101")
                .withString("sc_version", "1.4.1")
                .withString("player1", "Bunny")
                .withString("player2", "Catz")
                .withString("map_hash", "Overgrowth")
                .withString("winner", "Bunny");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("replay_id", "102")
                .withString("sc_version", "1.4.1")
                .withString("player1", "Huk")
                .withString("player2", "Catz")
                .withString("map_hash", "Overgrowth")
                .withString("winner", "Catz");
            table.putItem(item);

            item = new Item()
                .withPrimaryKey("replay_id", "103")
                .withString("sc_version", "1.4.0")
                .withString("player1", "Bunny")
                .withString("player2", "Huk")
                .withString("map_hash", "Overgrowth")
                .withString("winner", "Bunny");
            table.putItem(item);
            
        } catch (Exception e) {
            System.err.println("Failed to create item in " + tableName);
            System.err.println(e.getMessage());
        }

    }
    
    private static void getSampleData(String string, String tableName) {

        Table table = dynamoDB.getTable(tableName);

        Item item = table.getItem("replay_id", // attribute name
                string, // attribute value
                "replay_id, sc_version, winner, map_hash", // projection expression
                null); // name map - don't need this

        System.out.println("GetItem: printing results...");
        System.out.println(item.toJSONPretty());

    }

}
