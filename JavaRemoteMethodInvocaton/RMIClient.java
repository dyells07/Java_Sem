// RMIClient.java
import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object
            RemoteInterface remoteObj = (RemoteInterface) Naming.lookup("rmi://localhost/RemoteObject");

            // Invoke remote method
            String result = remoteObj.sayHello();

            // Display the result
            System.out.println("Message from server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
