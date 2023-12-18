// RMIServer.java
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create and export the remote object
            RemoteInterface remoteObj = new RemoteImplementation();

            // Create RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind the remote object to the registry
            Naming.bind("rmi://localhost/RemoteObject", remoteObj);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
