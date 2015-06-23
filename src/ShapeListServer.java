import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

public class ShapeListServer
{
    public static void main(String args[])
    {
        try{
            // create and initialize the ORB
            Properties props = new Properties();
        	props.put("org.omg.CORBA.ORBInitialPort", "1500");
        	ORB orb = ORB.init(args, props);
            
            // get reference to rootpoa & activate the POAManager
      		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
     		 rootpoa.the_POAManager().activate();
              // create servant - note that we pass it a reference to the POA
            ShapeListServant SLSRef = new ShapeListServant(rootpoa);
                    
          // register the servant with the POA and get object reference for the servant
          // narrow the object reference
      		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(SLSRef);
           	ShapeList SLRef = ShapeListHelper.narrow(ref);
   
            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
   
            // bind the Object Reference in Naming
            NameComponent nc = new NameComponent("ShapeList", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, SLRef);
            System.out.println("ShapeList Server ready and waiting...");
            // wait for invocations from clients
            orb.run();
     	} catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("ShapeList Server Exiting...");
    }
}



