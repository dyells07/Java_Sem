// RemoteImplementation.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
    protected RemoteImplementation() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, from the server!";
    }
}
