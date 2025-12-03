package mongoJava;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class DB {

    private static MongoClient client = null;

    public static MongoDatabase getDatabase() {

        if (client == null) {
            client = new MongoClient("localhost", 27017);
            System.out.println("Connected successfully!");
        }

        return client.getDatabase("LibManage");
    }
}

	


